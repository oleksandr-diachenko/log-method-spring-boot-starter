package com.epam.methodlog.aspect.log;

import com.epam.methodlog.aspect.AspectMethodLookup;
import com.epam.methodlog.aspect.AspectMethodParametersLookup;
import com.epam.methodlog.utils.formatter.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@RequiredArgsConstructor
public class InputMethodLogAspect {

    private final AspectMethodLookup aspectMethodLookup;
    private final AspectMethodParametersLookup aspectMethodParametersLookup;
    private final StringFormatter<Map<String, Object>> stringFormatter;
    private final AspectLoggerLookup aspectLoggerLookup;

    @Pointcut("@annotation(com.epam.methodlog.annotation.InputMethodLog)")
    public void anyMethodAnnotatedWithInputMethodLog() {
        // pointcut
    }

    @Before(value = "anyMethodAnnotatedWithInputMethodLog()")
    public void logMethod(JoinPoint jp) {
        Map<String, Object> args = aspectMethodParametersLookup.lookup(jp);
        String parameters = stringFormatter.format(args);
        Method method = aspectMethodLookup.lookup(jp);
        Logger logger = aspectLoggerLookup.lookup(jp);
        logger.info("Method: '{}' was called with parameters: {}", method.getName(), parameters);
    }
}