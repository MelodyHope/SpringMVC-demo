package com.tch.test;



//public class Test1 {
//    private static Test1 test1 = new Test1();
//    public static int counter1;
//    public static int counter2 = 0;
//
//    private Test1() {
//        counter1++;
//        counter2++;
//    }
//
//    public static Test1 getInstance() {
//        return test1;
//    }
//}
//
//class Test {
//    public static void main(String[] args) {
//        Test1 test1 = Test1.getInstance();
//        System.out.println("counter1 = " + test1.counter1);
//        System.out.println("counter2 = " + test1.counter2);
//    }
//}

public class Test1 {
	public static void main(String[] args) {
		new Graph1(5);

	}
}

class Graph {
	void draw() {
		System.out.println("graph.draw()");
	}

	Graph() {
		System.out.println("graph before draw()");// 会首先被输出，因为首先是初始化基类的构造方法
		draw();// 基类的一个方法，通过子类调的时候其实是调的覆盖方法，也就是子类的draw
		System.out.println("graph after draw()");
	}
}

class Graph1 extends Graph {
	private int radius = 1;

	Graph1(int r) {
		super();//默认先调父类构造器，无论是否显式声明
		System.out.println("Graph1 radius=" + radius);
		radius = r;
		System.out.println("Graph1 radius=" + radius);
		//super();// Error:Constructor call must be the first statement in a constructor
	}

	void draw() {// 对基类的draw方法进行一个覆盖或者重写
		System.out.println("graph1.draw() radius=" + radius);// 最开始时会输出radius=0，而不是1，因为先初始化
															// 而且是由父类的构造函数直接调过来的，所以并没有进行radiu=1的赋值操作
	}
}


