package com.it_bunchhean.studentmanagement.security.rate_limit;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimitService rateLimitService;

    public RateLimitInterceptor(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        String key = resolveKey(request);
        Bucket bucket = rateLimitService.resolveBuckets(key);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            response.addHeader("X-RateLimit-Remaining",
                    String.valueOf(probe.getRemainingTokens()));
            return true;
        }

        long retryAfterSec = TimeUnit.NANOSECONDS.toSeconds(probe.getNanosToWaitForRefill() + 1);
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.addHeader("Retry-After", String.valueOf(retryAfterSec));
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"Too many requests\"}");

        return false;
    }

    private String resolveKey(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-KEY");
        return apiKey != null ? apiKey : request.getRemoteAddr();
    }

}