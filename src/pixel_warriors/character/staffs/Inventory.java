package pixel_warriors.character.Staffs;

import javafx.scene.image.ImageView;
import pixel_warriors.character.Staffs.Items.Item;
import pixel_warriors.character.Staffs.Items.ItemType;
import pixel_warriors.character.Staffs.Slots.SlotInventory;

import java.util.ArrayList;
import java.util.Map;

public class Inventory extends Staff<SlotInventory> {
    public Inventory() {
        super();
        slots = new ArrayList<SlotInventory>();
    }

    public Inventory(ImageView[] views) {
        super(views);
        slots = new ArrayList<SlotInventory>();
        buildWearedStuff(views);
    }

    public Inventory(Map<ItemType, Item> items, ImageView[] views) {
        this();
        slots.add(new SlotInventory(items.get(ItemType.Armor), views[1], ItemType.Armor));
        slots.add(new SlotInventory(items.get(ItemType.Trousers), views[2], ItemType.Trousers));
        slots.add(new SlotInventory(items.get(ItemType.Helmets), views[0], ItemType.Helmets));
        slots.add(new SlotInventory(items.get(ItemType.Gloves), views[7], ItemType.Gloves));
        slots.add(new SlotInventory(items.get(ItemType.Shoes), views[3], ItemType.Shoes));
        slots.add(new SlotInventory(items.get(ItemType.MainWeapons), views[5], ItemType.MainWeapons));
        slots.add(new SlotInventory(items.get(ItemType.AdditionalWeapons), views[6], ItemType.AdditionalWeapons));
        slots.add(new SlotInventory(items.get(ItemType.Necklaces), views[4], ItemType.Necklaces));
    }

    public void setInventory(Item[] items) {
        this.find(ItemType.Armor).setItem(items[0]);
        this.find(ItemType.Trousers).setItem(items[2]);
        this.find(ItemType.Helmets).setItem(items[4]);
        this.find(ItemType.Gloves).setItem(items[1]);
        this.find(ItemType.Shoes).setItem(items[3]);
        this.find(ItemType.MainWeapons).setItem(items[5]);
        this.find(ItemType.AdditionalWeapons).setItem(items[6]);
        this.find(ItemType.Necklaces).setItem(items[7]);
    }

    private void buildWearedStuff(ImageView[] views) {
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

    public void update() {
        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).updateSlot();
        }
    }
}
