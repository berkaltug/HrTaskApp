package com.finartz.hrtaskapp.db.repository;

import com.finartz.hrtaskapp.model.entity.Metric;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MetricRepository extends PagingAndSortingRepository<Metric,Integer> {
}
