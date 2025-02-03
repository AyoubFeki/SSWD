import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage){
        LandingPage landing = new LandingPage(primaryStage);
        landing.renderPage();
    }
    public static void main(String[] args){
        launch(args);
    }
}
