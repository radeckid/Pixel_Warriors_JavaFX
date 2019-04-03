package pixel_warriors.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        path = item.GetPath();
        loadImage = new LoadImage(path, nameImgae);
        this.view = view;
        view.setImage(loadImage.getImage());
    }

    public void UpdateSlot()
    {
        if(item == null)
        {
            path = "etc\\empty_slot.gif";
            loadImage = new LoadImage();
        }
        else
        {
            path = item.path;
            loadImage = new LoadImage(path, "test");
        }
        view.setImage(loadImage.getImage());
    }

    public void SetItem(Item item)
    {
        this.item = item;
    }

    public Item GetItem()
    {
        return item;
    }

    public Image GetImage()
    {
        return loadImage.getImage();
    }
}
