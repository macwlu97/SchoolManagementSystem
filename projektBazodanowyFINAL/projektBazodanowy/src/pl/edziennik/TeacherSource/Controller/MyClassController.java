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
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;
import pl.edziennik.TeacherSource.Model.DriverDB;
import pl.edziennik.TeacherSource.Model.GetClass;
import pl.edziennik.TeacherSource.Model.GetSubjects;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClassController extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();

    public MyClassController() throws SQLException {
    }

    GetClass getClass = new GetClass();
    List<Uczniowie> uczniowies = new ArrayList<>();

    //    Przedmioty przedmiottmp = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig(Informacje informacje) throws SQLException {
        uczniowies = getClass.Get(informacje);
        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1x = new MenuItem("akcja");
        item1x.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

            }
        });
        contextMenu.getItems().addAll(item1x);
        for (Uczniowie uczen : uczniowies) {
            Button btn = new Button();
            btn.setMinWidth(100);
            btn.setMinHeight(100);
            btn.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
            btn.setText(uczen.IMIE + " " + uczen.NAZWISKO);

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(btn.getText()); //Or "1" as in your code
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("../View/Templates/StudentActions.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex){
                        Logger.getLogger(MySubjectsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    StudentActions display = Loader.getController();
                    try {
                        display.setConfig(uczen, informacje);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Parent blah = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setTitle("E-Dziennik - Uczeń: "+uczen.IMIE+" "+uczen.NAZWISKO);
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
//                    przedmiottmp = przedmiot;
                    contextMenu.show(btn, event.getScreenX(), event.getScreenY());
                }
            });
            flowx.getChildren().add(btn); //add button to your VBox
        }

    }
}


