package pl.edziennik.TeacherSource.Model;

import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Klasa;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetClass extends DriverDB {
    public GetClass() throws SQLException {
    }

    public List<Uczniowie> Get(Informacje informacje) throws SQLException {
        List<Uczniowie> sub = new ArrayList<>();
        Uczniowie temp2;
        int klasa = 0;
        zapytanie = conn.createStatement();
        String quest = "SELECT * FROM Klasa WHERE NAUCZYCIELE_ID_Nauczyciela = '"+informacje.ID+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) klasa= wynik.getInt("ID_KLASY");
         quest = "SELECT * FROM Uczniowie WHERE Klasa_ID_KLASY = '"+klasa+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Uczniowie();
            temp2.ID = wynik.getInt("ID");
            temp2.IMIE = wynik.getString("IMIE");
            temp2.NAZWISKO = wynik.getString("NAZWISKO");
//            temp2.ID_RODZICA = wynik.getInt("RODZICE_ID");
            temp2.AVATAR = wynik.getString("AVATAR");
            temp2.DATA_URODZENIA = wynik.getDate("DATA_URODZENIA");
            temp2.ID_KLASY = wynik.getInt("KLASA_ID_KLASY");
            sub.add(temp2);
        }
        return sub;
    }

    public int GetSimple(int idzaj) throws SQLException {
        int klasa = 0;
        zapytanie = conn.createStatement();
        String quest = "SELECT KLASA_ID_KLASY FROM PLAN WHERE ID_ZAJ = '"+idzaj+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) klasa= wynik.getInt("KLASA_ID_KLASY");
        return klasa;
    }
    public String GetSimpleName(int idzaj) throws SQLException {
        String klasa = null;
        zapytanie = conn.createStatement();
        String quest = "SELECT NAZWA FROM KLASA WHERE ID_KLASY = '"+idzaj+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) klasa= wynik.getString("Nazwa");
        return klasa;
    }

    public List<Klasa> GetClass(Informacje informacje) throws SQLException {
        List<Klasa> sub = new ArrayList<>();
        Klasa temp2;
//        int klasa = 0;
        zapytanie = conn.createStatement();
        String quest = "SELECT DISTINCT ID_KLASY, NAZWA, ROK_SZKOLNY, NAUCZYCIELE_ID_NAUCZYCIELA FROM KLASA INNER JOIN PLAN ON klasa.id_klasy = plan.klasa_id_klasy WHERE przedmioty_id_przedmiotu = '"+ informacje.ID +"'";
//        String quest = "SELECT * FROM klasa WHERE NAUCZYCIELE_ID_NAUCZYCIELA = '"+informacje.ID+"'";
//        String quest = "SELECT DISTINCT * FROM KLASA INNER JOIN (SELECT KLASA_ID_KLASY, NAUCZYCIELE_ID_NAUCZYCIELA FROM PLAN INNER JOIN PRZEDMIOTY ON plan.PRZEDMIOTY_ID_PRZEDMIOTU = przedmioty.ID_PRZEDMIOTU) nowy ON klasa.id_klasy = nowy.klasa_id_klasy WHERE klasa.NAUCZYCIELE_ID_NAUCZYCIELA = '"+ informacje.ID +"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Klasa();
            temp2.ID_KLASY = wynik.getInt("ID_KLASY");
            temp2.NAZWA = wynik.getString("NAZWA");
            temp2.ROK_SZKOLNY = wynik.getString("ROK_SZKOLNY");
            temp2.NAUCZYCIELE_ID_NAUCZYCIELA = wynik.getInt("NAUCZYCIELE_ID_NAUCZYCIELA");
            sub.add(temp2);
        }
        return sub;
    }
}
