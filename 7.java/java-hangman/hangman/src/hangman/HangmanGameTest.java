package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HangmanGameTest {

    private HangmanGame game;

    @BeforeEach
    public void setUp() {
        game = new HangmanGame(1, "APPLE");  // test용 단어 APPLE로 고정
    }
    
    //게임 시작 직후 상태 테스트
    @Test
    @DisplayName("초기 상태 테스트 : Status = IN_PROGRESS, life = 6, 초기 cuurentWord: _ _ _ _ _")
    public void testInitialState() {
        assertEquals(Status.IN_PROGRESS, game.getStatus());
        assertEquals(6, game.life);
        assertArrayEquals(new char[] {'_', '_', '_', '_', '_'}, game.getCurrentWord());
    }

    @Test
    @DisplayName("올바른 알파벳 입력 시: 목숨 6개 유지, Status = IN_PROGRESS 유지")
    public void testCorrectGuess() {
        game.guessLetter('A');
        assertEquals('A', game.getCurrentWord()[0]);
        assertEquals(6, game.life);
        assertEquals(Status.IN_PROGRESS, game.getStatus());
    }

    @Test
    @DisplayName("포함되지 않은 알파벳 입력 시: 목숨 -1 , Status = IN_PROGRESS 유지")
    public void testWrongGuess() {
        game.guessLetter('Z');
        assertEquals(5, game.life);
        assertEquals(Status.IN_PROGRESS, game.getStatus());
    }

    @Test
    @DisplayName("단어 APPLE 완성 시- Status = SUCCESS로 변경, currentWord와 APPLE 비교")
    public void testCompleteSuccess() {
        for(char c : new char[] {'A','P','L','E'}) {
            game.guessLetter(c);
        }
        assertEquals(Status.SUCCESS, game.getStatus());
        assertEquals("APPLE", new String(game.getCurrentWord()));
    }

    @Test
    @DisplayName("틀린 알파벳 6번 입력(목숨 잃고 종료): Status = Fail로 변경, life = 0 ")
    public void testFailGame() {
        for(char c : new char[] {'Z','Y','X','Q','W','R'}) {
            game.guessLetter(c);
        }
        assertEquals(Status.FAIL, game.getStatus());
        assertEquals(0, game.life);
    }
}
