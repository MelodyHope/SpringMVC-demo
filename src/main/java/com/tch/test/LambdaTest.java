/**
 * 
 */
package com.tch.test;

/**
 * @author hsadmin
 *
 */
public class LambdaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFunctionalInterface<Long, String> f = l -> String.valueOf(l);
		System.out.println(f.convert(10L));
		f = String::valueOf;
		System.out.println(f.convert(15L));
		
		MyFunctionalInterface<String, Long> g = s -> Long.parseLong(s);
		System.out.println(g.convert("20"));
		g = Long::parseLong;
		System.out.println(g.convert("25"));
	}

}

//@FunctionalInterface
interface MyFunctionalInterface<T, R> {
	R convert(T t);
//	T reconvert(R r);
}
