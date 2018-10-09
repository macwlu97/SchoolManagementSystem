package pl.edziennik.ParentSource.Controller;

//import pl.edziennik.Controller.AppParent;
//import pl.edziennik.DB;
import pl.edziennik.ParentSource.Helpers.Attendance;
import pl.edziennik.ParentSource.Helpers.Plan;
import pl.edziennik.ParentSource.Model.GetChildSubject;
import pl.edziennik.ParentSource.Model.DriverDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class selectAttendance extends JFrame implements ActionListener {
//    DriverDB tempLog;
    List<Plan> plan = new ArrayList<>();
    List<Attendance> tmp = new ArrayList<>();

    GetChildSubject getChildSubject = new GetChildSubject();
    int nieobecnosci;
    int obecnosci;
    int liczZajec;

    public selectAttendance(int child, int klasa, LocalDate today) throws SQLException {
//        tempLog = log;
//        try {
//            tempLog.polacz();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        plan = getChildSubject.GetP(klasa);

        tmp = getChildSubject.getAttendanceMyChild(child, today);


        ObecnoscControllerS.getLabTableModel().setRowCount(0);
        ObecnoscControllerS.getLabTableModel().setRowCount(10);
        ObecnoscControllerS.getLabTableModel().setValueAt(1, 0, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(2, 1, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(3, 2, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(4, 3, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(5, 4, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(6, 5, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(7, 6, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(8, 7, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(9, 8, 0);
        ObecnoscControllerS.getLabTableModel().setValueAt(10, 9, 0);
        for (Plan planx : plan) {
            System.out.println("(N)" + planx.NAZWA+ (planx.GODZINA - 8)+ planx.DZIEN);
            for (Attendance attendance : tmp) {
//                   Integer st = attendance.STAN;
                if (planx.ID_ZAJ.equals(attendance.ID_ZAJ)) {
                    switch (attendance.STAN) {
                        case 0:
                            System.out.println("(N)" + planx.NAZWA+ (planx.GODZINA - 8)+ planx.DZIEN);
                            ObecnoscControllerS.getLabTableModel().setValueAt("(N)" + planx.NAZWA, planx.GODZINA-1, planx.DZIEN);
                            break;
                        case 1:
                            System.out.println("(O)" + planx.NAZWA+ (planx.GODZINA - 8)+ planx.DZIEN);
                            ObecnoscControllerS.getLabTableModel().setValueAt("(O)" + planx.NAZWA, planx.GODZINA-1, planx.DZIEN);
                            break;
                        default:
                            break;
                    }
                }
            }

        }
//        for (Plan planx : plan) {
//
//
//
//
//                            ObecnoscControllerS.getLabTableModel().setValueAt("(N)" + planx.NAZWA, planx.GODZINA - 8, planx.DZIEN);
//
//                    }
//        for (Attendance attendance : tmp) {
//            System.out.println(attendance.ID_ZAJ+" "+attendance.STAN +" "+ attendance.DATA);
//        }




    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
