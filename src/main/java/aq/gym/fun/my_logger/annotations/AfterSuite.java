package aq.gym.fun.my_logger.annotations;

import aq.gym.fun.my_logger.util.LaunchOrder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@CallOrder(order = LaunchOrder.AFTER_ALL)
public @interface AfterSuite {
}
