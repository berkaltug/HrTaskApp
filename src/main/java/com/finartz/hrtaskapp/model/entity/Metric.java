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

    @OneToMany(mappedBy = "metric")
    private List<Task> task;

    private Date expectedDeadline;

    private Date realDeadline;

    public Metric(List<Task> task, Date expectedDeadline, Date realDeadline) {
        this.task = task;
        this.expectedDeadline = expectedDeadline;
        this.realDeadline = realDeadline;
    }

    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
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
        if (!(o instanceof Metric)) return false;
        Metric metric = (Metric) o;
        return Objects.equals(metricId, metric.metricId) &&
                Objects.equals(task, metric.task) &&
                Objects.equals(expectedDeadline, metric.expectedDeadline) &&
                Objects.equals(realDeadline, metric.realDeadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metricId, task, expectedDeadline, realDeadline);
    }

    @Override
    public String toString() {
        return "Metric{" +
                "id=" + metricId +
                ", task=" + task +
                ", expectedDeadline=" + expectedDeadline +
                ", realDeadline=" + realDeadline +
                '}';
    }
}


