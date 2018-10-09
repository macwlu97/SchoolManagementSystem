package pl.edziennik.AdminSource.Model;

import pl.edziennik.AdminSource.Helpers.GClass;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetClass extends DriverDB {
    public GetClass() throws SQLException {
    }

    public List<GClass> Get() throws SQLException {
        List<GClass> sub = new ArrayList<>();
        GClass temp2;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM KLASA";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new GClass();
            temp2.ID_KLASY = wynik.getInt("ID_KLASY");
            temp2.NAZWA = wynik.getString("NAZWA");
            temp2.ROK_SZKOLNY = wynik.getString("ROK_SZKOLNY");
            sub.add(temp2);
        }
        return sub;
    }
}
