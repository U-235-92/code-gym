package aq.gym.fun.my_logger;

import aq.gym.fun.my_logger.annotations.*;
import aq.gym.fun.my_logger.exception.NoSingleAnnotationException;
import aq.gym.fun.my_logger.exception.PriorityTestLevelException;
import aq.gym.fun.my_logger.test.TestDemo;
import aq.gym.fun.my_logger.util.LaunchOrder;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestApp {
    private static final Comparator<? super Method> CALL_METHOD_ORDER_COMPARATOR = ((m1, m2) -> {
        Annotation[] annotationsM1 = m1.getAnnotations();
        Annotation[] annotationsM2 = m2.getAnnotations();
        Annotation annotationM1 = annotationsM1[0];
        Annotation annotationM2 = annotationsM2[0];
        if(annotationsM1.length > 1) {
            for(int i = 0; i < annotationsM1.length; i++) {
                if(annotationsM1[i].annotationType() == BeforeSuite.class) {
                    annotationM1 = annotationsM1[i];
                    break;
                } else if(annotationsM1[i].annotationType() == AfterSuite.class) {
                    annotationM1 = annotationsM1[i];
                    break;
                }
            }
        }
        if(annotationsM2.length > 1) {
            for(int i = 0; i < annotationsM2.length; i++) {
                if(annotationsM1[i].annotationType() == BeforeSuite.class) {
                    annotationM2 = annotationsM2[i];
                    break;
                } else if(annotationsM1[i].annotationType() == AfterSuite.class) {
                    annotationM2 = annotationsM2[i];
                    break;
                }
            }
        }
        if(annotationM1.annotationType().getAnnotation(CallOrder.class) == null || 
        		annotationM2.annotationType().getAnnotation(CallOrder.class) == null) {
        	return 0;
        }
        LaunchOrder launchOrderM1 = annotationM1.annotationType().getAnnotation(CallOrder.class).order();
        LaunchOrder launchOrderM2 = annotationM2.annotationType().getAnnotation(CallOrder.class).order();
        return launchOrderM2.getPriority() - launchOrderM1.getPriority();
    });

    private static final Comparator<? super Method> PRIORITY_TEST_COMPARATOR = ((m1, m2) -> {
        int priorityM1 = 0;
        int priorityM2 = 0;
        for(int i = 0; i < m1.getAnnotations().length; i++) {
            if(m1.getAnnotations()[i].annotationType() == Test.class) {
                for(int j = 0; j < m2.getAnnotations().length; j++) {
                    if(m2.getAnnotations()[j].annotationType() == Test.class) {
                        priorityM1 = m1.getAnnotation(Test.class).priority();
                        priorityM2 = m2.getAnnotation(Test.class).priority();
                        break;
                    }
                }
                break;
            }
        }
        return priorityM2 - priorityM1;
    });
    private static int countBeforeSuitAnnotation = 0;

    private static int countAfterSuitAnnotation = 0;

    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        TestApp.test(testDemo.getClass(), testDemo); //"aq.koptev.level3.lesson7.test.TestDemo"
    }

    public static <T extends Object> void test(Class<?> clazz, T obj) {
        try {
            test0(clazz, obj);
        } catch (PriorityTestLevelException e) {
            e.printStackTrace();
        } catch (NoSingleAnnotationException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Object> void test(String clazz, T obj) {
        Class<?> cl;
        try {
            cl = Class.forName(clazz);
            test0(cl, obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (PriorityTestLevelException e) {
            e.printStackTrace();
        } catch (NoSingleAnnotationException e) {
            e.printStackTrace();
        }
    }

    private static <T extends Object> void test0(Class<?> clazz, T obj) throws PriorityTestLevelException, NoSingleAnnotationException {
        List<Method> methods = new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods()));
        methods.sort(CALL_METHOD_ORDER_COMPARATOR);
        methods.sort(PRIORITY_TEST_COMPARATOR);
        for(Method method : methods) {
            handleMethod(method, obj);
        }
    }

    private static <T extends Object> void handleMethod(Method method, T obj) throws NoSingleAnnotationException {
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if(annotation.annotationType() == BeforeSuite.class) {
                if(isSingleBeforeSuitAnnotation()) {
                    try {
                        handleBeforeSuitAnnotation(method, obj);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else if(annotation.annotationType() == AfterSuite.class) {
                if(isSingleAfterSuitAnnotation()) {
                    try {
                        handleAfterSuitAnnotation(method, obj);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else if(annotation.annotationType() == Test.class) {
                try {
                    handleTestAnnotation(method, obj);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (PriorityTestLevelException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isSingleBeforeSuitAnnotation() throws NoSingleAnnotationException {
        if(++countBeforeSuitAnnotation <= 1) {
            return true;
        }
        throw new NoSingleAnnotationException();
    }

    private static <T extends Object> void handleBeforeSuitAnnotation(Method method, T obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(obj);
    }

    private static boolean isSingleAfterSuitAnnotation() throws NoSingleAnnotationException {
        if(++countAfterSuitAnnotation <= 1) {
            return true;
        }
        throw new NoSingleAnnotationException();
    }

    private static <T extends Object> void handleAfterSuitAnnotation(Method method, T obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(obj);
    }

    private static <T extends Object> void handleTestAnnotation(Method method, T obj) throws InvocationTargetException, IllegalAccessException, PriorityTestLevelException {
        TestLevel level = method.getAnnotation(Test.class).annotationType().getAnnotation(TestLevel.class);
        Test test = method.getAnnotation(Test.class);
        if(test.priority() > level.max() || test.priority() < level.min()) {
            throw new PriorityTestLevelException();
        }
        Parameter[] parameters = method.getParameters();
        System.out.println(String.format("Priority next method is: %d", method.getAnnotation(Test.class).priority()));
        if(parameters.length > 0) {
            Object[] objects = new Object[method.getParameters().length];
            for(int i = 0; i < parameters.length; i++) {
                if(parameters[i].getType().equals(int.class)) {
                    objects[i] = 0;
                } else if(parameters[i].getType().equals(float.class)) {
                    objects[i] = 0.0f;
                } else if(parameters[i].getType().equals(double.class)) {
                    objects[i] = 0.0;
                } else if(parameters[i].getType().equals(boolean.class)) {
                    objects[i] = false;
                } else if(parameters[i].getType().equals(byte.class)) {
                    objects[i] = 0;
                } else if(parameters[i].getType().equals(long.class)) {
                    objects[i] = 0l;
                } else if(parameters[i].getType().equals(short.class)) {
                    objects[i] = 0;
                } else if(parameters[i].getType().equals(char.class)) {
                    objects[i] = ' ';
                } else {
                    objects[i] = null;
                }
            }
            method.invoke(obj, objects);
        } else {
            method.invoke(obj);
        }
    }
}
