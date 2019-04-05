package pixel_warriors.character.staffs.slots;

import javafx.scene.image.ImageView;
import pixel_warriors.character.staffs.items.Item;
import pixel_warriors.character.staffs.items.ItemType;

public class SlotInventory extends Slot
{
    private ItemType slotItemType;

    public SlotInventory(ItemType itemType, ImageView view)
    {
        super(view);
        this.slotItemType = itemType;
    }

    public SlotInventory(Item item, String nameImage, ImageView view)
    {
        super(item, nameImage, view);
        this.slotItemType = item.getItemType();
        if(super.getItem().getItemType()!=slotItemType)
        {
            super.item = null;
            super.item.setPath("etc\\empty_slot.gif");
        }
    }

    public void setItem(Item item)
    {
        if(item == null || item.getItemType() == slotItemType)
        {
            super.setItem(item);
        }
    }

    public ItemType getSlotItemType(){return slotItemType;}
}
