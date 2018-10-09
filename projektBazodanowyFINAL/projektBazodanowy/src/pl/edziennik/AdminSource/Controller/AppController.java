package pl.edziennik.AdminSource.Controller;

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
//import pl.edziennik.TeacherSource.Controller.MyClassController;
//import pl.edziennik.TeacherSource.Controller.MySubjectsController;
//import pl.edziennik.TeacherSource.Helpers.Informacje;
//import pl.edziennik.TeacherSource.Model.DriverDB;
//import pl.edziennik.TeacherSource.Model.GetInformation;
import pl.edziennik.User.User;

import javax.swing.*;
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
//    DriverDB log = new DriverDB();
//    GetInformation getInformation = new GetInformation();
//    Informacje informacje = new Informacje();

    public AppController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    double x,y;
    public void setConfig(User user) throws SQLException { // Setting the client-object in ClientViewController
    this.user = user;
    }

    public void setName() { // Setting the client-object in ClientViewController
        this.nameuser.setText("Panel Administatora");
    }

    @FXML
    public void utowrzklase(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
//zarzadzanie klasa -> utworzklase, lista klas(drugi klawisz myszy, akcja->stworzplan);
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/AddClass.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddClass display = Loader.getController();
        display.setConfig();
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("E-Dziennik - Dodaj nauczyciela");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    public void dodajucznia(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/AddStudent.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddStudent display = Loader.getController();
        display.setConfig();
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Dodaj ucznia");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();

    }
    @FXML
    public void dodajnauczyciela(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/AddTeacher.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddTeacher display = Loader.getController();
        display.setConfig();
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Dodaj nauczyciela");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();

    }
    @FXML
    public void dodajrodzica(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/AddParent.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddParent display = Loader.getController();
        display.setConfig();
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Dodaj rodzica");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();

    }
    @FXML
    public void dodajprzedmiot(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/AddSubject.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddSubject display = Loader.getController();
        display.setConfig();
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("E-Dziennik - Dodaj nauczyciela");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    public void Opcje(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/Options.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Options display = Loader.getController();
        display.setConfig();
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Opcje");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();

    }
    @FXML
    public void dodajzaj(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/AddLessons.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AddLessons display = Loader.getController();
        display.setConfig();
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Dodaj zajęcia");
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
