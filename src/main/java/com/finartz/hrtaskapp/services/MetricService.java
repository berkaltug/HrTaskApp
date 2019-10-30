package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.Metric;

import java.util.Optional;

public interface MetricService {

    Optional<Metric> getAllMetrics();
    Optional<Metric> getMetric(Integer id);
    Optional<Metric> deleteMetric(Integer id);
    Optional<Metric> updateMetric(Metric metric);
    Optional<Metric> addMetric(Metric metric);
}
