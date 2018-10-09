package pl.edziennik.AdminSource.Controller;

import com.jfoenix.controls.JFXButton;
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
import pl.edziennik.AdminSource.Helpers.Teacher;
import pl.edziennik.AdminSource.Model.DriverDB;
import pl.edziennik.AdminSource.Model.GetClass;
import pl.edziennik.AdminSource.Model.GetTeacher;
import pl.edziennik.auth.authUsers;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ClassCreator extends DriverDB implements Initializable {
    @FXML
    private JFXTextField nazwak;
    @FXML
    private JFXTextField rokszko;
    @FXML
    private JFXButton zapisz;
    @FXML
    private ChoiceBox choiceR;
    GetTeacher getTeacher = new GetTeacher();
    List<Teacher> teachers =  new ArrayList<>();
    authUsers authUser = new authUsers();
    int teacher;


    public ClassCreator() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig() throws SQLException { // Setting the client-object in ClientViewController
        zapisz.setDisable(true);
        try {
            teachers = getTeacher.Get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Teacher teacher :teachers)
            choiceR.getItems().add(new KeyValuePair(teacher.ID, teacher.IMIE+ " " +teacher.NAZWISKO));


        choiceR.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceR.getItems().get((Integer) number2);
                System.out.println(  keyValuePair.getKey());

                teacher = keyValuePair.getKey();
//                choiceob = keyValuePair.getKey();
                zapisz.setDisable(false);
            }
        });


    }



    @FXML
    public void addT(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        if ((nazwak.getText() != null && !nazwak.getText().isEmpty()) && (rokszko.getText() != null && !rokszko.getText().isEmpty())) {

            try {
                zapytanie = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String quest = "INSERT INTO Klasa (NAZWA, ROK_SZKOLNY, NAUCZYCIELE_ID_NAUCZYCIELA) VALUES ('" + nazwak.getText() + "','" + rokszko.getText() + "','" + teacher + "')";
            try {
                wynik = zapytanie.executeQuery(quest);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Utworzono klasę!");
            alert.setContentText("Pomyślne utworzono: " + nazwak.getText() + " " + rokszko.getText());

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
