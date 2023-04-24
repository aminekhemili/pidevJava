/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chat;
import entities.CrudChat;
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
public class ChatServices implements CrudChat<Chat>{

    public Connection conx;
    public Statement stm;

    
    public ChatServices() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(Chat c) {
        String req = 
                "INSERT INTO chat"
                + "(message_chat)"
                + "VALUES(?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, c.getMessage_chat());
            ps.executeUpdate();
            System.out.println("Chat Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Chat c) {
        try {
            String req = "UPDATE chat SET message_chat=? WHERE id_chat=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(2, c.getId_chat());
            pst.setString(1, c.getMessage_chat());
            pst.executeUpdate();
            System.out.println("Chat Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM chat WHERE id_chat=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Chat suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Chat> Show() {
        List<Chat> list = new ArrayList<>();

        try {
            String req = "SELECT * from chat";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Chat(rs.getInt("id_chat"), rs.getString("message_chat")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
}
