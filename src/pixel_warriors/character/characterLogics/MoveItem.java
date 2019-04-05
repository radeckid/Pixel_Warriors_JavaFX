package pixel_warriors.character.characterLogics;

import pixel_warriors.character.staffs.items.ItemType;
import pixel_warriors.character.staffs.items.Item;
import pixel_warriors.character.staffs.slots.SlotBackpack;
import pixel_warriors.character.staffs.slots.SlotInventory;
import pixel_warriors.character.staffs.Backpack;
import pixel_warriors.character.staffs.Inventory;

public class MoveItem {

    public static Item takeOffItem(SlotInventory slot, Inventory inventory) {
        Item temp = slot.getItem();
        slot.setItem(null);
        return temp;
    }

    public static void putOnItem(SlotBackpack slot, Inventory inventory, Backpack backpack) {
        if (slot.getItem() == null) return;
        int id = slot.getIdSlot();
        ItemType itemType = slot.getItem().getItemType();
        Item tempInv = inventory.find(itemType).getItem();
        inventory.find(itemType).setItem(slot.getItem());
        backpack.find(id).setItem(tempInv);
    }

    public static void update(Inventory inventory, Backpack backpack) {
        for (int i = 0; i < inventory.getList().size(); i++) {
            inventory.getElementById(i).updateSlot();
        }

        for (int i = 0; i < backpack.getList().size(); i++) {
            backpack.getElementById(i).updateSlot();
        }
    }
}
