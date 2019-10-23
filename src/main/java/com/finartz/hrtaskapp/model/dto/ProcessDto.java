package com.finartz.hrtaskapp.model.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProcessDto {

    private Integer processId;
    private List<TaskDto> tasks;
    private String status;
    private Date creationDate;
    private Date updateDate;
    private Date closeDate;

    public ProcessDto() {
    }

    public ProcessDto(Integer processId, List<TaskDto> tasks, String status, Date creationDate, Date updateDate, Date closeDate) {
        this.processId = processId;
        this.tasks = tasks;
        this.status = status;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.closeDate = closeDate;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProcessDto)) return false;
        ProcessDto that = (ProcessDto) o;
        return Objects.equals(processId, that.processId) &&
                Objects.equals(tasks, that.tasks) &&
                Objects.equals(status, that.status) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(closeDate, that.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, tasks, status, creationDate, updateDate, closeDate);
    }

    @Override
    public String toString() {
        return "ProcessDto{" +
                "processId=" + processId +
                ", tasks=" + tasks +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", closeDate=" + closeDate +
                '}';
    }
}
