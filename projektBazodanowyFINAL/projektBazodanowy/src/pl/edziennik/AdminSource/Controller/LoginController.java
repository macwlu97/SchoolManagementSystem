package pl.edziennik.AdminSource.Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edziennik.AdminSource.Model.DriverDB;
import pl.edziennik.User.User;
import pl.edziennik.auth.authUsers;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends DriverDB implements Initializable {
    authUsers authUser = new authUsers();

    public LoginController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML private JFXTextField loginfield;
    @FXML private JFXPasswordField passfield;
    @FXML
    public void onEnter(ActionEvent ae) throws SQLException {
        User user = new User();
        user.login = loginfield.getText();
        user.password = passfield.getText();
//        String hash = authUser.get_SHA_512_SecurePassword(user.password, "projekt");
//        DriverDB log = new DriverDB();
//            log.polacz();

        if(user.login.equals("admin") && user.password.equals("admin1")) {

            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("../View/Templates/Admin.fxml"));
            try {
                Loader.load();
            } catch (IOException ex){
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            AppController display = Loader.getController();
            display.setConfig(user);
            display.setName();
            Parent blah = Loader.getRoot();
            Stage appStage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            appStage.setScene(new Scene(blah));
            appStage.show();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Uwaga");
            errorAlert.setContentText("Nie poprawne dane!");
            errorAlert.showAndWait();
        }
    }
    @FXML
    public void LoginTo(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException, IOException {
        User user = new User();
        user.login = loginfield.getText();
        user.password = passfield.getText();
//        String hash = authUser.get_SHA_512_SecurePassword(user.password, "projekt");
//        DriverDB log = new DriverDB();
//            log.polacz();

            if(user.login.equals("admin") && user.password.equals("admin1")) {

FXMLLoader Loader = new FXMLLoader();
Loader.setLocation(getClass().getResource("../View/Templates/Admin.fxml"));
try {
    Loader.load();
} catch (IOException ex){
    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
}
AppController display = Loader.getController();
display.setConfig(user);
display.setName();
                Parent blah = Loader.getRoot();
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(new Scene(blah));
                appStage.show();
            }else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Uwaga");
                errorAlert.setContentText("Nie poprawne dane!");
                errorAlert.showAndWait();
            }
    }

}
