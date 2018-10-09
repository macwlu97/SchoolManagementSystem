package pl.edziennik.AdminSource.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edziennik.AdminSource.Helpers.GClass;
import pl.edziennik.AdminSource.Helpers.KeyValuePair;
import pl.edziennik.AdminSource.Helpers.PasswordGenerator;
import pl.edziennik.AdminSource.Helpers.Rodzice;
import pl.edziennik.AdminSource.Model.DriverDB;
import pl.edziennik.AdminSource.Model.GetClass;
import pl.edziennik.AdminSource.Model.GetParent;
import pl.edziennik.auth.authUsers;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AddStudent extends DriverDB implements Initializable {
    @FXML
    private JFXTextField imies;
    @FXML
    private JFXTextField nazwiskos;
    @FXML
    private JFXTextField logins;
    @FXML
    private JFXTextField avatars;
    @FXML
    private JFXDatePicker calendar;
    @FXML
    private ChoiceBox choiceK;
    @FXML
    private ChoiceBox choiceR;
    @FXML
    private JFXButton dodaj;
    int wybrane=0;
    int wybranez=0;
    GetParent getParent = new GetParent();
    GetClass getClass = new GetClass();
    List<Rodzice> rodzices =  new ArrayList<>();
    List<GClass> classes =  new ArrayList<>();
    authUsers authUser = new authUsers();
    int klasa;
    int rodzic;
    PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
            .useDigits(true)
            .useLower(true)
            .useUpper(true)
            .build();

    public AddStudent() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig() throws SQLException { // Setting the client-object in ClientViewController
        dodaj.setDisable(true);
        try {
            rodzices = getParent.Get();
            classes = getClass.Get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Rodzice rodzic :rodzices)
            choiceR.getItems().add(new KeyValuePair(rodzic.ID, rodzic.IMIE+ " " +rodzic.NAZWISKO));

        for(GClass klasa :classes)
            choiceK.getItems().add(new KeyValuePair(klasa.ID_KLASY, klasa.NAZWA + " " + klasa.ROK_SZKOLNY));

        choiceR.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceR.getItems().get((Integer) number2);
                System.out.println(  keyValuePair.getKey());

                rodzic = keyValuePair.getKey();
//                choiceob = keyValuePair.getKey();
//                zapisz.setDisable(false);
                if(wybrane==0) wybrane++;
                if((wybrane==1) && (wybranez==1)) dodaj.setDisable(false);
            }
        });

        choiceK.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceK.getItems().get((Integer) number2);
                System.out.println(  keyValuePair.getKey());

                klasa = keyValuePair.getKey();

//                choiceob = keyValuePair.getKey();
//                zapisz.setDisable(false);
                if(wybranez==0) wybranez++;
                if((wybrane==1) && (wybranez==1)) dodaj.setDisable(false);
            }
        });
    }



    @FXML
    public void addT(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        if ((imies.getText() != null && !imies.getText().isEmpty()) && (nazwiskos.getText() != null && !nazwiskos.getText().isEmpty()) && (logins.getText() != null && !logins.getText().isEmpty()) && (avatars.getText() != null && !avatars.getText().isEmpty())) {


        String password = passwordGenerator.generate(8); // output ex.: lrU12fmM 75iwI90o
        System.out.println(password);
        String hash = authUser.get_SHA_512_SecurePassword(password, "projekt");
        System.out.println(hash);

        System.out.println(calendar.getValue());

        try {
            zapytanie = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            String quest = "INSERT INTO Uczniowie (IMIE, NAZWISKO, LOGIN, PASSWORD, AVATAR, DATA_URODZENIA, KLASA_ID_KLASY) VALUES ('"+imies.getText()+"', '"+nazwiskos.getText()+"','"+logins.getText()+"','"+hash+"','"+avatars.getText()+"','"+calendar.getValue()+"','"+klasa+"')";

//        String quest = "INSERT INTO Uczniowie (IMIE, NAZWISKO, LOGIN, PASSWORD, AVATAR, DATA_URODZENIA, RODZICE_ID, KLASA_ID_KLASY) VALUES ('"+imies.getText()+"', '"+nazwiskos.getText()+"','"+logins.getText()+"','"+hash+"','"+avatars.getText()+"','"+calendar.getValue()+"','"+rodzic+"','"+klasa+"')";
        try {
            wynik = zapytanie.executeQuery(quest);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /////////
             quest = "INSERT INTO rodziceuczniowie (RODZICE_ID, UCZNIOWIE_ID) VALUES ('"+rodzic+"',UCZSEQ.currval)";

//        String quest = "INSERT INTO Uczniowie (IMIE, NAZWISKO, LOGIN, PASSWORD, AVATAR, DATA_URODZENIA, RODZICE_ID, KLASA_ID_KLASY) VALUES ('"+imies.getText()+"', '"+nazwiskos.getText()+"','"+logins.getText()+"','"+hash+"','"+avatars.getText()+"','"+calendar.getValue()+"','"+rodzic+"','"+klasa+"')";
            try {
                wynik = zapytanie.executeQuery(quest);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //////////
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Zapisz to hasło, brak możliwości przypomnienia!");
        alert.setContentText("login: " + logins.getText() +"\nhasło: " + password);

        alert.showAndWait();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Uwaga");
            errorAlert.setContentText("Nie może być puste!");
            errorAlert.showAndWait();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



}
