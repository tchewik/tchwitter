import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("tchwitter");
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    private int windowWidth = 550;
    private int windowHeight = 340;
    private Scene createScene(){
        GridPane grid = new GridPane();
        grid.setId("grid");
        grid.setHgap(5);
        grid.setVgap(5);

        Text title = new Text("Разберёшься как-нибудь");
        title.setId("title");
        grid.add(title, 0,  0, 2, 1);

        grid.setAlignment(Pos.BASELINE_CENTER);
        Label trashLabel = new Label("Найти:");
        trashLabel.setId("label");
        grid.add(trashLabel, 0, 2);

        TextField textFind = new TextField();
        textFind.setId("field");
        grid.add(textFind, 1, 2);

        Label putLabel = new Label("Заменить:");
        putLabel.setId("label");
        grid.add(putLabel, 0, 3);

        TextField textReplace = new TextField();
        textReplace.setId("field");
        grid.add(textReplace, 1, 3);

        Label timerLabel = new Label("Таймер (в минутах):");
        timerLabel.setId("label");
        grid.add(timerLabel, 0, 4);

        TextField textTimer = new TextField();
        textTimer.setId("field");
        grid.add(textTimer, 1, 4);

        Label timesLabel = new Label("Сколько раз:");
        timesLabel.setId("label");
        grid.add(timesLabel, 0, 5);

        TextField textHowM = new TextField();
        textHowM.setId("field");
        grid.add(textHowM, 1, 5);

        Label escapeLabel = new Label("(Опционально) Исключить слово:");
        escapeLabel.setId("labelOpt");
        grid.add(escapeLabel, 0, 6);

        TextField textEscape = new TextField();
        textEscape.setId("field");
        grid.add(textEscape, 1, 6);

        Button startBtn = new Button("Стартуем!");
        startBtn.setId("btn");
        startBtn.setMinSize(windowWidth, 1);
        startBtn.setOnAction(event -> twitting( textFind.getText(),
                                                textReplace.getText(),
                                                textTimer.getText(),
                                                textHowM.getText(),
                                                textEscape.getText()));

        grid.add(startBtn, 0, 7, 2, 1);

        Scene scene = new Scene(grid, windowWidth, windowHeight);
        scene.getStylesheets().add("style.css");
        return scene;
    }

    private void twitting(String whatReplace, String replaceWith, String textTimer, String textHowM, String textEscape) {
        try {
            int timer = (int)(60 * Double.parseDouble(textTimer));
            int times = Integer.parseInt(textHowM);
            String escape = textEscape.isEmpty() ? " " : textEscape;

            String command = String.format("python", "yellalenabot.py %s %s %d %d %s", whatReplace, replaceWith, timer, times, escape);
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.start();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}