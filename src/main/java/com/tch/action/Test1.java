package com.tch.action;


public class Test1 {
    private static Test1 test1 = new Test1();
    public static int counter1;
    public static int counter2 = 0;

    private Test1() {
        counter1++;
        counter2++;
    }

    public static Test1 getInstance() {
        return test1;
    }
}

class Test {
    public static void main(String[] args) {
        Test1 test1 = Test1.getInstance();
        System.out.println("counter1 = " + test1.counter1);
        System.out.println("counter2 = " + test1.counter2);
    }
}

