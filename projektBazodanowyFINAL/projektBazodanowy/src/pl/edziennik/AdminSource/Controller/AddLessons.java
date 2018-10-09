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
import pl.edziennik.AdminSource.Helpers.*;
import pl.edziennik.AdminSource.Model.DriverDB;
import pl.edziennik.AdminSource.Model.GetClass;
import pl.edziennik.AdminSource.Model.GetSubjects;
import pl.edziennik.AdminSource.Model.GetTeacher;
import pl.edziennik.ParentSource.Helpers.Przedmiot;
import pl.edziennik.auth.authUsers;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.util.*;


public class AddLessons extends DriverDB implements Initializable {

    @FXML
    private JFXButton dodaj;
    @FXML
    private ChoiceBox choiceK;
    @FXML
    private ChoiceBox choiceR;
    @FXML
    private ChoiceBox choiceGodz;
    @FXML
    private ChoiceBox choiceDzi;
    //    GetTeacher getTeacher = new GetTeacher();
    GetSubjects getSubjects = new GetSubjects();
    GetClass getClass = new GetClass();
    List<Przedmioty> przedmioties = new ArrayList<>();
    List<Plan> plans = new ArrayList<>();
    List<GClass> classes = new ArrayList<>();
    GetTeacher getTeacher = new GetTeacher();
    authUsers authUser = new authUsers();
    String nauczyciel = null;
    int klasa;
    int przedmiot;
    int wybrane = 0;
    int wybranez = 0;
    int wybraneg = 0;
    int wybraned = 0;

    int godz, dzien;

    public AddLessons() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig() throws SQLException { // Setting the client-object in ClientViewController
        Locale usersLocale = Locale.getDefault();

        DateFormatSymbols dfs = new DateFormatSymbols(usersLocale);
        String weekdays[] = dfs.getWeekdays();

//        Calendar cal = Calendar.getInstance(usersLocale);

//        int firstDayOfWeek = cal.getFirstDayOfWeek();
//        int dayOfWeek;

//        for (dayOfWeek = firstDayOfWeek; dayOfWeek < weekdays.length; dayOfWeek++)
//            System.out.println(weekdays[dayOfWeek]);
//
//        for (dayOfWeek = 0; dayOfWeek < firstDayOfWeek; dayOfWeek++)
//            System.out.println(weekdays[dayOfWeek]);

        dodaj.setDisable(true);
        try {
            przedmioties = getSubjects.GetSimple();
            classes = getClass.Get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Przedmioty przedmiot : przedmioties) {
            nauczyciel = getTeacher.GetSimple(przedmiot.ID_NAUCZYCIELA);
            choiceR.getItems().add(new KeyValuePair(przedmiot.ID_PRZEDMIOTU, przedmiot.NAZWA + " " + nauczyciel));
        }
        for (GClass klasa : classes)
            choiceK.getItems().add(new KeyValuePair(klasa.ID_KLASY, klasa.NAZWA + " " + klasa.ROK_SZKOLNY));
        for (int g = 1; g <= 10; g++)
            choiceGodz.getItems().add(new KeyValuePair(g, Integer.toString(g + 7) + ":00"));
        for (int d = 2; d <= 6; d++)
            choiceDzi.getItems().add(new KeyValuePair(d - 1, weekdays[d]));

        choiceR.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceR.getItems().get((Integer) number2);
                System.out.println(keyValuePair.getKey());

                przedmiot = keyValuePair.getKey();
//                choiceob = keyValuePair.getKey();
//                zapisz.setDisable(false);
                if (wybrane == 0) wybrane++;
                if ((wybrane == 1) && (wybranez == 1)) dodaj.setDisable(false);
            }
        });

        choiceK.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceK.getItems().get((Integer) number2);
                System.out.println(keyValuePair.getKey());

                klasa = keyValuePair.getKey();
                if (wybranez == 0) wybranez++;
                if ((wybrane == 1) && (wybranez == 1)) dodaj.setDisable(false);
//                choiceob = keyValuePair.getKey();
//                zapisz.setDisable(false);
            }
        });

        choiceGodz.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceGodz.getItems().get((Integer) number2);
                System.out.println(keyValuePair.getKey());

                godz = keyValuePair.getKey();
                if (wybraneg == 0) wybraneg++;
                if ((wybrane == 1) && (wybranez == 1) && (wybraneg == 1) && (wybraned == 1)) dodaj.setDisable(false);
//                choiceob = keyValuePair.getKey();
//                zapisz.setDisable(false);
            }
        });

        choiceDzi.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

                KeyValuePair keyValuePair = (KeyValuePair) choiceDzi.getItems().get((Integer) number2);
                System.out.println(keyValuePair.getKey());

                dzien = keyValuePair.getKey();
                if (wybraned == 0) wybraned++;
                if ((wybrane == 1) && (wybranez == 1) && (wybraneg == 1) && (wybraned == 1)) dodaj.setDisable(false);
//                choiceob = keyValuePair.getKey();
//                zapisz.setDisable(false);
            }
        });
    }


    @FXML
    public void addT(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        plans = getSubjects.GetPlan(klasa);
        int nie = 0;
        for (Plan plan : plans) {
            if ((dzien == plan.DZIEN) && (godz == plan.GODZINA)) {
                nie = 1;
            }
        }
//    switch (plan.DZIEN) {
//        case 1:
//        {
//            if((dzien==plan.DZIEN) && (godz==plan.GODZINA));
//        }
//
//        case 2:
//        {ciąg instrukcji dla danego wariantu}
//
//        case 3:
//        {ciąg instrukcji dla danego wariantu}
//
//        case 4:
//        {ciąg instrukcji dla danego wariantu}
//
//        case 5:
//        {ciąg instrukcji dla danego wariantu}
//
//        default:
//        System.out.println("brak dnia?");
//    }


        if (nie == 0) {
            try {
                zapytanie = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String quest = "INSERT INTO PLAN (PRZEDMIOTY_ID_PRZEDMIOTU, KLASA_ID_KLASY, GODZINA, DZIEN) VALUES ('" + przedmiot + "','" + klasa + "','" + godz + "','" + dzien + "')";
            try {
                wynik = zapytanie.executeQuery(quest);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Dodano zajęcia!");
            alert.setContentText("Pomyślnie dodano");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Nie powiodło się!");
            alert.setContentText("W tym czasie już są zajęcia...");

            alert.showAndWait();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }




}
