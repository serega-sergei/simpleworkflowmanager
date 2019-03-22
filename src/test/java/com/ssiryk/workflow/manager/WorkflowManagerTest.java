package com.ssiryk.workflow.manager;

import com.ssiryk.workflow.model.Job;
import com.ssiryk.workflow.model.Workflow;
import com.ssiryk.workflow.model.WorkflowExecutionResult;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Main functionality test.
 * TODO: Add more Tests: multiple workflows, big graphs, different cycles, move all workflow and workflow defenitions to json files and create them from there
 */
public class WorkflowManagerTest {

    private WorkflowManager workflowManager;

    private Workflow workflow;

    WorkflowExecutionResult expected;

    @Before
    public void setUp() throws Exception {
        workflowManager = new WorkflowManager();

        workflow = workflowManager.createWorkflow("Y");
        Job job1 = workflowManager.createWorkflowJob("D");
        Job job2 = workflowManager.createWorkflowJob("E");
        Job job3 = workflowManager.createWorkflowJob("F");
        workflowManager.registerJob(workflow, job1, null);
        workflowManager.registerJob(workflow, job2, Collections.singletonList(job1));
        workflowManager.registerJob(workflow, job3, Arrays.asList(job1, job2));

        expected = new WorkflowExecutionResult("Y", Arrays.asList(job1, job2, job3), workflow);
    }

    @Test
    public void executeBasic() {
        WorkflowExecutionResult actual = workflowManager.execute(workflow);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void executeCycle() {
        Job job1 = workflowManager.createWorkflowJob("G");
        Job job2 = workflowManager.createWorkflowJob("H");
        workflowManager.registerJob(workflow, job1, Collections.singletonList(job2));
        workflowManager.registerJob(workflow, job2, Collections.singletonList(job1));

        workflowManager.execute(workflow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void executeBigCycle() {
        Job job1 = workflowManager.createWorkflowJob("G");
        Job job2 = workflowManager.createWorkflowJob("H");
        Job job3 = workflowManager.createWorkflowJob("I");
        Job job4 = workflowManager.createWorkflowJob("J");
        Job job5 = workflowManager.createWorkflowJob("K");
        workflowManager.registerJob(workflow, job1, Collections.singletonList(job2));
        workflowManager.registerJob(workflow, job2, Collections.singletonList(job3));
        workflowManager.registerJob(workflow, job3, Collections.singletonList(job4));
        workflowManager.registerJob(workflow, job4, Collections.singletonList(job5));
        workflowManager.registerJob(workflow, job5, Collections.singletonList(job1));

        workflowManager.execute(workflow);
    }

    @Test
    public void executeEmpty() {
        workflow = workflowManager.createWorkflow("Z");
        WorkflowExecutionResult actual = workflowManager.execute(workflow);
        expected = new WorkflowExecutionResult();
        expected.setWorkflow(workflow);

        assertEquals(expected, actual);
    }
}