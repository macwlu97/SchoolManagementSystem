package pl.edziennik.AdminSource.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.FlowPane;
import pl.edziennik.AdminSource.Helpers.GClass;
import pl.edziennik.AdminSource.Helpers.Przedmioty;
import pl.edziennik.AdminSource.Model.DriverDB;
import pl.edziennik.AdminSource.Model.GetClass;
import pl.edziennik.AdminSource.Model.GetSubjects;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Options extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();


    public Options() throws SQLException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig() throws SQLException {
        Button btn = new Button();
        btn.setMinWidth(100);
        btn.setMinHeight(100);
        btn.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
        btn.setText("Wyczyść aplikację");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        Button zaladuj = new Button();
        zaladuj.setMinWidth(100);
        zaladuj.setMinHeight(100);
        zaladuj.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
        zaladuj.setText("Załaduj przykładowe dane");

        zaladuj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
            flowx.getChildren().add(btn); //add button to your VBox
        flowx.getChildren().add(zaladuj); //add button to your VBox
        }

    }



