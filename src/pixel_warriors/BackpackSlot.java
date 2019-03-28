package pixel_warriors;

public class BackpackSlot {

    private String itemName;
    private int itemId, slotId;

    public BackpackSlot(String itemName, int itemId, int slotId) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.slotId = slotId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
