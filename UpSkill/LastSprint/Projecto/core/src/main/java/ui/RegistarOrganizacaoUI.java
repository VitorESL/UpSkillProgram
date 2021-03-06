package ui;

import application.ServiceController;
import application.RegistarOrganizacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class RegistarOrganizacaoUI implements Initializable {

    public TextField txtNomeGestor;
    public TextField txtContactoGestor;
    public TextField txtEmailGestor;
    public TextField txtNomeOrg;
    public TextField txtNIFOrg;
    public TextField txtMoradaOrg;
    public TextField txtLocalidadeOrg;
    public TextField txtCodigoPostalOrg;
    public TextField txtTelefoneOrg;
    public TextField txtWebsiteOrg;
    public TextField txtEmailOrg;
    public Button btnVoltar;
    public Button limparDados;
    public Button registarOrganizacao;

    private ServiceController serviceController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceController = new ServiceController();
    }


    //registar organização
    public void registarOrganizacaoAction(ActionEvent actionEvent) {
        try {
            RegistarOrganizacaoController controller = new RegistarOrganizacaoController();

            boolean added = controller.registarOrganizacao(
                    txtNomeOrg.getText().trim(),
                    Integer.parseInt(txtNIFOrg.getText()),
                    txtWebsiteOrg.getText().trim(),
                    Integer.parseInt(txtTelefoneOrg.getText()),
                    txtEmailOrg.getText().trim(),
                    txtMoradaOrg.getText().trim(),
                    txtLocalidadeOrg.getText().trim(),
                    txtCodigoPostalOrg.getText().trim(),
                    txtNomeGestor.getText().trim(),
                    Integer.parseInt(txtContactoGestor.getText()),
                    txtEmailGestor.getText().trim()
            );

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar nova organização.",
                    added ? "Organização criada com sucesso! \n\n" +
                            serviceController.getOrganizacaoToStringCompletoByEmail(txtEmailGestor.getText().trim()) //MUDAR
                            : "Não foi possível registar a organização.").showAndWait();
            if (added) {
                limparDados();
                voltarJanelaInicial();
            }

        } catch (NumberFormatException nfe) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Letras em campos de valores numéricos (NIF, Contacto Gestor ou Telefone Organização) ou campos em vazio.").show();
        } catch (IllegalArgumentException iae) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();
        } catch (SQLException throwables) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro de SQL.",
                    throwables.getMessage()).show();
            throwables.printStackTrace();
        }
    }

    //limpar todos os campos
    public void limparAction(ActionEvent actionEvent) {
        limparDados();
    }

    public void limparDados() {
        txtNomeOrg.clear();
        txtNIFOrg.clear();
        txtWebsiteOrg.clear();
        txtTelefoneOrg.clear();
        txtEmailOrg.clear();
        txtMoradaOrg.clear();
        txtLocalidadeOrg.clear();
        txtCodigoPostalOrg.clear();
        txtNomeGestor.clear();
        txtContactoGestor.clear();
        txtEmailGestor.clear();
    }

    //voltar ao menu inicial
    public void voltarAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Voltar à janela inicial",
                "Vai perder os dados inseridos!", "Deseja mesmo voltar à janela inicial?");

        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            limparDados();
            voltarJanelaInicial();
        }
    }

    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }


}


