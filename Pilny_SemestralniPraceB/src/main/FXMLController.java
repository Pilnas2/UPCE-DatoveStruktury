/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import BVS.AbstrTable;
import BVS.eTypProhl;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pamatky.IPamatky;
import pamatky.Pamatky;
import pamatky.Zamek;
import pamatky.eTypKey;

/**
 * FXML Controller class
 *
 * @author marti
 */
public class FXMLController implements Initializable {

    @FXML
    private Button btnNacti;
    @FXML
    private Button btnNajit;
    @FXML
    private Button btnOdeber;
    @FXML
    private Button btnZrus;
    @FXML
    private Button btnPrebuduj;
    @FXML
    private ListView<Zamek> lvPamatky;
    @FXML
    private Button btnNovy;
    @FXML
    private ComboBox<String> comboBoxNazev;
    @FXML
    private ComboBox<String> compboBoxDruhProhlidky;

    private Button buttonVlozNovy = new Button("Vlož");

    private IPamatky proces;
    private final ObservableList<Zamek> vypis = FXCollections.observableArrayList();
    private final ObservableList<String> iterator = FXCollections.observableArrayList("Typ prohlídky", "Do hloubky", "Do šířky");
    private final ObservableList<String> iterator2 = FXCollections.observableArrayList("Klíč", "GPS", "NAZEV");

    private GridPane grid = new GridPane();
    private Stage novyStage = new Stage();
    private Label labelID = new Label("ID:");
    private Label labelNazevPamatky = new Label("Název Památky:");
    private Label labelSouradnice = new Label("Souřadnice:");

    private TextField textFieldID = new TextField();
    private TextField textFieldNazevPamatky = new TextField();
    private TextField textFieldSouradnice = new TextField();

    private BorderPane bpNovy = new BorderPane();
    @FXML
    private AnchorPane btnNejblizsi;

    private static TextInputDialog textInputDialog;

    private eTypProhl prohlidka;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        proces = new Pamatky();
        lvPamatky.setItems(vypis);
        compboBoxDruhProhlidky.setItems(iterator);
        compboBoxDruhProhlidky.getSelectionModel().select(0);

        comboBoxNazev.setItems(iterator2);
        comboBoxNazev.getSelectionModel().select(0);

        novyStage.setScene(new Scene(bpNovy));
        novyStage.setResizable(false);

        bpNovy.setLeft(grid);
        prohlidka = eTypProhl.HLOUBKA;

