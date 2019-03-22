package com.ssiryk.workflow;

import com.ssiryk.workflow.manager.WorkflowManager;
import com.ssiryk.workflow.model.Job;
import com.ssiryk.workflow.model.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

/**
 * Just Demo test which executes basic use case on application startup
 */
@Component
public class WorkflowTest implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Workflow.class);

    private WorkflowManager workflowManager;

    @Autowired
    public WorkflowTest(WorkflowManager workflowManager) {
        this.workflowManager = workflowManager;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("################# Just Sample Demo Execution: Start #################");

        WorkflowManager workflowManager = new WorkflowManager();

        Workflow workflow = workflowManager.createWorkflow("X");
        Job job1 = workflowManager.createWorkflowJob("A");
        Job job2 = workflowManager.createWorkflowJob("B");
        Job job3 = workflowManager.createWorkflowJob("C");
        workflowManager.registerJob(workflow, job1, null);
        workflowManager.registerJob(workflow, job2, Collections.singletonList(job1));
        workflowManager.registerJob(workflow, job3, Arrays.asList(job1, job2));

        workflowManager.execute(workflow);

        LOGGER.info("################# Just Sample Demo Execution: End #################");
    }
}
