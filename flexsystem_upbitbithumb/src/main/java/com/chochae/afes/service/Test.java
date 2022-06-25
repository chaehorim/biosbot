/**
 * 
 */
package com.chochae.afes.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Isco
 *
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("godj");
		List<String> a = new ArrayList<String>();
		a.add("asdfas");
		a.add("dddd");
		a.add("bbb");
		a.add("cccc");
		a.add("aaa");
		
		a.remove(0);
		
		for (String x : a) {
			System.out.println(x);
		}
	}

}
