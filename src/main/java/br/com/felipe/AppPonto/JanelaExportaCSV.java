package br.com.felipe.AppPonto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExportaCSV extends Application {

    Stage stage = new Stage();
    AnchorPane pane = new AnchorPane();
    Scene scene = new Scene(pane);
    ImageView close = new ImageView(new Image(getClass().getResource("/br/com/felipe/AppPonto/close.png").toExternalForm()));
    Button btClose = new Button();
    Button btExporta = new Button("Exportar CSV");
    TextField funcional = new TextField();
    Label txFuncional = new Label("Digite a funcional para obter o espelho de ponto:");
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        close.setFitWidth(25);
        close.setFitHeight(25);
        btClose.setGraphic(close);
        pane.setStyle("-fx-border-color: ORANGERED;" + "-fx-border-width: 2px");
        pane.getChildren().addAll(btClose, funcional, txFuncional, btExporta);
        pane.setPrefSize(350, 300);

        stage.show();
        initLayout();
        initListeners();


    }

    public void initLayout(){

        btClose.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        btClose.setLayoutY(10);
        btClose.setLayoutX(pane.getWidth()-45);

        funcional.setLayoutX((pane.getWidth()-funcional.getWidth())/2);
        funcional.setLayoutY(100);

        txFuncional.setLayoutX((pane.getWidth()- txFuncional.getWidth())/2);
        txFuncional.setLayoutY(funcional.getLayoutY()-30);

        btExporta.setLayoutY(pane.getHeight()-75);
        btExporta.setStyle("-fx-background-color: white; "    // Cor de fundo branca quando o mouse sai
                + "-fx-text-fill: ORANGERED; "        // Cor do texto laranja
                + "-fx-font-size: 14px; "          // Tamanho da fonte
                + "-fx-padding: 10px 20px; "       // Padding interno do botão
                + "-fx-border-color: ORANGERED; "    // Cor da borda laranja
                + "-fx-border-width: 2px; "       // Espessura da borda
                + "-fx-border-radius: 0px; "
                + "-fx-font-weight: bold;");
        btExporta.setLayoutX((pane.getWidth()- btExporta.getWidth())/2 - 25);

    }

    public void initListeners(){
        btClose.setOnAction(e -> stage.close());

        btExporta.setOnAction(e -> conexaoJDBC.exportaCSV(funcional, stage));
    }

    /*public void exportarCSV(){
        String sql = String.format("SELECT * FROM PONTO WHERE funcional = %s", funcional.getText());
        try (Connection conn = ConexaoJDBC.DatabaseHelper.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            PrintStream ps = new PrintStream("Ponto.csv");
            ps.println("Funcional;" + "Data;" + "Hora_Entrada;" + "Hora_Saida");
            while (rs.next()){
                ps.println(rs.getString("funcional") + ";" + rs.getString("dia") + ";" + rs.getString("hora_entrada") + ";" + rs.getString("hora_saida"));
            }
            ps.close();
            stage.close();
        }catch (Exception e){

        }

    }*/


}
