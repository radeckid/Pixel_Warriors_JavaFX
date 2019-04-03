package pixel_warriors.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SlotBackpack extends Slot
{
    static int idSlot = 0;
    int id;

    public SlotBackpack()
    {
        super();
        id = SetidSlot();
    }

    public SlotBackpack(ImageView view)
    {
        super(view);
        id = SetidSlot();
    }

    public SlotBackpack(Item item, String nameImage, ImageView view)
    {
        super(item, nameImage, view);
        id = SetidSlot();
    }

    int SetidSlot()
    {
        if((idSlot+1) < 12 )
            idSlot++;
        return idSlot;
    }

    public int GetIdSlot() {return id;}
}
