package pl.edziennik.TeacherSource.Model;

import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Helpers.Zajecia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetLessons extends DriverDB {
    public GetLessons() throws SQLException {
    }

    public List<Zajecia> Get(Przedmioty przedmiot) throws SQLException {
        List<Zajecia> sub = new ArrayList<>();
        Zajecia temp2;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM PLAN WHERE PRZEDMIOTY_ID_PRZEDMIOTU = '"+przedmiot.ID_PRZEDMIOTU+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Zajecia();
            temp2.ID_ZAJ = wynik.getInt("ID_ZAJ");
            temp2.ID_PRZEDMIOTU = wynik.getInt("PRZEDMIOTY_ID_PRZEDMIOTU");
            temp2.GODZINA = wynik.getInt("GODZINA");
            temp2.ID_KLASY = wynik.getInt("KLASA_ID_KLASY");
            temp2.DZIEN = wynik.getInt("DZIEN");
            sub.add(temp2);
        }
        return sub;
    }

    public List<Zajecia> GetA(int przedmiotID, int nauczycielID, int idklasy) throws SQLException {
        List<Zajecia> sub = new ArrayList<>();
        Zajecia temp2;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM PLAN INNER JOIN PRZEDMIOTY ON plan.PRZEDMIOTY_ID_PRZEDMIOTU = przedmioty.ID_PRZEDMIOTU WHERE PRZEDMIOTY_ID_PRZEDMIOTU = '"+ przedmiotID +"' AND NAUCZYCIELE_ID_NAUCZYCIELA = '"+ nauczycielID +"' AND KLASA_ID_KLASY = '"+ idklasy +"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Zajecia();
            temp2.ID_ZAJ = wynik.getInt("ID_ZAJ");
            temp2.ID_PRZEDMIOTU = wynik.getInt("PRZEDMIOTY_ID_PRZEDMIOTU");
            temp2.GODZINA = wynik.getInt("GODZINA");
            temp2.ID_KLASY = wynik.getInt("KLASA_ID_KLASY");
            temp2.DZIEN = wynik.getInt("DZIEN");
            sub.add(temp2);
        }
        return sub;
    }


    public Zajecia GetSimple(int idzaj) throws SQLException {
        Zajecia temp2 = null;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM PLAN WHERE ID_ZAJ = '"+ idzaj +"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Zajecia();
            temp2.ID_ZAJ = wynik.getInt("ID_ZAJ");
            temp2.ID_PRZEDMIOTU = wynik.getInt("PRZEDMIOTY_ID_PRZEDMIOTU");
            temp2.GODZINA = wynik.getInt("GODZINA");
            temp2.ID_KLASY = wynik.getInt("KLASA_ID_KLASY");
            temp2.DZIEN = wynik.getInt("DZIEN");
        }
        return temp2;
    }
}
