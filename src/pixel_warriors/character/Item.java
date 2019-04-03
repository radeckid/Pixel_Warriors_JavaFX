package pixel_warriors.character;

public class Item
{
    int idItem;
    String name;
    String path;
    ItemType itemType;

    public Item(int idItem, String name, String path, ItemType itemType)
    {
        this.idItem = idItem;
        this.name = name;
        this.path = path;
        this.itemType = itemType;
    }

    public String GetPath()
    {
        return path;
    }

    public String GetName()
    {
        return name;
    }

    public ItemType GetItemType() {return itemType;}
}
