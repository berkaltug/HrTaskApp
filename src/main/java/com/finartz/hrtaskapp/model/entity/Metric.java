package com.finartz.hrtaskapp.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer metricId;

    private String taskTitle;

    private Date expectedDeadline;

    private Date realDeadline;

    public Metric() {
    }

    public Metric(String taskTitle, Date expectedDeadline, Date realDeadline) {
        this.taskTitle = taskTitle;
        this.expectedDeadline = expectedDeadline;
        this.realDeadline = realDeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Metric)) return false;
        Metric metric = (Metric) o;
        return Objects.equals(metricId, metric.metricId) &&
                Objects.equals(taskTitle, metric.taskTitle) &&
                Objects.equals(expectedDeadline, metric.expectedDeadline) &&
                Objects.equals(realDeadline, metric.realDeadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricId, taskTitle, expectedDeadline, realDeadline);
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
    public String toString() {
        return "Metric{" +
                "metricId=" + metricId +
                ", taskTitle='" + taskTitle + '\'' +
                ", expectedDeadline=" + expectedDeadline +
                ", realDeadline=" + realDeadline +
                '}';
    }
}


