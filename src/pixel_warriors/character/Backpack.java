package pixel_warriors.character;


import java.util.ArrayList;

public class Backpack extends Staff<SlotBackpack>
{
    public Backpack() {
        slots = new ArrayList<SlotBackpack>();
        BuildItemMap();
    }

    private void BuildItemMap() {
        for (int i = 1; i <= 12; i++) {
            slots.add(new SlotBackpack());
        }
    }

    public <Integer> SlotBackpack Find(Integer goal) {
        for (SlotBackpack slot : slots)
        {
            if(slot.id == (int)goal)
            {
                return slot;
            }
        }
        return null;
    }

    public SlotBackpack FindFirstEmpty()
    {
        for (SlotBackpack slot : slots)
        {
            if(slot.item == null)
            {
                return slot;
            }
        }
        return null;
    }
}
