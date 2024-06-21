package com.example.spring.test;

public class Hash {

	public static void main(String[] args) {
		var text = "Hola Mundo Spring";
		
		System.out.println(text.hashCode());
		
		var textOtro = "Hola Mundo Spring2";
		
		System.out.println(textOtro.hashCode());
	}

}
