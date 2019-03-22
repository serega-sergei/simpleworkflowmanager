package com.ssiryk.workflow.model;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Method;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

public class WorkflowTest {

    @Test
    public void testPojo() {
        @SuppressWarnings("rawtypes") final Class classUnderTest = Workflow.class;

        assertPojoMethodsFor(classUnderTest).testing(Method.CONSTRUCTOR)
                .testing(Method.GETTER)
                .testing(Method.SETTER)
                .areWellImplemented();
    }
}