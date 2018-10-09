package pl.edziennik.ParentSource.Model;



import pl.edziennik.ParentSource.Helpers.Informacje;
import pl.edziennik.ParentSource.Helpers.Uczniowie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetChild extends DriverDB {
    public GetChild() throws SQLException {
    }

    public List<Uczniowie> Get(int idUser) throws SQLException {
        List<Uczniowie> Child = new ArrayList<>();
        Uczniowie temp2;
        zapytanie = conn.createStatement();
//        String quest = "SELECT * FROM Uczniowie WHERE RODZICE_ID = '" + idUser + "'";
        String quest = "SELECT * FROM uczniowie INNER JOIN rodziceuczniowie ON uczniowie.id = rodziceuczniowie.uczniowie_id WHERE rodzice_id = '" + idUser + "'";
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
            Child.add(temp2);


        }
        return Child;
        //return tablica z klasa dzieci
    }

    public Uczniowie Getu(int idUser) throws SQLException {
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
}
