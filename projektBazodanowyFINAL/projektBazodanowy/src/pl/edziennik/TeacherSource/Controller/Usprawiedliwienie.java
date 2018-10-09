package pl.edziennik.TeacherSource.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edziennik.TeacherSource.Helpers.*;
import pl.edziennik.TeacherSource.Model.*;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Usprawiedliwienie extends DriverDB implements Initializable {
//    @FXML
//    private FlowPane flowxa = new FlowPane();
//
    @FXML
    private ChoiceBox choiceL;
    @FXML
    private JFXButton zapisz;
      GetAttendance getAttendance = new GetAttendance();
    List<Obecnosc> nieobs =  new ArrayList<>();
    int choiceob;
    public Usprawiedliwienie() throws SQLException {


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public void setConfig(Uczniowie uczen) throws SQLException { // Setting the client-object in ClientViewController
        zapisz.setDisable(true);
                try {
                    nieobs = getAttendance.Get(uczen);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        for(Obecnosc nieob :nieobs)
            choiceL.getItems().add(new KeyValuePair(nieob.ID, "Zajęcia: " + nieob.NAZWA + " Data: "+nieob.DATA));
//                for(Obecnosc nieob :nieobs) System.out.println(nieob.ID+" "+ nieob.STAN+" "+nieob.NAZWA);
        choiceL.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceL.getItems().get((Integer) number2);
                System.out.println(  keyValuePair.getKey());


                choiceob = keyValuePair.getKey();
                zapisz.setDisable(false);
            }
        });
    }


    @FXML public void addAtt(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        try {
            zapytanie = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String quest = "UPDATE OBECNOSC SET STAN=1 WHERE ID_OBECNOSCI = '"+choiceob+"'";
        try {
            wynik = zapytanie.executeQuery(quest);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
