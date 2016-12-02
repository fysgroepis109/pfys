package pyfs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 *
 *
 * @author IS109-Groep 5
 *
 */
public class pyfs extends Application {

    /* Code is ingedeeld per sectie (login, menu, lost, found en stat)
    Aan het begin worden alle controls aangeroepen, dit is zo ingedeeld dat je
    snel kan onderscheiden welke control op welke pagina zit. Verder in de code zijn
    alle secties onderscheidde doormiddel van comments. Door control f te doen kan je snel naar de juiste sectie
    springen. Alle eens sectie meerder paginas heeft wordt dit doormiddel van een numering gedaan bijv. lost (eerste pag) lost1, lost2, lost 3 & lostfinal (laatste pag)

     */
    Stage thestage;

    //login
    Scene loginscherm;
    Button loginbtn;
    StackPane inlogschermpane;

    //Menu
    Button statbtn, lostbtn, foundbtn, logoutbtn;
    StackPane menupane;
    Scene menu;

    //Lost
    Button lostterugmenu, lostnext, lostnext2, lostback, lostback2, search, lostterugfinal, lostnext3, lostback3;
    TextField username;
    PasswordField password;
    StackPane lostpane, lost2pane, lost3pane, lost4pane, lostfinalpane;
    Scene lost, lost2, lost3, lost4, lostfinal;

    //Found
    Button foundterugmenu, foundnext, foundnext2, foundnext3, find, foundback, foundback2, foundfinalButton;
    StackPane foundpane, found2pane, found3pane, foundfinalpane;
    Scene found, found2, found3, foundfinal;

    //Stat
    Button statterugmenu;
    StackPane statpane;
    Scene stat;

