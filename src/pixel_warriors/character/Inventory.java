package pixel_warriors.character;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends Staff<SlotInventory>
{
    private List<SlotInventory> wearedStuff = new ArrayList<SlotInventory>();

    public Inventory() {
        BuildWearedStuff();
    }

    private void BuildWearedStuff() {
        wearedStuff.add(new SlotInventory(ItemType.Armor));
        wearedStuff.add(new SlotInventory(ItemType.Trousers));
        wearedStuff.add(new SlotInventory(ItemType.Helmets));
        wearedStuff.add(new SlotInventory(ItemType.Gloves));
        wearedStuff.add(new SlotInventory(ItemType.Shoes));
        wearedStuff.add(new SlotInventory(ItemType.MainWeapons));
        wearedStuff.add(new SlotInventory(ItemType.AdditionalWeapons));
        wearedStuff.add(new SlotInventory(ItemType.Necklaces));
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
