package pl.edziennik;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    double x,y;
    public void LoginParent(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {

        Parent blah = FXMLLoader.load(getClass().getResource("ParentSource/View/Templates/Login.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();

    }

    public void LoginTeacher(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {

        Parent blah = FXMLLoader.load(getClass().getResource("TeacherSource/View/Templates/Login.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void LoginAdmin(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {


        Parent blah = FXMLLoader.load(getClass().getResource("AdminSource/View/Templates/Login.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void info(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacje");
        alert.setHeaderText("Informacje");
        alert.setContentText("Autor programu: Maciej Wnuk Lipiński");

        alert.showAndWait();
    }

    public void dragged(MouseEvent event){

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    public void pressed(MouseEvent event){

       x = event.getSceneX();
       y = event.getSceneY();

    }
//    @FXML
//    private void closeButtonAction(MouseEvent event){
//        // get a handle to the stage
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        // do what you have to do
//        stage.close();
//
//    }
}
