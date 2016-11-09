package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    private static Main instance;
    public static Stage stage;
    public static int employeeId;
    public boolean correct = false;

    @FXML
    public TextField username;
    @FXML
    private PasswordField password;
    @FXML
    public AnchorPane thepane;
    @FXML
    private Button loginbut;
    @FXML
    private RadioButton ptrb, iarb, ccrb;
    @FXML
    private ChoiceBox dropDown;




    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("newscreen.fxml"));
        stage = primaryStage;
        stage.setResizable(false);
        primaryStage.setTitle("Kingfisher Time Login");
        primaryStage.setScene(new Scene(root, 350, 450));
        primaryStage.show();
    }





    //ObservableList<String> dropDownlist = FXCollections.observableArrayList("First", "Second", "Third");


    @FXML
    public void initialize() {
        System.out.println("second");
        //dropDown.setValue("ji");
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


    @FXML
    public void logIn(ActionEvent actionEvent) throws Exception{


        if(ptrb.isSelected() && iarb.isSelected() && ccrb.isSelected() || ptrb.isSelected() && iarb.isSelected() || ptrb.isSelected() && ccrb.isSelected() || ccrb.isSelected() && iarb.isSelected()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Please choose one option");
            alert.setHeaderText(null);
            alert.setContentText("You have selected too many options, please select only one.");


            alert.showAndWait();

            iarb.setSelected(false);
            ccrb.setSelected(false);
            ptrb.setSelected(false);


        } else if (ptrb.isSelected()) {
            System.out.println("hibro");
            iarb.setSelected(false);
            ccrb.setSelected(false);
            dbConnect();
            if (correct == true) {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Project.fxml"));/* Exception */
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else if (iarb.isSelected()) {
            System.out.println("hibro");
            iarb.setSelected(false);
            ccrb.setSelected(false);
            dbConnect();
            if (correct == true) {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Activity.fxml"));/* Exception */
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else if (ccrb.isSelected()) {
            System.out.println("hibro");
            iarb.setSelected(false);
            ccrb.setSelected(false);
            dbConnect();
            if (correct == true) {
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("CostCenter.fxml"));/* Exception */
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }


        }




    public void dbConnect() {
        Connection connection;
        PreparedStatement ps;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            ps = connection.prepareStatement("SELECT `employee_id`, `employee_password` FROM `employee` WHERE `employee_id` = ? AND `employee_password` = ?");
            ps.setString(1, username.getText());
            ps.setString(2, String.valueOf(password.getText()));
            String employeeIdInt = username.getText();
            employeeId = Integer.parseInt(employeeIdInt);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                System.out.println("Working");
                //mainMenuEmployee.start();
                correct = true;
            } else {
                System.out.println("Not");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Incorrect Login");
                alert.setHeaderText(null);
                alert.setContentText("You have entered the wrong login details, please try again.");

                alert.showAndWait();

            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Not");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Login");
            alert.setHeaderText(null);
            alert.setContentText("You have entered the wrong login details, please try again.");

            alert.showAndWait();
        } catch (NumberFormatException nw) {
            System.out.println("Not");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Login");
            alert.setHeaderText(null);
            alert.setContentText("You have entered the wrong login details, please try again.");

            alert.showAndWait();
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}
