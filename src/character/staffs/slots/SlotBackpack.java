package pixel_warriors.character.staffs.slots;

import javafx.scene.image.ImageView;
import pixel_warriors.character.staffs.items.Item;

public class SlotBackpack extends Slot
{
    static int idSlot = 0;
    int id;

    public SlotBackpack()
    {
        super();
        id = setIdSlot();
    }

    public SlotBackpack(ImageView view)
    {
        super(view);
        id = setIdSlot();
    }

    public SlotBackpack(Item item, String nameImage, ImageView view)
    {
        super(item, nameImage, view);
        id = setIdSlot();
    }

    int setIdSlot()
    {
        if((idSlot+1) < 12 )
            idSlot++;
        return idSlot;
    }

    public int getIdSlot() {return id;}
}
