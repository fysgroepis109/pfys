/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyfs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author Jaros
 */
public class Lostd {

    mysql Mysql = new mysql();
    Lost lost1 = new Lost();

    private String name;

    

    public int invullenP(String[]persoon) {

        final String USERNAME = Mysql.username();
        final String PASSWORD = Mysql.password();
        final String CONN_STRING = Mysql.urlmysql();

        Connection conn;
        int count5 = 0;
        int count6 = 0;
        try {

            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected persoon");
            Statement stmt = (Statement) conn.createStatement();

            ResultSet rs5 = stmt.executeQuery("SELECT COUNT(*) AS total FROM persoon where name = " + '"' + name + '"');

            while (rs5.next()) {
                count5 = rs5.getInt("total");
            }

            if (count5 > 0) {
                ResultSet rs6 = stmt.executeQuery("SELECT Pnr FROM persoon where name = " + '"' + name + '"');
                while (rs6.next()) {
                    count6 = rs6.getInt("Pnr");
                }

                System.out.println(count6);
            } else {
                ResultSet rs7 = stmt.executeQuery("SELECT COUNT(*) AS TOTAL FROM persoon");
                while (rs7.next()) {
                    count6 = rs7.getInt("total");
                    count6++;
                    System.out.println(count6);
                }

            }

        } catch (SQLException ed) {

            System.err.println(ed);

        }

        return count6;
    }

    
    public void invullenb(){
        
        
        try {

            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected persoon");
            Statement stmt = (Statement) conn.createStatement();

            ResultSet rs5 = stmt.executeQuery("SELECT COUNT(*) AS total FROM persoon where name = " + '"' + name + '"');

            while (rs5.next()) {
                count5 = rs5.getInt("total");
            }


        } catch (SQLException ed) {

            System.err.println(ed);

        }
        
            /*
            ResultSet rs1 = stmt.executeQuery("insert into bagage(Unr, Pnr, type, brand, colour, weight, spes, FLD) " + "values");

            ResultSet rs = stmt.executeQuery("count (*) from bagage where label = " + lost1.getTextLabelnr());

            int count = 0;
            while (rs1.next()) {
                count = rs1.getInt("total");

            }

            if (count > 0) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("warning");
                alert.setHeaderText("a bag whith the same label nummer has been found");
                alert.showAndWait();

                //DataTable airportData = dataBase.executeDataTableQuery("SELECT *  FROM bagage A inner join labels B on A.Unr = B.Unr inner join tijden C on A.Unr = C.Unr where FLD = F");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("warning");
                alert.setHeaderText("No bags whith the same labelnumber found, these results are bags without labels and matching spesifications");
                alert.showAndWait();

                //DataTable luggage information = dataBase.executeDataTableQuery("SELECT *  FROM bagage A inner join labels B on A.Unr = B.Unr inner join tijden C on A.Unr = C.Unr where FLD = F and labelnr = null and colour = " + lost1.getTextLugcolor());
            }
             */
    }
}
