package pixel_warriors.character.staffs.slots;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pixel_warriors.character.staffs.items.Item;
import pixel_warriors.character.characterLogics.LoadImage;

public abstract class Slot
{
    protected Item item;
    String path;
    LoadImage loadImage;
    ImageView view;

    public Slot()
    {
        this.item = null;
        path = "etc\\empty_slot.gif";
        loadImage = new LoadImage(path, "empty");
    }

    public Slot(ImageView view)
    {
        this();
        this.view = view;
        view.setImage(loadImage.getImage());
    }

    public Slot(Item item, String nameImgae, ImageView view)
    {
        this.item = item;
        path = item.getPath();
        loadImage = new LoadImage(path, nameImgae);
        this.view = view;
        view.setImage(loadImage.getImage());
    }

    public void updateSlot()
    {
        if(item == null)
        {
            path = "etc\\empty_slot.gif";
            loadImage = new LoadImage();
        }
        else
        {
            path = item.getPath();
            loadImage = new LoadImage(path, "test");
        }
        view.setImage(loadImage.getImage());
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public Item getItem()
    {
        return item;
    }

    public Image getImage()
    {
        return loadImage.getImage();
    }
}
