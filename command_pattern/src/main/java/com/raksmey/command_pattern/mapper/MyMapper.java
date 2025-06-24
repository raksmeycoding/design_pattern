package com.raksmey.command_pattern.mapper;

public interface MyMapper<S, D> {
    D mapFrom(S s);
    S mapTo(D d);
}
