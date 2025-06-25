package com.raksmey.command_pattern.config;


import com.raksmey.command_pattern.domain.WorkflowType;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class CommandTypeAutoConfiguration {

    @PostConstruct
    public void validateCommandTypes() {
        Set<String> values = new HashSet<>();

        try {
            Field[] fields = WorkflowType.class.getFields();

            for (Field field : fields) {
                if (isPublicStaticFinalString(field)) {
                    String value = (String) field.get(null);
                    if (!values.add(value)) {
                        throw new IllegalStateException(
                                "Duplicate command type value: " + value);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Command type validation failed", e);
        }
    }

    private boolean isPublicStaticFinalString(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isPublic(modifiers) &&
                Modifier.isStatic(modifiers) &&
                Modifier.isFinal(modifiers) &&
                field.getType().equals(String.class);
    }
}