/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.annuaireelectronique;

import java.util.Comparator;

/**
 *
 * @author Inclusiv Academy 13
 */
public class Student {
    
    private Integer id;
    private String nom;
    private String prenom;
    private String genre;
    private String dateEntry;
    private String localisation;
    private String formation;
    private String secteur;
    
    Student left;
    Student right;

    public Student(String nom, String prenom, String genre, String dateEntry, String localisation, String formation, String secteur) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.dateEntry = dateEntry;
        this.localisation = localisation;
        this.formation = formation;
        this.secteur = secteur;
    }

//    public Student(int id, String nom, String prenom, String genre, String dateEntry, String localisation, String formation, String secteur, Student left, Student right) {
//        this.id = id;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.genre = genre;
//        this.dateEntry = dateEntry;
//        this.localisation = localisation;
//        this.formation = formation;
//        this.secteur = secteur;
//        this.left = left;
//        this.right = right;
//    }

    Student(int i, String string, String string0, String string1, String string2, String string3, String string4, String string5) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(String dateEntry) {
        this.dateEntry = dateEntry;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public Student() {
        
        left = null;
        right = null;
    }
    
    @Override
    public String toString(){
        String line = dateEntry+"\t"+localisation+"\t"+formation+"\t"+secteur+"\t"+genre+"\t"+nom+"\t"+prenom;
        return line;
    }

    public static Comparator<Student> comparerNom = new Comparator<Student>() {
    
        @Override
        public int compare(Student s1, Student s2){
            String a = s1.getNom()+s1.getPrenom()+s1.getGenre()+s1.getDateEntry()+s1.getLocalisation()+s1.getFormation()+s1.getSecteur(); 
            String b = s2.getNom()+s2.getPrenom()+s2.getGenre()+s2.getDateEntry()+s2.getLocalisation()+s2.getFormation()+s2.getSecteur(); 
         return (a).compareTo(b);
        } 
    };
    
    
}
