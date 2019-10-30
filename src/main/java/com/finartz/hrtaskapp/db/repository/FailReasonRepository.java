package com.finartz.hrtaskapp.db.repository;

import com.finartz.hrtaskapp.model.entity.FailReason;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FailReasonRepository extends PagingAndSortingRepository<FailReason,Integer> {

}
