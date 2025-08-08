package hangman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordProvider {
	private Map<Integer, List<String>> wordsByCategory;
	private Random random;

	public WordProvider() {
		this.wordsByCategory = new HashMap<>();
		this.random = new Random();
		CategoryWords();
	}

	private void CategoryWords() {
		wordsByCategory.put(1, Arrays.asList("BREAD", "APPLE", "PIZZA", "GRAPE", "STEAK", "MANGO", "SALAD", "BACON",
				"LEMON", "SUSHI"));
		wordsByCategory.put(2,
				Arrays.asList("TIGER", "ZEBRA", "MOUSE", "HORSE", "SNAKE", "PANDA", "FROG", "WHALE", "EAGLE", "SHEEP"));
		wordsByCategory.put(3,
				Arrays.asList("TABLE", "CHAIR", "CLOCK", "BOOK", "PEN", "LAMP", "BAG", "DOOR", "KEY", "CUP"));
		wordsByCategory.put(4,
				Arrays.asList("RUN", "EAT", "JUMP", "PLAY", "READ", "SING", "SWIM", "DRIVE", "COOK", "DRAW"));
	}

	public static String getRandomWord(int categoryKey) {
		return null;
	}

	public String getCategoryName(int categoryKey) {
		switch (categoryKey) {
		case 1:
			return "음식 🍕";
		case 2:
			return "동물 🐶";
		case 3:
			return "사물 📦";
		case 4:
			return "동사 🏃";
		default:
			return "알 수 없는 카테고리";
		}
	}
}
