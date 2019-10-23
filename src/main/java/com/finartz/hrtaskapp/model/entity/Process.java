package com.finartz.hrtaskapp.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class Process {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer processId;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "process")
    private List<Task> tasks=new LinkedList<>();

    @NotEmpty
    private String status;


    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @Temporal(TemporalType.DATE)
    private Date closeDate;

    public Process() {
    }

    public Process(List<Task> tasks, @NotEmpty String status, @NotEmpty Date creationDate, Date updateDate, Date closeDate) {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
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
        if (!(o instanceof Process)) return false;
        Process process = (Process) o;
        return Objects.equals(processId, process.processId) &&
                Objects.equals(tasks, process.tasks) &&
                Objects.equals(status, process.status) &&
                Objects.equals(creationDate, process.creationDate) &&
                Objects.equals(updateDate, process.updateDate) &&
                Objects.equals(closeDate, process.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, tasks, status, creationDate, updateDate, closeDate);
    }

    @Override
    public String toString() {
        return "Process{" +
                "processId=" + processId +
                ", tasks=" + tasks +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", closeDate=" + closeDate +
                '}';
    }
}
