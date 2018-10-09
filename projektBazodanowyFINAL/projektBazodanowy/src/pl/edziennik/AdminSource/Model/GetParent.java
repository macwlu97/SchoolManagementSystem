package pl.edziennik.AdminSource.Model;

import pl.edziennik.AdminSource.Helpers.Rodzice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetParent extends DriverDB {
    public GetParent() throws SQLException {
    }

    public List<Rodzice> Get() throws SQLException {
        List<Rodzice> sub = new ArrayList<>();
        Rodzice temp2;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM Rodzice";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Rodzice();
            temp2.ID = wynik.getInt("ID");
            temp2.IMIE = wynik.getString("IMIE");
            temp2.NAZWISKO = wynik.getString("NAZWISKO");
            sub.add(temp2);
        }
        return sub;
    }
}
