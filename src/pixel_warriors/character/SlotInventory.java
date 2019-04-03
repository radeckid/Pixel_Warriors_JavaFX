package pixel_warriors.character;

import java.lang.reflect.Type;

public class SlotInventory extends Slot
{
    ItemType itemType;

    public SlotInventory(Item item, ItemType itemType, String nameImage)
    {
        super(item, nameImage);
        this.itemType = itemType;
        if(super.GetItem().GetItemType()!=itemType)
        {
            super.item.itemType = itemType;
            super.item.path = "etc\\empty_slot.gif";
        }
    }


    public SlotInventory(ItemType itemType)
    {
        super();
        this.itemType = itemType;
    }

    public void SetItem(Item item)
    {
        if(item.GetItemType() == itemType)
        {
            super.SetItem(item);
        }
    }
}
