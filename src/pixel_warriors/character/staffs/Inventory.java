package pixel_warriors.character.staffs;

import javafx.scene.image.ImageView;
import pixel_warriors.character.staffs.items.*;
import pixel_warriors.character.staffs.slots.SlotInventory;
import pixel_warriors.character.staffs.slots.SlotInventoryBody;

import java.util.ArrayList;
import java.util.Map;

public class Inventory extends Staff<SlotInventory> {
    public Inventory() {
        super();
        slots = new ArrayList<SlotInventory>();
    }

    public Inventory(ImageView[] views, ImageView[] viewsBody) {
        super(views);
        slots = new ArrayList<SlotInventory>();
        BuildWearedStuff(views, viewsBody);
    }

    public Inventory(Map<ItemType, Item> items, ImageView[] views, ImageView[] viewsBody) {
        this();
        slots.add(new SlotInventoryBody((ItemBody) items.get(ItemType.Helmets), views[0], ItemType.Helmets, new ImageView[]{viewsBody[0]}));
        slots.add(new SlotInventoryBody((ItemBody) items.get(ItemType.Armors), views[1], ItemType.Armors, new ImageView[]{viewsBody[1]}));
        slots.add(new SlotInventoryBody((ItemBody) items.get(ItemType.Trousers), views[2], ItemType.Trousers, new ImageView[]{viewsBody[2]}));
        slots.add(new SlotInventoryBody((ItemBody) items.get(ItemType.Shoes), views[3], ItemType.Shoes, new ImageView[]{viewsBody[3]}));
        slots.add(new SlotInventory(items.get(ItemType.Necklaces), views[4], ItemType.Necklaces));
        slots.add(new SlotInventoryBody((ItemBody) items.get(ItemType.MainWeapons), views[5], ItemType.MainWeapons, new ImageView[]{viewsBody[4]}));
        slots.add(new SlotInventoryBody((ItemBody) items.get(ItemType.AdditionalWeapons), views[6], ItemType.AdditionalWeapons, new ImageView[]{viewsBody[5]}));
        slots.add(new SlotInventoryBody((ItemBody) items.get(ItemType.Gloves), views[7], ItemType.Gloves, new ImageView[]{viewsBody[6], viewsBody[7]}));
    }

    public void setInventory(Item[] items) {
        this.find(ItemType.Armors).setItem(items[0]);
        this.find(ItemType.Trousers).setItem(items[2]);
        this.find(ItemType.Helmets).setItem(items[4]);
        this.find(ItemType.Gloves).setItem(items[1]);
        this.find(ItemType.Shoes).setItem(items[3]);
        this.find(ItemType.MainWeapons).setItem(items[5]);
        this.find(ItemType.AdditionalWeapons).setItem(items[6]);
        this.find(ItemType.Necklaces).setItem(items[7]);
    }

    private void BuildWearedStuff(ImageView[] views, ImageView[] viewsBody) {
        slots.add(new SlotInventoryBody(ItemType.Armors, views[1], new ImageView[]{viewsBody[3]}));
        slots.add(new SlotInventoryBody(ItemType.Trousers, views[2], new ImageView[]{viewsBody[4]}));
        slots.add(new SlotInventoryBody(ItemType.Helmets, views[0], new ImageView[]{viewsBody[2]}));
        slots.add(new SlotInventoryBody(ItemType.Gloves, views[7], new ImageView[]{viewsBody[6], viewsBody[7]}));
        slots.add(new SlotInventoryBody(ItemType.Shoes, views[3], new ImageView[]{viewsBody[5]}));
        slots.add(new SlotInventoryBody(ItemType.MainWeapons, views[5], new ImageView[]{viewsBody[0]}));
        slots.add(new SlotInventoryBody(ItemType.AdditionalWeapons, views[6], new ImageView[]{viewsBody[1]}));
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

    public void update()
    {
        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).updateSlot();
        }
    }
}
