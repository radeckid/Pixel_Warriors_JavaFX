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
        String user = "lab";
        String password = "lab";
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
            String joinFrom = null, whatID = null;
            if (where.equals("inv")) {
                joinFrom = "Inventory";
                whatID = "IDPLayer_Inventory";
            } else if (where.equals("eq")) {
                joinFrom = "Equipment";
                whatID = "IDPlayer_Equipment";
            }

            String query = "SELECT" + FactoryItem.getAttributesItem(item) + "FROM Players JOIN " + joinFrom + " ON " + whatID + "=IDPlayer JOIN" + FactoryItem.getStringToConnection(item);
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

            items.add(getItem(getType("ADMIN12345", 1, stmt), itemID[0], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 2, stmt), itemID[1], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 3, stmt), itemID[2], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 4, stmt), itemID[3], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 5, stmt), itemID[4], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 6, stmt), itemID[5], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 7, stmt), itemID[6], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 8, stmt), itemID[7], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 9, stmt), itemID[8], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 10, stmt), itemID[9], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 11, stmt), itemID[10], "ADMIN12345", stmt, "eq"));
            items.add(getItem(getType("ADMIN12345", 12, stmt), itemID[11], "ADMIN12345", stmt, "eq"));

            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ItemType getType(String idPlayer, int i, Statement stmt) {
        try {
            String query = "SELECT Item" + i + ", Type" + i + " FROM Equipment WHERE IDPlayer_Equipment=\"" + idPlayer + "\";";
            ResultSet rs = stmt.executeQuery(query);
            ItemType itemType = ItemType.empty;

            if (rs.next()) {
                String item = rs.getString(1);
                String type = rs.getString(2);

                if (type.equals("Helmets")) {
                    itemType = ItemType.Helmets;
                } else if (type.equals("Armors")) {
                    itemType = ItemType.Armor;
                } else if (type.equals("Trousers")) {
                    itemType = ItemType.Trousers;
                } else if (type.equals("Shoes")) {
                    itemType = ItemType.Shoes;
                } else if (type.equals("Necklaces")) {
                    itemType = ItemType.Necklaces;
                } else if (type.equals("MainWeapons")) {
                    itemType = ItemType.MainWeapons;
                } else if (type.equals("AdditionalWeapons")) {
                    itemType = ItemType.AdditionalWeapons;
                } else if (type.equals("Gloves")) {
                    itemType = ItemType.Gloves;
                } else {
                    itemType = ItemType.empty;
                }
                itemID[i - 1] = Integer.parseInt(item);
            }
            return itemType;
        } catch (SQLException ex) {
            //ex.printStackTrace();
        } catch (NullPointerException ex) {
            //ex.printStackTrace();
        }
        return null;
    }

}
