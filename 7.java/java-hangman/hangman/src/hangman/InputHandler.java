package hangman;

import java.util.HashSet;
import java.util.Set;
import service.cloud.Console;

public class InputHandler {

	private Set<Character> usedLetters = new HashSet<>();

	public char inputLetter() {
		// 입력 받아서 유효성 검사
		while(true) {
			Console.print("문자 하나를 입력하세요: ");
			// 중복 입력 체크
			String input = Console.read();
			if(input.length()!=1) {
				Console.print("⚠️ 한 글자만 입력하세요.");
				continue;
			}
			
			char letter = Character.toLowerCase(input.charAt(0));
			
			if(usedLetters.contains(letter)) {
				Console.print("⚠️ 이미 입력한 글자입니다: "+ letter);
				continue;
			}
			
			usedLetters.add(letter);
			return letter;
		}
	}
}