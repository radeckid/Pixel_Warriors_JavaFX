package pixel_warriors.character.Staffs.Items;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryItem {
    public static Item getItem(Item item) {
        if (item == null) {
            return null;
        }
        ItemType itemType = item.getItemType();
        switch (itemType) {
            case Helmets:
                return new Helmets((Helmets) item);
            case Armor:
                return new Armor((Armor) item);
            case Trousers:
                return new Trousers((Trousers) item);
            case Shoes:
                return new Shoes((Shoes) item);
            case Gloves:
                return new Gloves((Gloves) item);
            case MainWeapons:
                return new MainWeapon((MainWeapon) item);
            case AdditionalWeapons:
                return new AdditionalWeapon((AdditionalWeapon) item);
            case Necklaces:
                return new Necklace((Necklace) item);
            case empty:
                return null;
            default:
                return null;
        }
    }

    public static Item getItem(ItemType itemType, ResultSet rs) {
        if (itemType == null) {
            return null;
        }

        try {
            rs.next();
            switch (itemType) {
                case Helmets:
                    return new Helmets(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                case Armor:
                    return new Armor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                case Trousers:
                    return new Trousers(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                case Shoes:
                    return new Shoes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                case Gloves:
                    return new Gloves(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                case MainWeapons:
                    return new MainWeapon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), WeaponType.valueOf(rs.getInt(5)));
                case AdditionalWeapons:
                    return new AdditionalWeapon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                case Necklaces:
                    TypeFirstBonus ch1 = TypeFirstBonus.valueOf(rs.getString(5).charAt(0));
                    FourthBonusType ch2 = FourthBonusType.valueOf(rs.getString(9).charAt(0));
                    return new Necklace(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), TypeFirstBonus.valueOf(rs.getString(5).charAt(0)), rs.getInt(6), rs.getInt(7), rs.getInt(8), FourthBonusType.valueOf(rs.getString(9).charAt(0)));
                case empty:
                    return null;
                default:
                    return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public static String getAttributesItem(ItemType itemType) {
        switch (itemType) {
            case Helmets:
                return " idHelmet, Name, Path, PhysicalDefense, MagicalDefense ";
            case Armor:
                return " idArmor, Name, Path, PhysicalDefense, MagicalDefense ";
            case Trousers:
                return " idTrousers, Name, Path, PhysicalDefense, MagicalDefense ";
            case Shoes:
                return " idShoes, Name, Path, PhysicalDefense, MagicalDefense ";
            case Gloves:
                return " idGloves, Name, Path, PhysicalDefense, MagicalDefense ";
            case MainWeapons:
                return " IDMainWeapon, Name, Path, Attack, Type ";
            case AdditionalWeapons:
                return " IDAdditionalWeapon, Name, Path, Attack ";
            case Necklaces:
                return " IDNecklace, Name, Path, FirstBonus, TypeFirstBonus, SecondBonus, ThirdBonus, FourthBonus, FourthBonusType ";
            case empty:
                break;
            default:
                return null;
        }
        return null;
    }

    public static String getStringToConnection(ItemType itemType) {
        switch (itemType) {
            case Helmets:
                return " Helmets on IDInventory_Helmet=IDHelmet;";
            case Armor:
                return " Armors on IDInventory_Armor=IDArmor;";
            case Trousers:
                return " Trousers on IDInventory_Trousers=IDTrousers;";
            case Shoes:
                return " Shoes on IDInventory_Shoes=IDShoes;";
            case Gloves:
                return " Gloves on IDInventory_Gloves=IDGloves;";
            case MainWeapons:
                return " MainWeapons on IDInventory_MainWeapon=IDMainWeapon;";
            case AdditionalWeapons:
                return " AdditionalWeapons on IDInventory_AdditionalWeapon=IDAdditionalWeapon;";
            case Necklaces:
                return " Necklaces on IDInventory_Necklace=IDNecklace;";
            case empty:
                break;
            default:
                return null;
        }
        return null;
    }
}
