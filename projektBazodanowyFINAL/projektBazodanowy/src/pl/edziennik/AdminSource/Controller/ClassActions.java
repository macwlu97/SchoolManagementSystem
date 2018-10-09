package pl.edziennik.AdminSource.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pl.edziennik.AdminSource.Helpers.GClass;
import pl.edziennik.AdminSource.Helpers.Przedmioty;
import pl.edziennik.AdminSource.Model.DriverDB;
import pl.edziennik.AdminSource.Model.GetClass;
import pl.edziennik.AdminSource.Model.GetSubjects;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassActions extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();


    public ClassActions() throws SQLException {
    }

    GetClass getClass = new GetClass();
  List<GClass> klasy = new ArrayList<>();
  GClass tmpklasa = null;

  ///////////

    GetSubjects getSubjects = new GetSubjects();
    List<Przedmioty> przedmioties = new ArrayList<>();


    ////////
    //    Przedmioty przedmiottmp = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig() throws SQLException {
        klasy = getClass.Get();
//        ContextMenu contextMenu = new ContextMenu();

//        MenuItem item1x = new MenuItem("Stwórz plan");
//        item1x.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    zapytanie = conn.createStatement();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                String quest=null;
//                 quest = "DELETE FROM TMP_OBECNOSCI WHERE ID IN (SELECT ID FROM TMP_OBECNOSCI INNER JOIN PLAN ON TMP_OBECNOSCI.PLAN_ID_ZAJ=PLAN.ID_ZAJ WHERE KLASA_ID_KLASY = '"+tmpklasa.ID_KLASY+"')";
//                try {
//                    wynik = zapytanie.executeQuery(quest);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }//'"+tmpklasa.ID_KLASY"'
//                try {
//                    zapytanie = conn.createStatement();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                 quest = "DELETE FROM OBECNOSC WHERE ID_OBECNOSCI IN (SELECT ID_OBECNOSCI FROM OBECNOSC INNER JOIN PLAN ON PLAN.ID_ZAJ=OBECNOSC.PLAN_ID_ZAJ WHERE PLAN.KLASA_ID_KLASY='"+tmpklasa.ID_KLASY+"')";
//                try {
//                    wynik = zapytanie.executeQuery(quest);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }//'"+tmpklasa.ID_KLASY"'
//
//
//                try {
//                    zapytanie = conn.createStatement();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                 quest = "DELETE FROM PLAN WHERE KLASA_ID_KLASY = '"+tmpklasa.ID_KLASY+"'";
//                try {
//                    wynik = zapytanie.executeQuery(quest);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }//'"+tmpklasa.ID_KLASY"'
//
//                try {
//                    przedmioties = getSubjects.Get(tmpklasa);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                int x=0;
//                int hour = 8;
//                int day = 1;
//                for(Przedmioty przedmiot :przedmioties){
//                    System.out.println(przedmiot.ID_PRZEDMIOTU + " "+ przedmiot.NAZWA);
//
//                    x++;
//                    if(hour == 14){
//                        hour=8;
//                        day++;
//                    }
//                    if(day == 6) break;
//
//                    if(x<31) {
//                        try {
//                            zapytanie = conn.createStatement();
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                        quest = "INSERT INTO PLAN (ID_PRZEDMIOTU, GODZINA, DZIEN, KLASA_ID_KLASY) VALUES ('" + przedmiot.ID_PRZEDMIOTU + "','" + hour + "','" + day + "','" + tmpklasa.ID_KLASY + "')";
//                        try {
//                            wynik = zapytanie.executeQuery(quest);
//                        } catch (SQLException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        Alert alert = new Alert(Alert.AlertType.WARNING);
//                        alert.setTitle("Uwaga!");
//                        alert.setHeaderText("Informacja:");
//                        alert.setContentText("Zbyt dużo przedmiotów wygenerowano dla max 30 zajęć");
//                    }
//                    hour++;
//
//
//
//
//
//
//                }
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Uwaga!");
//                alert.setHeaderText("Informacja:");
//                alert.setContentText("Wygenerowano plan");
//
//                alert.showAndWait();
//            }
//        });
//        contextMenu.getItems().addAll(item1x);
        for (GClass klasa : klasy) {
            Button btn = new Button();
            btn.setMinWidth(100);
            btn.setMinHeight(100);
            btn.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
            btn.setText(klasa.NAZWA + " " + klasa.ROK_SZKOLNY);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
//                    System.out.println(btn.getText()); //Or "1" as in your code
//                    FXMLLoader Loader = new FXMLLoader();
//                    Loader.setLocation(getClass().getResource("../View/Templates/StudentActions.fxml"));
//                    try {
//                        Loader.load();
//                    } catch (IOException ex){
//                        Logger.getLogger(MySubjectsController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    StudentActions display = Loader.getController();
//                    try {
//                        display.setConfig(uczen);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                    Parent blah = Loader.getRoot();
//                    Stage stage = new Stage();
//                    stage.setTitle("E-Dziennik - Uczeń: "+uczen.IMIE+" "+uczen.NAZWISKO);
//                    stage.getIcons().add(new Image("icon.png"));
//                    stage.setScene(new Scene(blah));
//                    stage.showAndWait();

                }
            });
            btn.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

                @Override
                public void handle(ContextMenuEvent event) {
//                    tmp = uczniowie;
//                    przedmiottmp = przedmiot;
                    tmpklasa = klasa;
//                    contextMenu.show(btn, event.getScreenX(), event.getScreenY());
                }
            });
            flowx.getChildren().add(btn); //add button to your VBox
        }

    }
}


