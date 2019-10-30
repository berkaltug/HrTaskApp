package com.finartz.hrtaskapp.services.impl;

import com.finartz.hrtaskapp.db.repository.FailReasonRepository;
import com.finartz.hrtaskapp.db.repository.MetricRepository;
import com.finartz.hrtaskapp.model.entity.FailReason;
import com.finartz.hrtaskapp.services.FailReasonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FailReasonServiceImpl implements FailReasonService {

    private FailReasonRepository failReasonRepository;

    public FailReasonServiceImpl(FailReasonRepository failReasonRepository) {
        this.failReasonRepository = failReasonRepository;
    }

    @Override
    public Page<FailReason> getAllFails(int page) {
        Pageable pageable= PageRequest.of(page,5, Sort.by("failId"));
        return failReasonRepository.findAll(pageable);
    }

    @Override
    public Optional<FailReason> getFail(Integer id) {
        return failReasonRepository.findById(id);
    }

    @Override
    public Optional<FailReason> deleteFailReason(Integer id) {
        Optional<FailReason> optional=failReasonRepository.findById(id);
        optional.ifPresent(opt->failReasonRepository.delete(opt));
        return optional;
    }

    @Override
    public Optional<FailReason> addFailReason(FailReason failReason) {
        return Optional.of(failReasonRepository.save(failReason));
    }

    @Override
    public Optional<FailReason> updateFailReason(FailReason failReason) {
        return Optional.of(failReasonRepository.save(failReason));
    }
}
