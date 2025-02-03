import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CommandDetails {
    private Scene commandPage;
    private String commandName;
    private String commandExp;
    private Stage stage;
    public CommandDetails(Stage primaryStage,String commandName,String commandExp){
        this.stage = primaryStage;
        this.commandName = commandName;
        this.commandExp = commandExp;
    }
    public void renderPage(){
        GridPane commandDetailsLayout = new GridPane();
        //ArrayList<String> details = getallDetails(commandOption);
        Button goingBackButton = new Button("back");
        goingBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LandingPage goingBack = new LandingPage(stage);
                goingBack.renderPage();
            }
        });
        commandDetailsLayout.add(new Label(commandExp),0,0);
        commandDetailsLayout.add(goingBackButton,1,1);
        commandDetailsLayout.setHgap(10);
        commandDetailsLayout.setVgap(10);

        commandPage = new Scene(commandDetailsLayout,500,200);
        stage.setTitle(commandName);
        stage.setScene(commandPage);
        stage.show();

    }
}
