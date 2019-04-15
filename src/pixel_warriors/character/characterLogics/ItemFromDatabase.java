package pixel_warriors.character.CharacterLogics;

import pixel_warriors.character.Staffs.Items.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemFromDatabase {
    Connection con;
    static int[] itemID = new int[12];


    public ItemFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "lab";    //TODO if kurka baza then 'root'  //TODO
        String password = "lab";  //TODO if kurka baza then 'test'
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {

        }
    }

    public Map<ItemType, Item> getInventory() {
        Map<ItemType, Item> items = new HashMap<ItemType, Item>();
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");

            items.put(ItemType.Armor, (Armor) getItem(ItemType.Armor, 1, "ADMIN12345", stmt, "inv"));

            items.put(ItemType.Gloves, (Gloves) getItem(ItemType.Gloves, 1, "ADMIN12345", stmt, "inv"));

            items.put(ItemType.Trousers, (Trousers) getItem(ItemType.Trousers, 1, "ADMIN12345", stmt, "inv"));

            items.put(ItemType.Shoes, (Shoes) getItem(ItemType.Shoes, 1, "ADMIN12345", stmt, "inv"));

            items.put(ItemType.Helmets, (Helmets) getItem(ItemType.Helmets, 1, "ADMIN12345", stmt, "inv"));

            items.put(ItemType.MainWeapons, (MainWeapon) getItem(ItemType.MainWeapons, 1, "ADMIN12345", stmt, "inv"));

            items.put(ItemType.AdditionalWeapons, (AdditionalWeapon) getItem(ItemType.AdditionalWeapons, 1, "ADMIN12345", stmt, "inv"));

            items.put(ItemType.Necklaces, (Necklace) getItem(ItemType.Necklaces, 1, "ADMIN12345", stmt, "inv"));

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Item getItem(ItemType item, int idItem, String idPlayer, Statement stmt, String where) {
        try {
            if (item == ItemType.empty)
                return null;
            String joinFrom = null, whatID = null;
            String whatCompare = null;
            if (where.equals("inv")) {
                joinFrom = "Inventory";
                whatID = "IDPLayer_Inventory";
                whatCompare = FactoryItem.getTable(item) + FactoryItem.getStringToConnection(item);
            } else if (where.equals("eq")) {
                joinFrom = "Equipment";
                whatID = "IDPlayer_Equipment";
                whatCompare = FactoryItem.getTable(item) + " Item" + idItem + "=" + FactoryItem.getStringItem(item);
            }

            String query = "SELECT" + FactoryItem.getAttributesItem(item) + "FROM Players JOIN " + joinFrom + " ON " + whatID + "=IDPlayer JOIN" + whatCompare;
            ResultSet rs = stmt.executeQuery(query);
            Item temp = FactoryItem.getItem(item, rs);
            temp.validation();
            return temp;
        } catch (SQLException ex) {
            return null;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public ArrayList<Item> getEquipment() {
        ArrayList<Item> items = new ArrayList<Item>();
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");

            items.add(getItem(getType("ADMIN12345", 1, stmt), 1, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 2, stmt), 2, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 3, stmt), 3, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 4, stmt), 4, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 5, stmt), 5, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 6, stmt), 6, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 7, stmt), 7, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 8, stmt), 8, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 9, stmt), 9, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 10, stmt), 10, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 11, stmt), 11, "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 12, stmt), 12, "ADMIN12345", stmt, "eq"));

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ItemType getType(String idPlayer, int i, Statement stmt) {
        try {
            String query = "SELECT Type" + i + " FROM Equipment WHERE IDPlayer_Equipment=\"" + idPlayer + "\";";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return FactoryItem.getItemType(rs.getString(1));
        } catch (SQLException ex) {
            return ItemType.empty;
        } catch (NullPointerException ex) {
            return ItemType.empty;
        }
    }

}
