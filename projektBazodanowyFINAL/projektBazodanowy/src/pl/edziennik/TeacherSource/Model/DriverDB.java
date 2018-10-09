package pl.edziennik.TeacherSource.Model;

import oracle.jdbc.pool.OracleDataSource;
import pl.edziennik.User.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DriverDB extends User {
    public DriverDB() throws SQLException {
        polacz();
    }
    protected String jdbcurl = "jdbc:oracle:thin:@localhost:1521:xe";
    protected String userid = "G3_lipinski97";
    protected String password = "password";
    protected Connection conn;
    protected Statement zapytanie;
    public ResultSet wynik = null;

    public void polacz() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(jdbcurl);
        conn=ds.getConnection(userid,password);
    }


    public void pobierzDaneTeacher() throws SQLException {
        zapytanie = conn.createStatement();
        wynik = zapytanie.executeQuery("SELECT * FROM Nauczyciele");

    }
    public User getTeacher(String idUser) throws SQLException {
        User temp = new User();
        zapytanie = conn.createStatement();
        String quest = "SELECT * FROM Nauczyciele WHERE login = '" + idUser + "'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp.ID = wynik.getString("ID_NAUCZYCIELA");
            temp.login = wynik.getString("LOGIN");
            temp.password = wynik.getString("PASSWORD");
            temp.imie = wynik.getString("IMIE");
            temp.nazwisko = wynik.getString("NAZWISKO");

        }
        return temp;
//        return Usr
    }

    protected static String logowanie(ResultSet wynik, String login, String password) throws SQLException {
        String tmpLogin = null, tmpPass = null;

        while (wynik.next()) {

            tmpLogin = wynik.getString("LOGIN");
            tmpPass = wynik.getString("PASSWORD");
        if(tmpLogin.equals(login)) {
            break;
        }

        }
        if(!(tmpPass.equals(password))) return "niezalogowany";


        return "zalogowany";
    }

}
