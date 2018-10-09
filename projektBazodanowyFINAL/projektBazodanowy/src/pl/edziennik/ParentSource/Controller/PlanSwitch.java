package pl.edziennik.ParentSource.Controller;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import pl.edziennik.ParentSource.Helpers.Klasa;
import pl.edziennik.ParentSource.Helpers.Plan;
import pl.edziennik.ParentSource.Helpers.Uczniowie;
import pl.edziennik.ParentSource.Model.GetChildInformation;
import pl.edziennik.ParentSource.Model.GetChildSubject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class PlanSwitch {
    //        // create Agenda
    Agenda agenda = new Agenda();
    Uczniowie uczen;
    Klasa klasa;
    List<Plan> plan;
    GetChildInformation getChildInformation = new GetChildInformation();
    GetChildSubject getChildSubject = new GetChildSubject();
    static ZoneId zonedId = ZoneId.of( "Europe/Warsaw" );
    static LocalDate today = LocalDate.now( zonedId );
    static int week = 7 - LocalDate.now( zonedId ).getDayOfWeek().getValue();
    static LocalDate niedz = LocalDate.now( zonedId ).plusDays(week);
    String start,end;
//    static LocalDate SeriouslyToday = LocalDate.now( zonedId ).plusDays(week);
    public PlanSwitch(int childID) throws SQLException {
                uczen = getChildInformation.Get(childID);
        klasa = getChildInformation.GetKlasa(uczen.getID_KLASY());
//        przedmioties = getChildSubject.Get(klasa.getID());
        plan = getChildSubject.GetP(klasa.getID());

        // add an appointment
//        System.out.println(LocalDate.now().atTime(4, 00));
//        agenda.appointments().addAll(
//                new Agenda.AppointmentImplLocal()
//                        .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
//                        .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
//                        .withDescription("It's time")
//                        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
//        );

                for(Plan sub : plan) {
//                    int week =  LocalDate.now( zonedId ).getDayOfWeek().getValue();
                    //chce uzyskać pon, wt zawsze
//                    LocalDate tmpday = LocalDate.now( zonedId ).minusDays(sub.DZIEN);
                    TemporalField fieldUS = WeekFields.of(Locale.GERMANY).dayOfWeek();
                    System.out.println(today.with(fieldUS, sub.getDZIEN()));
                    //int temphour;
                    if((sub.getGODZINA()+7)<10) {
                         start = today.with(fieldUS, sub.DZIEN) + "T0" + String.valueOf((sub.getGODZINA()+7)) + ":00";
                         end = today.with(fieldUS, sub.DZIEN) + "T0" + String.valueOf((sub.getGODZINA()+7)) + ":00";
                    } else {
                         start = today.with(fieldUS, sub.DZIEN) + "T" + String.valueOf((sub.getGODZINA()+7)) + ":00";
                         end = today.with(fieldUS, sub.DZIEN) + "T" + String.valueOf((sub.getGODZINA()+7)) + ":00";
                    }
                    System.out.println(start);
                    agenda.appointments().addAll(
                            new Agenda.AppointmentImplLocal()
                                    .withStartLocalDateTime(LocalDateTime.parse(start))
                                    .withEndLocalDateTime(LocalDateTime.parse(end).plusHours(1))
//                                    .withDescription(sub.getNAZWA())
                                    .withSummary(sub.getNAZWA())
                                    .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
                    );
//sub.
                }


        // show it
        Stage stage = new Stage();
        stage.setTitle("Plan ucznia");
        stage.getIcons().add(new Image("icon.png"));

        stage.setScene(new Scene(agenda, 800, 600));
        stage.show();
    }

}
