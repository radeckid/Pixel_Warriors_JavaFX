package pixel_warriors.character.staffs.items;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryItem
{
    public static ItemType getItemType(String itemType)
    {
        switch (itemType)
        {
            case "Helmets":
                return ItemType.Helmets;
            case "Armors":
                return ItemType.Armors;
            case "Trousers":
                return ItemType.Trousers;
            case "Shoes":
                return ItemType.Shoes;
            case "Necklaces":
                return ItemType.Necklaces;
            case "MainWeapons":
                return ItemType.MainWeapons;
            case "AdditionalWeapons":
                return ItemType.AdditionalWeapons;
            case "Gloves":
                return ItemType.Gloves;
            default:
                return ItemType.empty;
        }
    }

    public static Item getItem(Item item)
    {
        if(item == null)
        {
            return null;
        }
        ItemType itemType = item.getItemType();
        switch (itemType)
        {
            case Helmets:
                return new Helmets((Helmets) item);
            case Armors:
                return new Chest((Chest) item);
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

    public static Item getItem(ItemType itemType, ResultSet rs)
    {
        if(itemType == null)
        {
            return null;
        }

        try
        {
            rs.next();
            switch (itemType)
            {
                case Helmets:
                    return new Helmets(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                case Armors:
                    return new Chest(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                case Trousers:
                    return new Trousers(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                case Shoes:
                    return new Shoes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                case Gloves:
                    return new Gloves(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
                case MainWeapons:
                    return new MainWeapon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), WeaponType.valueOf(rs.getInt(5)), rs.getString(6));
                case AdditionalWeapons:
                    return new AdditionalWeapon(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), TypeAttackAdditionalWeapons.valueOf(rs.getString(6).charAt(0)));
                case Necklaces:
                    return new Necklace(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), TypeFirstBonus.valueOf(rs.getString(5).charAt(0)),  rs.getInt(6),  rs.getInt(7),  rs.getInt(8),  FourthBonusType.valueOf(rs.getString(9).charAt(0)));
                case empty:
                    return null;
                default:
                    return null;
            }
        }
        catch (SQLException ex)
        {
            return null;
        }
    }

    public static String getAttributesItem(ItemType itemType)
    {
        switch (itemType)
        {
            case Helmets:
                return " idHelmet, Name, Path, PhysicalDefense, MagicalDefense, PathBody ";
            case Armors:
                return " idArmor, Name, Path, PhysicalDefense, MagicalDefense, PathBody ";
            case Trousers:
                return " idTrousers, Name, Path, PhysicalDefense, MagicalDefense, PathBody ";
            case Shoes:
                return " idShoes, Name, Path, PhysicalDefense, MagicalDefense, PathBody ";
            case Gloves:
                return " idGloves, Name, Path, PhysicalDefense, MagicalDefense, PathBody ";
            case MainWeapons:
                return " IDMainWeapon, Name, Path, Attack, Type, PathBody ";
            case AdditionalWeapons:
                return " IDAdditionalWeapon, Name, Path, Attack, PathBody,TypeAttack ";
            case Necklaces:
                return " IDNecklace, Name, Path, FirstBonus, TypeFirstBonus, SecondBonus, ThirdBonus, FourthBonus, FourthBonusType ";
            case empty:
                break;
            default:
                return null;
        }
        return null;
    }

    public static String getStringToConnection(ItemType itemType)
    {
        switch (itemType)
        {
            case Helmets:
                return " IDInventory_Helmet=IDHelmet;";
            case Armors:
                return " IDInventory_Armor=IDArmor;";
            case Trousers:
                return " IDInventory_Trousers=IDTrousers;";
            case Shoes:
                return " IDInventory_Shoes=IDShoes;";
            case Gloves:
                return " IDInventory_Gloves=IDGloves;";
            case MainWeapons:
                return " IDInventory_MainWeapon=IDMainWeapon;";
            case AdditionalWeapons:
                return " IDInventory_AdditionalWeapon=IDAdditionalWeapon;";
            case Necklaces:
                return " IDInventory_Necklace=IDNecklace;";
            case empty:
                break;
            default:
                return null;
        }
        return null;
    }

    public static String getStringItem(ItemType itemType)
    {
        switch (itemType)
        {
            case Helmets:
                return " IDHelmet;";
            case Armors:
                return " IDArmor;";
            case Trousers:
                return " IDTrousers;";
            case Shoes:
                return " IDShoes;";
            case Gloves:
                return " IDGloves;";
            case MainWeapons:
                return " IDMainWeapon;";
            case AdditionalWeapons:
                return " IDAdditionalWeapon;";
            case Necklaces:
                return " IDNecklace;";
            case empty:
                break;
            default:
                return null;
        }
        return null;
    }

    public static String getTable(ItemType itemType)
    {
        switch (itemType)
        {
            case Helmets:
                return " Helmets on ";
            case Armors:
                return " Armors on ";
            case Trousers:
                return " Trousers on ";
            case Shoes:
                return " Shoes on ";
            case Gloves:
                return " Gloves on ";
            case MainWeapons:
                return " MainWeapons on ";
            case AdditionalWeapons:
                return " AdditionalWeapons on ";
            case Necklaces:
                return " Necklaces on ";
            case empty:
                break;
            default:
                return null;
        }
        return null;
    }

    public static String getIdInventory(ItemType itemType)
    {
        switch (itemType)
        {
            case Helmets:
                return " IDInventory_Helmet";
            case Armors:
                return " IDInventory_Armor ";
            case Trousers:
                return " IDInventory_Trousers ";
            case Shoes:
                return " IDInventory_Shoes ";
            case Gloves:
                return " IDInventory_Gloves ";
            case MainWeapons:
                return " IDInventory_MainWeapon ";
            case AdditionalWeapons:
                return " IDInventory_AdditionalWeapon ";
            case Necklaces:
                return " IDInventory_Necklace ";
            case empty:
                break;
            default:
                return null;
        }
        return null;
    }
}
