package aq.gym.fun.my_logger.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestLevel {
    int min();
    int max();
}
