package com.raksmey.command_pattern.command;

public  class CreateCategoryWorkflowCommand  extends WorkflowCommandAbstract<String>{


    public CreateCategoryWorkflowCommand(String moduleType) {
        super(moduleType);
    }

    @Override
    public void approved(String dto) throws Exception {

    }

    @Override
    public void rejected(String dto) throws Exception {

    }

    @Override
    public void pending(String dto) throws Exception {

    }
}
