package com.it_bunchhean.studentmanagement.service.impl;

import com.it_bunchhean.studentmanagement.entity.Subject;
import com.it_bunchhean.studentmanagement.exception.ResourceNotFoundException;
import com.it_bunchhean.studentmanagement.dto.request.SubjectRequest;
import com.it_bunchhean.studentmanagement.dto.response.SubjectResponse;
import com.it_bunchhean.studentmanagement.mapper.SubjectMapper;
import com.it_bunchhean.studentmanagement.repository.SubjectRepository;
import com.it_bunchhean.studentmanagement.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectResponse create(SubjectRequest request) {
        Subject subject = subjectMapper.toEntity(request);
        return subjectMapper.toResponse(subjectRepository.save(subject));
    }

    @Override
    public SubjectResponse getById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
        return subjectMapper.toResponse(subject);
    }
}
