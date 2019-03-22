package com.ssiryk.workflow.manager;

import com.ssiryk.workflow.model.Job;
import com.ssiryk.workflow.model.Workflow;
import com.ssiryk.workflow.model.WorkflowExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class WorkflowManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowManager.class);

    /**
     * @param name
     * @return workflow
     */
    public Workflow createWorkflow(String name) {

        return new Workflow(name);
    }

    /**
     * @param name
     * @return job
     */
    public Job createWorkflowJob(String name) {

        return new Job(name);
    }

    /**
     * @param workflow
     * @param job
     * @param dependencies
     * @return
     */
    public void registerJob(Workflow workflow, Job job, List<Job> dependencies) {

        workflow.addJob(job, dependencies);
    }

    /**
     * Executes all the jobs within workflow
     *
     * @param workflow to execute
     * @return workflow execution result
     */
    public WorkflowExecutionResult execute(Workflow workflow) {
        LOGGER.info("\tExecuting Workflow {}:", workflow.getName());

        WorkflowExecutionResult workflowExecutionResult = new WorkflowExecutionResult();
        workflowExecutionResult.setWorkflow(workflow);

        List<Job> jobsInOrder = getJobsInOrder(workflow);

        for (Job job : jobsInOrder) {
            workflowExecutionResult.addJob(job.execute());
        }
        return workflowExecutionResult;
    }

    /**
     * Method to get jobs of a workflow in topologically sorted order
     *
     * @param workflow
     * @return list of jobs in topologically sorted order
     */
    private List<Job> getJobsInOrder(Workflow workflow) {
        List<Job> result = new LinkedList<>();

        //used for graph nodes coloring/cycle detection: 0 - not visited, 1 - visiting, 2 - visited
        Map<Job, Integer> visited = new LinkedHashMap<>();
        fillVisited(workflow, visited);

        for (Job job : visited.keySet()) {
            if (visited.get(job) == 0) {
                if (!dfs(workflow.getJobs(), result, visited, job)) {
                    throw new IllegalArgumentException("Jobs Cycle Detected!");
                }
            }
        }

        return result;
    }

    private void fillVisited(Workflow workflow, Map<Job, Integer> visited) {
        for (Job job : workflow.getJobs().keySet()) {
            visited.put(job, 0);
        }
    }

    private boolean dfs(Map<Job, List<Job>> adjacencyList, List<Job> result, Map<Job, Integer> visited, Job job) {
        List<Job> dependencies = adjacencyList.get(job);

        visited.put(job, 1);

        if (dependencies != null) {
            for (Job dep : dependencies) {
                if (visited.get(dep) == 1) {
                    return false;
                }
                if (visited.get(dep) == 0) {
                    if (!dfs(adjacencyList, result, visited, dep)) {
                        return false;
                    }
                }
            }
        }

        result.add(job);

        visited.put(job, 2);

        return true;
    }
}
