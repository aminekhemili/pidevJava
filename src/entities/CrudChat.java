/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author amine
 */
public interface CrudChat<Ch> {
    public void ajouter(Ch c);
    public void modifier(Ch c);
    public void supprimer(int id) throws SQLException;
    public List<Chat> Show();
}
