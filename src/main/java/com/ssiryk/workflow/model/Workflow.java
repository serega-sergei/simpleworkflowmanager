package com.ssiryk.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Workflow {

    @JsonProperty("name")
    private String name;

    @JsonProperty("jobs")
    private Map<Job, List<Job>> jobs;

    public Workflow() {
    }

    public Workflow(String name) {
        this.name = name;
        this.jobs = new LinkedHashMap<>();
    }

    public Workflow(String name, Map<Job, List<Job>> jobs) {
        this.name = name;
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Job, List<Job>> getJobs() {
        return jobs;
    }

    public void setJobs(Map<Job, List<Job>> jobs) {
        this.jobs = jobs;
    }

    public void addJob(Job job, List<Job> dependencies) {
        this.jobs.put(job, dependencies);
    }

    public void deleteJob(Job job) {
        this.jobs.remove(job);
    }

    public List<Job> getJobDependencies(Job job) {
        return this.jobs.get(job);
    }
}
