package hangman;

import service.cloud.Console;

public class HangmanGame {
	
	Status status;
	char[] currentWord;
	String targetWord;
	int life = 6;
	
	
	public HangmanGame() {
		WordProvider wordprovider = new WordProvider();
		
		
	}
	
	public void start(int categoryKey) {
		targetWord = WordProvider.getRandomWord(categoryKey);
		currentWord = new char[targetWord.length()];
		for(int i = 0 ; i< currentWord.length;i++) {
			currentWord[i] = '_';
		}
		
		
		do {
			// 게임 진행 로직
		} while(status == status.INPROGRESS);
	}

	void guessLetter(char inputLetter) {
		boolean correct = false;
		
		for(int i = 0 ; i< targetWord.length(); i++) {
			if(targetWord.charAt(i) == inputLetter && currentWord[i] =='_') {
				currentWord[i] = inputLetter;
				correct = true;
			}
		}
		
		if(!correct) {
			life --;
		}
		
		if(isWordCompleted()) {
			Console.print(targetWord);
			Console.print("성공 ! 🎉🎉🎉");
		}
		
	}
	
	private boolean isWordCompleted() {
		for(char c: currentWord) {
			if(c=='_') {
				return false;
			}
		}
		status = status.SUCCESS;
		return true;
	}
	
	public Character[] getCurrentWord() {
		return null;
	}
	
	public int getLife() {
		return life;
	}
	
	public String getTargetWord() {
		return targetWord;
	}
	
	public Status getStatus() {
		return status;
	}

}
