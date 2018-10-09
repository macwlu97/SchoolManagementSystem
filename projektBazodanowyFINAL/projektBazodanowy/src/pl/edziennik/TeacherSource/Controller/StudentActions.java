package pl.edziennik.TeacherSource.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Obecnosc;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;
import pl.edziennik.TeacherSource.Model.DriverDB;
import pl.edziennik.TeacherSource.Model.GetAttendance;
import pl.edziennik.TeacherSource.Model.GetClass;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentActions extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();
    @FXML
    private Label info = new Label();
  //  GetAttendance getAttendance = new GetAttendance();
//    List<Obecnosc> nieobs =  new ArrayList<>();

    public StudentActions() throws SQLException {
    }



    //    Przedmioty przedmiottmp = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig(Uczniowie uczen, Informacje informacje) throws SQLException {
    info.setText("Uczeń: "+uczen.IMIE+ " " + uczen.NAZWISKO);
        Button btn = new Button();
        btn.setMinWidth(100);
        btn.setMinHeight(100);
        btn.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
        btn.setText("Usprawiedliwij nieobecności");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                try {
//                    nieobs = getAttendance.Get(uczen);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                for(Obecnosc nieob :nieobs) System.out.println(nieob.ID+" "+ nieob.STAN+" "+nieob.NAZWA);
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("../View/Templates/Usprawiedliwienie.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex){
                    Logger.getLogger(StudentActions.class.getName()).log(Level.SEVERE, null, ex);
                }
                Usprawiedliwienie display = Loader.getController();
                try {
                    display.setConfig(uczen);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Parent blah = Loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Usprawiedliwienie: " + uczen.IMIE + " " + uczen.NAZWISKO);
                stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(blah));
                stage.setResizable(false);
                stage.show();

//

            }
        });
        Button uwaga = new Button();
        uwaga.setMinWidth(100);
        uwaga.setMinHeight(100);
        uwaga.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
        uwaga.setText("Napisz uwagę");

        uwaga.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Napisz uwagę");
                dialog.setHeaderText("Napisz uwagę");
                dialog.setContentText("Treść:");


                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    // System.out.println("tresc: " + result.get());
                    ZoneId zonedId = ZoneId.of("Europe/Warsaw");
                    LocalDate a = LocalDate.now(zonedId);
                    Date date = java.sql.Date.valueOf(a);
                    System.out.println(a);
                    String tekst = result.get();
                    if (tekst != null && !tekst.isEmpty()) {
                        try {
                            zapytanie = conn.createStatement();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        String quest = "INSERT INTO UWAGI (UCZNIOWIE_ID, OPIS, DATA, NAUCZYCIELE_ID_NAUCZYCIELA) VALUES ('" + uczen.ID + "','" + tekst + "','" + date + "','" + informacje.ID + "')";
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
                }


            }
        });
        flowx.getChildren().add(btn);
        flowx.getChildren().add(uwaga);
    }
}


