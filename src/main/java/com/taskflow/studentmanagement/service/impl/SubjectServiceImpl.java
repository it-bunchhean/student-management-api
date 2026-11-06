package com.taskflow.studentmanagement.service.impl;

import com.taskflow.studentmanagement.entity.Subject;
import com.taskflow.studentmanagement.exception.ResourceNotFoundException;
import com.taskflow.studentmanagement.dto.request.SubjectRequest;
import com.taskflow.studentmanagement.dto.response.SubjectResponse;
import com.taskflow.studentmanagement.mapper.SubjectMapper;
import com.taskflow.studentmanagement.repository.SubjectRepository;
import com.taskflow.studentmanagement.service.SubjectService;
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
