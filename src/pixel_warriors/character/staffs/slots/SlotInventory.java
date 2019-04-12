package pixel_warriors.character.Staffs.Slots;

import javafx.scene.image.ImageView;
import pixel_warriors.character.Staffs.Items.Item;
import pixel_warriors.character.Staffs.Items.ItemType;

public class SlotInventory extends Slot {
    private ItemType slotItemType;

    public SlotInventory(ItemType itemType, ImageView view) {
        super(view);
        this.slotItemType = itemType;
    }

    public SlotInventory(Item item, ImageView view, ItemType itemType) {
        super(item, view);
        this.slotItemType = itemType;
        if (super.getItem() != null && (super.getItem().getItemType() != slotItemType)) {
            super.item = null;
            super.item.setPath("etc\\empty_slot.gif");
        }
    }

    public SlotInventory(ItemType itemType, SlotInventory slotInventory) {
        super(slotInventory.getItem(), slotInventory.getImageView());
        this.slotItemType = itemType;
        if (super.getItem() != null && (slotInventory.getItem().getItemType() != slotItemType)) {
            super.item = null;
            super.item.setPath("etc\\empty_slot.gif");
        }
    }

    public void setItem(Item item) {
        if (item == null || item.getItemType() == slotItemType) {
            super.setItem(item);
        }
    }

    public ItemType getSlotItemType() {
        return slotItemType;
    }
}
