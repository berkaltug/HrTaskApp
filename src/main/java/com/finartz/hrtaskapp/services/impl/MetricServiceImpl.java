package com.finartz.hrtaskapp.services.impl;

import com.finartz.hrtaskapp.db.repository.MetricRepository;
import com.finartz.hrtaskapp.model.entity.FailReason;
import com.finartz.hrtaskapp.model.entity.Metric;
import com.finartz.hrtaskapp.services.FailReasonService;
import com.finartz.hrtaskapp.services.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetricServiceImpl implements MetricService {

    private MetricRepository metricRepository;
    private FailReasonService failReasonService;


    @Autowired
    public MetricServiceImpl(MetricRepository metricRepository, FailReasonService failReasonService) {
        this.metricRepository = metricRepository;
        this.failReasonService = failReasonService;
    }

    @Override
    public Optional<Page<Metric>> getAllMetrics(int page) {
        Pageable pageable= PageRequest.of(page,5, Sort.by("metricId"));
        return Optional.ofNullable(metricRepository.findAll(pageable));
    }

    @Override
    public Optional<Metric> getMetric(Integer id) {
        return metricRepository.findById(id);
    }

    @Override
    public Optional<Metric> deleteMetric(Integer id) {
        Optional<Metric> optional=metricRepository.findById(id);
        optional.ifPresent(opt->metricRepository.delete(opt));

        return optional;
    }

    @Override
    public Optional<Metric> updateMetric(Metric metric) {
        return Optional.of(metricRepository.save(metric));
    }

    @Override
    public Optional<Metric> addMetric(Metric metric) {
        if(metric.getRealDeadline().after(metric.getExpectedDeadline())){
            failReasonService.addFailReason(new FailReason(metric,"fail açıklaması girilmedi"));
        }
        return Optional.of(metricRepository.save(metric));
    }
}
