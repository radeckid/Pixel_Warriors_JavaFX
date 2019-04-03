package pixel_warriors.Character;

public abstract class Slot
{
    protected Item item;
    String path;
    LoadImage loadImage;

    public Slot()
    {
        this.item = null;
        path = "etc\\empty_slot.gif";
        loadImage = new LoadImage(path, "empty");
    }

    public Slot(Item item, String nameImgae)
    {
        this.item = item;
        path = item.GetPath();
        loadImage = new LoadImage(path, nameImgae);
    }

    public void SetItem(Item item)
    {
        this.item = item;
    }

    public Item GetItem()
    {
        return item;
    }
}
