/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.annuaireelectronique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Inclusiv Academy 001
 */
public class AnnuairFXMLController implements Initializable {

    @FXML
    private Button bt_ajouter;
    @FXML
    private TextField tf_rechercher;
    @FXML
    private Button bt_rechercher;
    @FXML
    private Button bt_annuler;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private ComboBox<String> combo_genre;
    @FXML
    private TextField tf_date;
    @FXML
    private TextField tf_localisation;
    @FXML
    private Label label;
    @FXML
    private TextField tf_formation;
    @FXML
    private TableView<Student> tableview;
    @FXML
    private TableColumn<Student, Integer> col_id;
    @FXML
    private TableColumn<Student, String> col_nom;
    @FXML
    private TableColumn<Student, String> col_prenom;
    @FXML
    private TableColumn<Student, String> col_genre;
    @FXML
    private TableColumn<Student, String> col_date;
    @FXML
    private TableColumn<Student, String> col_localisation;
    @FXML
    private TableColumn<Student, String> col_formation;
    @FXML
    private TableColumn<Student, String> col_secteur;
    @FXML
    private Button bt_supprimer;
    @FXML
    private Button bt_modifier;
    @FXML
    private TextField tf_total;
    @FXML
    private ComboBox<String> combo_secteur;

    //Lire le fichier txt, Creation list, insertion dans la liste
    public ArrayList<Student> liste_Init() {
        ArrayList<Student> list_student = new ArrayList<>();
        Student student;

        try {
            FileInputStream fis = new FileInputStream("src/main/java/com/mycompany/annuaireelectronique/database.txt");
            InputStreamReader isr = new InputStreamReader(fis, "Cp863");//lire en codage UTF-8
            BufferedReader bf = new BufferedReader(isr);

            String line1 = bf.readLine();

            //insertion dans la liste
            int i = 1;
            String line;
            while ((line = bf.readLine()) != null) {
                String[] decomp = line.split("\t");//decomposition d'une ligne
                student = new Student(decomp[5], decomp[6], decomp[4], decomp[0], decomp[1], decomp[2], decomp[3]);
                list_student.add(student);
                student.setId(i);
                i++;
            }
            bf.close();

        } catch (IOException e) {
            System.out.println("data input failed!");
        }

        return list_student;
    }

    public ArrayList<Student> listInit = liste_Init();//list initial d'origine

