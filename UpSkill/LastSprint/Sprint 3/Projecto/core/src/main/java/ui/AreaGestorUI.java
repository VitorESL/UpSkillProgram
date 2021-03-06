package ui;

import application.*;
import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AreaGestorUI implements Initializable {

    //Registar Colaborador elements
    public Button btnRegistarColaborador;
    public Button btnLimparRegistarColaborador;

    public TextField txtNomeColaborador;
    public TextField txtContactoColaborador;
    public TextField txtEmailColaborador;
    public TextField txtFuncaoColaborador;


    //Criar Tarefa Elements
    public Button btnRegistarTarefa;
    public Button btnLimparTarefa;

    public TextField txtCodigoUnicoTarefa;
    public TextField txtCustoTarefa;
    public TextArea txtDescInfTarefa;
    public TextArea txtDescTecnicaTarefa;
    public TextField txtDuracaoTarefa;
    public ComboBox<CategoriaTarefa> comboCategoria;
    public TextField txtNomeTarefa;


    //General Elements
    public Button btnSelecionarRegistarColaborador;
    public Button btnSelecionarCriarTarefa;
    public Button btnLogout;

    public BorderPane criarTarefaPane;
    public BorderPane registarColaboradorPane;
    public BorderPane homePane;
    public BorderPane seriacaoManualPane;
    public ListView<Candidatura> listViewCandidaturasPorSelecionarSeriacaoManual;
    public ListView<Colaborador> listViewColaboradoresPorSelecionarSeriacaoManual;
    public ListView<Candidatura> listViewCandidaturasSelecionadasSeriacaoManual;
    public ListView<Colaborador> listViewColaboradoresSelecionadosSeriacaoManual;
    public Button btnRemoverUltimaCandidatura;
    public Button btnRemoverUltimoColaborador;
    public BorderPane IniciarSeriacaoPane;
    public ListView<Anuncio> listViewAnunciosSeriarAnuncio;
    public BorderPane seriacaoAutomaticaPane;
    public ListView<Candidatura> listViewCandidaturasSeriarAnuncioSeriacaoAutomatica;
    public BorderPane publicarTarefaPane;
    public Button btnPublicarTarefa;
    public Button btnLimparDadosPublicarTarefa;
    public ListView<Tarefa> listViewTarefasMatchedPublicarTarefa;
    public DatePicker btnDataFimPub;
    public DatePicker btnDataInicioCand;
    public DatePicker btnDataFimCand;
    public DatePicker btnDataInicioSeriacao;
    public DatePicker btnDataFimSeriacao;
    public ComboBox<TipoRegimento> btnTipoRegimento;

    private RegistarColaboradorController registarColaboradorController;
    private AuthenticationController authController;
    private ServiceController serviceController;
    private DefinirTarefaController tarefaController;
    private PublicarTarefaController publicarTarefaController;
    private SeriarCandidaturaController seriarCandidaturaController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registarColaboradorController = new RegistarColaboradorController();
        authController = new AuthenticationController();
        serviceController = new ServiceController();
        tarefaController = new DefinirTarefaController();
        publicarTarefaController = new PublicarTarefaController();
        seriarCandidaturaController = new SeriarCandidaturaController();





        //popular combo boxes do painel Publicar Tarefa
        try {
            btnTipoRegimento.getItems().setAll(serviceController.getTiposRegimento());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Registar Colaborador
    @FXML
    void registarColaboradorAction(ActionEvent event) {
        try {
            boolean registou = registarColaboradorController.registarColaborador(txtNomeColaborador.getText().trim(),
                    Integer.parseInt(txtContactoColaborador.getText().trim()),
                    txtEmailColaborador.getText().trim(), txtFuncaoColaborador.getText().trim(), authController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Registar novo colaborador.",
                    registou ? "Colaborador criado com sucesso! \n\n" +
                            serviceController.getColaboradorToStringCompletoByEmail(txtEmailColaborador.getText().trim())
                            : "N??o foi poss??vel registar o colaborador.").show();

            if (registou) {
                limparDadosRegistarColaboradorPane();
            }

        } catch (NumberFormatException nfe) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Letras em campos de valores num??ricos (NIF, Contacto Gestor ou Telefone Organiza????o) ou campos em vazio.").show();
        } catch (IllegalArgumentException iae) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    iae.getMessage()).show();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Criar Tarefa
    public void criarTarefaActionTarefa(ActionEvent actionEvent) {
        try {
            boolean criou = tarefaController.definirTarefa(
                    txtCodigoUnicoTarefa.getText().trim(),
                    txtNomeTarefa.getText().trim(),
                    txtDescInfTarefa.getText().trim(),
                    txtDescTecnicaTarefa.getText().trim(),
                    Integer.parseInt(txtDuracaoTarefa.getText().trim()),
                    Float.parseFloat(txtCustoTarefa.getText().trim()),
                    comboCategoria.getValue(),
                    authController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar nova tarefa.",
                    criou ? "Tarefa criada com sucesso! \n\n" +
                            serviceController.getTarefaToStringCompletoByCodigoUnico(txtCodigoUnicoTarefa.getText().trim(),
                                    authController.getEmail())
                            : "N??o foi poss??vel criar a tarefa.").show();

            if (criou) {
                limparDadosRegistarTarefaPane();
            }

        } catch (NumberFormatException nfe) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    "Letras em campos de valores num??ricos (NIF, Contacto Gestor ou Telefone Organiza????o) ou campos em vazio.").show();
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

    //limpar campos do registo colaborador
    public void limparRegistarColaboradorAction(ActionEvent actionEvent) {
        limparDadosRegistarColaboradorPane();
    }

    public void limparDadosRegistarColaboradorPane() {
        txtNomeColaborador.clear();
        txtContactoColaborador.clear();
        txtEmailColaborador.clear();
        txtFuncaoColaborador.clear();
    }

    //Limpar campos do registo tarefa
    public void limparActionTarefa(ActionEvent actionEvent) {
        limparDadosRegistarTarefaPane();
    }

    public void limparDadosRegistarTarefaPane() {
        txtCodigoUnicoTarefa.clear();
        txtCustoTarefa.clear();
        txtDescInfTarefa.clear();
        txtDescTecnicaTarefa.clear();
        txtDuracaoTarefa.clear();
        txtNomeTarefa.clear();
    }

    //selecionar menu registo colaborador
    public void btnSelecionarRegistarColaboradorAction(ActionEvent actionEvent) {
        //desligar
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        homePane.setVisible(false);
        homePane.setDisable(true);
        publicarTarefaPane.setDisable(true);
        publicarTarefaPane.setVisible(false);
        IniciarSeriacaoPane.setVisible(false);
        IniciarSeriacaoPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoManualPane.setVisible(false);
        seriacaoManualPane.setDisable(true);

        //ligar
        registarColaboradorPane.setVisible(true);
        registarColaboradorPane.setDisable(false);

    }

    //selecionar menu cria????o de tarefa
    public void btnSelecionarCriarTarefaAction(ActionEvent actionEvent) {
        //desligar
        registarColaboradorPane.setVisible(false);
        registarColaboradorPane.setDisable(true);
        homePane.setVisible(false);
        homePane.setDisable(true);
        IniciarSeriacaoPane.setVisible(false);
        IniciarSeriacaoPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoManualPane.setVisible(false);
        seriacaoManualPane.setDisable(true);
        publicarTarefaPane.setDisable(true);
        publicarTarefaPane.setVisible(false);

        //ligarr
        criarTarefaPane.setVisible(true);
        criarTarefaPane.setDisable(false);

        //popular elementos
        try {
            comboCategoria.getItems().setAll(serviceController.getCategoriasTarefa());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //selecionar menu Home
    public void goHomeSelectAction(ActionEvent actionEvent) {
        //desligar
        registarColaboradorPane.setVisible(false);
        registarColaboradorPane.setDisable(true);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        IniciarSeriacaoPane.setVisible(false);
        IniciarSeriacaoPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoManualPane.setVisible(false);
        seriacaoManualPane.setDisable(true);
        publicarTarefaPane.setDisable(true);
        publicarTarefaPane.setVisible(false);

        //ligar
        homePane.setVisible(true);
        homePane.setDisable(false);
    }

    public void btnPublicarTarefaSelectAction(ActionEvent actionEvent) {
        //desligar
        homePane.setVisible(false);
        homePane.setDisable(true);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);
        seriacaoManualPane.setDisable(true);
        seriacaoManualPane.setVisible(false);
        IniciarSeriacaoPane.setDisable(true);
        IniciarSeriacaoPane.setVisible(false);
        registarColaboradorPane.setDisable(true);
        registarColaboradorPane.setVisible(false);

        //ligar
        publicarTarefaPane.setVisible(true);
        publicarTarefaPane.setDisable(false);


        //popular elementos
        try {
            listViewTarefasMatchedPublicarTarefa.getItems().setAll(serviceController.getTarefasOrganizacao(authController.getEmail()));
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Problema preencher lista de tarefas.",
                    e.getMessage()).show();
        }
    }

    public void btnSeriarAnuncioSelectAction(ActionEvent actionEvent) {
        //desligar
        homePane.setVisible(false);
        homePane.setDisable(true);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        publicarTarefaPane.setVisible(false);
        publicarTarefaPane.setDisable(true);
        seriacaoAutomaticaPane.setDisable(true);
        seriacaoAutomaticaPane.setVisible(false);
        seriacaoManualPane.setDisable(true);
        seriacaoManualPane.setVisible(false);
        registarColaboradorPane.setDisable(true);
        registarColaboradorPane.setVisible(false);

        //ligar
        IniciarSeriacaoPane.setDisable(false);
        IniciarSeriacaoPane.setVisible(true);
        try {
            listViewAnunciosSeriarAnuncio.getItems().setAll(serviceController.getAllAnunciosSeriacao(authController.getEmail()));
        } catch (SQLException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Problema preencher lista de tarefas.",
                    e.getMessage()).show();
        }
    }

    //fazer logout
    public void btnLogoutAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Ir?? voltar ?? pagina inicial ap??s confirma????o.", "Deseja mesmo fazer logout?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            try {
                limparTodosOsCampos();
                authController.logout();
                serviceController.resetUserAPI();
                voltarJanelaInicial();
            } catch (SQLException e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                        "Erro de SQL.",
                        e.getMessage()).show();
            }

        }
    }

    //limpar todos os campos
    public void limparTodosOsCampos() {
        txtNomeColaborador.clear();
        txtContactoColaborador.clear();
        txtEmailColaborador.clear();

        txtCodigoUnicoTarefa.clear();
        txtCustoTarefa.clear();
        txtDescInfTarefa.clear();
        txtDescTecnicaTarefa.clear();
        txtDuracaoTarefa.clear();
        txtNomeTarefa.clear();
        comboCategoria.setValue(null);
        btnDataFimPub.getEditor().clear();
        btnDataFimCand.getEditor().clear();
        btnDataFimSeriacao.getEditor().clear();
        btnDataInicioCand.getEditor().clear();
        btnDataInicioSeriacao.getEditor().clear();
        btnTipoRegimento.setValue(null);
    }

    //volta ?? janela inicial
    public void voltarJanelaInicial() {
        MainApp.screenController.activate("JanelaInicial");
    }

    public void finalizarSeriacaoManualAction(ActionEvent actionEvent) {
        try {
            boolean criou = seriarCandidaturaController.criarProcessoSeriacao(
                    listViewCandidaturasSelecionadasSeriacaoManual.getItems(),
                    listViewColaboradoresSelecionadosSeriacaoManual.getItems(),
                    authController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Processo de seria????o.",
                    criou ? "Processo de seria????o realizado com sucesso!" :
                            "N??o foi poss??vel realizar o processo de seria????o").show();

            if (criou) {
                listViewCandidaturasSelecionadasSeriacaoManual.getItems().clear();
                listViewCandidaturasPorSelecionarSeriacaoManual.getItems().clear();
                listViewColaboradoresPorSelecionarSeriacaoManual.getItems().clear();
                listViewColaboradoresSelecionadosSeriacaoManual.getItems().clear();
                seriacaoManualPane.setDisable(true);
                seriacaoManualPane.setVisible(false);
                IniciarSeriacaoPane.setVisible(true);
                IniciarSeriacaoPane.setDisable(false);
                try {
                    listViewAnunciosSeriarAnuncio.getItems().setAll(serviceController.getAllAnunciosSeriacao(authController.getEmail()));
                } catch (SQLException e) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                            "Problema preencher lista de tarefas.",
                            e.getMessage()).show();
                }

            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    e.getMessage()).show();
        } catch (SQLException throwables) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro de SQL.",
                    throwables.getMessage()).show();
            throwables.printStackTrace();
        }
    }

    public void voltarSeriacaoManualAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Ir?? voltar ao menu de seria????o.", "Deseja mesmo fazer voltar?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            try {
                listViewCandidaturasSelecionadasSeriacaoManual.getItems().clear();
                listViewCandidaturasPorSelecionarSeriacaoManual.getItems().clear();
                listViewColaboradoresPorSelecionarSeriacaoManual.getItems().clear();
                listViewColaboradoresSelecionadosSeriacaoManual.getItems().clear();
                seriacaoManualPane.setDisable(true);
                seriacaoManualPane.setVisible(false);
                IniciarSeriacaoPane.setVisible(true);
                IniciarSeriacaoPane.setDisable(false);
            } catch (Exception e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                        "Problema ao voltar ao menu anterior.",
                        e.getMessage()).show();
            }
        }
    }

    public void classificarCandidaturaSeriacaoManualAction(ActionEvent actionEvent) {
        if (listViewCandidaturasPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem() != null) {
            listViewCandidaturasSelecionadasSeriacaoManual.getItems().add(
                    listViewCandidaturasPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem());
            if (btnRemoverUltimaCandidatura.isDisable()) {
                btnRemoverUltimaCandidatura.setDisable(false);
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar candidatura",
                    "?? obrigat??rio escolher uma candidatura para adicionar!").show();
        }
    }

    public void removerUltimaCandidaturaSeriacaoManualAction(ActionEvent actionEvent) {
        listViewCandidaturasSelecionadasSeriacaoManual.getItems().remove(listViewCandidaturasSelecionadasSeriacaoManual.getItems().size() - 1);

        if (listViewCandidaturasSelecionadasSeriacaoManual.getItems().size() == 0) {
            btnRemoverUltimaCandidatura.setDisable(true);
        }
    }

    public void adicionarColaboradorSeriacaoManualAction(ActionEvent actionEvent) {
        if (listViewColaboradoresPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem() != null) {
            listViewColaboradoresSelecionadosSeriacaoManual.getItems().add(
                    listViewColaboradoresPorSelecionarSeriacaoManual.getSelectionModel().getSelectedItem());
            if (btnRemoverUltimoColaborador.isDisable()) {
                btnRemoverUltimoColaborador.setDisable(false);
            }
        } else {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro ao adicionar candidatura",
                    "?? obrigat??rio escolher uma candidatura para adicionar!").show();
        }
    }

    public void removerUltimoColaboradorSeriacaoManualAction(ActionEvent actionEvent) {
        listViewColaboradoresSelecionadosSeriacaoManual.getItems().remove(listViewColaboradoresSelecionadosSeriacaoManual.getItems().size() - 1);

        if (listViewColaboradoresSelecionadosSeriacaoManual.getItems().size() == 0) {
            btnRemoverUltimoColaborador.setDisable(true);
        }
    }

    public void iniciarSeriacaoAction(ActionEvent actionEvent) {
        //desligar
        homePane.setVisible(false);
        homePane.setDisable(true);
        IniciarSeriacaoPane.setDisable(true);
        IniciarSeriacaoPane.setVisible(false);
        criarTarefaPane.setVisible(false);
        criarTarefaPane.setDisable(true);
        publicarTarefaPane.setVisible(false);
        publicarTarefaPane.setDisable(true);
        registarColaboradorPane.setDisable(true);
        registarColaboradorPane.setVisible(false);
        btnRemoverUltimaCandidatura.setDisable(true);
        btnRemoverUltimoColaborador.setDisable(true);
        listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems().clear();
        listViewColaboradoresPorSelecionarSeriacaoManual.getItems().clear();

        try {
            if (seriarCandidaturaController.isSeriacaoAutomatica(listViewAnunciosSeriarAnuncio.getSelectionModel().getSelectedItem())) {
                //ligar
                seriacaoAutomaticaPane.setDisable(false);
                seriacaoAutomaticaPane.setVisible(true);
                seriacaoManualPane.setDisable(true);
                seriacaoManualPane.setVisible(false);
                listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems().setAll(seriarCandidaturaController.
                        candidaturasSeriadasPorValor(listViewAnunciosSeriarAnuncio.getSelectionModel().getSelectedItem()));
            } else {
                //ligar
                seriacaoManualPane.setDisable(false);
                seriacaoManualPane.setVisible(true);
                seriacaoAutomaticaPane.setDisable(true);
                seriacaoAutomaticaPane.setVisible(false);
                listViewCandidaturasPorSelecionarSeriacaoManual.getItems().setAll(seriarCandidaturaController.
                        getAllCandidaturasPorSelecionar(listViewAnunciosSeriarAnuncio.getSelectionModel().getSelectedItem()));
                listViewColaboradoresPorSelecionarSeriacaoManual.getItems().setAll(seriarCandidaturaController.
                        getAllColaboradoresOrganizacao(authController.getEmail()));
            }

        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Problema ao iniciar seria????o.",
                    e.getMessage()).show();
        }
    }

    public void confirmarSeriacaoAutomaticaAction(ActionEvent actionEvent) {
        try {

            boolean seriou = seriarCandidaturaController.criarProcessoSeriacao(listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems(),
                    new ArrayList<Colaborador>(),
                    authController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Seria????o de Candidaturas.",
                    seriou ? "Seria????o Autom??tica realizada com sucesso! \n\n"
                            : "N??o foi poss??vel seriar autom??ticamente as candidaturas.").show();
            if (seriou) {
                listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems().clear();
                seriacaoAutomaticaPane.setDisable(true);
                seriacaoAutomaticaPane.setVisible(false);
                IniciarSeriacaoPane.setVisible(true);
                IniciarSeriacaoPane.setDisable(false);
                try {
                    listViewAnunciosSeriarAnuncio.getItems().setAll(serviceController.getAllAnunciosSeriacao(authController.getEmail()));
                } catch (SQLException e) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                            "Problema preencher lista de tarefas.",
                            e.getMessage()).show();
                }
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    e.getMessage()).show();
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    "Datas inv??lidas ou campos em falta");
        }

    }

    public void voltarSeriacaoAutomaticaAction(ActionEvent actionEvent) {
        Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, "Logout",
                "Ir?? voltar ao menu de seria????o.", "Deseja mesmo fazer voltar?");
        if (alerta.showAndWait().get() == ButtonType.CANCEL) {
            actionEvent.consume();
        } else {
            try {
                listViewCandidaturasSeriarAnuncioSeriacaoAutomatica.getItems().clear();
                seriacaoAutomaticaPane.setDisable(true);
                seriacaoAutomaticaPane.setVisible(false);
                IniciarSeriacaoPane.setVisible(true);
                IniciarSeriacaoPane.setDisable(false);
            } catch (Exception e) {
                AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                        "Problema ao voltar ao menu anterior.",
                        e.getMessage()).show();
            }
        }
    }

    public void publicarTarefaAction(ActionEvent actionEvent) {
        try {

            boolean publicou = publicarTarefaController.publicarTarefa(
                    listViewTarefasMatchedPublicarTarefa.getSelectionModel().getSelectedItem(),
                    btnTipoRegimento.getValue(),
                    Data.dataAtual(),
                    btnDataFimPub.getValue(),
                    btnDataInicioCand.getValue(),
                    btnDataFimCand.getValue(),
                    btnDataInicioSeriacao.getValue(),
                    btnDataFimSeriacao.getValue(),
                    authController.getEmail());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar novo An??ncio.",
                    publicou ? "An??ncio criado com sucesso! \n\n" +
                            serviceController.getAnunciotoStringCompletoByTarefa(listViewTarefasMatchedPublicarTarefa.
                                    getSelectionModel().getSelectedItem())
                            : "N??o foi poss??vel criar o an??ncio.").show();

            if (publicou) {
                limparTodosOsCampos();
                try {
                    listViewTarefasMatchedPublicarTarefa.getItems().setAll(serviceController.getTarefasOrganizacao(authController.getEmail()));
                } catch (Exception e) {
                    AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                            "Problema preencher lista de tarefas.",
                            e.getMessage()).show();
                }
            }

        } catch (IllegalArgumentException e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    e.getMessage()).show();
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO,
                    "Erro nos dados.",
                    "Datas inv??lidas ou campos em falta");
        }
    }


    public void btnLimparDadosPublicarTarefaAction(ActionEvent actionEvent) {
        limparTodosOsCampos();
    }

}
