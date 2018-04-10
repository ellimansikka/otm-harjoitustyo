
package datingsimulator.ui;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import datingsimulator.domain.PlayerService;
import datingsimulator.domain.Result;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import datingsimulator.dao.FileResultDao;
import datingsimulator.dao.FilePlayerDao;
import javafx.stage.WindowEvent;


public class DatingSimulatorUi extends Application {
    private PlayerService service;
    
    private Label menuLabel = new Label();
    private Scene playerScene;
    private Scene resultsScene;
    private Scene loginScene;
    private Scene newPlayerScene;
    
    
     @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String playerFile = properties.getProperty("playerFile");
        String resultFile = properties.getProperty("resultFile");
            
        FilePlayerDao playerDao = new FilePlayerDao(playerFile);
        FileResultDao resultDao = new FileResultDao(resultFile, playerDao);
        service = new PlayerService(resultDao, playerDao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dating Simulator");
        VBox loginPane = new VBox();
        HBox textPane = new HBox(10);
        
        loginPane.setPadding(new Insets(10));
        loginPane.setSpacing(10);
                
        Label loginText = new Label("Name");
        TextField nameInput = new TextField();
        
        textPane.getChildren().addAll(loginText, nameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("Log in");
        Button createButton = new Button("Create new user");
       
        loginButton.setOnAction(e->{
            String name = nameInput.getText();
            menuLabel.setText(name + " logged in");
            if ( service.logIn(name) ){
                loginMessage.setText("");

                primaryStage.setScene(playerScene);  
                nameInput.setText("");
            } else {
                loginMessage.setText("User does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });
        
        
        createButton.setOnAction(e->{
            nameInput.setText("");
            primaryStage.setScene(newPlayerScene);   
        });  
        
        loginPane.getChildren().addAll(loginMessage, textPane, loginButton, createButton); 
        loginScene = new Scene(loginPane, 300, 250);
        
        
        
        //createNewPlayerScene
        
        VBox newPlayerPane = new VBox(10);
        
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField(); 
        Label newNameLabel = new Label("Name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);
        
        Label userCreationMessage = new Label();
        
        Button createNewUserButton = new Button("Create");
        createNewUserButton.setPadding(new Insets(10));
        
        createNewUserButton.setOnAction(e->{
            String name = newNameInput.getText();
   
            if (name.length()<2) {
                userCreationMessage.setText("Name too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if ( service.createPlayer(name) ){
                userCreationMessage.setText("");                
                loginMessage.setText("New player created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("Name has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
        }); 
        
        newPlayerPane.getChildren().addAll(userCreationMessage, newNamePane, createNewUserButton); 
       
        newPlayerScene = new Scene(newPlayerPane, 300, 250);
        
        // main scene
        
        ScrollPane resultsScollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(resultsScollbar);
        playerScene = new Scene(mainPane, 300, 250);
        
        HBox menuPane = new HBox(10);    
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button playButton = new Button("Play");
        Button logoutButton = new Button("Logout");      
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton, playButton);
        logoutButton.setOnAction(e->{
            service.logOut();
            primaryStage.setScene(loginScene);
        });        
        
        mainPane.setTop(menuPane);
        
        // seutp primary stage
        
        primaryStage.setTitle("Dating Simulator player");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent e)->{
            System.out.println("Closing");
            System.out.println(service.getLoggedInPlayer());
            if (service.getLoggedInPlayer()!=null) {
                e.consume();   
            }
            
        });
        
        
    }
    
    @Override
    public void stop() {
      System.out.println("Dating Simulator is closing");
    }    
    
    public static void main(String[] args) {
        launch(DatingSimulatorUi.class);
    }
    
}
