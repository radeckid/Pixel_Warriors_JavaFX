package pixel_warriors.character.characterlogics;

import pixel_warriors.character.Player;
import pixel_warriors.character.staffs.items.ItemType;
import pixel_warriors.character.staffs.items.Item;
import pixel_warriors.character.staffs.slots.SlotBackpack;
import pixel_warriors.character.staffs.slots.SlotInventory;
import pixel_warriors.character.staffs.Inventory;
import pixel_warriors.character.Statistic;

public class MoveItem {

    public static Item takeOffItem(SlotInventory slot, Inventory inventory, Statistic statistic) {
        Item temp = slot.getItem();
        if (temp != null) {
            temp.substractStatistic(statistic);
        }
        slot.setItem(null);
        return temp;
    }

    public static void putOnItem(SlotBackpack slot, Player player) {
        slot.getItem().addStatistic(player.getStatistic());

        if (slot.getItem() == null) {
            return;
        }


        int id = slot.getIdSlot();
        ItemType itemType = slot.getItem().getItemType();
        Item tempInv = player.getInventory().find(itemType).getItem();

        if (tempInv != null) {
            tempInv.substractStatistic(player.getStatistic());

        }
        player.getInventory().find(itemType).setItem(slot.getItem());
        player.getBackpack().find(id).setItem(tempInv);
    }
}
