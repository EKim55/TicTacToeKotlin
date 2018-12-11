package edu.rosehulman.kime2.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var game = TicTacToeGame(this)
    var tttButtons = Array(TicTacToeGame.NUM_ROWS) { arrayOfNulls<Button>(TicTacToeGame.NUM_COLUMNS)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        new_game_button.setOnClickListener {
            game.resetGame()
            updateView()
        }

        for (row in 0 until TicTacToeGame.NUM_ROWS) {
            for (col in 0 until TicTacToeGame.NUM_COLUMNS) {
                var id = resources.getIdentifier("button$row$col", "id", packageName)
                tttButtons[row][col] = findViewById<Button>(id)
                tttButtons[row][col]?.setOnClickListener {
                    game.pressedButtonAt(row, col)
                    updateView()
                }
            }
        }
    }

    fun updateView() {
        textView.text = game.stringForGameState()
        for (row in 0 until TicTacToeGame.NUM_ROWS) {
            for (col in 0 until TicTacToeGame.NUM_COLUMNS) {
                tttButtons[row][col]?.text = game.stringForButtonAt(row, col)
            }
        }
    }
}