    @Override
    public void start(Stage primaryStage) {

        thestage = primaryStage; //verklaart toegoevoegde stage
        Found found1 = new Found(); //Maakt nieuwe Found genaamd found1
        Lost lost1 = new Lost();    //maakt niuewe Lost genaamd lost1
        Login login = new Login();  //maakt nieuwe Login genaamd login
        mysql Mysql = new mysql();

        //BEGIN CONTROLS
        //Loginscherm
        Button loginbtn = new Button();
        loginbtn.setText("Login");                                           //inlog button
        loginbtn.setPrefSize(200, 50);
        loginbtn.setTranslateY(90);
        loginbtn.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String UserName = login.getTextUsername();   //getting username
                String Password = login.getTextPassword();   //getting password

                Connection conn;                                                            //making connection to database

                final String USERNAME = Mysql.username();
                final String PASSWORD = Mysql.password();
                final String CONN_STRING = Mysql.urlmysql();

                try {

                    conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
                    System.out.println("Connected!");
                    Statement stmt = (Statement) conn.createStatement();
                    ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) AS total FROM login WHERE naam= " + '"' + UserName + '"');   //check if there is a accout with name
                    int count = 0;

                    while (rs1.next()) {

                        count = rs1.getInt("total");

                    }

                    ResultSet rs = stmt.executeQuery("SELECT * FROM login WHERE naam = " + "'" + UserName + "'");               //getting password form database

                    if (count > 0) {

                        while (rs.next()) {

                            String pass = rs.getString("wachtwoord");
                            if (pass.equals(Password)) {                         // check if passwords are the same

                                thestage.setScene(menu);

                            } else {

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("waarschuwing");
                                alert.setHeaderText("username and/or password are incorrect");
                                alert.showAndWait();

                            }

                        }

                    } else {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("waarschuwing");
                        alert.setHeaderText("username and/or password are incorrect");
                        alert.showAndWait();

                    }

                } catch (SQLException ed) {

                    System.err.println(ed);

                }

            }

        });

        //verklaren alle toegevoegde controls
        //menu
        logoutbtn = new Button();

        logoutbtn.setText("Logout");                                           //logoutbutton
        logoutbtn.setPrefSize(200, 50);
        logoutbtn.setTranslateY(-370);
        logoutbtn.setTranslateX(700);
        logoutbtn.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        logoutbtn.setOnAction((ActionEvent event) -> {

            thestage.setScene(loginscherm);

        });

        lostbtn = new Button();
        lostbtn.setText("Lost");                                           //lost button
        lostbtn.setPrefSize(200, 50);
        lostbtn.setTranslateX(-500);
        lostbtn.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostbtn.setOnAction((ActionEvent event) -> {

            thestage.setScene(lost);

        });

        foundbtn = new Button();
        foundbtn.setText("Found");                                           //lost button
        foundbtn.setPrefSize(200, 50);
        foundbtn.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        foundbtn.setOnAction((ActionEvent event) -> {

            thestage.setScene(found);

        });

        statbtn = new Button();
        statbtn.setText("Statistics");                                           //lost button
        statbtn.setPrefSize(200, 50);
        statbtn.setTranslateX(500);
        statbtn.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        statbtn.setOnAction((ActionEvent event) -> {

            thestage.setScene(stat);

        });

        //lost
        //lost 1
        lostterugmenu = new Button();
        lostterugmenu.setText("Back");                                           //logoutbutton
        lostterugmenu.setPrefSize(200, 50);
        lostterugmenu.setTranslateY(-370);
        lostterugmenu.setTranslateX(700);
        lostterugmenu.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostterugmenu.setOnAction((ActionEvent event) -> {

            thestage.setScene(menu);

        });

        lostnext = new Button();
        lostnext.setText("Next");                                           //logoutbutton
        lostnext.setPrefSize(120, 50);
        lostnext.setTranslateY(105);
        lostnext.setTranslateX(55);
        lostnext.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostnext.setOnAction((ActionEvent event) -> {

            thestage.setScene(lost2);

        });

        //lost 2
        lostback = new Button();
        lostback.setText("Back");                                           //logoutbutton
        lostback.setPrefSize(200, 50);
        lostback.setTranslateY(-370);
        lostback.setTranslateX(700);
        lostback.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostback.setOnAction((ActionEvent event) -> {

            thestage.setScene(lost);

        });

        lostnext2 = new Button();
        lostnext2.setText("Next");                                           //logoutbutton
        lostnext2.setPrefSize(120, 50);
        lostnext2.setTranslateY(105);
        lostnext2.setTranslateX(55);
        lostnext2.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostnext2.setOnAction((ActionEvent event) -> {

            thestage.setScene(lost3);

        });

        lostback2 = new Button();
        lostback2.setText("Back");                                           //logoutbutton
        lostback2.setPrefSize(200, 50);
        lostback2.setTranslateY(-370);
        lostback2.setTranslateX(700);
        lostback2.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostback2.setOnAction((ActionEvent event) -> {

            thestage.setScene(lost2);

        });

        lostnext3 = new Button();
        lostnext3.setText("Next");                                           //logoutbutton
        lostnext3.setPrefSize(120, 50);
        lostnext3.setTranslateY(175);
        lostnext3.setTranslateX(92);
        lostnext3.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostnext3.setOnAction((ActionEvent event) -> {

            thestage.setScene(lost4);

        });

        search = new Button();
        search.setText("Search");                                           //logoutbutton
        search.setPrefSize(120, 50);
        search.setTranslateY(175);
        search.setTranslateX(92);
        search.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        search.setOnAction((ActionEvent event) -> {

            thestage.setScene(lostfinal);

        });

        lostback3 = new Button();
        lostback3.setText("Back");                                           //logoutbutton
        lostback3.setPrefSize(200, 50);
        lostback3.setTranslateY(-370);
        lostback3.setTranslateX(700);
        lostback3.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostback3.setOnAction((ActionEvent event) -> {

            thestage.setScene(lost3);

        });

        //lost final
        lostterugfinal = new Button();
        lostterugfinal.setText("Menu");                                           //lost terug menu
        lostterugfinal.setPrefSize(200, 50);
        lostterugfinal.setTranslateY(-370);
        lostterugfinal.setTranslateX(700);
        lostterugfinal.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        lostterugfinal.setOnAction((ActionEvent event) -> {

            thestage.setScene(menu);

        });

        //found
        foundterugmenu = new Button();
        foundterugmenu.setText("Back");                                           //logoutbutton
        foundterugmenu.setPrefSize(200, 50);
        foundterugmenu.setTranslateY(-370);
        foundterugmenu.setTranslateX(700);
        foundterugmenu.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        foundterugmenu.setOnAction((ActionEvent event) -> {

            thestage.setScene(menu);

        });

        foundnext = new Button();
        foundnext.setText("Next");                                           //logoutbutton
        foundnext.setPrefSize(120, 50);
        foundnext.setTranslateY(105);
        foundnext.setTranslateX(55);
        foundnext.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        foundnext.setOnAction((ActionEvent event) -> {

            thestage.setScene(found2);

        });

        foundback = new Button();
        foundback.setText("Back");                                           //logoutbutton
        foundback.setPrefSize(200, 50);
        foundback.setTranslateY(-370);
        foundback.setTranslateX(700);
        foundback.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        foundback.setOnAction((ActionEvent event) -> {

            thestage.setScene(found);

        });

        foundnext2 = new Button();
        foundnext2.setText("Next");                                           //nextbutton
        foundnext2.setPrefSize(120, 50);
        foundnext2.setTranslateY(105);
        foundnext2.setTranslateX(55);
        foundnext2.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        foundnext2.setOnAction((ActionEvent event) -> {

            thestage.setScene(found3);

        });

        foundback2 = new Button();
        foundback2.setText("Back");                                           //logoutbutton
        foundback2.setPrefSize(200, 50);
        foundback2.setTranslateY(-370);
        foundback2.setTranslateX(700);
        foundback2.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        foundback2.setOnAction((ActionEvent event) -> {

            thestage.setScene(found2);

        });

        foundnext3 = new Button();
        foundnext3.setText("Place");                                           //logoutbutton
        foundnext3.setPrefSize(120, 50);
        foundnext3.setTranslateY(175);
        foundnext3.setTranslateX(92);
        foundnext3.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        foundnext3.setOnAction((ActionEvent event) -> {

            thestage.setScene(foundfinal);

        });

        //foundfinal
        foundfinalButton = new Button();
        foundfinalButton.setText("Menu");                                           //lost terug menu
        foundfinalButton.setPrefSize(200, 50);
        foundfinalButton.setTranslateY(-370);
        foundfinalButton.setTranslateX(700);
        foundfinalButton.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        foundfinalButton.setOnAction((ActionEvent event) -> {

            thestage.setScene(menu);

        });

        //stat
        statterugmenu = new Button();
        statterugmenu.setText("Back");                                           //logoutbutton
        statterugmenu.setPrefSize(200, 50);
        statterugmenu.setTranslateY(-370);
        statterugmenu.setTranslateX(700);
        statterugmenu.setStyle("-fx-base:darkcyan;-fx-border-color:black");
        statterugmenu.setOnAction((ActionEvent event) -> {

            thestage.setScene(menu);

        });

        //EINDE CONTROLS
        //PANES
        StackPane inlogschermpane = new StackPane();                                             //toevoegen button

        //Stackpane inlogscherm
        inlogschermpane.getChildren().add(loginbtn);                        //toevoegen button
        inlogschermpane.getChildren().add(login.username());                   //toevoegen username text
        inlogschermpane.getChildren().add(login.password());
        inlogschermpane.getChildren().add(login.logologin());
        inlogschermpane.getChildren().add(login.loginerror(false));
        inlogschermpane.setStyle("-fx-background-color:#FFFFFF");

        menupane = new StackPane();
        menupane.getChildren().add(logoutbtn);
        menupane.getChildren().add(lostbtn);
        menupane.getChildren().add(foundbtn);
        menupane.getChildren().add(statbtn);
        menupane.setStyle("-fx-background-color:#FFFFFF");

        lostpane = new StackPane();
        lostpane.setStyle("-fx-background-color:#FFFFFF");
        lostpane.getChildren().add(lostterugmenu);
        lostpane.getChildren().add(lost1.date());
        lostpane.getChildren().add(lost1.Time());
        lostpane.getChildren().add(lost1.airport());
        lostpane.getChildren().add(lostnext);
        
        lost2pane = new StackPane();
        lost2pane.setStyle("-fx-background-color:#FFFFFF");
        lost2pane.getChildren().add(lostback);
        lost2pane.getChildren().add(lost1.Naam());
        lost2pane.getChildren().add(lost1.adres());
        lost2pane.getChildren().add(lost1.City());
        lost2pane.getChildren().add(lost1.Zip());
        lost2pane.getChildren().add(lost1.country());
        lost2pane.getChildren().add(lost1.Phone());
        lost2pane.getChildren().add(lost1.Mail());
        lost2pane.getChildren().add(lostnext2);

        lost3pane = new StackPane();
        lost3pane.setStyle("-fx-background-color:#FFFFFF");
        lost3pane.getChildren().add(lost1.Lugtype());
        lost3pane.getChildren().add(lost1.Lugbrand());
        lost3pane.getChildren().add(lost1.Lugcolor());
        lost3pane.getChildren().add(lost1.lugspef());
        lost3pane.getChildren().add(lost1.Lugweight());
        lost3pane.getChildren().add(lostback2);
        lost3pane.getChildren().add(lostnext3);
        
        lost4pane = new StackPane();
        lost4pane.setStyle("-fx-background-color:#FFFFFF");
        lost4pane.getChildren().add(lost1.Labelnr());
        lost4pane.getChildren().add(lost1.Flightnr());
        lost4pane.getChildren().add(lost1.Destin());
        lost4pane.getChildren().add(search);
        lost4pane.getChildren().add(lostback3);

        lostfinalpane = new StackPane();
        lostfinalpane.setStyle("-fx-background-color:#FFFFFF");
        lostfinalpane.getChildren().add(lostterugfinal);

        foundpane = new StackPane();
        foundpane.setStyle("-fx-background-color:#FFFFFF");
        foundpane.getChildren().add(foundterugmenu);
        foundpane.getChildren().add(found1.date());
        foundpane.getChildren().add(found1.Time());
        foundpane.getChildren().add(found1.airport());
        foundpane.getChildren().add(foundnext);

        found2pane = new StackPane();
        found2pane.setStyle("-fx-background-color:#FFFFFF");
        found2pane.getChildren().add(found1.Labelnr());
        found2pane.getChildren().add(found1.Flightnr());
        found2pane.getChildren().add(found1.Destin());
        found2pane.getChildren().add(found1.NameTrav());
        found2pane.getChildren().add(foundback);
        found2pane.getChildren().add(foundnext2);
        
        found3pane = new StackPane();
        found3pane.setStyle("-fx-background-color:#FFFFFF");
        found3pane.getChildren().add(found1.Lugtype());
        found3pane.getChildren().add(found1.Lugbrand());
        found3pane.getChildren().add(found1.Lugcolor());
        found3pane.getChildren().add(found1.Lugspef());
        found3pane.getChildren().add(found1.Lugweight());
        found3pane.getChildren().add(foundback2);
        found3pane.getChildren().add(foundnext3);

        foundfinalpane = new StackPane();
        foundfinalpane.getChildren().add(foundfinalButton);
        foundfinalpane.setStyle("-fx-background-color:#FFFFFF");

        statpane = new StackPane();
        statpane.setStyle("-fx-background-color:#FFFFFF");
        statpane.getChildren().add(statterugmenu);

        //geeft alle scenes in
        loginscherm = new Scene(inlogschermpane, 1600, 800);
        menu = new Scene(menupane, 1600, 800);
        lost = new Scene(lostpane, 1600, 800);
        lost2 = new Scene(lost2pane, 1600, 800);
        lost3 = new Scene(lost3pane, 1600, 800);
        lost4 = new Scene(lost4pane, 1600, 800);
        lostfinal = new Scene(lostfinalpane, 1600, 800);
        found = new Scene(foundpane, 1600, 800);
        found2 = new Scene(found2pane, 1600, 800);
        found3 = new Scene(found3pane, 1600, 800);
        foundfinal = new Scene(foundfinalpane, 1600, 800);
        stat = new Scene(statpane, 1600, 800);

        primaryStage.setTitle("Applicatie naam");
        primaryStage.setScene(loginscherm);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     *
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {

        launch(args);

    }

}
