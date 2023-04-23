package com.example.tictactoe_april;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    // declaration of global variables
    private Button buttons[][] = new Button [3][3];
    private Label playerXScoreLebel , playerOScoreLebel;

    private int  playerXScore = 0 , playerOScore = 0;

    private boolean playerXTurn = true;
    /*starting point of our project all the graphical
    content will bw in this method */
    private BorderPane createContent(){
        BorderPane root = new BorderPane();
        // Title
        Label titleLable = new Label("Tic Tac Toe");
        // now we will set the title as bold and big
        titleLable.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
        root.setTop(titleLable);   // setting the title on the top
        //Game Board
        GridPane gridPane = new GridPane();
        // giving the gaps in between the buttons
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // generation of 9 buttons
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // creation of the button in my grid
                Button button = new Button();
                // we can adjust the size of the buttons
                button.setPrefSize(100 , 100);
                button.setStyle("-fx-font-size : 24pt; -fx-font-weight : bold;");
                // command for creation the outputs of buttons
                // It will display the X and O ont the button
                button.setOnAction(actionEvent -> buttonClicked(button));
                // adding actual button ot it
                buttons[i][j] = button;
                gridPane.add(button , j , i);
            }
     }
        // set the grid to the centre
        root.setCenter(gridPane);

        //Score
        HBox scoreBoard = new HBox(20); // Hbox arrange the node in a single row
        playerXScoreLebel = new Label("player X : 0");
        playerXScoreLebel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        playerOScoreLebel = new Label("player O : 0");
        playerOScoreLebel.setStyle("-fx-font-size : 16pt; -fx-font-weight : bold;");
        scoreBoard.getChildren().addAll(playerXScoreLebel , playerOScoreLebel);

        root.setBottom(scoreBoard);
        return root;
    }
    private void buttonClicked(Button button){
        // if no any prior text will present on the button
        if (button.getText().equals("")){
            if (playerXTurn)
                button.setText("X");
            else
                button.setText("O");

            // reverting the button if it is true then make it false and vice versa
            playerXTurn = !playerXTurn;

            //at every pass we will check
            checkWinner();
        }
        return;
    }

    // this function will check weather the person is
    // winner or not
    private void checkWinner(){
        // row
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(buttons[row][1].getText())
            && buttons[row][1].getText().equals(buttons[row][2].getText())
                    && !buttons[row][0].getText().isEmpty()
            ){
                // we will have winner
                String winner = buttons[row][0].getText(); // that row sign's person will be winner
                // call both the function
                showWinnerDialogue(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        // column
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()
            ){
                // we will have winner
                String winner = buttons[0][col].getText(); // that row sign's person will be winner
                // call both the function
                showWinnerDialogue(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }

        //diagonal
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()
        ){
            // we will have winner
            String winner = buttons[0][0].getText(); // that row sign's person will be winner
            // call both the function
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        if (buttons[2][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[0][2].getText())
                && !buttons[0][2].getText().isEmpty()
        ){
            // we will have winner
            String winner = buttons[2][0].getText(); // that row sign's person will be winner
            // call both the function
            showWinnerDialogue(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        // condition of tie
        boolean tie = true;
        for(Button row[] : buttons){
            for(Button button : row){
                if (button.getText().isEmpty()){
                    tie =false;
                    break;
                }
            }
        }
        if(tie){
            showTieDialogue();
            resetBoard();
        }

    }
// this function will show the winning dialogue
    private void showWinnerDialogue(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations "+ winner + " ! You won the game");
        alert.setHeaderText("");//we use this to avoid the display of the message
        alert.showAndWait();

    }
    private void showTieDialogue(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("tie");
        alert.setContentText("Game Over It's a tie");
        alert.setHeaderText("");//we use this to avoid the display of the message
        alert.showAndWait();

    }

    private void updateScore(String winner){
 if (winner.equals("X")) {
     playerXScore++;
     playerXScoreLebel.setText("Player X : " + playerXScore);
 }
     else{
         playerOScore++;
     playerOScoreLebel.setText("Player O : " + playerOScore);
 }
}
// this is for setting all the buttons empty again for new game
  private void resetBoard(){
        for(Button row[] : buttons){
            for(Button button : row){
                button.setText("");
            }
        }
  }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}