package com.finartz.hrtaskapp.model.dto;

import com.finartz.hrtaskapp.model.entity.Task;

import java.util.Date;
import java.util.Objects;

public class MetricDto {

    private Integer metricId;
    private String taskTitle;
    private Date expectedDeadline;
    private Date realDeadline;

    public MetricDto() {
    }

    public MetricDto(Integer metricId, String taskTitle, Date expectedDeadline, Date realDeadline) {
        this.metricId = metricId;
        this.taskTitle = taskTitle;
        this.expectedDeadline = expectedDeadline;
        this.realDeadline = realDeadline;
    }

    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getExpectedDeadline() {
        return expectedDeadline;
    }

    public void setExpectedDeadline(Date expectedDeadline) {
        this.expectedDeadline = expectedDeadline;
    }

    public Date getRealDeadline() {
        return realDeadline;
    }

    public void setRealDeadline(Date realDeadline) {
        this.realDeadline = realDeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetricDto)) return false;
        MetricDto metricDto = (MetricDto) o;
        return Objects.equals(metricId, metricDto.metricId) &&
                Objects.equals(taskTitle, metricDto.taskTitle) &&
                Objects.equals(expectedDeadline, metricDto.expectedDeadline) &&
                Objects.equals(realDeadline, metricDto.realDeadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricId, taskTitle, expectedDeadline, realDeadline);
    }

    @Override
    public String toString() {
        return "MetricDto{" +
                "metricId=" + metricId +
                ", taskTitle='" + taskTitle + '\'' +
                ", expectedDeadline=" + expectedDeadline +
                ", realDeadline=" + realDeadline +
                '}';
    }
}
