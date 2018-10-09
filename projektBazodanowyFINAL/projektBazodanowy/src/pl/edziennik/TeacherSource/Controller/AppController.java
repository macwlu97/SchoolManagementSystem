package pl.edziennik.TeacherSource.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Model.DriverDB;
import pl.edziennik.TeacherSource.Model.GetInformation;
import pl.edziennik.User.User;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppController implements Initializable {
    @FXML
    private Label nameuser;
    User user = new User();
    DriverDB log = new DriverDB();
    GetInformation getInformation = new GetInformation();
    Informacje informacje = new Informacje();

    public AppController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    double x,y;
    public void setConfig(User user, DriverDB log) throws SQLException { // Setting the client-object in ClientViewController
        this.log = log;
        this.user = log.getTeacher(user.login);
        try {

            informacje = getInformation.Get(user.login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
     //   System.out.println(informacje.EMAIL);

    }
public void setName() { // Setting the client-object in ClientViewController
    this.nameuser.setText("Witaj, "+user.imie+"!");
}

    @FXML public void obecnosci(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/zarzadzanieObecnosciami.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        zarzadzanieObecnosciamiController display = Loader.getController();
        display.setConfig(informacje);
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Zarządzanie obecnościami");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();
    }


@FXML public void mojeprzedmioty(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
    FXMLLoader Loader = new FXMLLoader();
    Loader.setLocation(getClass().getResource("../View/Templates/MySubjects.fxml"));
    try {
        Loader.load();
    } catch (IOException ex){
        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
    }
    MySubjectsController display = Loader.getController();
    display.setConfig(informacje);
    Parent blah = Loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("E-Dziennik - Moje przedmioty");
                stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(blah));
                stage.setResizable(false);
                stage.show();
}


    @FXML public void mojaklasa(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/MyClass.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        MyClassController display = Loader.getController();
        display.setConfig(informacje);
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("E-Dziennik - Moja klasa");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();
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
    public void wyjscie(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void powrot(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("/app.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
