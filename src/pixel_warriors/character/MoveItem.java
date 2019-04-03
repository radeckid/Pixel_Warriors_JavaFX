package pixel_warriors.character;

public class MoveItem {

    public static Item TakeOffItem(SlotInventory slot, Inventory inventory)
    {
        Item temp = slot.GetItem();
        slot.item = null;
        return temp;
    }

    public static void PutOnItem(SlotBackpack slot, Inventory inventory, Backpack backpack)
    {
        int id = slot.GetIdSlot();
        ItemType itemType = slot.item.itemType;
        Item tempInv = inventory.Find(itemType).GetItem();
        inventory.Find(itemType).item = slot.item;
        backpack.Find(id).item = tempInv;
    }

    public static void Update(Inventory inventory, Backpack backpack)
    {

        for(int i=0; i < inventory.GetList().size(); i++) {
            inventory.GetElementById(i).UpdateSlot();
        }

        for(int i=0; i < backpack.GetList().size(); i++) {
            backpack.GetElementById(i).UpdateSlot();
        }
    }
}
