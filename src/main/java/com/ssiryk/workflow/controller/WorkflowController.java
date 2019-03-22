package com.ssiryk.workflow.controller;

import com.ssiryk.workflow.manager.WorkflowManager;
import com.ssiryk.workflow.model.Workflow;
import com.ssiryk.workflow.model.WorkflowExecutionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

/**
 * Demo endpoint to execute workflow.
 * Probably it is worth to add workflow CRUD operations endpoints,
 * and execution by name or id endpoint
 */
@RestController
@RequestMapping(value = WorkflowController.WORKFLOW)
public class WorkflowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowController.class);

    static final String WORKFLOW = "/workflow";

    private static final String EXECUTE = "/execute";

    private WorkflowManager workflowManager;

    @Autowired
    public WorkflowController(WorkflowManager workflowManager) {
        this.workflowManager = workflowManager;
    }

    @RequestMapping(value = EXECUTE,
            produces = MediaType.APPLICATION_JSON,
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<WorkflowExecutionResult> execute(@RequestBody Workflow workflow,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {
        LOGGER.info("execute called");

        return new ResponseEntity<WorkflowExecutionResult>(workflowManager.execute(workflow), HttpStatus.OK);
    }
}