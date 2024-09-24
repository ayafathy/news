package com.apps.wave.news.service;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.Deployment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class WorkflowDeploymentService {

    private final RepositoryService repositoryService;
    private final RuntimeService runtimeService;

    public WorkflowDeploymentService(RepositoryService repositoryService, RuntimeService runtimeService) {
        this.repositoryService = repositoryService;
        this.runtimeService = runtimeService;
    }

    @PostConstruct
    public void deployWorkflow() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("process.bpmn") // BPMN file in resources folder
                .name("News Approval and Deletion Process")
                .deploy();

        System.out.println("Deployed Process ID: " + deployment.getId());
    }
}

