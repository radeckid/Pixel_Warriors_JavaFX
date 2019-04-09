package pixel_warriors.character.Staffs.Slots;

import javafx.scene.image.ImageView;
import pixel_warriors.character.Staffs.Items.Item;

public class SlotBackpack extends Slot
{
    static int idSlot = 0;
    int id;

    public SlotBackpack()
    {
        super();
        id = setidSlot();
    }

    public SlotBackpack(ImageView view)
    {
        super(view);
        id = setidSlot();
    }

    public SlotBackpack(Item item, ImageView view)
    {
        super(item, view);
        id = setidSlot();
    }

    int setidSlot()
    {
        if((idSlot+1) < 12 )
            idSlot++;
        return idSlot;
    }

    public int getIdSlot() {return id;}
}
