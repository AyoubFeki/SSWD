import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LandingPage {
    private Scene landingPageUser;
    private TextField commandTextField = new TextField();
    //private String[] commands = getAllCommands();
    private Stage stage;
    public LandingPage(Stage primaryStage){this.stage = primaryStage;}
    public void renderPage(){
        VBox landingPageLayOut = new VBox(10);
        landingPageLayOut.setPadding(new Insets(10));
        Button findCommandButton = new Button("Look up command");
        findCommandButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("OI");
            }
        });
        landingPageLayOut.getChildren().addAll(new Label("Commands"),
                new Label("Choose a command by entering a number"),commandTextField,findCommandButton);
        landingPageUser = new Scene(landingPageLayOut,300,450);
        stage.setTitle("Commands");
        stage.setScene(landingPageUser);
        stage.show();

    }
    private void getAllCommands(){

        //return ["stuff"];
    }

}
