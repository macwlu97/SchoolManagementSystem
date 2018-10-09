package pl.edziennik.ParentSource.Model;



import pl.edziennik.ParentSource.Helpers.Klasa;
import pl.edziennik.ParentSource.Helpers.Nauczyciele;
import pl.edziennik.ParentSource.Helpers.Uczniowie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetChildInformation extends DriverDB {
    public GetChildInformation() throws SQLException {
    }

    public Uczniowie Get(int idUser) throws SQLException {
        Uczniowie temp2 = null;
        zapytanie = conn.createStatement();
        String quest = "SELECT * FROM Uczniowie WHERE ID = '" + idUser + "'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Uczniowie();
//            temp2.ID = Integer.toString(wynik.getInt("ID"));
            temp2.setID(wynik.getInt("ID"));
            temp2.setIMIE(wynik.getString("IMIE"));
            temp2.setNAZWISKO(wynik.getString("NAZWISKO"));
            temp2.setAVATAR(wynik.getString("AVATAR"));
            temp2.setDATA_URODZENIA(wynik.getDate("DATA_URODZENIA"));
            temp2.setID_KLASY(wynik.getInt("KLASA_ID_KLASY"));


        }
        return temp2;
        //return tablica z klasa dzieci
    }



    public Klasa GetKlasa(int idKlasa) throws SQLException {
        Klasa temp2 = null;
        zapytanie = conn.createStatement();
        String quest = "SELECT * FROM KLASA WHERE ID_KLASY = '" + idKlasa + "'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Klasa();
//            temp2.ID = Integer.toString(wynik.getInt("ID"));
            temp2.setID(wynik.getInt("ID_KLASY"));
            temp2.setNAZWA(wynik.getString("NAZWA"));
            temp2.setROK_SZKOLNY(wynik.getString("ROK_SZKOLNY"));
            temp2.setID_NAUCZYCIELA(wynik.getInt("NAUCZYCIELE_ID_NAUCZYCIELA"));

        }
        return temp2;
    }
    public Nauczyciele GetWychowawca(int idNauczyciel) throws SQLException {
        Nauczyciele temp2 = null;
        zapytanie = conn.createStatement();
        String quest = "SELECT * FROM Nauczyciele WHERE ID_NAUCZYCIELA = '" + idNauczyciel + "'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Nauczyciele();
//            temp2.ID = Integer.toString(wynik.getInt("ID"));
            temp2.setID(wynik.getInt("ID_NAUCZYCIELA"));
            temp2.setIMIE(wynik.getString("IMIE"));
            temp2.setNAZWISKO(wynik.getString("NAZWISKO"));
            temp2.setTELEFON(wynik.getString("TELEFON"));
            temp2.setEMAIL(wynik.getString("EMAIL"));
        }
        return temp2;
    }


}
