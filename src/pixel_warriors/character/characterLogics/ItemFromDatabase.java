package pixel_warriors.character.CharacterLogics;
import pixel_warriors.character.Staffs.Items.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ItemFromDatabase
{
    Connection con;
    public ItemFromDatabase()
    {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "lab";
        String password = "lab";
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url,user,password);
        }
        catch (Exception e)
        {

        }
    }

    public Map<ItemType, Item> getInventory()
    {
        Map<ItemType, Item> items = new HashMap<ItemType, Item>();
        try
        {
            Statement stmt=con.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");

            items.put(ItemType.Armor,(Armor)getItem(ItemType.Armor, 1, "ADMIN12345", stmt));

            items.put(ItemType.Gloves,(Gloves) getItem(ItemType.Gloves, 1, "ADMIN12345", stmt));

            items.put(ItemType.Trousers,(Trousers)getItem(ItemType.Trousers, 1, "ADMIN12345", stmt));

            items.put(ItemType.Shoes,(Shoes)getItem(ItemType.Shoes, 1, "ADMIN12345", stmt));

            items.put(ItemType.Helmets,(Helmets)getItem(ItemType.Helmets, 1, "ADMIN12345", stmt));

            items.put(ItemType.MainWeapons,(MainWeapon)getItem(ItemType.MainWeapons, 1, "ADMIN12345", stmt));

            items.put(ItemType.AdditionalWeapons,(AdditionalWeapon)getItem(ItemType.AdditionalWeapons, 1, "ADMIN12345", stmt));

            items.put(ItemType.Necklaces,(Necklace)getItem(ItemType.Necklaces, 1, "ADMIN12345", stmt));

            return items;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public static Item getItem(ItemType item, int idItem, String idPlayer, Statement stmt)
    {
        try
        {
            String query = "select" + FactoryItem.getAttributesItem(item) + "from Players Join Inventory on IDPlayer_Inventory=IDPlayer Join" + FactoryItem.getStringToConnection(item);
            ResultSet rs=stmt.executeQuery(query);
            Item temp = FactoryItem.getItem(item,rs);
            temp.validation();
            return temp;
        }
       catch(SQLException ex)
       {
           return null;
       }
        catch (NullPointerException ex)
        {
            return null;
        }
    }
}
