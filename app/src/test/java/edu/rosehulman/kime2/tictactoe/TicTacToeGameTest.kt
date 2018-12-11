package edu.rosehulman.kime2.tictactoe

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TicTacToeGameTest {
    @Test
    fun resetBoard() {
        val game = TicTacToeGame()
        for (row in 0 until TicTacToeGame.NUM_ROWS) {
            for (col in 0 until TicTacToeGame.NUM_COLUMNS) {
                assertEquals(" ", game.stringForButtonAt(row, col))
            }
        }
    }

    @Test
    fun press() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressedButtonAt(1, 2)
        assertEquals("X", game.stringForButtonAt(1, 2))
        game.pressedButtonAt(0, 2)
        assertEquals("O", game.stringForButtonAt(0, 2))
        game.pressedButtonAt(2, 2)
        assertEquals("X", game.stringForButtonAt(2, 2))
        assertEquals(TicTacToeGame.GameState.O_TURN, game.gameState)
    }

    @Test
    fun pressOutOfBoundsIgnored() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressedButtonAt(3, 1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
        game.pressedButtonAt(-1, 1)
        game.pressedButtonAt(1, 3)
        game.pressedButtonAt(1, -1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.gameState)
    }

    //TODO Test wins
}
