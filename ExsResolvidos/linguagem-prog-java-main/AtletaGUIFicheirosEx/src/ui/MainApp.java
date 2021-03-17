package ui;

import controller.AppController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainApp extends Application {

    public static final String TITULO_APLICACAO = "Atletas";

    @Override
    public void start(Stage stage) throws Exception{
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/JanelaPrincipalScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle(TITULO_APLICACAO);
            stage.setScene(scene);

            stage.sizeToScene();
            stage.setResizable(false);

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                            "Confirma��o da a��o.", "Deseja mesmo encerrar a aplica��o?");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    } else {
                        AppController appController = ((JanelaPrincipalUI) loader.getController()).getAppController();
                        if (!appController.listaVazia() && !appController.serializarTodos()) {
                            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO, "Exportar Lista.",
                                    "Problema a exportar a lista de contactos!").show();
                        }
                    }
                }
            });
            stage.show();
        } catch (IOException ex) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO,
                    "Problemas no arranque da aplica��o.", ex.getMessage()).show();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
