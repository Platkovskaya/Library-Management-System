package com.epam.library.utils;

import java.util.Scanner;

public class ScannerUtils {

	private ScannerUtils() {}

	public static String inputString(String message) {
		System.out.println(message);
		System.out.print("-> ");
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public static int inputInt(String message) {
		System.out.println(message);
		System.out.print("-> ");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

}
