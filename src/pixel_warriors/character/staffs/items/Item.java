package pixel_warriors.character.Staffs.Items;

import java.sql.SQLException;

public abstract class Item<T> {
    int idItem;
    String name;
    String path;
    ItemType itemType;

    Item() {
        this.idItem = 0;
        this.name = "null";
        this.path = "../../images/etc/empty_slot.gif";
        this.itemType = ItemType.empty;
    }

    public Item(int idItem, String name, String path, ItemType itemType) {
        this.idItem = idItem;
        this.name = name;
        this.path = path;
        this.itemType = itemType;
    }

    public void setItem(T item) {
        Item temp = (Item) item;
        this.idItem = temp.getIdItem();
        this.name = temp.getName();
        this.path = temp.getPath();
        this.itemType = temp.getItemType();
    }

    public String toString() {
        return "Item type: " + itemType + "\nName: " + name + "\n";
    }

    public String getPath() {
        return path;
    }

    public void validation() throws SQLException {
        if (path.isEmpty() == true || name == "" || idItem == 0) throw new SQLException();
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public int getIdItem() {
        return idItem;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
