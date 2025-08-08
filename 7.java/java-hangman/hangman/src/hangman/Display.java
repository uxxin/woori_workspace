package hangman;

public class Display {
	public void showGameScreen(int life, char[] currentWord) {
		
	}
	
	public void showMessage(String message) {
		
	}
	
	public static class HangmanDrawing {
        private static final String[] HANGMAN_STAGES = {
            """
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
              |   |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
            =========
            """,
            """
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
            =========
            """
        };
        public static String getDrawing(int mistakes) {
            if (mistakes < 0 || mistakes >= HANGMAN_STAGES.length) {
                return HANGMAN_STAGES[HANGMAN_STAGES.length - 1];
            }
            return HANGMAN_STAGES[mistakes];
        }
        public static int getMaxStages() {
            return HANGMAN_STAGES.length;
        }
    }
}
