package pl.edziennik.ParentSource.Model;


import pl.edziennik.ParentSource.Helpers.Attendance;
import pl.edziennik.ParentSource.Helpers.Plan;
import pl.edziennik.ParentSource.Helpers.Ratings;
import pl.edziennik.ParentSource.Helpers.Subject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GetStat extends DriverDB {
    public GetStat() throws SQLException {
    }

    public List<Attendance> getStatAttendanceMyChild(int child) throws SQLException {
        List<Attendance> attendances = new ArrayList<>();
        Attendance temp2;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM OBECNOSC INNER JOIN  (SELECT id_zaj, id_przedmiotu, nazwa FROM PLAN INNER JOIN PRZEDMIOTY ON plan.PRZEDMIOTY_ID_PRZEDMIOTU = przedmioty.ID_PRZEDMIOTU) prz ON plan_id_zaj = prz.id_zaj WHERE UCZNIOWIE_ID = '"+child+"'";
//        String quest = "SELECT * FROM OBECNOSC WHERE UCZNIOWIE_ID  = '"+ child+"'"; //between data sevendays
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Attendance();
            temp2.ID_OBECNOSCI = wynik.getInt("ID_OBECNOSCI");
            temp2.ID_UCZNIA = wynik.getInt("UCZNIOWIE_ID");
            temp2.ID_PRZEDMIOTU = wynik.getInt("ID_PRZEDMIOTU");
            temp2.OPIS = wynik.getString("OPIS");
            temp2.ID_ZAJ = wynik.getInt("PLAN_ID_ZAJ");
            temp2.STAN = wynik.getInt("STAN");
            temp2.DATA = wynik.getDate("DATA");
            attendances.add(temp2);
        }
        return attendances;
    }
}
