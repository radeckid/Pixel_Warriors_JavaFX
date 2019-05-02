package pixel_warriors.character.characterlogics;

import pixel_warriors.character.staffs.items.*;
import pixel_warriors.character.staffs.slots.Slot;
import pixel_warriors.character.staffs.slots.SlotBackpack;
import pixel_warriors.character.staffs.slots.SlotInventory;
import pixel_warriors.character.Statistic;
import pixel_warriors.connectiondb.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItemFromDatabase {
    private Connection con;
    static int[] itemID = new int[12];

    private volatile static ItemFromDatabase instance;

    private ItemFromDatabase() {
        con = ConnectionDB.getInstance().getConnection();
    }

    public static ItemFromDatabase getInstance() {
        if (instance == null) {
            synchronized (ItemFromDatabase.class) {
                if (instance == null) {
                    instance = new ItemFromDatabase();
                }
            }
        }
        return instance;
    }

    public Map<ItemType, Item> getInventory() {
        Map<ItemType, Item> items = new HashMap<ItemType, Item>();
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");

            items.put(ItemType.Armors, (Chest) getItem(ItemType.Armors, 1, "ADMIN12345", stmt, "inv"));

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

    public Item getItem(ItemType item, int idItem, String idPlayer, Statement stmt, String where) {
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

    public ItemType getType(String idPlayer, int i, Statement stmt) {
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

    public void update(Slot slot) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");
            String query = null;
            if (slot instanceof SlotInventory) {
                if (slot.getItem() == null) {
                    query = "Update Inventory set " + FactoryItem.getIdInventory(((SlotInventory) slot).getSlotItemType()) + " = " + "NULL where  IDPLayer_Inventory='ADMIN12345';";
                } else {
                    query = "Update Inventory set " + FactoryItem.getIdInventory(((SlotInventory) slot).getSlotItemType()) + " = " + slot.getItem().getIdItem() + " where  IDPLayer_Inventory='ADMIN12345';";
                }
            } else if (slot instanceof SlotBackpack) {

                if (slot.getItem() == null) {
                    query = "Update Equipment set Type" + ((SlotBackpack) slot).getIdSlot() + " = NULL , Item" + ((SlotBackpack) slot).getIdSlot() + " = NULL where  IDPLayer_Equipment='ADMIN12345';";
                } else {
                    query = "Update Equipment set Type" + ((SlotBackpack) slot).getIdSlot() + " = '" + slot.getItem().getItemType() + "' , Item" + ((SlotBackpack) slot).getIdSlot() + " = " + slot.getItem().getIdItem() + " where  IDPLayer_Equipment='ADMIN12345';";
                }
            }
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Statistic getStatistic() {
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");
            String query = "Select Level,Gold,Experience,Strength,Intelligence,Agility,HP,Mana,Stamina,Attack,PhysicalDefense,MagicalDefense,Critical,DefenseChance from Statistics where IDPLayer_Statistic='ADMIN12345';";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return new Statistic(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getInt(13), rs.getInt(14));
        } catch (SQLException ex) {
            return null;
        }
    }

    public void updateStatistic(Statistic statistic) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");
            String query = "Update Select set Level=" + statistic.getLevel() + ", Gold=" + statistic.getGold() + ", Experience=" + statistic.getExp() + ", Strength=" + statistic.getStrength() + ", Intelligence=" + statistic.getInteligence() + ", Agility=" + statistic.getAgility() + ", Hp=" + statistic.getHp() + ", Mana=" + statistic.getMana() + ", Stamina=" + statistic.getStamina() + ",PhysicalDefense=" + statistic.getPhysicalDefense() + ",MagicalDefense=" + statistic.getMagicalDefense() + ", Critical=" + statistic.getCritical() + ", DefenseChance=" + statistic.getDefenseChance() + " where IDPLayer_Statistic='ADMIN12345';";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {

        }
    }
}
