package pixel_warriors.character;

public class SlotBackpack extends Slot
{
    static int idSlot = 1;
    int id;

    public SlotBackpack()
    {
        super();
        id = SetidSlot();
    }

    public SlotBackpack(Item item, String nameImage)
    {
        super(item, nameImage);
        id = SetidSlot();
    }

    int SetidSlot()
    {
        if((idSlot++) < 12 )
            idSlot++;
        return idSlot;
    }

    public int GetIdSlot() {return id;}
}
