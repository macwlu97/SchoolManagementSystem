package pl.edziennik.AdminSource.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edziennik.AdminSource.Helpers.PasswordGenerator;
import pl.edziennik.AdminSource.Model.DriverDB;
import pl.edziennik.auth.authUsers;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AddParent extends DriverDB implements Initializable {
    @FXML
    private JFXTextField imiep;
    @FXML
    private JFXTextField nazwiskop;
    @FXML
    private JFXTextField loginp;

    authUsers authUser = new authUsers();
    PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
            .useDigits(true)
            .useLower(true)
            .useUpper(true)
            .build();

    public AddParent() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig() throws SQLException { // Setting the client-object in ClientViewController

    }



    @FXML
    public void addT(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException {
        if ((imiep.getText() != null && !imiep.getText().isEmpty()) && (nazwiskop.getText() != null && !nazwiskop.getText().isEmpty()) && (loginp.getText() != null && !loginp.getText().isEmpty())) {


            String password = passwordGenerator.generate(8); // output ex.: lrU12fmM 75iwI90o
//        System.out.println(password);
            String hash = authUser.get_SHA_512_SecurePassword(password, "projekt");
//        System.out.println(hash);

            try {
                zapytanie = conn.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String quest = "INSERT INTO Rodzice (IMIE, NAZWISKO, LOGIN, PASSWORD) VALUES ('" + imiep.getText() + "', '" + nazwiskop.getText() + "','" + loginp.getText() + "','" + hash + "')";
            try {
                wynik = zapytanie.executeQuery(quest);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Zapisz to hasło, brak możliwości przypomnienia!");
            alert.setContentText("login: " + loginp.getText() + "\nhasło: " + password);

            alert.showAndWait();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Uwaga");
            errorAlert.setContentText("Nie może być puste!");
            errorAlert.showAndWait();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



}
