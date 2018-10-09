package pl.edziennik.AdminSource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent fxml = FXMLLoader.load(getClass().getResource("View/Templates/Parent.fxml"));

        Scene scene = new Scene(fxml);

        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();



    }




    public static void main(String... args){
        launch(args);

    }
}
