package pl.edziennik.ParentSource.Model;

import pl.edziennik.ParentSource.Helpers.Informacje;

import java.sql.SQLException;

public class GetInformationTeacher extends DriverDB {
    public GetInformationTeacher() throws SQLException {
    }

    public Informacje Get(int idUser) throws SQLException {
        Informacje temp2 = null;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM NAUCZYCIELE WHERE ID_NAUCZYCIELA = '" + idUser + "'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Informacje();
            temp2.setID(wynik.getInt("ID_NAUCZYCIELA"));
            temp2.setIMIE( wynik.getString("IMIE"));
            temp2.setNAZWISKO(wynik.getString("NAZWISKO"));


        }
        return temp2;
    }
}
