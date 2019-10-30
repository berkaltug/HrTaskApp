package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.FailReason;
import org.springframework.data.domain.Page;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface FailReasonService {
    Page<FailReason> getAllFails(int page);
    Optional<FailReason> getFail(Integer id);
    Optional<FailReason> deleteFailReason(Integer id);
    Optional<FailReason> addFailReason(FailReason failReason);
    Optional<FailReason> updateFailReason (FailReason failReason);
}
