/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main;

import BVS.eTypProhl;
import fronta.AbstrHeap;
import fronta.IAbstrHeap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pamatky.IPamatky;
import pamatky.Pamatky;
import pamatky.Zamek;

/**
 * FXML Controller class
 *
 * @author marti
 */
public class HeapController implements Initializable {

    private IAbstrHeap abstrHeap;
    private final ObservableList<Zamek> vypis = FXCollections.observableArrayList();
    private final ObservableList<String> obserList = FXCollections.observableArrayList("Do hloubky", "Do šířky");
    private eTypProhl prohlidka;
    private Pamatky pam;
    String aktualni = "N48 55.2222 E015 55.5340";

    private TextField textFieldID = new TextField();
    private TextField textFieldNazevPamatky = new TextField();
    private Stage novyStage = new Stage();
    private GridPane grid = new GridPane();
    private Label labelID = new Label("ID:");
    private Label labelNazevPamatky = new Label("Název Památky:");
    private Label labelSouradnice = new Label("Souřadnice:");
    private TextField textFieldSouradnice = new TextField();
    private Button buttonVlozNovy = new Button("Vlož");

    @FXML
    private ListView<Zamek> LVPamatly;
    @FXML
    private Button btnZrus;
    @FXML
    private Button btnVloz;
    @FXML
    private Button btnOdeberMax;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnZpristupni;
    @FXML
    private Button btnZmena;
    @FXML
    private TextField tfAktualniPoloha;
    @FXML
    private ComboBox<String> cbDruhProhlídky;

    private BorderPane bpNovy = new BorderPane();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Comparator<Zamek> comparator = new Comparator<Zamek>() {
            @Override
            public int compare(Zamek zam1, Zamek zam2) {
                return Double.compare(zam1.getVzdalenost(), zam2.getVzdalenost());
            }
        };
        pam = new Pamatky();
        abstrHeap = new AbstrHeap(comparator);
        LVPamatly.setItems(vypis);
        cbDruhProhlídky.setItems(obserList);
        novyStage.setScene(new Scene(bpNovy));
        novyStage.setResizable(false);
        tfAktualniPoloha.setText(aktualni);

    }

    @FXML
    private void onActionButtonZrus(ActionEvent event) {
        abstrHeap.zrus();
        vypis.clear();
    }

    @FXML
    private void onActionButtonVloz(ActionEvent event) {
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

        btnVloz.setOnAction((ActionEvent update) -> {
            try {
                Zamek novyProces = new Zamek(textFieldID.getText(), textFieldNazevPamatky.getText(), textFieldSouradnice.getText());
                abstrHeap.vloz(novyProces);
                vypis.add(novyProces);
                LVPamatly.refresh();
                novyStage.close();
                obnovit();
            } catch (Exception e) {
                error("Chyba při vytváření nového  manuálního procesu");

                novyStage.close();
            }

        });

    }

    private void error(String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("nastala chyba");
        alert.setContentText(title);
        alert.showAndWait();
    }

    @FXML
    private void onActionButtonOdeberMax(ActionEvent event) {
        abstrHeap.odeberMax();
        obnovit();
    }

    @FXML
    private void onActionButtonImport(ActionEvent event) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line = "";
            String id;
            String gps;
            String nazev;
            ArrayList<Zamek> zamek = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                id = line.substring(3, 9);
                gps = line.substring(19, 44).trim();
                nazev = line.substring(69, 89).trim();
                Zamek novyZamek = new Zamek(id, nazev, gps);
                novyZamek.setVzdalenost(prevodGpsNaVzdalenost(aktualni, novyZamek.getGps()));
                zamek.add(novyZamek);
            }
            abstrHeap.vybuduj(zamek.toArray());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        vypis.clear();
        Iterator<Zamek> it = abstrHeap.iterator(prohlidka);
        while (it.hasNext()) {
            vypis.add(it.next());
        }
        obnovit();
    }

    private void obnovit() {
        LVPamatly.getItems().clear();
        Iterator<Zamek> iterator = abstrHeap.iterator(prohlidka);
        while (iterator.hasNext()) {
            LVPamatly.getItems().add(iterator.next());
        }
    }

    @FXML
    private void onActionButtonZpristupni(ActionEvent event) {
        Zamek novyZa = (Zamek) abstrHeap.zpristupniMax();
        LVPamatly.getSelectionModel().select(novyZa);
    }

    @FXML
    private void onActionButtonZmena(ActionEvent event) {
        String aktPoloha = tfAktualniPoloha.getText();
        Iterator it = abstrHeap.iterator(eTypProhl.SIRKA);
        
        while (it.hasNext()) {
            Zamek novyZamek = (Zamek) it.next();
            novyZamek.setVzdalenost(prevodGpsNaVzdalenost(aktPoloha, novyZamek.getGps()));
        }
        abstrHeap.prebuduj();
        obnovit();
    }

    @FXML
    private void onActionTextFieldAktualniPoloha(ActionEvent event) {
    }

    @FXML
    private void onActionCB(ActionEvent event) {

        switch (cbDruhProhlídky.getSelectionModel().getSelectedItem()) {
            case "Do hloubky":
                prohlidka = eTypProhl.HLOUBKA;
                break;

            case "Do šířky":
                prohlidka = eTypProhl.SIRKA;
                break;

            default:
                System.out.println("Chyba");
        }
    }

    public void importDatZTXT() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String line = "";
            String id;
            String gps;
            String nazev;
            br.readLine();
            while ((line = br.readLine()) != null) {
                id = line.substring(3, 9);
                gps = line.substring(19, 43);
                nazev = line.substring(69, 89).trim();
                Zamek novyZamek = new Zamek(id, nazev, gps);
                abstrHeap.vloz(novyZamek);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Pamatky.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static public Double prevodGpsNaVzdalenost(String aktualniGps, String zamekGps) {
        Double aktualniGpsStupneLat = Double.parseDouble(aktualniGps.substring(1, 3));
        Double aktualniGpsMinutyLat = Double.parseDouble(aktualniGps.substring(4, 11));
        Double latitudeAktualni = aktualniGpsStupneLat + (aktualniGpsMinutyLat / 60); 

        Double aktualniGpsStupneLon = Double.parseDouble(aktualniGps.substring(14, 16));
        Double aktualniGpsMinutyLon = Double.parseDouble(aktualniGps.substring(17, 24));
        Double longitudeAktualni = aktualniGpsStupneLon + (aktualniGpsMinutyLon / 60); 

        Double zamekGpsStupneLat = Double.parseDouble(zamekGps.substring(1, 3));
        Double zamekGpsMinutyLat = Double.parseDouble(zamekGps.substring(4, 11));
        Double latitudeZamek = zamekGpsStupneLat + (zamekGpsMinutyLat / 60);

        Double zamekGpsStupneLon = Double.parseDouble(zamekGps.substring(14, 16));
        Double zamekGpsMinutyLon = Double.parseDouble(zamekGps.substring(17, 24));
        Double longitudeZamek = zamekGpsStupneLon + (zamekGpsMinutyLon / 60); 

        return vzdalenost(latitudeAktualni, latitudeZamek, longitudeAktualni, longitudeZamek);
    }

    static private double vzdalenost(double lat1, double lat2, double lon1, double lon2) {

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 6371;

        return (c * r);
    }

}
