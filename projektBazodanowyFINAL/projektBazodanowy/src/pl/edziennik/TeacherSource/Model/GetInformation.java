package pl.edziennik.TeacherSource.Model;

import pl.edziennik.TeacherSource.Helpers.Informacje;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetInformation extends DriverDB {
    public GetInformation() throws SQLException {
    }

    public Informacje Get(String idUser) throws SQLException {
        Informacje temp2 = null;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM Nauczyciele WHERE LOGIN = '" + idUser + "'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Informacje();
            temp2.ID = wynik.getInt("ID_NAUCZYCIELA");
            temp2.IMIE = wynik.getString("IMIE");
            temp2.NAZWISKO = wynik.getString("NAZWISKO");
            temp2.LOGIN = wynik.getString("LOGIN");
            temp2.EMAIL = wynik.getString("EMAIL");
            temp2.TELEFON = wynik.getString("TELEFON");
        }
        return temp2;
    }
}
