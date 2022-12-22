package com.epam.methodlog.aspect.log.level;

import com.epam.methodlog.aspect.lookup.AspectLoggerLookup;
import com.epam.methodlog.tags.Junit;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Junit
@ExtendWith(MockitoExtension.class)
class LoggerDebugTest {

    @InjectMocks
    private LoggerDebug loggerDebug;

    @Mock
    private AspectLoggerLookup aspectLoggerLookup;
    @Mock
    private JoinPoint joinPoint;
    @Mock
    private Logger logger;

    @Test
    void shouldCallLogDebug() {
        when(aspectLoggerLookup.lookup(joinPoint)).thenReturn(logger);

        loggerDebug.log(joinPoint, "Numbers are {}{}{}", 1, 2, 3);

        verify(logger).debug("Numbers are {}{}{}", 1, 2, 3);
    }
}