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
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;
import pl.edziennik.TeacherSource.Helpers.Zajecia;
import pl.edziennik.TeacherSource.Model.DriverDB;
import pl.edziennik.TeacherSource.Model.GetStudents;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubjectsParticipantsController extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();

    public SubjectsParticipantsController() throws SQLException {
    }

    GetStudents getSubjects = new GetStudents();
    List<Uczniowie> uczniowies = new ArrayList<>();
    Uczniowie tmp = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig(Zajecia zajecia, int informacje) throws SQLException {
        uczniowies = getSubjects.Get(zajecia.ID_KLASY);

        // Create ContextMenu
        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Dodaj ocenę");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println(tmp.IMIE+" "+tmp.NAZWISKO);

                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("../View/Templates/AddOneGrade.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex){
                    Logger.getLogger(SubjectsParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                AddOneGrade display = Loader.getController();
                try {
                    display.setConfig(tmp, zajecia);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Parent blah = Loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("E-Dziennik - Dodawanie oceny: " + tmp.IMIE + " " + tmp.NAZWISKO);
                stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(blah));
                stage.setResizable(false);
                stage.show();
            }
        });
        MenuItem item2 = new MenuItem("Dodaj uwagę");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("../View/Templates/AddComments.fxml"));
                try {
                    Loader.load();
                } catch (IOException ex){
                    Logger.getLogger(SubjectsParticipantsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                AddComments display = Loader.getController();
                try {
                    display.setConfig(tmp, informacje);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Parent blah = Loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("E-Dziennik - Dodawanie uwagi: " + tmp.IMIE + " " + tmp.NAZWISKO);
                stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(blah));
                stage.setResizable(false);
                stage.show();

            }
        });


        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(item1, item2);


        for (Uczniowie uczniowie : uczniowies) {
            Button btn = new Button();
            btn.setMinWidth(100);
            btn.setMinHeight(100);
            btn.setStyle("-fx-background-color:#070E2D; -fx-border-color: #ffffff; -fx-text-fill: #ffffff;  -fx-padding: 25 25 25 25;");
            btn.setText(uczniowie.IMIE+" "+uczniowie.NAZWISKO);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(btn.getText());
                }

            });
            btn.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

                @Override
                public void handle(ContextMenuEvent event) {
                    tmp = uczniowie;
                    contextMenu.show(btn, event.getScreenX(), event.getScreenY());
                }
            });
            flowx.getChildren().add(btn);
        }

    }
}
