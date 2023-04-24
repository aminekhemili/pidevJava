/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CrudTournois;
import entities.Tournois;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author amine
 */
public class TournoisServices implements CrudTournois<Tournois>{

    public Connection conx;
    public Statement stm;

    
    public TournoisServices() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(Tournois t) {
        String req = 
                "INSERT INTO tournois"
                + "(nombre_joueur,nom_jeu,detail,date_tournoi,id_chat)"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, t.getNombre_joueur());
            ps.setString(2, t.getNom_jeu());
            ps.setString(3, t.getDetail());
            ps.setDate(4, new java.sql.Date(t.getDate_tournoi().getTime()));
            ps.setInt(5, t.getId_chat());
            ps.executeUpdate();
            System.out.println("Tournois Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Tournois t) {
        try {
            String req = "UPDATE tournois SET nombre_joueur=?, nom_jeu=?, detail=?, date_tournoi=?, id_chat=? WHERE id_tournoi=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(6, t.getId_tournoi());
            pst.setInt(1, t.getNombre_joueur());
            pst.setString(2, t.getNom_jeu());
            pst.setString(3, t.getDetail());
            pst.setDate(4, new java.sql.Date(t.getDate_tournoi().getTime()));
            pst.setInt(5, t.getId_chat());
            pst.executeUpdate();
            System.out.println("Tournoi " + t.getNom_jeu()+ " Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM tournois WHERE id_tournoi=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Tournois suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Tournois> Show() {
        List<Tournois> list = new ArrayList<>();

        try {
            String req = "SELECT * from tournois";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Tournois(rs.getInt("id_tournoi"), rs.getInt("nombre_joueur"), 
                        rs.getString("nom_jeu"), rs.getString("detail"), 
                        rs.getDate("date_tournoi"), rs.getInt("id_chat")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
}
