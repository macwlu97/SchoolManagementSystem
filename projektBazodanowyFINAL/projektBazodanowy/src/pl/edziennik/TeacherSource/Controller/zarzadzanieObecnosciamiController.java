package pl.edziennik.TeacherSource.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import pl.edziennik.TeacherSource.Helpers.*;
import pl.edziennik.TeacherSource.Model.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class zarzadzanieObecnosciamiController extends DriverDB implements Initializable {
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn IDSTUD = new TableColumn();
    @FXML
    private TableColumn UCZEN = new TableColumn();
    @FXML
    private TableColumn OBECNOSC = new TableColumn();
    @FXML
    private DatePicker datep = new DatePicker();
    @FXML
    private ChoiceBox przedmiotbox = new ChoiceBox();
    @FXML
    private ChoiceBox klasabox = new ChoiceBox();
    @FXML
    private ChoiceBox zajeciabox = new ChoiceBox();
    @FXML
    private Button zapiszok = new Button();
    ArrayList<Obecnosc> values = null;
    public zarzadzanieObecnosciamiController() throws SQLException {
    }
//
    GetSubjects getSubjects = new GetSubjects();
    List<Przedmioty> przedmioties = new ArrayList<>();
    Przedmioty przedmiottmp = null;
    int przedmiotID;

    GetClass getClass = new GetClass();
    List<Klasa> klasy = new ArrayList<>();
    int klasaID;

    GetLessons getLessons = new GetLessons();
    List<Zajecia> zajecia = new ArrayList<>();
    Zajecia zajeciatmp;
    int zajeciaID;

    GetStudents getStudents = new GetStudents();
    List<Uczniowie> uczniowies = null;

    GetAttendance getAttendance = new GetAttendance();
    Obecnosc obecnosctmp;
    int wybrane = 0;
    int wybranezaj = 0;
    int wybranedate = 0;
    int wybraneok = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        for(int i=1; i<10; i++){
//            Button btn = new Button();
//            btn.setMinWidth(100);
//            btn.setMinHeight(100);
//            btn.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
//            btn.setText("Button" + i);
//            btn.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    System.out.println(btn.getText()); //Or "1" as in your code
//                }
//            });
//            flowx.getChildren().add(btn); //add button to your VBox
//        }


    }

    public void setConfig(Informacje informacje) throws SQLException {
        przedmioties = getSubjects.Get(informacje);
        klasy = getClass.GetClass(informacje);

        Locale usersLocale = Locale.getDefault();

        DateFormatSymbols dfs = new DateFormatSymbols(usersLocale);
        String weekdays[] = dfs.getWeekdays();

        for (Przedmioty przedmiot : przedmioties) {
//            nauczyciel = getTeacher.GetSimple(przedmiot.ID_NAUCZYCIELA);
            przedmiotbox.getItems().add(new KeyValuePair(przedmiot.ID_PRZEDMIOTU, przedmiot.NAZWA));
        }

        klasabox.setDisable(true);
        zajeciabox.setDisable(true);
        datep.setDisable(true);
        zapiszok.setDisable(true);

        przedmiotbox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                wybrane =0;
                KeyValuePair keyValuePair = (KeyValuePair) przedmiotbox.getItems().get((Integer) number2);
//                System.out.println(keyValuePair.getKey());

                przedmiotID = keyValuePair.getKey();

//                choiceob = keyValuePair.getKey();
//                zapisz.setDisable(false);
//                if (wybrane == 0) wybrane++;
//                if (wybrane == 1) klasabox.setDisable(false);
                klasabox.getItems().removeAll(klasabox.getItems());
                klasabox.setDisable(false);

//                klasabox.getSelectionModel().select(1);
                zajeciabox.setDisable(true);
                datep.setDisable(true);
                zapiszok.setDisable(true);
                datep.setValue(null);
                for (Klasa klasa : klasy) {
                    klasabox.getItems().add(new KeyValuePair(klasa.ID_KLASY, klasa.NAZWA + " " + klasa.ROK_SZKOLNY));
                }
//                System.out.println(przedmiotID);
                wybrane = 1;
            }
        });

        klasabox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if (wybrane == 1) {
                    wybranezaj=0;
                    KeyValuePair keyValuePair = (KeyValuePair) klasabox.getItems().get((Integer) number2);
//                System.out.println(keyValuePair.getKey());

                    klasaID = keyValuePair.getKey();

                    zajeciabox.getItems().removeAll(zajeciabox.getItems());
                    zajeciabox.setDisable(false);
//                zajeciabox.getSelectionModel().select(1);
                    datep.setDisable(true);
                    zapiszok.setDisable(true);
                    datep.setValue(null);
                    try {
                        zajecia = getLessons.GetA(przedmiotID, informacje.ID, klasaID);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    for (Zajecia zaj : zajecia) {
                        zajeciabox.getItems().add(new KeyValuePair(zaj.ID_ZAJ, "Zajęcia: " + weekdays[zaj.DZIEN + 1] + ", godz. " + (zaj.GODZINA + 7) + ":00 "));
                    }
//                System.out.println(przedmiotID);
                    wybranezaj=1;
                }
            }
        });


        zajeciabox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                if (wybranezaj == 1) {
                    KeyValuePair keyValuePair = (KeyValuePair) zajeciabox.getItems().get((Integer) number2);
//                System.out.println(keyValuePair.getKey());

                    zajeciaID = keyValuePair.getKey();
//                if (wybranedate == 0) wybranedate++;
//                if (wybranedate == 1) datep.setDisable(false);
                    datep.setDisable(false);
                    zapiszok.setDisable(true);
                    datep.setValue(null);
                    try {
                        zajeciatmp = getLessons.GetSimple(zajeciaID);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
                        @Override
                        public DateCell call(final DatePicker datePicker) {
                            return new DateCell() {
                                @Override
                                public void updateItem(LocalDate item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if(zajeciatmp.DZIEN == 1) {
                                        setDisable(empty || item.getDayOfWeek() != DayOfWeek.MONDAY);
                                    }
                                    if(zajeciatmp.DZIEN == 2) {
                                        setDisable(empty || item.getDayOfWeek() != DayOfWeek.TUESDAY);
                                    }
                                    if(zajeciatmp.DZIEN == 3) {
                                        setDisable(empty || item.getDayOfWeek() != DayOfWeek.WEDNESDAY);
                                    }
                                    if(zajeciatmp.DZIEN == 4) {
                                        setDisable(empty || item.getDayOfWeek() != DayOfWeek.THURSDAY);
                                    }
                                    if(zajeciatmp.DZIEN == 5) {
                                        setDisable(empty || item.getDayOfWeek() != DayOfWeek.FRIDAY);
                                    }
//                        if (item.isBefore(datep.getValue().plusDays(1))) {
//                            setDisable(true);
////                            setStyle("-fx-background-color: #EEEEEE;");
//                        }
                                }
                            };
                        }
                    };
                    datep.setDayCellFactory(dayCellFactory);
                }
            }
        });



        datep.setOnAction(event -> {
            if(datep.getValue() != null){
            zapiszok.setDisable(false);
            LocalDate a = datep.getValue();
            Date date = java.sql.Date.valueOf(a);
            System.out.println("Selected date: " + date);

            try {
                uczniowies = getStudents.Get(klasaID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            final ObservableList<Person> data = FXCollections.observableArrayList();
            for(Uczniowie ucz : uczniowies) {
                try {
                    obecnosctmp = getAttendance.GetSimple(ucz.ID, date, zajeciaID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                CheckBox check = new CheckBox();
                if(obecnosctmp != null) {
                    if ((obecnosctmp.STAN).equals(0)) check.setSelected(false);
                    if ((obecnosctmp.STAN).equals(1)) check.setSelected(true);
                }
                Person tmp =  new Person(String.valueOf(ucz.ID), ucz.IMIE+" "+ucz.NAZWISKO, check);
                data.add(tmp);
            }
            //uczen.
            IDSTUD.setCellValueFactory(
                    new PropertyValueFactory<Person, String>("IDSTUD"));
            UCZEN.setCellValueFactory(
                    new PropertyValueFactory<Person, String>("UCZEN"));
            OBECNOSC.setCellValueFactory(
                    new PropertyValueFactory<Person, Object>("OBECNOSC"));
            table.setEditable(true);
            table.setItems(data);
            }
        });



    }


    @FXML public void addAtt(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        values = getTableViewValues.getTableViewValues(table);
        ZoneId zonedId = ZoneId.of( "Europe/Warsaw" );
        LocalDate a = datep.getValue();
        Date date = java.sql.Date.valueOf(a);
        try {
            zapytanie = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getAttendance.Delete(date, zajeciaID);
        for(Obecnosc v : values){
            String quest = "INSERT INTO OBECNOSC (UCZNIOWIE_ID, STAN, PLAN_ID_ZAJ, DATA) VALUES ('"+v.ID+"','"+v.STAN+"','"+zajeciaID+"','"+date+"')";
            try {
                wynik = zapytanie.executeQuery(quest);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println(v.ID + " "+ v.STAN + v.NAZWA);

        }


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
