package br.com.felipe.AppPonto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaSaida extends Application {

    String funcionalJanela1;
    String dataJanela1;
    String horaJanela1;

    Stage stage = new Stage();
    AnchorPane pane = new AnchorPane();
    Scene scene = new Scene(pane);
    RadioButton entrada = new RadioButton("Entrada");
    RadioButton saida = new RadioButton("Saída");
    String parsed = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    Label horario = new Label(String.valueOf("Horário a registrar: " + parsed));
    ImageView close = new ImageView(new Image(getClass().getResource("/br/com/felipe/AppPonto/close.png").toExternalForm()));
    Button btClose = new Button();
    Button btRegistra = new Button("Registrar");
    ToggleGroup group = new ToggleGroup();

    public EntradaSaida(String funcionalJanela1, String dataJanela1, String horaJanela1){
        this.funcionalJanela1 = funcionalJanela1;
        this.dataJanela1 = dataJanela1;
        this.horaJanela1 = horaJanela1;
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        stage.setScene(scene);

        entrada.setToggleGroup(group);
        saida.setToggleGroup(group);
        pane.setStyle("-fx-border-color: ORANGERED;" + "-fx-border-width: 2px");

        close.setFitWidth(25);
        close.setFitHeight(25);

        btClose.setGraphic(close);

        System.out.println(funcionalJanela1);

        pane.getChildren().addAll(entrada, saida, horario, btClose, btRegistra);
        pane.setPrefSize(350, 300);

        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();

        initLayout();
        initListeners();

        System.out.println();

    }

    public void initLayout(){

        btClose.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        btClose.setLayoutY(10);
        btClose.setLayoutX(pane.getWidth()-45);

        entrada.setLayoutX((pane.getWidth()-entrada.getWidth())/2);
        entrada.setLayoutY((pane.getHeight()-entrada.getHeight())/2-15);
        saida.setLayoutX(entrada.getLayoutX());
        saida.setLayoutY(entrada.getLayoutY()+35);

        horario.setFont(Font.font("Lucida Grande", FontWeight.BOLD, 15));
        horario.setTextFill(Color.ORANGERED);
        horario.setLayoutX((pane.getWidth()-horario.getWidth())/2-20);
        horario.setLayoutY(25);

        btRegistra.setLayoutX((pane.getWidth()- btRegistra.getWidth())/2 - 20);
        btRegistra.setLayoutY(pane.getHeight()-75);
        btRegistra.setStyle("-fx-background-color: white; "    // Cor de fundo branca quando o mouse sai
                + "-fx-text-fill: ORANGERED; "        // Cor do texto laranja
                + "-fx-font-size: 14px; "          // Tamanho da fonte
                + "-fx-padding: 10px 20px; "       // Padding interno do botão
                + "-fx-border-color: ORANGERED; "    // Cor da borda laranja
                + "-fx-border-width: 2px; "       // Espessura da borda
                + "-fx-border-radius: 0px; "
                + "-fx-font-weight: bold;");

    }

    public void initListeners(){
        btClose.setOnAction(e -> stage.close());
        btRegistra.setOnAction(e -> registraPontoJDBC());
    }

    public void registraPontoJDBC(){
        if(group.getSelectedToggle().isSelected()){
            ConexaoJDBC conn = new ConexaoJDBC();
            conn.registra(funcionalJanela1, dataJanela1, horaJanela1, entrada.isSelected());
            JOptionPane.showMessageDialog(null, "Ponto registrado às " + parsed);
            stage.close();
        }
    }

}
