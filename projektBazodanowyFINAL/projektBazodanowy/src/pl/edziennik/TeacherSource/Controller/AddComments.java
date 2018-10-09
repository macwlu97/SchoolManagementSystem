package pl.edziennik.TeacherSource.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;
import pl.edziennik.TeacherSource.Model.DriverDB;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddComments extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowxa = new FlowPane();

    @FXML
    private JFXTextField uwaga = new JFXTextField();

    @FXML
    private Label labeluwaga;
    Uczniowie uczen;
    int informacje;
    public AddComments() throws SQLException {


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public void setConfig(Uczniowie uczen, int informacje) throws SQLException { // Setting the client-object in ClientViewController
        this.uczen = uczen;
        this.informacje = informacje;
        labeluwaga.setText("Wstaw uwagę osobie: "+uczen.IMIE+" "+ uczen.NAZWISKO);
    }

    @FXML public void addComm(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        String  value = uwaga.getText();
        ZoneId zonedId = ZoneId.of( "Europe/Warsaw" );
        LocalDate a = LocalDate.now(zonedId);
      Date date = java.sql.Date.valueOf(a);
        System.out.println(a);
                        try {
                    zapytanie = conn.createStatement();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        if (value != null && !value.isEmpty()) {
            String quest = "INSERT INTO UWAGI (UCZNIOWIE_ID, OPIS, DATA, NAUCZYCIELE_ID_NAUCZYCIELA) VALUES ('"+uczen.ID+"','"+value+"','"+date+"', '"+informacje+"')";
            try {
                wynik = zapytanie.executeQuery(quest);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
