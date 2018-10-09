package pl.edziennik.TeacherSource.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;
import pl.edziennik.TeacherSource.Helpers.Zajecia;
import pl.edziennik.TeacherSource.Model.DriverDB;
import pl.edziennik.TeacherSource.Model.GetInformation;
import pl.edziennik.User.User;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddOneGrade extends DriverDB implements Initializable {
    @FXML
    private JFXButton zapisz;
    @FXML
    private FlowPane flowxa = new FlowPane();

    @FXML
    private ChoiceBox choiceocene;

    @FXML
    private Label labelocen;
    Uczniowie uczen;
    Przedmioty przedmiot;
    Zajecia zaj;
    public AddOneGrade() throws SQLException {


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public void setConfig(Uczniowie uczen, Zajecia zaj) throws SQLException { // Setting the client-object in ClientViewController
        zapisz.setDisable(true);
        choiceocene.getItems().add("1");
        choiceocene.getItems().add("2");
        choiceocene.getItems().add("3");
        choiceocene.getItems().add("4");
        choiceocene.getItems().add("5");
        choiceocene.getItems().add("6");
        this.uczen = uczen;
        this.zaj = zaj;
        labelocen.setText("Wstaw ocenę osobie: "+uczen.IMIE+" "+ uczen.NAZWISKO);
        choiceocene.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                zapisz.setDisable(false);
            }
        });
    }

    @FXML public void addGrade(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        int value = Integer.parseInt((String) choiceocene.getValue());
        System.out.println(value);
                        try {
                    zapytanie = conn.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String quest = "INSERT INTO OCENY (UCZNIOWIE_ID, PRZEDMIOTY_ID_PRZEDMIOTU, OCENA) VALUES ('"+uczen.ID+"','"+zaj.ID_PRZEDMIOTU+"','"+value+"')";
                try {
                    wynik = zapytanie.executeQuery(quest);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
