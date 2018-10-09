package pl.edziennik.TeacherSource.Model;

import pl.edziennik.TeacherSource.Helpers.Obecnosc;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;
import pl.edziennik.TeacherSource.Helpers.Zajecia;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetAttendance extends DriverDB {
    public GetAttendance() throws SQLException {
    }
    Statement zapytanie2 = null;
    ResultSet wynik2;
    public List<Obecnosc> Get(Uczniowie uczen) throws SQLException {
        List<Obecnosc> sub = new ArrayList<>();
        Obecnosc temp2;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM OBECNOSC INNER JOIN  (SELECT id_zaj, id_przedmiotu, nazwa FROM PLAN INNER JOIN PRZEDMIOTY ON plan.PRZEDMIOTY_ID_PRZEDMIOTU = przedmioty.ID_PRZEDMIOTU) prz ON plan_id_zaj = prz.id_zaj WHERE UCZNIOWIE_ID = '"+uczen.ID+"' AND STAN=0";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Obecnosc();
            temp2.ID = wynik.getInt("ID_OBECNOSCI");
             zapytanie2 = conn.createStatement();

            String quest2 = "SELECT * FROM Przedmioty WHERE ID_PRZEDMIOTU = '"+wynik.getInt("ID_PRZEDMIOTU")+"'";
             wynik2 = zapytanie2.executeQuery(quest2);
            while (wynik2.next()) temp2.NAZWA = wynik2.getString("NAZWA");

            temp2.STAN = wynik.getInt("STAN");
            temp2.DATA = wynik.getDate("DATA");
            sub.add(temp2);
        }
        return sub;
    }

    public void Delete( Date datas, int idzaj) throws SQLException {
//        List<Obecnosc> sub = new ArrayList<>();
//        Obecnosc temp2;
        zapytanie = conn.createStatement();

        String quest = "DELETE FROM OBECNOSC WHERE DATA = '"+ datas+"' AND PLAN_ID_ZAJ = '"+idzaj+"'";
        wynik = zapytanie.executeQuery(quest);
    }


    public Obecnosc GetSimple(int iducznia, Date datas, int idzaj) throws SQLException {
        Obecnosc temp2 = null;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM OBECNOSC WHERE UCZNIOWIE_ID = '"+iducznia+"' AND DATA = '"+ datas+"' AND PLAN_ID_ZAJ = '"+idzaj+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Obecnosc();
            temp2.ID = wynik.getInt("ID_OBECNOSCI");
            temp2.STAN = wynik.getInt("STAN");
            temp2.DATA = wynik.getDate("DATA");
        }
        return temp2;
    }
}
