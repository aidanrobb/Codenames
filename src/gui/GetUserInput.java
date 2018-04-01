package gui;

import java.util.Scanner;

import code.Board;

public class GetUserInput {
	 
	private static Board b = new Board();
	private static String var;
	static Scanner answer;
	
	public static String input() {
		String codeName;
		Boolean ok = false;
		answer = new Scanner(System.in);
		
		while(!ok) {
			System.out.println("What is your clue?");
			var = answer.nextLine();
			if(b.goodClue(var) == true) {
				System.out.println("Good Clue!");
				ok = true;
			}
		}
		codeName = var;
		return codeName;
		
	}
	
	public static void main(String[] args) {
		input();
		answer.close();
	}
}
