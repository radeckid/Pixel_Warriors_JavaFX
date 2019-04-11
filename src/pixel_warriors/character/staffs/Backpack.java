package pixel_warriors.character.Staffs;


import javafx.scene.image.ImageView;
import pixel_warriors.character.Staffs.Items.Item;
import pixel_warriors.character.Staffs.Items.ItemType;
import pixel_warriors.character.Staffs.Slots.SlotBackpack;

import java.util.ArrayList;
import java.util.Map;

public class Backpack extends Staff<SlotBackpack> {

    public Backpack() {
        super();
        slots = new ArrayList<SlotBackpack>();
        buildItemMap();
    }

    public Backpack(ImageView[] view) {
        super(view);
        slots = new ArrayList<SlotBackpack>();
        buildItemMap();
    }

    public Backpack(ArrayList<Item> items, ImageView[] view) {
        super(view);
        slots = new ArrayList<SlotBackpack>();

        slots.add(new SlotBackpack(items.get(0), view[0]));
        slots.add(new SlotBackpack(items.get(1), view[1]));
        slots.add(new SlotBackpack(items.get(2), view[2]));
        slots.add(new SlotBackpack(items.get(3), view[3]));
        slots.add(new SlotBackpack(items.get(4), view[4]));
        slots.add(new SlotBackpack(items.get(5), view[5]));
        slots.add(new SlotBackpack(items.get(6), view[6]));
        slots.add(new SlotBackpack(items.get(7), view[7]));
        slots.add(new SlotBackpack(items.get(8), view[8]));
        slots.add(new SlotBackpack(items.get(9), view[9]));
        slots.add(new SlotBackpack(items.get(10), view[10]));
        slots.add(new SlotBackpack(items.get(11), view[11]));
    }

    private void buildItemMap() {
        for (int i = 1; i <= 12; i++) {
            slots.add(new SlotBackpack(super.views[i - 1]));
        }
    }

    public <Integer> SlotBackpack find(Integer goal) {
        for (SlotBackpack slot : slots) {
            if (slot.getIdSlot() == (int) goal) {
                return slot;
            }
        }
        return null;
    }

    public SlotBackpack findFirstEmpty() {
        for (SlotBackpack slot : slots) {
            if (slot.getItem() == null) {
                return slot;
            }
        }
        return null;
    }

    public void update() {
        for (int i = 0; i < slots.size(); i++) {
            slots.get(i).updateSlot();
        }
    }
}
