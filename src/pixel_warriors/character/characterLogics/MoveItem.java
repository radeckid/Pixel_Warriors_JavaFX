package pixel_warriors.character.CharacterLogics;

import pixel_warriors.character.Staffs.Items.ItemType;
import pixel_warriors.character.Staffs.Items.Item;
import pixel_warriors.character.Staffs.Slots.SlotBackpack;
import pixel_warriors.character.Staffs.Slots.SlotInventory;
import pixel_warriors.character.Staffs.Backpack;
import pixel_warriors.character.Staffs.Inventory;

public class MoveItem {

    public static Item takeOffItem(SlotInventory slot, Inventory inventory)
    {
        Item temp = slot.getItem();
        slot.setItem(null);
        return temp;
    }

    public static void putOnItem(SlotBackpack slot, Inventory inventory, Backpack backpack)
    {
        if(slot.getItem()==null) return;
        int id = slot.getIdSlot();
        ItemType itemType = slot.getItem().getItemType();
        Item tempInv = inventory.find(itemType).getItem();
        inventory.find(itemType).setItem(slot.getItem());
        backpack.find(id).setItem(tempInv);
    }
}
