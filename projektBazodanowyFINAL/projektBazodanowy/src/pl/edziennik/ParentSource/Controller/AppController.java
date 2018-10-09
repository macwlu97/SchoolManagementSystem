package pl.edziennik.ParentSource.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//import pl.edziennik.TeacherSource.Helpers.Informacje;
import jfxtras.scene.control.agenda.Agenda;
import pl.edziennik.ParentSource.Helpers.Informacje;
import pl.edziennik.ParentSource.Helpers.KeyValuePair;
import pl.edziennik.ParentSource.Helpers.Uczniowie;
import pl.edziennik.ParentSource.Model.DriverDB;
//import pl.edziennik.Pa.Model.GetInformation;
import pl.edziennik.ParentSource.Model.GetChild;
import pl.edziennik.ParentSource.Model.GetInformation;
import pl.edziennik.User.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppController implements Initializable {
    @FXML
    private Label nameuser;
    @FXML
    private ComboBox choicechild;
    User user = new User();
    DriverDB log = new DriverDB();
    GetInformation getInformation = new GetInformation();
    Informacje informacje = new Informacje();
    GetChild getChild = new GetChild();
    List<Uczniowie> Child = new ArrayList<>();
    int SelectedChildID;
    Uczniowie uczklasa;

    public AppController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    double x,y;
    public void setConfig(User user, DriverDB log) throws SQLException { // Setting the client-object in ClientViewController
        this.log = log;
        this.user = log.getParent(user.login);
        try {

            informacje = getInformation.Get(user.login);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Child = getChild.Get(informacje.getID());
        int c=0;
        for(Uczniowie uczen :Child) {
            if(c==0){ SelectedChildID = uczen.getID(); c=1;    uczklasa = getChild.Getu(SelectedChildID); }
            choicechild.getItems().add(new KeyValuePair(uczen.getID(), uczen.getIMIE() + " " + uczen.getNAZWISKO()));

        }
        choicechild.getSelectionModel().selectFirst();


        choicechild.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choicechild.getItems().get((Integer) number2);
                System.out.println(  keyValuePair.getKey());


                SelectedChildID
                        = keyValuePair.getKey();
                try {
                    uczklasa = getChild.Getu(SelectedChildID);
                    System.out.println(uczklasa.getID_KLASY());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
public void setName() { // Setting the client-object in ClientViewController
    this.nameuser.setText("Witaj, "+user.imie+"!");
}

@FXML public void informacje(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
    FXMLLoader Loader = new FXMLLoader();
    Loader.setLocation(getClass().getResource("../View/Templates/Informacje.fxml"));
    try {
        Loader.load();
    } catch (IOException ex){
        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
    }
    InformacjeController display = Loader.getController();
    display.setConfig(SelectedChildID);
    Parent blah = Loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Informacje o dziecku");
                stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(blah));
                stage.setResizable(false);
                stage.show();
}


    @FXML public void oceny(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/Oceny.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        OcenyController display = Loader.getController();
        display.setConfig(SelectedChildID);
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Oceny ucznia");
        stage.getIcons().add(new Image("icon.png"));
        stage.setScene(new Scene(blah));
        stage.setResizable(false);
        stage.show();
    }
    @FXML public void plan(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
//        FXMLLoader Loader = new FXMLLoader();
//        Loader.setLocation(getClass().getResource("../View/Templates/Plan.fxml"));
//        try {
//            Loader.load();
//        } catch (IOException ex){
//            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        PlanController display = Loader.getController();
//        display.setConfig(SelectedChildID);
//        Parent blah = Loader.getRoot();
//        Stage stage = new Stage();
//        stage.setTitle("Plan ucznia");
//        stage.getIcons().add(new Image("icon.png"));
//        stage.setScene(new Scene(blah));
//        stage.show();

//                Stage stage = new Stage();
//        stage.setTitle("Plan ucznia");
//        stage.getIcons().add(new Image("icon.png"));
//
//        // create Agenda
//        Agenda agenda = new Agenda();
//
//        // add an appointment
//        agenda.appointments().addAll(
//                new Agenda.AppointmentImplLocal()
//                        .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
//                        .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
//                        .withDescription("It's time")
//                        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
//        );
//
//        // show it
//        stage.setScene(new Scene(agenda, 800, 600));
//        stage.show();
        PlanSwitch planSwitch = new PlanSwitch(SelectedChildID);
    }
    @FXML public void obecnosci(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
//        ObecnoscController obecnoscController = new ObecnoscController(SelectedChildID);
        ObecnoscControllerS App = new ObecnoscControllerS(log, SelectedChildID, uczklasa);
        App.setIconImage(Toolkit.getDefaultToolkit().getImage("src/icon.png"));
//        App.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        App.setResizable(false);
        App.setVisible(true);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        SwingUtilities.updateComponentTreeUI(App);

    }
    @FXML public void uwagi(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("../View/Templates/Uwagi.fxml"));
        try {
            Loader.load();
        } catch (IOException ex){
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        UwagiController display = Loader.getController();
        display.setConfig(SelectedChildID);
        Parent blah = Loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Uwagi ucznia");
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
