module com.example.tictactoe_april {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoe_april to javafx.fxml;
    exports com.example.tictactoe_april;
}