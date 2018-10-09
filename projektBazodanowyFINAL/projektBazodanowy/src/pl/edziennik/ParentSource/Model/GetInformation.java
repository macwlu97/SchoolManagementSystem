package pl.edziennik.ParentSource.Model;

import pl.edziennik.ParentSource.Helpers.Informacje;
import pl.edziennik.ParentSource.Model.DriverDB;

import java.sql.SQLException;

public class GetInformation extends DriverDB {
    public GetInformation() throws SQLException {
    }

    public Informacje Get(String idUser) throws SQLException {
        Informacje temp2 = null;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM Rodzice WHERE LOGIN = '" + idUser + "'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Informacje();
            temp2.setID(wynik.getInt("ID"));
            temp2.setIMIE( wynik.getString("IMIE"));
            temp2.setNAZWISKO(wynik.getString("NAZWISKO"));
            temp2.setLOGIN(wynik.getString("LOGIN"));

        }
        return temp2;
    }
}
