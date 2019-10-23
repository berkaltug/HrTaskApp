package com.finartz.hrtaskapp.db.repository;

import com.finartz.hrtaskapp.model.entity.Process;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends PagingAndSortingRepository<Process,Integer> {
}
