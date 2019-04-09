package pixel_warriors.character.staffs.items;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FactoryItem
{
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
                    break;
                case Armor:
                    return new Armor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                case Trousers:
                    break;
                case Shoes:
                    break;
                case Gloves:
                    break;
                case MainWeapons:
                    break;
                case AdditionalWeapons:
                    break;
                case Necklaces:
                    break;
                case empty:
                    break;
                default:
                    return null;
            }
        }
        catch (SQLException ex)
        {
            return null;
        }
        return null;
    }

    public static String getAttributesItem(ItemType itemType)
    {
        switch (itemType)
        {
            case Helmets:
                break;
            case Armor:
                return " idArmor, Name, Path, PhysicalDefense, MagicalDefense ";
            case Trousers:
                break;
            case Shoes:
                break;
            case Gloves:
                break;
            case MainWeapons:
                break;
            case AdditionalWeapons:
                break;
            case Necklaces:
                break;
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
                break;
            case Armor:
                return " Armors on IDInventory_Armor=IDArmor;";
            case Trousers:
                break;
            case Shoes:
                break;
            case Gloves:
                break;
            case MainWeapons:
                break;
            case AdditionalWeapons:
                break;
            case Necklaces:
                break;
            case empty:
                break;
            default:
                return null;
        }
        return null;
    }
}
