package com.finartz.hrtaskapp.model.dto;

import java.util.Objects;

public class FailReasonDto {

    private Integer failId;
    private MetricDto metric;
    private String description;

    public FailReasonDto() {
    }

    public FailReasonDto(Integer failId, MetricDto metric, String description) {
        this.failId = failId;
        this.metric = metric;
        this.description = description;
    }

    public Integer getFailId() {
        return failId;
    }

    public void setFailId(Integer failId) {
        this.failId = failId;
    }

    public MetricDto getMetric() {
        return metric;
    }

    public void setMetric(MetricDto metric) {
        this.metric = metric;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FailReasonDto)) return false;
        FailReasonDto that = (FailReasonDto) o;
        return Objects.equals(failId, that.failId) &&
                Objects.equals(metric, that.metric) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(failId, metric, description);
    }

    @Override
    public String toString() {
        return "FailReasonDto{" +
                "failId=" + failId +
                ", metric=" + metric +
                ", description='" + description + '\'' +
                '}';
    }
}