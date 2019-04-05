package pixel_warriors.character.characterLogics;

import pixel_warriors.character.staffs.items.FactoryItem;
import pixel_warriors.character.staffs.items.Item;
import pixel_warriors.character.staffs.items.ItemType;

import java.sql.*;

public class ItemFromDatabase {
    Connection con;

    public ItemFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "test";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {

        }
    }

    public Item getItem(ItemType item, int idItem, String idPlayer) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");
            String query = "select" + FactoryItem.getAttributesItem(item) + "from Players Join Inventory on IDPlayer_Inventory=IDPlayer Join" + FactoryItem.getStringToConnection(item);
            ResultSet rs = stmt.executeQuery(query);
            Item temp = FactoryItem.getItem(item, rs);
            return temp;
        } catch (SQLException ex) {
            return null;
        }
    }
}
