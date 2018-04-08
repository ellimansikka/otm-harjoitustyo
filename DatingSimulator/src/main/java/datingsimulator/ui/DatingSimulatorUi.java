
package datingsimulator.ui;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import datingsimulator.domain.PelaajaKayttoliittyma;
import datingsimulator.domain.Tulos;
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
import datingsimulator.dao.FileTulosDao;
import datingsimulator.dao.FilePelaajaDao;

public class DatingSimulatorUi extends Application {
    private PelaajaKayttoliittyma kayttoliittyma;
    @Override
    public void start(Stage ikkuna) {
        ikkuna.setTitle("Dating Simulator");
        VBox kirjautumisAsettelu = new VBox();
        kirjautumisAsettelu.setSpacing(10);
        
        kirjautumisAsettelu.getChildren().add(new Button("Kirjaudu sisään"));
        kirjautumisAsettelu.getChildren().add(new Button("Luo tunnus"));
       
        
        Scene nakyma = new Scene(kirjautumisAsettelu);
        
        ikkuna.setScene(nakyma);
        ikkuna.show();
    }
    
    public static void main(String[] args) {
        launch(DatingSimulatorUi.class);
    }
    
}
