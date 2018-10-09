package pl.edziennik.TeacherSource.Model;

import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetSubjects extends DriverDB {
    public GetSubjects() throws SQLException {
    }

    public List<Przedmioty> Get(Informacje informacje) throws SQLException {
        List<Przedmioty> sub = new ArrayList<>();
        Przedmioty temp2;
        zapytanie = conn.createStatement();
        String quest ="SELECT * FROM PRZEDMIOTY WHERE NAUCZYCIELE_ID_Nauczyciela = '"+informacje.ID+"'";
//        String quest = "SELECT * FROM PRZEDMIOTY WHERE ID_Nauczyciela = '"+informacje.ID+"'";
//        String quest = "SELECT DISTINCT NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  ZAJECIA.ID_PRZEDMIOTU AS ID_PRZEDMIOTU, ZAJECIA.ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM ZAJECIA  INNER JOIN PRZEDMIOTY  ON ZAJECIA.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE ID_Nauczyciela = '"+informacje.ID+"')";
//        String quest = "SELECT DISTINCT NAZWA, ID_PRZEDMIOTU, ID_KLASY, ID_NAUCZYCIELA FROM (SELECT  PLAN.ID_PRZEDMIOTU AS ID_PRZEDMIOTU, PLAN.KLASA_ID_KLASY AS ID_KLASY, PRZEDMIOTY.NAZWA AS NAZWA, PRZEDMIOTY.ID_NAUCZYCIELA AS ID_NAUCZYCIELA  FROM PLAN  INNER JOIN PRZEDMIOTY  ON PLAN.ID_PRZEDMIOTU=PRZEDMIOTY.ID_PRZEDMIOTU WHERE ID_Nauczyciela = '"+informacje.ID+"' ORDER BY GODZINA)";
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
}
