package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.w3c.dom.css.Rect;

import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by jsaich on 11/05/2016.
 */
public class Activity {

    public Date clockInTime = new Date();
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Main main = new Main();



    public String clockInTimeString = dateFormat.format(clockInTime);


    int testTime = main.employeeId;


    public void clockIn() {
        System.out.println("Your employee id is" + main.employeeId);

        System.out.println(dateFormat.format(clockInTime)); //2014/08/06 15:59:48
    }

    public void closeProject(ActionEvent actionEvent) throws Exception{
        System.out.println("hi");
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("newscreen.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clockOut() {
        System.out.println("fuck");
        Date clockOutTime = new Date();
        double difference = clockOutTime.getTime() - clockInTime.getTime();
        System.out.println(((difference/1000)/60)/60 + "print"); //2014/08/06 15:59:48


        System.out.println((difference/1000)/60 + "print2");
        double timeElapsed = (((difference/1000)/60));


        Connection connection;
        PreparedStatement ps;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");


            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = connection.createStatement();

            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO `clock`(fk_employee_id, clock_in_time, approval) VALUE ('"+main.employeeId+"',"+timeElapsed+", 'P')");
            pstmt.executeUpdate();

            // stmt.executeUpdate("INSERT INTO `clock`(clock_time_id, fk_employee_id) VALUE ('?,'"+testTime+"')");


        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }






    @FXML
    Rectangle selected1;
    @FXML
    Rectangle selected2;
    @FXML
    Rectangle selected3;
    @FXML
    Rectangle selected4;

    public void selected1click() {
        System.out.println("Click1");
        if (selected1.getOpacity() == 0) {
            selected2.setOpacity(0);
            selected3.setOpacity(0);
            selected4.setOpacity(0);
            selected1.setOpacity(0.3);
        } else {
            selected1.setOpacity(0);
        }
    }

    public void selected2click() {
        System.out.println("Click2");
        if (selected2.getOpacity() == 0) {
            selected1.setOpacity(0);
            selected2.setOpacity(0.3);
            selected3.setOpacity(0);
            selected4.setOpacity(0);
        } else {
            selected2.setOpacity(0);
        }
    }
    public void selected3click() {
        System.out.println("Click2");
        if (selected3.getOpacity() == 0) {
            selected1.setOpacity(0);
            selected2.setOpacity(0);
            selected3.setOpacity(0.3);
            selected4.setOpacity(0);
        } else {
            selected3.setOpacity(0);
        }
    }public void selected4click() {
        System.out.println("Click2");
        if (selected4.getOpacity() == 0) {
            selected1.setOpacity(0);
            selected2.setOpacity(0);
            selected3.setOpacity(0);
            selected4.setOpacity(0.3);
        } else {
            selected4.setOpacity(0);
        }
    }


}


/*

    DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date1 = new Date();
System.out.println(dateFormat1.format(date1));

        String dateString1 = dateFormat1.format(date1);


        Date date1 = format.parse(date);
        Date date2 = format.parse(date1);
        long difference = date2.getTime() - date1.getTime();

        long difference = date1.getTime() - date.getTime();

        System.out.println("lol" + difference);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        String dateString = dateFormat.format(date);*/

