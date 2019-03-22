package com.ssiryk.workflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(Workflow.class);

    @JsonProperty("name")
    private String name;

    public Job() {
    }

    public Job(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job execute() {
        LOGGER.info("\t\tExecuting Job {}:", name);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return name.equals(job.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
