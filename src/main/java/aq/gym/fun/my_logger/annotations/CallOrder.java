package aq.gym.fun.my_logger.annotations;

import aq.gym.fun.my_logger.util.LaunchOrder;

public @interface CallOrder {
    LaunchOrder order() default LaunchOrder.QUEUE;
}
