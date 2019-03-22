#### Build

`mvn clean install`

#### Execute

`mvn spring-boot:run`

#### Sample Endpoint

**URI:** /simpleworkflowmanager/api/workflow/execute

**METHOD:** POST

**Sample Payload:** 

`{
    "name": "X",
    "jobs": {
        "A": null,
        "B": [
            {
                "name": "A"
            }
        ],
        "C": [
            {
                "name": "A"
            },
            {
                "name": "B"
            }
        ]
    }
}`

**Sample Response:**

`{
    "workflowName": "X",
    "jobs": [
        {
            "name": "A"
        },
        {
            "name": "B"
        },
        {
            "name": "C"
        }
    ]
}`

#### Bullet Points.

**1. See Build and Execute section**

**2. Some unit tests provided**

**3. Service** 

    - Check for test implementation `com.ssiryk.workflow.controller.WorkflowController`
	- OAuth
	- Persistence and CRUD Operations for Jobs and Workflows?
	- Documentation/Swagger 
	- API Rate Limiting and ... / Apigee
	
**4. Scaling**

	- See #3
	- Preferably horizontal scaling with LB, Caching, ...
	- Parallel execution of Workflows as well as Jobs without dependencies or with already executed dependencies
	- Asynchronous/Batch/Scheduled workflow execution with later notification on completion
	- Splitting/Aggregation of the Jobs for parallel execution
	- Circuit Breaker to mitigate failures
	- Improving Topological Sorting Algorithm (use existing one which proved its run time effectiveness)
	
**5. Practical Applications**

	- School courses scheduling
	- Dependency management/maven
	- Order of equipment assembly
