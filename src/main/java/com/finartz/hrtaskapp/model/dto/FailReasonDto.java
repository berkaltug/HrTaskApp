package com.finartz.hrtaskapp.model.dto;

import com.finartz.hrtaskapp.model.entity.FailReason;

public class FailReasonDto {

    private Integer failId;
    private MetricDto metricDto;
    private String description;

    public FailReasonDto(Integer failId, MetricDto metricDto, String description) {
        this.failId = failId;
        this.metricDto = metricDto;
        this.description = description;
    }

    public Integer getFailId() {
        return failId;
    }

    public void setFailId(Integer failId) {
        this.failId = failId;
    }

    public MetricDto getMetricDto() {
        return metricDto;
    }

    public void setMetricDto(MetricDto metricDto) {
        this.metricDto = metricDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}