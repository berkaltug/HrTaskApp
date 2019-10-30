package com.finartz.hrtaskapp.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class FailReason {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer failId;

    //unidirectional
    @OneToOne
    private Metric metric;

    private String description;

    public FailReason(Metric metric, String description) {
        this.metric = metric;
        this.description = description;
    }

    public Integer getFailId() {
        return failId;
    }

    public void setFailId(Integer failId) {
        this.failId = failId;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
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
        if (!(o instanceof FailReason)) return false;
        FailReason that = (FailReason) o;
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
        return "FailReason{" +
                "FailId=" + failId +
                ", metric=" + metric +
                ", description='" + description + '\'' +
                '}';
    }
}
