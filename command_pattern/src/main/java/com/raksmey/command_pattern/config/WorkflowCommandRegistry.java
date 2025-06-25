package com.raksmey.command_pattern.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raksmey.command_pattern.annotation.ModuleMethodHandler;
import com.raksmey.command_pattern.command.JsonCommandAdapter;
import com.raksmey.command_pattern.command.RawWorkflowCommand;
import com.raksmey.command_pattern.interface_segregation_principle.WorkflowHandlerMarker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WorkflowCommandRegistry implements SmartInitializingSingleton {

    private final List<WorkflowHandlerMarker<?>> commandBeans;
    private final ObjectMapper objectMapper;
    private final Map<String, RawWorkflowCommand> registry = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(WorkflowCommandRegistry.class);

    @SuppressWarnings("unchecked")
    @Override
    public void afterSingletonsInstantiated() {
//        for (WorkflowCommand<?> command : commands) {
//            ModuleMethodHandler handler = command.getClass().getAnnotation(ModuleMethodHandler.class);
//            if (handler != null) {
//                String key = handler.value();
//                Class<Object> dtoType = (Class<Object>) extractDtoType(command);
//                registry.put(key, new JsonCommandAdapter<>(dtoType, (WorkflowCommand<Object>) command, objectMapper));
//            }
//        }
        logger.info("All command beans: {}", commandBeans.size());

        for (WorkflowHandlerMarker<?> bean : commandBeans) {
            ModuleMethodHandler handler = bean.getClass().getAnnotation(ModuleMethodHandler.class);
            if (handler != null) {
                String key = handler.value();
                Class<Object> dtoType = (Class<Object>) extractDtoType(bean);
                registry.put(key, new JsonCommandAdapter<>(dtoType, bean, objectMapper));
            }
        }
    }


//    private Class<?> extractDtoType(WorkflowCommand<?> command) {
//        ParameterizedType type = (ParameterizedType) command.getClass().getGenericInterfaces()[0];
//        return (Class<?>) type.getActualTypeArguments()[0];
//    }


    public RawWorkflowCommand getCommand(String type) {
        return registry.get(type);
    }

    private Class<?> extractDtoType(Object bean) {
        for (Type iface : bean.getClass().getGenericInterfaces()) {
            logger.info("iface: {}", iface.getTypeName());
            if (iface instanceof ParameterizedType pt) {
                Type raw = pt.getRawType();
                logger.info("raw: {}", raw);
                if (raw instanceof Class<?> clazz &&
                        WorkflowHandlerMarker.class.isAssignableFrom(clazz)) {
                    return (Class<?>) pt.getActualTypeArguments()[0];
                }
            }
        }
        throw new IllegalStateException("Handler does not declare generic type: " + bean.getClass());
    }
}
