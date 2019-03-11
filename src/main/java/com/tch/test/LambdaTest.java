/**
 * 
 */
package com.tch.test;

import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

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
		
		Function<String, Boolean> function = StringUtils::isBlank;
		System.out.println(function.apply("sdfa"));
		
		Function<String, Long> function1 = Long::parseLong;
		Function<Long, String> function2 = String::valueOf;
		Function<String, String> andThenFunc = function1.andThen(function2);
		System.out.println(andThenFunc.apply("1234"));
		
		Function<Integer, Integer> times2 = e -> e * 2;
		Function<Integer, Integer> squared = e -> e * e;
		int n = times2.compose(squared).apply(4); 
		System.out.println(n);
		n = times2.andThen(squared).apply(4);  
		System.out.println(n);
		
		Predicate<String> predicate1 = String::isEmpty;
		Predicate<Object> predicate2 = obj -> obj!=null;
		Predicate<String> andpredicate = predicate1.and(predicate2);
		System.out.println(andpredicate.test("234"));
	}

}

//@FunctionalInterface
interface MyFunctionalInterface<T, R> {
	R convert(T t);
//	T reconvert(R r);
}
