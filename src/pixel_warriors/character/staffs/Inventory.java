package pixel_warriors.character.staffs;

import javafx.scene.image.ImageView;
import pixel_warriors.character.staffs.items.ItemType;
import pixel_warriors.character.staffs.slots.SlotInventory;

import java.util.ArrayList;

public class Inventory extends Staff<SlotInventory> {
    public Inventory() {
        super();
        slots = new ArrayList<SlotInventory>();
        buildWearedStuff();
    }

    public Inventory(ImageView[] views) {
        super(views);
        slots = new ArrayList<SlotInventory>();
        buildWearedStuff();
    }

    private void buildWearedStuff() {
        slots.add(new SlotInventory(ItemType.Armor, views[1]));
        slots.add(new SlotInventory(ItemType.Trousers, views[2]));
        slots.add(new SlotInventory(ItemType.Helmets, views[0]));
        slots.add(new SlotInventory(ItemType.Gloves, views[7]));
        slots.add(new SlotInventory(ItemType.Shoes, views[3]));
        slots.add(new SlotInventory(ItemType.MainWeapons, views[5]));
        slots.add(new SlotInventory(ItemType.AdditionalWeapons, views[6]));
        slots.add(new SlotInventory(ItemType.Necklaces, views[4]));
    }

    @Override
    public <ItemType> SlotInventory find(ItemType goal) {
        SlotInventory temp = null;

        for (SlotInventory slot : slots) {
            if (slot.getSlotItemType() == goal) {
                temp = slot;
                break;
            }
        }
        return temp;
    }
}
