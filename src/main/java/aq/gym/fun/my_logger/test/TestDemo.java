package aq.gym.fun.my_logger.test;

import aq.gym.fun.my_logger.annotations.*;

public class TestDemo {

    @BeforeSuite
    public void before() {
        System.out.println("Before All");
    }

    @AfterSuite
    public void after() {
        System.out.println("After All");
    }

    @Test
    public int getTest4() {
        System.out.println("getTest4()");
        return 3;
    }

    @Test(priority = 9)
    public int getTest1() {
        System.out.println("getTest()");
        return 3;
    }

    @Test(priority = 0)
    public int getTest2() {
        System.out.println("getTest2()");
        return 3;
    }

    @Test
    public int getTest3() {
        System.out.println("getTest3()");
        return 3;
    }

    @Test(priority = 6)
    public int getTest5(int i, double d, String s) {
        System.out.println("getTest5(): i = " + i + "; d = " + d + "; s =  " + s);
        return i;
    }
}
