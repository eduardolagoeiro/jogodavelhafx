/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogovelhafx;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author lagoeiro
 */
public class FXMLDocumentController implements Initializable {

    private Jogo jogo = new Jogo();
    private Quadrinho[] quadro;
    private HashMap<ImageView, Quadrinho> mapa = new HashMap<ImageView, Quadrinho>();
    private int turno = 0;
    private byte vez = 0;
    private int placarPCInt = 0;
    private int placarHumInt = 0;

    @FXML
    private Button btnReset;

    //placar
    @FXML
    private Label placarPC;
    @FXML
    private Label placarHumano;
    @FXML
    private Label seuNome;

    @FXML
    private ImageView imgView0;
    @FXML
    private ImageView imgView1;
    @FXML
    private ImageView imgView2;
    @FXML
    private ImageView imgView3;
    @FXML
    private ImageView imgView4;
    @FXML
    private ImageView imgView5;
    @FXML
    private ImageView imgView6;
    @FXML
    private ImageView imgView7;
    @FXML
    private ImageView imgView8;

    //tabela do jogo
    @FXML
    private GridPane gridPaneJogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Optional<String> result;
        do {
            ButtonType buttonOk = new ButtonType("confirmar", ButtonData.OK_DONE);
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Bem-vindo ao Jogo da Velha");
            dialog.setHeaderText("Por favor, insira seu nome");
            dialog.setContentText("Seu nome:");
            dialog.getDialogPane().getButtonTypes().setAll(buttonOk);

            // Traditional way to get the response value.
            result = dialog.showAndWait();
            if (result.isPresent()) {
                seuNome.setText(result.get());
            }
        } while ("".equals(seuNome.getText()));

        placarPC.setText(placarPCInt + "");
        placarHumano.setText(placarHumInt + "");
        quadro = jogo.getQuadro();
        mapa.put(imgView0, quadro[0]);
        mapa.put(imgView1, quadro[1]);
        mapa.put(imgView2, quadro[2]);
        mapa.put(imgView3, quadro[3]);
        mapa.put(imgView4, quadro[4]);
        mapa.put(imgView5, quadro[5]);
        mapa.put(imgView6, quadro[6]);
        mapa.put(imgView7, quadro[7]);
        mapa.put(imgView8, quadro[8]);
        gridPaneJogo.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Object alvo = event.getTarget();
            if (mapa.containsKey(alvo)) {

                Quadrinho q = mapa.get(alvo);
                if (q.isNotMarcado()) {
                    turno++;
                    switch (vez) {
                        case 0:
                            q.setO((ImageView) alvo);
                            vez = 1;
                            break;
                        case 1:
                            q.setX((ImageView) alvo);
                            vez = 0;
                            break;
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle(null);
                    if (jogo.checkWin()) {
                        alert.setTitle("Temos um vencedor!");
                        System.out.println("win");
                        switch (vez) {
                            case 0:
                                alert.setContentText("O PC venceu =/");
                                System.out.println("X venceu");
                                placarPCInt++;
                                placarPC.setText(placarPCInt + "");
                                break;
                            case 1:
                                alert.setContentText(seuNome.getText()+" venceu!");
                                System.out.println("O venceu");
                                placarHumInt++;
                                placarHumano.setText(placarHumInt + "");
                                break;
                        }
                        alert.showAndWait();
                        cleanGame();
                    } else if (turno == 9) {
                        alert.setContentText("velha!");
                        System.out.println("velha");
                        alert.showAndWait();
                        cleanGame();
                    }
                    jogo.printaJogo();
                }
            }
        });
    }

    @FXML
    public void handleBtnReset(Event e) {
        cleanGame();
    }

    public void cleanGame() {
        int i = 0;
        for (ImageView imgView : mapa.keySet()) {
            quadro[i].setNull(imgView);
            i++;
        }
        turno = 0;
        vez = 0;
    }
}
