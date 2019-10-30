package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.Metric;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MetricService {

    Optional<Page<Metric>> getAllMetrics(int page);
    Optional<Metric> getMetric(Integer id);
    Optional<Metric> deleteMetric(Integer id);
    Optional<Metric> updateMetric(Metric metric);
    Optional<Metric> addMetric(Metric metric);
}
