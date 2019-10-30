package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.FailReason;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface FailReasonService {
    Optional<FailReason> getAllFails();
    Optional<FailReason> getFail(Integer id);
    Optional<FailReason> deleteFailReason(Integer id);
    Optional<FailReason> addFailReason(FailReason failReason);
    Optional<FailReason> updateFailReason (FailReason failReason);
}
