package pixel_warriors.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SlotInventory extends Slot {
    ItemType itemType;

    public SlotInventory(ItemType itemType, ImageView view) {
        super(view);
        this.itemType = itemType;
    }


    public SlotInventory(Item item, String nameImage, ItemType itemType, ImageView view) {
        super(item, nameImage, view);
        this.itemType = itemType;
        if (super.GetItem().GetItemType() != itemType) {
            super.item.itemType = itemType;
            super.item.path = "etc\\empty_slot.gif";
        }
    }

    public void SetItem(Item item) {
        if (item.GetItemType() == itemType) {
            super.SetItem(item);
        }
    }
}
