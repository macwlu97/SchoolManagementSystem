package pl.edziennik.ParentSource.Controller;

import pl.edziennik.ParentSource.Helpers.Attendance;
import pl.edziennik.ParentSource.Helpers.GradeRenderer;
import pl.edziennik.ParentSource.Helpers.Uczniowie;
import pl.edziennik.ParentSource.Model.DriverDB;
import pl.edziennik.ParentSource.Model.GetChildSubject;
import pl.edziennik.ParentSource.Model.GetStat;
import pl.edziennik.User.User;
import pl.edziennik.auth.authUsers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ObecnoscControllerS extends JFrame implements ActionListener {
    DriverDB tempLog;
//    List<Plan> plan = new ArrayList<>();
//    List<Attendance> tmp = new ArrayList<>();
//    List<Attendance> tmpStat = new ArrayList<>();
        public static JTable tab;
        static DefaultTableModel model;
//        authUsers authUser = new authUsers();
        public  JButton buttonMinus;
        public static JButton buttonNext ;
        static ZoneId zonedId = ZoneId.of( "Europe/Warsaw" );
//        static JLabel AktualnaDataObecnosci = new JLabel();
//        static int week = 7 - LocalDate.now( zonedId ).getDayOfWeek().getValue();
//        static LocalDate today = LocalDate.now( zonedId ).plusDays(week);
//        static LocalDate SeriouslyToday = LocalDate.now( zonedId ).plusDays(week);
    static JLabel AktualnaDataObecnosci;
    static int week;
    static LocalDate today;
    static LocalDate SeriouslyToday;
    public static JScrollPane sp;

    int liczZajec;
    int child;
    Uczniowie klasa;
    selectAttendance selectAttendance;
     JLabel  sredniaObecnosci;

    GetStat getStat;
        public ObecnoscControllerS(DriverDB log, int child, Uczniowie uczklasa) throws SQLException {
            this.tempLog = log;
            this.child = child;
            this.klasa = uczklasa;

            setSize(800, 300);
            setTitle("EDziennik - sds");
            setLayout(null);


            buttonMinus = new JButton("poprzedni tydzien");
            buttonNext = new JButton("nastepny tydzien");
            AktualnaDataObecnosci = new JLabel();
            week = 7 - LocalDate.now(zonedId).getDayOfWeek().getValue();
            today = LocalDate.now(zonedId).plusDays(week);
            System.out.println(today.minusDays(7)+" "+today);
            SeriouslyToday = LocalDate.now(zonedId).plusDays(week);

            model = new DefaultTableModel();
            tab = new JTable(model) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

            };


            model.addColumn("");
            model.addColumn("Poniedziałek");
            model.addColumn("Wtorek");
            model.addColumn("Sroda");
            model.addColumn("Czwartek");
            model.addColumn("Piątek");

            tab.getColumnModel().getColumn(0).setMaxWidth(1);
            tab.getColumnModel().getColumn(1).setCellRenderer(new GradeRenderer());
            tab.getColumnModel().getColumn(2).setCellRenderer(new GradeRenderer());
            tab.getColumnModel().getColumn(3).setCellRenderer(new GradeRenderer());
            tab.getColumnModel().getColumn(4).setCellRenderer(new GradeRenderer());
            tab.getColumnModel().getColumn(5).setCellRenderer(new GradeRenderer());
            tab.setBounds(0,50,600,160);
            add(tab);
//            sp = new JScrollPane(tab); // aby były suwaki i nagłówki kolumn!
//            sp.setBounds(0, 50, 600, 200);
//            add(sp);

            selectAttendance = new selectAttendance(child, klasa.getID_KLASY(), today);
            buttonMinus.setBounds(0, 0, 150, 50);
            buttonMinus.addActionListener(this);
            buttonNext.setBounds(640, 0, 150, 50);
            buttonNext.addActionListener(this);
            buttonNext.setEnabled(false);
            sredniaObecnosci = new JLabel();
            sredniaObecnosci.setBounds(640, 100, 100, 50);
            AktualnaDataObecnosci.setBounds(250, 0, 300, 50);
            AktualnaDataObecnosci.setText("Obecności w dniach: " + today.minusDays(6) + " - " + today);


            add(buttonMinus);
            add(buttonNext);
            add(AktualnaDataObecnosci);
            add(sredniaObecnosci);


            getStat = new GetStat();
            List<Attendance> tmpStat = getStat.getStatAttendanceMyChild(child);
            int nieobecnosci=0;
            int obecnosci=0;
            for (Attendance attendanceStat : tmpStat) {
                if (attendanceStat.STAN == 0) {
                    nieobecnosci++;
                } else if (attendanceStat.STAN == 1) {
                    obecnosci++;
                }

            }
           sredniaObecnosci.setText("<html><b>Nieobecności: " + nieobecnosci + "/" + tmpStat.size() + "<br> Obecności: " + obecnosci + "/" + tmpStat.size() + "</b></html>");



        }


    public static DefaultTableModel  getLabTableModel(){
        return model;

    }
//    public   static LocalDate getLocalDate(LocalDate tempLoc){
//        return today=tempLoc;
//
//    }
//    public  static LocalDate  getLocal2Date(LocalDate tempLoc){
//        return  SeriouslyToday = tempLoc;
//
//    }
//    public static JLabel getStatObecnosci(){
//        return sredniaObecnosci;
//    }
        @Override
        public void actionPerformed(ActionEvent e) {

            Object selekcja = e.getSource();

            if (selekcja == buttonMinus){
                System.out.println("powinno");
                try {
//                    selectAttendance selectAttendance;
                    selectAttendance = new selectAttendance( child, klasa.getID_KLASY(),today.minusDays(7));

                    today = today.minusDays(7);
                    AktualnaDataObecnosci.setText("Obecności w dniach: " + today.minusDays(6) +" - " + today);
                    if(today.minusDays(7).isBefore(SeriouslyToday) ){ buttonNext.setEnabled(true); }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (selekcja == buttonNext){
                System.out.println("powinno");
                try {
//                    selectAttendance selectAttendance;

                    selectAttendance = new selectAttendance( child, klasa.getID_KLASY(),today.plusDays(7));
                    today = today.plusDays(7);
                    System.out.println(today + " " + SeriouslyToday + " " );
                    AktualnaDataObecnosci.setText("Obecności w dniach: " + today.minusDays(6) +" - " + today);
                    if(today.isEqual(SeriouslyToday)) buttonNext.setEnabled(false);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }


        }

        public static void main(String[] args) {


//            LoginAsParent Okno = new LoginAsParent();
//            Okno.setIconImage(Toolkit.getDefaultToolkit().getImage("src/icon.png"));
//            Okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            Okno.setResizable(false);
//            Okno.setVisible(true);


        }
    }

