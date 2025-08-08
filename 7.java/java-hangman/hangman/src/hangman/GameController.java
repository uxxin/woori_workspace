package hangman;

import service.cloud.Console;

public class GameController {

	public static void main(String[] args) {
		String hangmanIntro = """

				      +----+
				      |    |
				   Welcome |
				     to    |
				  Hangman  |
				    Game   |
				           |
				===============

				    """;
		// 게임 시작 콘솔
		Console.println(hangmanIntro);
		// 카테고리 인풋 받기
		int categoryKey = inputCategory();
		Console.println(categoryKey);

		// HangmanGame 인스턴스 생성
		HangmanGame game = new HangmanGame();
		game.start(categoryKey);

	}

	private static int inputCategory() {
		String input;

	    while (true) {
	        Console.println("~~~~~~~ Select Category ~~~~~~~");
			Console.println("1.Food 2.Animal 3.Object 4.Verbs");
			Console.print("input Category Number (1, 2, 3, 4) : ");
	        input = Console.read();

	        // 유효성 검사: categoryKey가 1,2,3,4 중 하나인지 체크
	        if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
	            break;  // 유효하면 반복 종료
	        } else {
	            System.out.println("\n⚠️ 잘못된 입력입니다. \n1, 2, 3, 4 중 하나를 입력하세요.");
	        }
	    }
	    return Integer.parseInt(input);
	}
}
