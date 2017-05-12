package AgentProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static MainWindow myWin;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        myWin = new MainWindow(primaryStage);
        myWin.nextScene(new welcomeController());

    }


    public static void main(String[] args) {
        launch(args);
    }
}
