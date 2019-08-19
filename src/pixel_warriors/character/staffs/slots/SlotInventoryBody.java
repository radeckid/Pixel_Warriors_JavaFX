package pixel_warriors.character.staffs.slots;

import javafx.scene.image.ImageView;
import pixel_warriors.character.characterlogics.LoadImage;
import pixel_warriors.character.staffs.items.Item;
import pixel_warriors.character.staffs.items.ItemBody;
import pixel_warriors.character.staffs.items.ItemType;

public class SlotInventoryBody extends SlotInventory
{
    private ImageView[] viewBody;
    String pathBody;

    public SlotInventoryBody(ItemType itemType, ImageView view, ImageView[] viewBody)
    {
        super(itemType,view);
        this.viewBody = viewBody;
    }

    public SlotInventoryBody(ItemBody item, ImageView view, ItemType itemType, ImageView[] viewBody)
    {
        super(item,view,itemType);
        if(item != null)
        {
            pathBody = item.getPathBody();
        }
        this.viewBody = viewBody;
    }

    public SlotInventoryBody(ItemType itemType, SlotInventoryBody slotInventoryBody)
    {
        super(itemType, slotInventoryBody);
        if(slotInventoryBody.getItem() != null)
        {
            pathBody = slotInventoryBody.getPathBody();
        }
        viewBody = slotInventoryBody.getViewBody();
    }

    public void setItem(Item item)
    {
        if(item != null)
        {
            if(item.getItemType() != ItemType.empty)
                pathBody = ((ItemBody)item).getPathBody();
        }
        super.setItem(item);
    }

    public void updateSlot()
    {
        if(item == null)
        {
            pathBody = "etc\\empty_wear.gif";
            for(ImageView imageView : viewBody)
                imageView.setImage(new LoadImage(pathBody,"empty",EmptySlotType.Body).getImage());
        }
        else
        {
            pathBody = ((ItemBody)item).getPathBody();
            for(ImageView imageView : viewBody)
                imageView.setImage(new LoadImage(pathBody,item.getName(),EmptySlotType.Body).getImage());
        }

        super.updateSlot();
    }

    public ImageView[] getViewBody() {
        return viewBody;
    }

    public void setViewBody(ImageView[] viewBody) {
        this.viewBody = viewBody;
    }

    public String getPathBody() {
        return pathBody;
    }

    public void setPathBody(String pathBody) {
        this.pathBody = pathBody;
    }
}
