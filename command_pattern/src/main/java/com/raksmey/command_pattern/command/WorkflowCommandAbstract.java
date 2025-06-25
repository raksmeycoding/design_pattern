package com.raksmey.command_pattern.command;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public abstract class WorkflowCommandAbstract<T> implements WorkflowCommand<T>{
    public final String moduleType;
}
