package pl.edziennik.AdminSource.Model;

import pl.edziennik.AdminSource.Helpers.Rodzice;
import pl.edziennik.AdminSource.Helpers.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetTeacher extends DriverDB {
    public GetTeacher() throws SQLException {
    }

    public List<Teacher> Get() throws SQLException {
        List<Teacher> sub = new ArrayList<>();
        Teacher temp2;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM Nauczyciele";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Teacher();
            temp2.ID = wynik.getInt("ID_NAUCZYCIELA");
            temp2.IMIE = wynik.getString("IMIE");
            temp2.NAZWISKO = wynik.getString("NAZWISKO");
            sub.add(temp2);
        }
        return sub;
    }

    public String GetSimple(int idn) throws SQLException {
        Teacher temp2=null;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM Nauczyciele WHERE ID_NAUCZYCIELA ='"+idn+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Teacher();
            temp2.ID = wynik.getInt("ID_NAUCZYCIELA");
            temp2.IMIE = wynik.getString("IMIE");
            temp2.NAZWISKO = wynik.getString("NAZWISKO");
        }
        return temp2.IMIE + " " + temp2.NAZWISKO;
    }
}
