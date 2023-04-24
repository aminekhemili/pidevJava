/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author amine
 */
public class Tournois {
    private int id_tournoi;
    private int nombre_joueur;
    private String nom_jeu;
    private String detail;
    private Date date_tournoi;
    private int id_chat;

    public Tournois() {
    }

    public Tournois(int id_tournoi, int nombre_joueur, String nom_jeu, String detail, Date date_tournoi, int id_chat) {
        this.id_tournoi = id_tournoi;
        this.nombre_joueur = nombre_joueur;
        this.nom_jeu = nom_jeu;
        this.detail = detail;
        this.date_tournoi = date_tournoi;
        this.id_chat = id_chat;
    }

    public Tournois(int nombre_joueur, String nom_jeu, String detail, Date date_tournoi, int id_chat) {
        this.nombre_joueur = nombre_joueur;
        this.nom_jeu = nom_jeu;
        this.detail = detail;
        this.date_tournoi = date_tournoi;
        this.id_chat = id_chat;
    }

    public int getId_tournoi() {
        return id_tournoi;
    }

    public void setId_tournoi(int id_tournoi) {
        this.id_tournoi = id_tournoi;
    }

    public int getNombre_joueur() {
        return nombre_joueur;
    }

    public void setNombre_joueur(int nombre_joueur) {
        this.nombre_joueur = nombre_joueur;
    }

    public String getNom_jeu() {
        return nom_jeu;
    }

    public void setNom_jeu(String nom_jeu) {
        this.nom_jeu = nom_jeu;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate_tournoi() {
        return date_tournoi;
    }

    public void setDate_tournoi(Date date_tournoi) {
        this.date_tournoi = date_tournoi;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }
    
    
    
}
