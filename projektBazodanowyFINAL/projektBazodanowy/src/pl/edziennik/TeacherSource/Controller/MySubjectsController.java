package pl.edziennik.TeacherSource.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Model.DriverDB;
import pl.edziennik.TeacherSource.Model.GetSubjects;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySubjectsController extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();

    public MySubjectsController() throws SQLException {
    }
    GetSubjects getSubjects = new GetSubjects();
    List<Przedmioty> przedmioties = new ArrayList<>();
    Przedmioty przedmiottmp = null;
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
        // Create ContextMenu
        ContextMenu contextMenu = new ContextMenu();

        /* SPRAWDŹ OBECNOSC */


/* DODAJ OCENY */

        MenuItem itemoceny = new MenuItem("Dodaj oceny");

        itemoceny.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("../View/Templates/AddGrades.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex){
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                AddGrades display = Loader.getController();
                try {
                    display.setConfig(przedmiottmp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Parent blah = Loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Dodaj oceny");
                stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(blah));
                stage.setResizable(false);
                stage.showAndWait();
            }
        });


        //end//
//        contextMenu.getItems().addAll(item1x);
        contextMenu.getItems().addAll(itemoceny);
        for (Przedmioty przedmiot : przedmioties){
            Button btn = new Button();
            btn.setMinWidth(100);
            btn.setMinHeight(100);
            btn.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
            btn.setText(przedmiot.NAZWA);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
//                    System.out.println(btn.getText()); //Or "1" as in your code
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("../View/Templates/Lessons.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex){
                        Logger.getLogger(MySubjectsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    LessonsController display = Loader.getController();
                    try {
                        display.setConfig(przedmiot, informacje.ID);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Parent blah = Loader.getRoot();
                Stage stage = new Stage();
//                    stage.setTitle("E-Dziennik - Uczniowie uczęszczający na: "+przedmiot.NAZWA);
                    stage.setTitle("Zajęcia");
                    stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(blah));
                stage.setResizable(false);
                stage.showAndWait();

                }
            });
            btn.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

                @Override
                public void handle(ContextMenuEvent event) {
//                    tmp = uczniowie;
                    przedmiottmp = przedmiot;
                    contextMenu.show(btn, event.getScreenX(), event.getScreenY());
                }
            });
            flowx.getChildren().add(btn); //add button to your VBox
        }
    }

}
