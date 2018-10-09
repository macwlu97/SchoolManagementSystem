package pl.edziennik.ParentSource.Model;



import pl.edziennik.ParentSource.Helpers.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetChildSubject extends DriverDB {
    public GetChildSubject() throws SQLException {
    }

    public List<Subject> Get(int idKlasy) throws SQLException {
        List<Subject> p = new ArrayList<>();
        Subject temp2 = null;
        zapytanie = conn.createStatement();
        String quest = " SELECT DISTINCT NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  PLAN.PRZEDMIOTY_ID_PRZEDMIOTU AS ID_PRZEDMIOTU, PLAN.KLASA_ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.NAUCZYCIELE_ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.PRZEDMIOTY_ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE PLAN.Klasa_ID_Klasy = '"+idKlasy+"' ORDER BY GODZINA)";
//        String quest = "SELECT * FROM Przedmioty WHERE ID_Klasy = '" + idKlasy + "'";
//        String quest = "SELECT DISTINCT NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  PLAN.ID_PRZEDMIOTU AS ID_PRZEDMIOTU, PLAN.KLASA_ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE PLAN.Klasa_ID_Klasy = '" + idKlasy + "' ORDER BY GODZINA)";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Subject();
//            temp2.ID = Integer.toString(wynik.getInt("ID"));
            temp2.setID(wynik.getInt("ID_PRZEDMIOTU"));
            temp2.setID_Klasy(wynik.getInt("ID_Klasy"));
            temp2.setID_Nauczyciela(wynik.getInt("ID_Nauczyciela"));
            temp2.setNazwa(wynik.getString("NAZWA"));
            p.add(temp2);

        }
        return p;
        //return tablica z klasa dzieci
    }

    public List<Ratings> GetR(int idPrzedmiot, int childID) throws SQLException {
        List<Ratings> r = new ArrayList<>();
        Ratings temp2 = null;
        zapytanie = conn.createStatement();
        String quest = "SELECT * FROM OCENY WHERE PRZEDMIOTY_ID_PRZEDMIOTU = '" + idPrzedmiot + "' AND UCZNIOWIE_ID = '"+childID+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Ratings();
//            temp2.ID = Integer.toString(wynik.getInt("ID"));
            temp2.setID(wynik.getInt("ID_OCENY"));
            temp2.setID_UCZNIA(wynik.getInt("UCZNIOWIE_ID"));
            temp2.setID_PRZEDMIOTU(wynik.getInt("PRZEDMIOTY_ID_PRZEDMIOTU"));
            temp2.setOCENA(wynik.getInt("OCENA"));
            r.add(temp2);

        }
        return r;
        //return tablica z klasa dzieci
    }
    public List<Plan> GetP( int idKlasy) throws SQLException {
        List<Plan> r = new ArrayList<>();
        Plan temp2 = null;
        zapytanie = conn.createStatement();
        String quest =" SELECT PLAN.ID_ZAJ, PLAN.PRZEDMIOTY_ID_PRZEDMIOTU, PLAN.GODZINA, PLAN.KLASA_ID_KLASY, PLAN.DZIEN, PRZEDMIOTY.NAZWA FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.PRZEDMIOTY_ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE PLAN.KLASA_ID_KLASY='"+ idKlasy+"' ORDER BY GODZINA";
//        String quest = "SELECT PLAN.ID_ZAJ, PLAN.ID_PRZEDMIOTU, PLAN.GODZINA, PLAN.KLASA_ID_KLASY, PLAN.DZIEN, PRZEDMIOTY.NAZWA FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE PLAN.KLASA_ID_KLASY='"+ idKlasy+"' ORDER BY GODZINA";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Plan();
//            temp2.ID = Integer.toString(wynik.getInt("ID"));
            temp2.setID(wynik.getInt("ID_ZAJ"));
            temp2.setID_PRZEDMIOTU(wynik.getInt("PRZEDMIOTY_ID_PRZEDMIOTU"));
            temp2.setGODZINA(wynik.getInt("GODZINA"));
            temp2.setID_KLASY(wynik.getInt("KLASA_ID_KLASY"));
            temp2.setDZIEN(wynik.getInt("DZIEN"));
            temp2.setNazwa(wynik.getString("NAZWA"));

            r.add(temp2);

        }
        return r;
        //return tablica z klasa dzieci
    }

    public List<Attendance> getAttendanceMyChild(int child, LocalDate today) throws SQLException {
        List<Attendance> attendances = new ArrayList<>();
        Attendance temp2;
        zapytanie = conn.createStatement();


        String quest = "SELECT * FROM OBECNOSC WHERE UCZNIOWIE_ID = '"+ child+"' AND DATA BETWEEN  '" + today.minusDays(7) +"' AND '"+ today +"'"; //between data sevendays
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Attendance();
            temp2.ID_OBECNOSCI = wynik.getInt("ID_OBECNOSCI");
//            temp2.ID_UCZNIA = wynik.getInt("ID_UCZNIA");
//            temp2.ID_PRZEDMIOTU = wynik.getInt("PRZEDMIOTY_ID_PRZEDMIOTU");
            temp2.STAN = wynik.getInt("STAN");
            temp2.OPIS = wynik.getString("OPIS");
            temp2.ID_ZAJ = wynik.getInt("PLAN_ID_ZAJ");
            temp2.DATA = wynik.getDate("DATA");
            attendances.add(temp2);
        }
        return attendances;
    }
    public List<Attendance> getStatAttendanceMyChild(int child) throws SQLException {
        List<Attendance> attendances = new ArrayList<>();
        Attendance temp2;
        zapytanie = conn.createStatement();


        String quest = "SELECT * FROM OBECNOSC WHERE UCZNIOWIE_ID = '"+ child+"'"; //between data sevendays
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Attendance();
            temp2.ID_OBECNOSCI = wynik.getInt("ID_OBECNOSCI");
//            temp2.ID_UCZNIA = wynik.getInt("ID_UCZNIA");
            temp2.STAN = wynik.getInt("STAN");
            attendances.add(temp2);
        }
        return attendances;
    }
}
