package com.finartz.hrtaskapp.model.dto;

import com.finartz.hrtaskapp.model.entity.Task;

import java.util.Date;

public class MetricDto {

    private Integer metricId;
    private TaskDto taskDto;
    private Date expectedDeadline;
    private Date realDeadline;

    public MetricDto(Integer metricId, TaskDto taskDto, Date expectedDeadline, Date realDeadline) {
        this.metricId = metricId;
        this.taskDto = taskDto;
        this.expectedDeadline = expectedDeadline;
        this.realDeadline = realDeadline;
    }

    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public TaskDto getTaskDto() {
        return taskDto;
    }

    public void setTaskDto(TaskDto taskDto) {
        this.taskDto = taskDto;
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
}
