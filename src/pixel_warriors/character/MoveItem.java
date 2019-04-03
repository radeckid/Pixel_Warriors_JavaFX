package pixel_warriors.character;

public class MoveItem {

    public static Item TakeOffItem(SlotInventory slot, Inventory inventory)
    {
        SlotInventory temp = slot;
        slot.item = null;
        return temp.item;
    }

    public static void PutOnItem(SlotBackpack slot, int id, Inventory inventory, Backpack backpack)
    {
        ItemType itemType = slot.item.itemType;
        SlotInventory temp = inventory.Find(itemType);
        inventory.Find(itemType).item = slot.item;
        backpack.Find(id).item = temp.item;
    }

}
