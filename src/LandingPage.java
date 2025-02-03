import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class LandingPage {
    private Scene landingPageUser;
    private TextField commandTextField = new TextField();
    private Label errorLabel;
    private Stage stage;

    public LandingPage(Stage primaryStage){this.stage = primaryStage;}
    public void renderPage(){
        //VBox landingPageLayOut = new VBox(10);
        GridPane landingPageLayOut = new GridPane();
        ArrayList<String> commandsList = getAllCommands();
        landingPageLayOut.setPadding(new Insets(10));
        Button findCommandButton = new Button("Look up command");
        errorLabel = new Label();
        findCommandButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    int command = Integer.parseInt(commandTextField.getText());
                    if (command !=1 && command != 2 && command != 3){
                        throw new RuntimeException("Unacceptable command");
                    }
                    errorLabel.setText("");
                    // Go to next view
                } catch (NumberFormatException e) {
                    errorLabel.setText("Unacceptable command");
                }
                catch(RuntimeException e){
                    errorLabel.setText("Unacceptable command");
                }


            }
        });
        landingPageLayOut.add(new Label("All the available commands:"),0,0);
        landingPageLayOut.add(new Label("1 "+commandsList.get(0)),0,1);
        landingPageLayOut.add(new Label("2 "+commandsList.get(1)),1,1);
        landingPageLayOut.add(new Label("3 "+commandsList.get(2)),2,1);
        landingPageLayOut.add(commandTextField,0,2);
        landingPageLayOut.add(findCommandButton,0,3);
        landingPageLayOut.add(errorLabel,0,4);
        landingPageLayOut.setHgap(10);
        landingPageLayOut.setVgap(10);

        //landingPageLayOut.getChildren().addAll(new Label("Commands"),
          //      new Label("Choose a command by entering a number"),commandTextField,
            //    findCommandButton);
        landingPageUser = new Scene(landingPageLayOut,500,200);
        stage.setTitle("Commands");
        stage.setScene(landingPageUser);
        stage.show();

    }
    private ArrayList<String> getAllCommands(){
        ArrayList<String> result = new ArrayList<String>();
        Connection con = DBUtils.establishConnection();
        String query = "SELECT * FROM lab04_table;";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet results = statement.executeQuery();
            while (results.next()){
                result.add(results.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
