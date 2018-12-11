package edu.rosehulman.kime2.tictactoe
import android.content.Context

class TicTacToeGame() {

    var gameState = GameState.X_TURN
    private var board = Array(3) { IntArray(3) }
    lateinit var context: Context

    enum class GameState {
        X_TURN,
        O_TURN,
        X_WINS,
        O_WINS,
        TIE_GAME
    }
    
    constructor(context: Context): this() {
        this.context = context
    }

    init {
        resetGame()
    }

    fun resetGame() {
        gameState = GameState.X_TURN
        board = Array(3) { IntArray(3) }
    }

    fun stringForButtonAt(row: Int, col: Int): String {
        if (row in 0 until NUM_ROWS && col in 0..(NUM_COLUMNS - 1)) {
            if (board[row][col] == MARK_X) {
                return "X"
            } else if (board[row][col] == MARK_O) {
                return "O"
            }
        }
        return " "
    }

    fun pressedButtonAt(row: Int, col: Int) {
        if (row !in 0 until NUM_ROWS || col !in 0..(NUM_COLUMNS - 1)) {
            return
        }
        if (board[row][col] != MARK_NONE) {
            return
        }
        if (gameState == GameState.X_TURN) {
            board[row][col] = MARK_X
            gameState = GameState.O_TURN
            checkForWin()
        } else if (gameState == GameState.O_TURN) {
            board[row][col] = MARK_O
            gameState = GameState.X_TURN
            checkForWin()
        }
    }

    fun checkForWin() {
        if (gameState != GameState.X_TURN && gameState != GameState.O_TURN) {
            return
        }
        if (didPieceWin(MARK_X)) {
            gameState = GameState.X_WINS
        } else if (didPieceWin(MARK_O)) {
            gameState = GameState.O_WINS
        } else if (isBoardFull()) {
            gameState = GameState.TIE_GAME
        }
    }

    private fun didPieceWin(mark: Int): Boolean {
        for (row in 0 until NUM_ROWS) {
            var winHere = true
            for (col in 0 until NUM_COLUMNS) {
                if (board[row][col] != mark) {
                    winHere = false
                }
            }
            if (winHere) {
                return true
            }
        }
        for (col in 0 until NUM_COLUMNS) {
            var winHere = true
            for (row in 0 until NUM_ROWS) {
                if (board[row][col] != mark) {
                    winHere = false
                }
            }
            if (winHere) {
                return true
            }
        }
        var winHere= true
        for (row in 0 until NUM_ROWS) {
            if (board[row][row] != mark) {
                winHere = false
            }
        }
        if (winHere) {
            return true
        }
        winHere= true
        for (row in 0 until NUM_ROWS) {
            if (board[row][NUM_ROWS - row - 1] != mark) {
                winHere = false
            }
        }
        if (winHere) {
            return true
        }
        return false
    }

    private fun isBoardFull(): Boolean {
        for (row in 0 until NUM_ROWS) {
            for (col in 0 until NUM_COLUMNS) {
                if (board[row][col] == MARK_NONE) {
                    return false
                }
            }
        }
        return true
    }
    
    fun stringForGameState(): String {
        return when (gameState) {
            GameState.X_WINS -> context.getString(R.string.X_Win)
            GameState.O_WINS -> context.getString(R.string.O_Win)
            GameState.X_TURN -> context.getString(R.string.X_Turn)
            GameState.O_TURN -> context.getString(R.string.O_Turn)
            GameState.TIE_GAME -> context.getString(R.string.Tie_Game)
        }
    }

    companion object {
        val NUM_ROWS = 3
        val NUM_COLUMNS = 3
        val MARK_NONE = 0
        val MARK_X = 1
        val MARK_O = 2
    }

}