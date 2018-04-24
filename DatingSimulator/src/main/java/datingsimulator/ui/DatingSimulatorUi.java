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
import datingsimulator.dao.StoryReader;
import datingsimulator.domain.Logic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.WindowEvent;

public class DatingSimulatorUi extends Application {

    private PlayerService service;

    private Label menuLabel = new Label();
    private Scene playerScene;
    private Scene resultsScene;
    private Scene loginScene;
    private Scene newPlayerScene;
    private Scene gameScene;
    private StoryReader storyReader;
    private Logic logic;
    private VBox resultNodes;
    private VBox gameNodes;
    private HBox gameButtons;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        String playerFile = properties.getProperty("playerFile");
        String resultFile = properties.getProperty("resultFile");
        String storyFile = properties.getProperty("storyFile");
        String finalAnswersFile = properties.getProperty("finalAnswersFile");

        FilePlayerDao playerDao = new FilePlayerDao(playerFile);
        FileResultDao resultDao = new FileResultDao(resultFile, playerDao);
        storyReader = new StoryReader(storyFile, finalAnswersFile);
        
        service = new PlayerService(resultDao, playerDao);
    }

    public Node createResultNode(int result) {
        HBox box = new HBox(10);
        Label label = new Label(Integer.toString(result));
        label.setMinHeight(20);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));

        box.getChildren().addAll(label, spacer);
        return box;
    }
    

    public void redrawResultList() {
        resultNodes.getChildren().clear();
        List<Result> results = service.getPlayersResults();
        List<Integer> points = new ArrayList<>();
        for (Result r : results) {
            points.add(r.getResult());
        }
        Collections.sort(points);
        List<Integer> bestResults = new ArrayList<>();
        int i = 1;
        while (i <= 10) {
            if (points.size() >= i) {
                bestResults.add(points.get(points.size() - i));
                i++;
            } else {
                break;
            }
        }
        
        bestResults.forEach(result -> {
            resultNodes.getChildren().add(createResultNode(result));
        });
    }
//    
//    public void redrawGame() throws Exception {
//        gameNodes.getChildren().clear();
//        gameButtons.getChildren().clear();
//        gameButtons.setPadding(new Insets(10));
//        
//        button1 = new Button(logic.getPlayersReplyToButton(1));
//        button2 = new Button(logic.getPlayersReplyToButton(2));
//        button3 = new Button(logic.getPlayersReplyToButton(3));
//        gameButtons.getChildren().addAll(button1, button2, button3);
//        
//        Label datesReply = new Label(logic.getDatesReply());
//        gameNodes.getChildren().addAll(datesReply, gameButtons);
//    }
//    
//    public void redrawFinal() throws Exception {
//        gameNodes.getChildren().clear();
//        gameButtons.getChildren().clear();
//        
//        Label datesReply = new Label(logic.getFinalReply());
//        int points = logic.getPoints();
//        Label results = new Label("Result: " + Integer.toString(points));
//        service.createResult(points,  service.getLoggedInPlayer().getName());
//        gameNodes.getChildren().addAll(datesReply, results);
//    }
//    
//    public void play() throws Exception {
//        while (logic.continueGame()) {
//            
//            button1.setOnAction(e -> {
//                try {
//                    logic.findNextAndUpdate(1);
//                    redrawGame();
//                } catch (Exception ex) {
//                    
//                }
//            });
//            button2.setOnAction(e -> {
//                try {
//                    logic.findNextAndUpdate(2);
//                    redrawGame();
//                } catch (Exception ex) {
//                    
//                }
//            });
//            button3.setOnAction(e -> {
//                try {
//                    logic.findNextAndUpdate(3);
//                    redrawGame();
//                } catch (Exception ex) {
//                    
//                }
//            });
//        }
//        redrawFinal();
//        
//    }

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

        loginButton.setOnAction(e -> {
            String name = nameInput.getText();
            menuLabel.setText(name + " logged in");
            if (service.logIn(name)) {
                loginMessage.setText("");
                primaryStage.setScene(playerScene);
                redrawResultList();
                nameInput.setText("");
            } else {
                loginMessage.setText("User does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });

        createButton.setOnAction(e -> {
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

        createNewUserButton.setOnAction(e -> {
            String name = newNameInput.getText();

            if (name.length() < 2) {
                userCreationMessage.setText("Name too short");
                userCreationMessage.setTextFill(Color.RED);
            } else if (service.createPlayer(name)) {
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
        ScrollPane resultsScrollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(resultsScrollbar);
        playerScene = new Scene(mainPane, 300, 250);

        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button playButton = new Button("Play");
        Button logoutButton = new Button("Logout");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton, playButton);
        logoutButton.setOnAction(e -> {
            service.logOut();
            primaryStage.setScene(loginScene);
        });

        Label resultText = new Label("Top 10 Results");

        resultNodes = new VBox(10);
        resultNodes.setMaxWidth(150);
        resultNodes.setMinWidth(150);

        resultsScrollbar.setContent(resultNodes);
        mainPane.setLeft(resultText);
        mainPane.setTop(menuPane);

        
        
        // game scene
        VBox gamePane = new VBox(10);
//        gameNodes = new VBox(10);
//        gameNodes.setMaxWidth(900);
//        gameNodes.setMinWidth(900);
        Button quitButton = new Button("Quit");

        gamePane.getChildren().addAll(quitButton);
        playButton.setOnAction(e -> {
            primaryStage.setScene(gameScene);
//            logic = new Logic(storyReader);
//            try {
//                redrawGame();
//                play();
//            } catch (Exception ex) {
//                
//            }
        });
        
        quitButton.setOnAction(e -> {
            primaryStage.setScene(playerScene);
        });

        gameScene = new Scene(gamePane, 1000, 1000);

        
        
        // seutp primary stage
        primaryStage.setTitle("Dating Simulator player");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent e) -> {
            System.out.println("Closing");
            System.out.println(service.getLoggedInPlayer());
            if (service.getLoggedInPlayer() != null) {
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
