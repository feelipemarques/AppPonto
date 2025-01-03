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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends Application {

    Stage stage;
    AnchorPane pane = new AnchorPane();
    Scene scene = new Scene(pane);
    String parsedTotal = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    String parsedDia = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String parsedHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    Label funcional = new Label("Funcional:");
    Label senha = new Label("Senha:");
    TextField userID = new TextField();
    PasswordField password = new PasswordField();
    Label horario = new Label(String.valueOf(parsedTotal));
    Button btRefresh = new Button();
    Button btClose = new Button();
    Button btPonto = new Button("Registrar Ponto");
    Button btExportaCSV = new Button("Exportar CSV");
    ImageView logo = new ImageView(new Image(getClass().getResource("/br/com/felipe/AppPonto/logo.png").toExternalForm()));
    ImageView refresh = new ImageView(new Image(getClass().getResource("/br/com/felipe/AppPonto/refresh.jpg").toExternalForm()));
    ImageView close = new ImageView(new Image(getClass().getResource("/br/com/felipe/AppPonto/close.png").toExternalForm()));

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;

        pane.setPrefSize(650, 200);
        pane.setStyle("-fx-background-color: orange;" + "-fx-border-color: white; "    // Cor da borda laranja
                + "-fx-border-width: 4px; " );

        stage.initStyle(StageStyle.UNDECORATED);

        logo.setFitHeight(25);
        logo.setFitWidth(25);
        logo.setLayoutX(10);
        logo.setLayoutY(10);

        stage.setTitle("Sistema de Ponto");
        stage.setScene(scene);

        pane.getChildren().addAll(funcional, senha, userID, password, horario, btRefresh, logo, btPonto, btClose, btExportaCSV);
        stage.show();

        initLayout();
        initListeners();

    }

    public static void main(String[] args) {
        launch();
    }

    public void refreshHour(){
        horario.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    public void initListeners(){

        btExportaCSV.setOnAction(e -> abrirExportaCSV());

        btPonto.setOnAction(e -> abrirEntradaSaida());

        btRefresh.setOnAction(e -> refreshHour());

        btClose.setOnAction(e -> stage.close());

        btPonto.setOnMouseEntered(event -> {
            btPonto.setStyle(
                    "-fx-background-color: orange; "   // Cor de fundo laranja quando o mouse passa por cima
                            + "-fx-text-fill: white; "         // Cor do texto branca quando o mouse passa por cima
                            + "-fx-font-size: 14px; "          // Tamanho da fonte
                            + "-fx-padding: 10px 20px; "       // Padding interno do botão
                            + "-fx-border-color: orange; "    // Cor da borda laranja
                            + "-fx-border-width: 2px; "       // Espessura da borda
                            + "-fx-border-radius: 0px; "
                            + "-fx-font-weight: bold;"
                            + "-fx-border-color: white;"// Bordas quadradas
            );
        });
        btPonto.setOnMouseExited(event -> {
            btPonto.setStyle(
                    "-fx-background-color: white; "    // Cor de fundo branca quando o mouse sai
                            + "-fx-text-fill: orange; "        // Cor do texto laranja
                            + "-fx-font-size: 14px; "          // Tamanho da fonte
                            + "-fx-padding: 10px 20px; "       // Padding interno do botão
                            + "-fx-border-color: orange; "    // Cor da borda laranja
                            + "-fx-border-width: 2px; "       // Espessura da borda
                            + "-fx-border-radius: 0px; "
                            + "-fx-font-weight: bold;"// Bordas quadradas
            );
        });
    }

    public void initLayout(){

        btRefresh.setPrefSize(25,25);
        refresh.setFitHeight(25);
        refresh.setFitWidth(25);
        btRefresh.setGraphic(refresh);
        btRefresh.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        btRefresh.setLayoutY(45);
        btRefresh.setLayoutX(pane.getWidth()-horario.getWidth()-50);


        btClose.setPrefSize(30,30);
        close.setFitHeight(32);
        close.setFitWidth(32);
        btClose.setGraphic(close);
        btClose.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        btClose.setLayoutY(42);
        btClose.setLayoutX(btRefresh.getLayoutX() + 35);

        horario.setFont(Font.font("Lucida Grande", FontWeight.BOLD, 15));
        horario.setTextFill(Color.WHITE);
        horario.setLayoutY(20);
        horario.setLayoutX(pane.getWidth()-horario.getWidth()-50);

        funcional.setLayoutX(20);
        funcional.setLayoutY(50);
        funcional.setFont(Font.font("Lucida Grande", FontWeight.BOLD, 15));
        funcional.setTextFill(Color.WHITE);

        senha.setLayoutX(200);
        senha.setLayoutY(50);
        senha.setFont(Font.font("Lucida Grande", FontWeight.BOLD, 15));
        senha.setTextFill(Color.WHITE);

        userID.setLayoutX(20);
        userID.setLayoutY(80);
        userID.setPromptText("987000000");
        userID.setFont(Font.font("Lucida Grande"));

        password.setLayoutX(200);
        password.setLayoutY(80);
        password.setPromptText("******");

        btPonto.setLayoutX(funcional.getLayoutX());
        btPonto.setLayoutY(125);
        btPonto.setStyle(
                "-fx-background-color: white; "    // Cor de fundo branca
                        + "-fx-text-fill: orange; "        // Cor do texto laranja
                        + "-fx-font-size: 14px; "          // Tamanho da fonte
                        + "-fx-padding: 10px 20px; "       // Padding interno do botão
                        + "-fx-border-color: orange; "    // Cor da borda laranja
                        + "-fx-border-width: 2px; "       // Espessura da borda
                        + "-fx-border-radius: 2px; "
                        + "-fx-border-color: white;"
                        + "-fx-font-weight: bold;"// Bordas quadradas
        );

        btExportaCSV.setLayoutX(password.getLayoutX());
        btExportaCSV.setLayoutY(125);
        btExportaCSV.setStyle(
                "-fx-background-color: white; "    // Cor de fundo branca
                        + "-fx-text-fill: orange; "        // Cor do texto laranja
                        + "-fx-font-size: 14px; "          // Tamanho da fonte
                        + "-fx-padding: 10px 20px; "       // Padding interno do botão
                        + "-fx-border-color: orange; "    // Cor da borda laranja
                        + "-fx-border-width: 2px; "       // Espessura da borda
                        + "-fx-border-radius: 2px; "
                        + "-fx-border-color: white;"
                        + "-fx-font-weight: bold;"// Bordas quadradas
        );
        btExportaCSV.setOnMouseEntered(event -> {
            btExportaCSV.setStyle(
                    "-fx-background-color: orange; "   // Cor de fundo laranja quando o mouse passa por cima
                            + "-fx-text-fill: white; "         // Cor do texto branca quando o mouse passa por cima
                            + "-fx-font-size: 14px; "          // Tamanho da fonte
                            + "-fx-padding: 10px 20px; "       // Padding interno do botão
                            + "-fx-border-color: orange; "    // Cor da borda laranja
                            + "-fx-border-width: 2px; "       // Espessura da borda
                            + "-fx-border-radius: 0px; "
                            + "-fx-font-weight: bold;"
                            + "-fx-border-color: white;"// Bordas quadradas
            );
        });
        btExportaCSV.setOnMouseExited(event -> {
            btExportaCSV.setStyle(
                    "-fx-background-color: white; "    // Cor de fundo branca quando o mouse sai
                            + "-fx-text-fill: orange; "        // Cor do texto laranja
                            + "-fx-font-size: 14px; "          // Tamanho da fonte
                            + "-fx-padding: 10px 20px; "       // Padding interno do botão
                            + "-fx-border-color: orange; "    // Cor da borda laranja
                            + "-fx-border-width: 2px; "       // Espessura da borda
                            + "-fx-border-radius: 0px; "
                            + "-fx-font-weight: bold;"// Bordas quadradas
            );
        });


    }

    public void abrirEntradaSaida(){
        if(userID.getText().isBlank() || password.getText().isBlank()){
            System.out.println("Precisa preencher");
        }else {
            String sql = "SELECT * FROM AUTH";
            boolean autenticado = false;
            try(Connection conn = ConexaoJDBC.DatabaseHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()){
                    String funcional = rs.getString("funcional");
                    String senha = rs.getString("senha");

                    if(userID.getText().equals(funcional) && password.getText().equals(senha)) {
                        EntradaSaida cena = new EntradaSaida(userID.getText(), parsedDia, parsedHora);
                        try {
                            cena.start(new Stage());
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        autenticado = true;
                        break;
                    }
                }
                if(!autenticado){
                    JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorreto(s)!");
                }
            }catch(Exception e){
                System.out.println("Ruim na autenticação!");
            }

        }

    }

    public void abrirExportaCSV(){
        try{
            ExportaCSV exportaCSV = new ExportaCSV();
            exportaCSV.start(new Stage());
        }catch (Exception e){

        }
    }
}