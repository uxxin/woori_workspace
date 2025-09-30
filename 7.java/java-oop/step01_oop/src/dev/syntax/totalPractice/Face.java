package dev.syntax.totalPractice;

public class Face {
	private boolean eyes;
	private boolean nose;
	private boolean mouth;
	
	
	
	public Face() {
		this.eyes = true;
		this.nose = true;
		this.mouth = true;
	}

	
	public void printFace() {	
		System.out.println("   _______   ");
		System.out.println("  /       \\  ");
		System.out.println(" |  o   o  |");
		System.out.println(" |    ^    | ");
		System.out.println(" |  \\___/  |");
		System.out.println("  \\_______/");
		System.out.println();
		}
	
	public void printFace(boolean bool) {
		if (bool) {			
			System.out.println("   _______   ");
			System.out.println("  /       \\  ");
			System.out.println(" |  o   o  |");
			System.out.println(" | // ^ // | ");
			System.out.println(" |  \\___/  |");
			System.out.println("  \\_______/");
			System.out.println();
		} else {
			System.out.println("   _______   ");
			System.out.println("  / ..  ..\\  ");
			System.out.println(" |  o   o  |");
			System.out.println(" |    ^    | ");
			System.out.println(" |  \\___/  |");
			System.out.println("  \\_______/");
			System.out.println();
		}
	}
	
	public void printFace(String str) {
		System.out.println(str);
		System.out.println("   _______   ");
		System.out.println("  /       \\  ");
		System.out.println(" |  o   o  |");
		System.out.println(" | // ^ // | ");
		System.out.println(" |  \\___/  |");
		System.out.println("  \\_______/");
		System.out.println();
		}
	
	
	
	
	}




