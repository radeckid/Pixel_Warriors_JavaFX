package pixel_warriors.character;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Backpack extends Staff<SlotBackpack>
{
    public Backpack() {
        super();
        slots = new ArrayList<SlotBackpack>();
        BuildItemMap();
    }

    public Backpack(ImageView[] view) {
        super(view);
        slots = new ArrayList<SlotBackpack>();
        BuildItemMap();
    }

    private void BuildItemMap() {
        for (int i = 1; i <= 12; i++) {
            slots.add(new SlotBackpack(super.views[i-1]));
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
