package dev.syntax.totalPractice;

public class Rabbit extends Face{
	public void printFace() {	
		System.out.println("  |‾‾| |‾‾| ");
		System.out.println("  |__|_|__|   ");
		System.out.println("  /       \\  ");
		System.out.println(" |  o   o  |");
		System.out.println(" |    ^    | ");
		System.out.println(" |  \\___/  |");
		System.out.println("  \\_______/");
		System.out.println();
		}
	
	public void printFace(boolean bool) {
		if (bool) {
			System.out.println("  |‾‾| |‾‾| ");
			System.out.println("  |__|_|__|   ");
			System.out.println("  /       \\  ");
			System.out.println(" |  o   o  |");
			System.out.println(" | // ^ // | ");
			System.out.println(" |  \\___/  |");
			System.out.println("  \\_______/");
			System.out.println();
		} else {
			System.out.println("  |‾‾| |‾‾| ");
			System.out.println("  |__|_|__|   ");
			System.out.println(" / ..  .. \\  ");
			System.out.println(" |  o   o  |");
			System.out.println(" |    ^    | ");
			System.out.println(" |  \\___/  |");
			System.out.println("  \\_______/");
			System.out.println();
		}
	}
}