        btnNajit.setOnAction((event) -> {
            try {
            TextInputDialog dialog = new TextInputDialog("Najdi");
            
            dialog.setTitle("Najdi zámek");
            dialog.setHeaderText("Vložte klíč");
            dialog.setContentText("Klíč:");
            
            Optional<String> result = dialog.showAndWait();
            
            if (result.isPresent()) {
                obnovit(proces.najdiZamek(result.get()));
            }
        } catch (Exception e) {
            error("Nalezení zámku");
        }
        });

    }

    private Zamek najitZamek() {
        int i = Integer.parseInt(textDialog("Hledání zámku", "Název zámku", "Zadej název zámku"));
        String s = Integer.toString(i);
        return new Zamek("0", s, "0");
    }

    @FXML
    private void onActionButtonZrus(ActionEvent event) {
        proces.zrus();
        vypis.clear();
    }

    @FXML
    private void onActionButtonNacti(ActionEvent event) {
        proces.importDatZTXT();
        vypis.clear();
        Iterator<Zamek> it = proces.VytvorIterator(eTypProhl.SIRKA);

        while (it.hasNext()) {
            vypis.add(it.next());
        }
        obnovit();
    }

    private void obnovit() {
        lvPamatky.getItems().clear();
        Iterator<Zamek> iterator = proces.VytvorIterator(prohlidka);
        while (iterator.hasNext()) {
            lvPamatky.getItems().add(iterator.next());
        }
    }

    private void obnovit(Zamek zamek) {
        obnovit();
        lvPamatky.getSelectionModel().select(zamek);
    }

    @FXML
    private void onActionButtonOdeber(ActionEvent event) {
        final int selectedId = lvPamatky.getSelectionModel().getSelectedIndex();
        if (selectedId != -1) {
            Zamek itemToRemove = lvPamatky.getSelectionModel().getSelectedItem();

            final int newSelectedIdx
                    = (selectedId == lvPamatky.getItems().size() - 1)
                    ? selectedId - 1
                    : selectedId;

            lvPamatky.getItems().remove(selectedId);
            lvPamatky.getSelectionModel().select(newSelectedIdx);
            System.out.println("selectIdx: " + selectedId);
            System.out.println("item: " + itemToRemove);
            vypis.remove(itemToRemove);

        }
    }

    @FXML
    private void onActionButtonPrebuduj(ActionEvent event) {
        proces.prebuduj();
        lvPamatky.getItems().clear();
        Iterator<Zamek> it = proces.VytvorIterator(eTypProhl.HLOUBKA);

        while (it.hasNext()) {
            vypis.add(it.next());
        }
    }

    @FXML
    private void onActionButtonDruhProhlidky(ActionEvent event) {
        try {
            switch (compboBoxDruhProhlidky.getSelectionModel().getSelectedItem()) {
                case "HLOUBKA":
                    proces.VytvorIterator(eTypProhl.HLOUBKA);
                    break;
                case "SIRKA":
                    proces.VytvorIterator(eTypProhl.SIRKA);
                    break;
                default:
                    throw new AssertionError("neexistuje");
            }
        } catch (Exception e) {
            error("chyba");
        }

        compboBoxDruhProhlidky.getSelectionModel().select(0);
        obnovit();
    }

    private void error(String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("nastala chyba");
        alert.setContentText(title);
        alert.showAndWait();
    }

    @FXML
    private void onActionButtonVlozNovy(ActionEvent event) {
        textFieldID.clear();
        textFieldNazevPamatky.clear();
        textFieldSouradnice.clear();

        grid.getChildren().clear();
        novyStage.setTitle("Nový zámek");

        grid.add(labelID, 0, 0);
        grid.add(textFieldID, 1, 0);
        grid.add(labelNazevPamatky, 0, 1);
        grid.add(textFieldNazevPamatky, 1, 1);
        grid.add(labelSouradnice, 0, 2);
        grid.add(textFieldSouradnice, 1, 2);
        grid.add(buttonVlozNovy, 1, 3);

        novyStage.show();

        buttonVlozNovy.setOnAction((ActionEvent update) -> {
            try {
                Zamek novyProces = new Zamek(textFieldID.getText(), textFieldNazevPamatky.getText(), textFieldSouradnice.getText());
                proces.vlozZamek(novyProces);
                vypis.add(novyProces);
                lvPamatky.refresh();
                novyStage.close();
                obnovit();
            } catch (Exception e) {
                error("Chyba při vytváření nového  manuálního procesu");

                novyStage.close();
            }

        });
    }

    @FXML
    private void onActionButtonNastaveniKlice(ActionEvent event) {
        if (comboBoxNazev.getSelectionModel().getSelectedIndex() != 0) {
            try {
                switch (comboBoxNazev.getSelectionModel().getSelectedItem()) {
                    case "GPS":
                        proces.nastavKlic(eTypKey.GPS);
                        break;
                    case "NAZEV":
                        proces.nastavKlic(eTypKey.NAZEV);
                        break;
                    default:
                        throw new AssertionError("neexistuje");
                }
            } catch (Exception e) {
                error("chyba");
            }
        }

    }

    private String textDialog(String title, String header, String content) {
        TextInputDialog textInput = new TextInputDialog("");
        textInput.setTitle(title);
        textInput.setHeaderText(header);
        textInput.setContentText(content);
        Optional<String> result = textInput.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;

    }


    @FXML
    private void onActionButtonNajitZamek(ActionEvent event) {
    }
}