    public static void saveToFile(String file, ArrayList<Student> students, boolean reset) {
        try {
            File f = new File(file);
            FileOutputStream fos = new FileOutputStream(f, reset);
            OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("Cp863"));
            BufferedWriter bw = new BufferedWriter(osw);

            // Write each student's data to the file
            for (Student student : students) {
                bw.write(student.getDateEntry()
                        + "\t" + student.getLocalisation()
                        + "\t" + student.getFormation()
                        + "\t" + student.getSecteur()
                        + "\t" + student.getGenre()
                        + "\t" + student.getNom()
                        + "\t" + student.getPrenom());
                bw.newLine();
            }

            bw.close();
            System.out.println("File saved successfully!");

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("An error occurred while saving the file: " + ex.getMessage());
        }
    }

    //afficher list sur tableview
    public void showList(ArrayList<Student> list_annuaire) {

        ObservableList<Student> liste = FXCollections.observableArrayList();

        for (Student temp : list_annuaire) {
            liste.add(temp);
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateEntry"));
        col_localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        col_formation.setCellValueFactory(new PropertyValueFactory<>("formation"));
        col_secteur.setCellValueFactory(new PropertyValueFactory<>("secteur"));

        tableview.setItems(liste);
        tf_total.setText(String.valueOf(liste.size()));
    }

    public void overwrite() {
        saveToFile("src/main/java/com/mycompany/annuaireelectronique/database.txt", listInit, false);
    }

    @FXML
    private void ajouterAction(ActionEvent event) {
        ajouter();
        raz();
        // Refresh the table view to display the updated list
        showList(listInit);
    }

    public void ajouter() {
        // Retrieving the data entered by the user
        Student student = new Student();
        student.setNom(tf_nom.getText());
        student.setPrenom(tf_prenom.getText());
        student.setGenre(combo_genre.getValue());
        student.setDateEntry(tf_date.getText());
        student.setLocalisation(tf_localisation.getText());
        student.setFormation(tf_formation.getText());
        student.setSecteur(combo_secteur.getValue());

        listInit.add(student);
        student.setId(listInit.lastIndexOf(student) + 1);

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/com/mycompany/annuaireelectronique/database.txt", true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("Cp863"));
            BufferedWriter bf = new BufferedWriter(osw);

            bf.write(student.getDateEntry()
                    + "\t" + student.getLocalisation()
                    + "\t" + student.getFormation()
                    + "\t" + student.getSecteur()
                    + "\t" + student.getGenre()
                    + "\t" + student.getNom()
                    + "\t" + student.getPrenom());
            bf.newLine();
            bf.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    @FXML
    private void supprimerAction(ActionEvent event) {
        supprimer();
        raz();
        overwrite();
        showList(listInit);
    }

    public void supprimer() {
        Student studentselected = tableview.getSelectionModel().getSelectedItem();

        if (studentselected != null) {
            listInit.remove(studentselected);

            //saveToFile("C:\\Users\\Inclusiv Academy 26\\Desktop\\backup mande CRUD 26-05\\P1 ANNUAIRE ELE\\AnnuaireElectronique\\src\\main\\java\\com\\mycompany\\annuaireelectronique\\database.txt", liste,false);
            tableview.setItems(FXCollections.observableArrayList(listInit));

        }

    }

    @FXML
    private void modifierAction(ActionEvent event) {
        // modifierSurTable();
        modifier();
        enregModifier();
        raz();
        showList(listInit);
    }

    public void modifier() {
        Student studentupdated = tableview.getSelectionModel().getSelectedItem();

        studentupdated.setNom(tf_nom.getText());
        studentupdated.setPrenom(tf_prenom.getText());
        studentupdated.setGenre(combo_genre.getValue());
        studentupdated.setLocalisation(tf_localisation.getText());
        studentupdated.setFormation(tf_formation.getText());
        studentupdated.setSecteur(combo_secteur.getValue());

        tableview.refresh(); // Rafraîchit la TableView avec les modifications
        System.out.println("Élément modifié avec succès");

    }

    public void enregModifier() {

        try {
            FileOutputStream fos = new FileOutputStream("src/main/java/com/mycompany/annuaireelectronique/database.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("Cp863"));
            BufferedWriter bf = new BufferedWriter(osw);

            for (Student student : listInit) {
                bf.write(student.getDateEntry()
                        + "\t" + student.getLocalisation()
                        + "\t" + student.getFormation()
                        + "\t" + student.getSecteur()
                        + "\t" + student.getGenre()
                        + "\t" + student.getNom()
                        + "\t" + student.getPrenom());
                bf.newLine();
            }
            System.out.println("Élément modifié avec succès dans la base de données");
            bf.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    public void modifierSurTable() {

        col_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        col_nom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Student, String> ev) {
                Student studentselect = ev.getRowValue();
                studentselect.setNom(ev.getNewValue());
                //tf_nom.setText(col_nom.getCellData(studentselect));
                System.out.println("okay");
            }
        });

        col_prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        col_prenom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Student, String> ev) {
                Student studentselect = ev.getRowValue();
                studentselect.setPrenom(ev.getNewValue());
                //tf_prenom.setText(col_prenom.getCellData(studentselect));
                System.out.println("okay ihany");
            }
        });
        enregModifier();
        System.out.println("Données modifié avec succès dans BD");
    }

    @FXML
    private void selectedItem(MouseEvent event) {
        if (event.getClickCount() == 1) {
            selectItem();
        }
        if (event.getClickCount() == 2) { // Detect double-click
            modifierSurTable();
//            Student studentupdated = tableview.getSelectionModel().getSelectedItem();
//
//            studentupdated.setNom(col_nom.getCellData(studentupdated));
//            studentupdated.setPrenom(col_prenom.getCellData(studentupdated));
//            studentupdated.setGenre(combo_genre.getValue());
//            studentupdated.setLocalisation(tf_localisation.getText());
//            studentupdated.setFormation(tf_formation.getText());
//            studentupdated.setSecteur(combo_secteur.getValue());
//
//            tableview.refresh(); // Rafraîchit la TableView avec les modifications
//            System.out.println("Élément modifié avec succès");
//
//            try {
//                FileOutputStream fos = new FileOutputStream("src/main/java/com/mycompany/annuaireelectronique/database.txt");
//                OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("Cp863"));
//                BufferedWriter bf = new BufferedWriter(osw);
//
//                for (Student student : listInit) {
//                    bf.write(student.getDateEntry()
//                            + "\t" + student.getLocalisation()
//                            + "\t" + student.getFormation()
//                            + "\t" + student.getSecteur()
//                            + "\t" + student.getGenre()
//                            + "\t" + student.getNom()
//                            + "\t" + student.getPrenom());
//                    bf.newLine();
//                }
//
//                bf.close();
//
//            } catch (IOException e) {
//                System.out.println("An error occurred while writing to the file: " + e.getMessage());
//            }
//            if (selectedItem != null) {
//                TableColumn<Student, String> columnToEdit
//                        = // Replace with the desired column
//                        // Get the TableColumn that corresponds to the clicked cell
//                        // For example: tableview.getColumns().get(0) to edit the first column
//                        tableview.edit(selectedItem, columnToEdit);
//            }
        }
    }

    public void selectItem() {
        Student studentselected = tableview.getSelectionModel().getSelectedItem();
        if (studentselected != null) {
            tf_nom.setText(studentselected.getNom());
            tf_prenom.setText(studentselected.getPrenom());
            combo_genre.setValue(studentselected.getGenre());
            tf_date.setText(studentselected.getDateEntry());
            tf_localisation.setText(studentselected.getLocalisation());
            tf_formation.setText(studentselected.getFormation());
            combo_secteur.setValue(studentselected.getSecteur());
        }

    }

    @FXML
    private void annulerAction(ActionEvent event) {
        raz();
    }

    public void raz() {

        tf_nom.setText("");
        tf_prenom.setText("");
        combo_genre.setValue("");
        tf_date.setText("");
        tf_formation.setText("");
        combo_secteur.setValue("");
        tf_localisation.setText("");

    }

    @FXML
    private void combogenreAction(ActionEvent event) {
        String genre = combo_genre.getValue();
    }

    @FXML
    private void combosecteurAction(ActionEvent event) {
        String secteur = combo_secteur.getValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] secteur = {"Établissements publics", "Établissements privés", "Autre"};
        ObservableList<String> combolist_secteur = FXCollections.observableArrayList(secteur);
        combo_secteur.setItems(combolist_secteur);

        String[] genre = {"Masculin", "Feminin", "Autre"};
        ObservableList<String> combolist = FXCollections.observableArrayList(genre);
        combo_genre.setItems(combolist);

        showList(listInit);

    }

}
