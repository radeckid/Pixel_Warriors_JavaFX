package pixel_warriors.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory extends Staff<SlotInventory>
{
    public Inventory() {
        super();
        slots = new ArrayList<SlotInventory>();
        BuildWearedStuff();
    }

    public Inventory(ImageView[] views)
    {
        super(views);
        slots = new ArrayList<SlotInventory>();
        BuildWearedStuff();
    }

    private void BuildWearedStuff() {
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
    public <ItemType> SlotInventory Find(ItemType goal) {
        SlotInventory temp = null;

        for (SlotInventory slot : slots)
        {
            if(slot.itemType == goal)
            {
                temp = slot;
                break;
            }
        }
        return temp;
    }
}
