package com.raksmey.command_pattern.command;

public  class CreateCategoryWorkflowCommand  extends WorkflowCommandAbstract<String>{


    public CreateCategoryWorkflowCommand(String moduleType) {
        super(moduleType);
    }

    @Override
    public void execute(String dto) throws Exception {

    }



}
