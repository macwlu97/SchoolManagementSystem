package pl.edziennik.AdminSource.Controller;

import com.jfoenix.controls.JFXButton;
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
import pl.edziennik.TeacherSource.Controller.MySubjectsController;
import pl.edziennik.TeacherSource.Controller.StudentActions;
import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;
import pl.edziennik.TeacherSource.Model.DriverDB;
import pl.edziennik.TeacherSource.Model.GetClass;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddClass extends DriverDB implements Initializable {

    @FXML
    private FlowPane flowx = new FlowPane();

    public AddClass() throws SQLException {
    }

//    GetClass getClass = new GetClass();
//    List<Uczniowie> uczniowies = new ArrayList<>();

    //    Przedmioty przedmiottmp = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig() throws SQLException {


            Button btn = new Button();
            btn.setMinWidth(100);
            btn.setMinHeight(100);
            btn.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
            btn.setText("Utwórz klasę");

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(btn.getText()); //Or "1" as in your code
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("../View/Templates/ClassCreator.fxml"));
                    try {
                        Loader.load();
                    } catch (IOException ex){
                        Logger.getLogger(MySubjectsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ClassCreator display = Loader.getController();
                    try {
                        display.setConfig();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Parent blah = Loader.getRoot();
                    Stage stage = new Stage();
                    stage.setTitle("Utwórz klasę");
                    stage.getIcons().add(new Image("icon.png"));
                    stage.setScene(new Scene(blah));
                    stage.setResizable(false);
                    stage.showAndWait();

                }
            });
        Button btn_lista = new Button();
        btn_lista.setMinWidth(100);
        btn_lista.setMinHeight(100);
        btn_lista.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
        btn_lista.setText("Lista klas");

        btn_lista.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(btn.getText()); //Or "1" as in your code
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("../View/Templates/ClassActions.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex){
                    Logger.getLogger(MySubjectsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ClassActions display = Loader.getController();
                try {
                    display.setConfig();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Parent blah = Loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Lista klas");
                stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(blah));
                stage.setResizable(false);
                stage.showAndWait();

            }
        });
            flowx.getChildren().add(btn); //add button to your VBox
        flowx.getChildren().add(btn_lista);
        }

    }



