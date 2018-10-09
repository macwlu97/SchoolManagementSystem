package pl.edziennik.TeacherSource.Controller;

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
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

//import pl.edziennik.ParentSource.Subject;

public class AddGrades extends DriverDB implements Initializable {
//    @FXML
//    private FlowPane flowxa = new FlowPane();
//
    @FXML
    private ChoiceBox choiceKlasa;
    @FXML
    private TableView<PersonGrades> table;
    @FXML
    private TableColumn IDSTUD = new TableColumn();
    @FXML
    private TableColumn UCZEN = new TableColumn();
    @FXML
    private TableColumn OCENA = new TableColumn();

    GetStudents getStudents = new GetStudents();
    GetLessons getLessons = new GetLessons();
    getTableViewValuesGrade getTableViewValues = new getTableViewValuesGrade();
    List<Uczniowie> uczniowies = null;
    ArrayList<Ocena> values = null;
    Przedmioty przedmiot=null;
    List<Zajecia> zajecias = null;
    GetClass getClass = new GetClass();
    public AddGrades() throws SQLException {


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public void setConfig(Przedmioty przedmiot) throws SQLException { // Setting the client-object in ClientViewController
        Locale usersLocale = Locale.getDefault();

        DateFormatSymbols dfs = new DateFormatSymbols(usersLocale);
        String weekdays[] = dfs.getWeekdays();
        this.zajecias = getLessons.Get(przedmiot);
//        this.uczniowies = getStudents.Get(przedmiot);
        this.przedmiot=przedmiot;
//        ObservableList<Object> tmp = FXCollections.observableArrayList();
        for(Zajecia zajecia : zajecias){
            choiceKlasa.getItems().add(new KeyValuePair(zajecia.ID_ZAJ, "Zajęcia: "+ weekdays[zajecia.DZIEN+1] +" godz." + (zajecia.GODZINA+7)+":00"));
        }


        choiceKlasa.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceKlasa.getItems().get((Integer) number2);
                System.out.println(  keyValuePair.getKey());

                int klasa = 0;
                try {
                    klasa = getClass.GetSimple(keyValuePair.getKey());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    uczniowies = getStudents.Get(klasa);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ObservableList<Integer> cursors = FXCollections.observableArrayList(1,2,3,4,5, 6);
                final ObservableList<PersonGrades> data = FXCollections.observableArrayList();
                for(Uczniowie ucz : uczniowies) {
                    PersonGrades tmp =  new PersonGrades(ucz.ID, ucz.IMIE+" "+ucz.NAZWISKO, new ChoiceBox(cursors));
                    ((ChoiceBox) tmp.getOCENA()).getSelectionModel().selectFirst();

                    data.add(tmp);
                }

                IDSTUD.setCellValueFactory(
                        new PropertyValueFactory<PersonGrades, Integer>("IDSTUD"));
                UCZEN.setCellValueFactory(
                        new PropertyValueFactory<PersonGrades, String>("UCZEN"));
                OCENA.setCellValueFactory(
                        new PropertyValueFactory<PersonGrades, Object>("OCENA"));
                table.setEditable(true);
                table.setItems(data);

            }
        });



//        final ObservableList<PersonGrades> data = FXCollections.observableArrayList();
//        for(Uczniowie ucz : uczniowies) {
//           PersonGrades tmp =  new PersonGrades(ucz.ID, ucz.IMIE+" "+ucz.NAZWISKO, new ChoiceBox(cursors));
//            ((ChoiceBox) tmp.getOCENA()).getSelectionModel().selectFirst();
//
//           data.add(tmp);
//        }
//
//                IDSTUD.setCellValueFactory(
//                        new PropertyValueFactory<PersonGrades, Integer>("IDSTUD"));
//                UCZEN.setCellValueFactory(
//                        new PropertyValueFactory<PersonGrades, String>("UCZEN"));
//                OCENA.setCellValueFactory(
//                        new PropertyValueFactory<PersonGrades, Object>("OCENA"));
//                table.setEditable(true);
//                table.setItems(data);
    }


    @FXML public void addGrades(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        values = getTableViewValues.getTableViewValues(table);
        for (Ocena v : values) System.out.println("uczen: " + v.UCZEN + ", ocena:" + v.OCENA);
        try {
            zapytanie = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Ocena v : values) {
            String quest = "INSERT INTO OCENY (UCZNIOWIE_ID, PRZEDMIOTY_ID_PRZEDMIOTU, OCENA) VALUES ('" + v.ID_UCZNIA + "','" + przedmiot.ID_PRZEDMIOTU + "','" + v.OCENA + "')";
            try {
                wynik = zapytanie.executeQuery(quest);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

}
