package com.ssiryk.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkflowExecutionResult {

    @JsonIgnore
    private Workflow workflow;

    @JsonProperty("workflow")
    private String workflowName;

    @JsonProperty("jobs")
    private List<Job> jobs;

    public WorkflowExecutionResult() {
        this.jobs = new ArrayList<>();
    }

    public WorkflowExecutionResult(String workflowName, List<Job> jobs, Workflow workflow) {
        this.workflow = workflow;
        this.workflowName = workflowName;
        this.jobs = jobs;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;

        setWorkflowName(workflow.getName());
    }

    public void addJob(Job job) {
        this.jobs.add(job);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowExecutionResult that = (WorkflowExecutionResult) o;
        return workflow.equals(that.workflow) &&
                workflowName.equals(that.workflowName) &&
                jobs.equals(that.jobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workflow, workflowName, jobs);
    }
}
