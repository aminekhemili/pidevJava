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
public class Chat {
    private int id_chat;
    private String message_chat;

    public Chat(String message_chat) {
        this.message_chat = message_chat;
    }

    public Chat() {
    }

    public Chat(int id_chat, String message_chat) {
        this.id_chat = id_chat;
        this.message_chat = message_chat;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public String getMessage_chat() {
        return message_chat;
    }

    public void setMessage_chat(String message_chat) {
        this.message_chat = message_chat;
    }
    
    
}
