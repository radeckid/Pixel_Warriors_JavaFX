package pixel_warriors;

import java.util.HashMap;
import java.util.Map;

public class BackpackSlot {

    private String itemName;
    private int itemId, slotId;
    private Map<Integer, LoadImage> invBackpackMap = new HashMap<Integer, LoadImage>();
    private Map<Integer, LoadImage> wearedStuff = new HashMap<Integer, LoadImage>();


    public BackpackSlot(String itemName, int itemId, int slotId) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.slotId = slotId;
    }

    public BackpackSlot() {
        BuildItemMap();
        BuildWearedStuff();
    }

    private void BuildItemMap() {
        for (int i = 1; i <= 12; i++) {
            invBackpackMap.put(i, new LoadImage("etc\\empty_slot.gif", "slot_" + i, i));
        }
    }

    private void BuildWearedStuff() {
        wearedStuff.put(1, new LoadImage("etc\\empty_slot.gif", "head", 1));
        wearedStuff.put(2, new LoadImage("etc\\empty_slot.gif", "chest", 2));
        wearedStuff.put(3, new LoadImage("etc\\empty_slot.gif", "legs", 3));
        wearedStuff.put(4, new LoadImage("etc\\empty_slot.gif", "shoes", 4));
        wearedStuff.put(5, new LoadImage("jewelerys\\Naszyjnik1_Gif.gif", "jewelery", 5));
        wearedStuff.put(6, new LoadImage("etc\\empty_slot.gif", "weapon_one", 6));
        wearedStuff.put(7, new LoadImage("etc\\empty_slot.gif", "weapon_two", 7));
        wearedStuff.put(8, new LoadImage("etc\\empty_slot.gif", "gloves", 8));
    }

    public Map<Integer, LoadImage> getInvBackpackMap() {
        return invBackpackMap;
    }

    public Map<Integer, LoadImage> getWearedStuff() {
        return wearedStuff;
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
