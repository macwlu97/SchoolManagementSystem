package pl.edziennik.AdminSource.Model;



import pl.edziennik.AdminSource.Helpers.GClass;
import pl.edziennik.AdminSource.Helpers.Przedmioty;
import pl.edziennik.AdminSource.Helpers.Plan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetSubjects extends DriverDB {
    public GetSubjects() throws SQLException {
    }

    public List<Przedmioty> Get(GClass tmpklasa) throws SQLException {
        List<Przedmioty> sub = new ArrayList<>();
        Przedmioty temp2;
        zapytanie = conn.createStatement();

//        String quest = "SELECT * FROM PRZEDMIOTY WHERE ID_KLASY = '"+tmpklasa.ID_KLASY+"'";
        String quest = "SELECT  NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  ZAJECIA.ID_PRZEDMIOTU AS ID_PRZEDMIOTU, ZAJECIA.ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM ZAJECIA  INNER JOIN PRZEDMIOTY  ON ZAJECIA.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE ZAJECIA.ID_klasy = '"+tmpklasa.ID_KLASY+"')";
//        String quest = "SELECT NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  PLAN.ID_PRZEDMIOTU AS ID_PRZEDMIOTU, PLAN.ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE PLAN.ID_klasy =  '"+tmpklasa.ID_KLASY+"' ORDER BY GODZINA)";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Przedmioty();
            temp2.ID_PRZEDMIOTU = wynik.getInt("ID_PRZEDMIOTU");
            temp2.ID_KLASY = wynik.getInt("ID_KLASY");
            temp2.ID_NAUCZYCIELA = wynik.getInt("ID_NAUCZYCIELA");
            temp2.NAZWA = wynik.getString("NAZWA");
            sub.add(temp2);
        }
        return sub;
    }

    public List<Przedmioty> GetSimple() throws SQLException {
        List<Przedmioty> sub = new ArrayList<>();
        Przedmioty temp2;
        zapytanie = conn.createStatement();

//        String quest = "SELECT * FROM PRZEDMIOTY WHERE ID_KLASY = '"+tmpklasa.ID_KLASY+"'";
        String quest = "SELECT  NAZWA, ID_PRZEDMIOTU, NAUCZYCIELE_ID_NAUCZYCIELA FROM PRZEDMIOTY";
//        String quest = "SELECT NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  PLAN.ID_PRZEDMIOTU AS ID_PRZEDMIOTU, PLAN.ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE PLAN.ID_klasy =  '"+tmpklasa.ID_KLASY+"' ORDER BY GODZINA)";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Przedmioty();
            temp2.ID_PRZEDMIOTU = wynik.getInt("ID_PRZEDMIOTU");
//            temp2.ID_KLASY = wynik.getInt("ID_KLASY");
            temp2.ID_NAUCZYCIELA = wynik.getInt("NAUCZYCIELE_ID_NAUCZYCIELA");
            temp2.NAZWA = wynik.getString("NAZWA");
            sub.add(temp2);
        }
        return sub;
    }

    public List<Plan> GetPlan(int klasa) throws SQLException {
        List<Plan> sub = new ArrayList<>();
        Plan temp2;
        zapytanie = conn.createStatement();

//        String quest = "SELECT * FROM PRZEDMIOTY WHERE ID_KLASY = '"+tmpklasa.ID_KLASY+"'";
        String quest = "SELECT ID_ZAJ, GODZINA, DZIEN  FROM PLAN WHERE KLASA_ID_KLASY = '"+klasa+"'";
//        String quest = "SELECT NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  PLAN.ID_PRZEDMIOTU AS ID_PRZEDMIOTU, PLAN.ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE PLAN.ID_klasy =  '"+tmpklasa.ID_KLASY+"' ORDER BY GODZINA)";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Plan();
            temp2.ID_ZAJ = wynik.getInt("ID_ZAJ");
//            temp2.ID_KLASY = wynik.getInt("ID_KLASY");
            temp2.GODZINA = wynik.getInt("GODZINA");
            temp2.DZIEN = wynik.getInt("DZIEN");
            sub.add(temp2);
        }
        return sub;
    }

//    public List<Przedmioty> GetTMP(GClass tmpklasa) throws SQLException {
//        List<Przedmioty> sub = new ArrayList<>();
//        Przedmioty temp2;
//        zapytanie = conn.createStatement();
//
////        String quest = "SELECT * FROM PRZEDMIOTY WHERE ID_KLASY = '"+tmpklasa.ID_KLASY+"'";
//        String quest = "SELECT * FROM TMP_OBECNOSCI INNER JOIN PLAN ON TMP_OBECNOSCI.PLAN_ID_ZAJ=PLAN.ID_ZAJ WHERE KLASA_ID_KLASY = '"+tmpklasa+'";
////        String quest = "SELECT NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  PLAN.ID_PRZEDMIOTU AS ID_PRZEDMIOTU, PLAN.ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE PLAN.ID_klasy =  '"+tmpklasa.ID_KLASY+"' ORDER BY GODZINA)";
//        wynik = zapytanie.executeQuery(quest);
//        while (wynik.next()) {
//            temp2 = new Przedmioty();
//            temp2.ID_PRZEDMIOTU = wynik.getInt("ID_PRZEDMIOTU");
//            temp2.ID_KLASY = wynik.getInt("ID_KLASY");
//            temp2.ID_NAUCZYCIELA = wynik.getInt("ID_NAUCZYCIELA");
//            temp2.NAZWA = wynik.getString("NAZWA");
//            sub.add(temp2);
//        }
//        return sub;
//    }
}
