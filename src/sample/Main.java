package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.stage.*;
import java.io.File;

public class Main extends Application {

    String path;
    Label label;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PornHub");

        Group root = new Group();
        Scene scene = new Scene(root, 320, 250, Color.WHITE);

        BorderPane borderPane = new BorderPane();

        root.getChildren().add(borderPane);

        VBox vbox = new VBox();

        HBox hbox = new HBox(5);

        label = new Label("Suka?");
        label.setFont(Font.font("SanSerif", 20));

        TextField url = new TextField();
        url.setFont(Font.font("SanSerif", 15));
        url.setPromptText("Url");

        TextField textFieldPath = new TextField();
        textFieldPath.setFont(Font.font("SanSerif", 15));
        textFieldPath.setPromptText("Path");

        Button destinationFile = new Button("Destination");
        destinationFile.setOnAction(e ->{
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if(selectedDirectory == null){

            }else{
                path = selectedDirectory.getAbsolutePath();
                textFieldPath.setText(path);
                System.out.println(selectedDirectory.getAbsolutePath());
            }
        });

        Button download = new Button("Download");
        download.setOnAction(e ->{

            try {

                String retValue_ph = Search.Query(url.getText());

                String[] value_ph = retValue_ph.split(";");

                System.out.println("Title: " + value_ph[0]);
                System.out.println("Url: " + value_ph[1]);

                DownloadManager.downloadFileAsync(value_ph[1], textFieldPath.getText(), value_ph[0]);

            }catch (Exception ex){
                System.err.println(ex);
            }
        });
        hbox.getChildren().addAll(textFieldPath, destinationFile);
        vbox.getChildren().addAll(url, hbox, download, label);
        label.setPadding(new Insets(50,0,0,100));
        vbox.setPadding(new Insets(12));
        borderPane.setCenter(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
