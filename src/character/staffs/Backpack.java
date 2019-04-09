package pixel_warriors.character.staffs;


import javafx.scene.image.ImageView;
import pixel_warriors.character.staffs.slots.SlotBackpack;

import java.util.ArrayList;

public class Backpack extends Staff<SlotBackpack>
{
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

    private void buildItemMap() {
        for (int i = 1; i <= 12; i++) {
            slots.add(new SlotBackpack(super.views[i-1]));
        }
    }

    public <Integer> SlotBackpack find(Integer goal) {
        for (SlotBackpack slot : slots)
        {
            if(slot.getIdSlot() == (int)goal)
            {
                return slot;
            }
        }
        return null;
    }

    public SlotBackpack findFirstEmpty()
    {
        for (SlotBackpack slot : slots)
        {
            if(slot.getItem() == null)
            {
                return slot;
            }
        }
        return null;
    }
}
