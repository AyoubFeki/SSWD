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
        GridPane landingPageLayOut = new GridPane();
        ArrayList<String> commandsList = getAllCommands();
        Button findCommandButton = new Button("Look up command");
        errorLabel = new Label();
        findCommandButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    int command = Integer.parseInt(commandTextField.getText());
                    if (command>commandsList.size() || command<=0){
                        throw new RuntimeException("Unacceptable command");
                    }
                    errorLabel.setText("");
                    String commandName = commandsList.get(command-1);
                    String commandExp = commandsList.get(command);
                    CommandDetails goToDetails = new CommandDetails(stage,commandName,commandExp);
                    goToDetails.renderPage();
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
                result.add(results.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
