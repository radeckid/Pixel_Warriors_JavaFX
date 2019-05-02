package pixel_warriors.character.staffs.slots;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pixel_warriors.character.characterlogics.InsertTread;
import pixel_warriors.character.staffs.items.FactoryItem;
import pixel_warriors.character.staffs.items.Item;
import pixel_warriors.character.characterlogics.LoadImage;
import pixel_warriors.character.staffs.items.ItemType;

public abstract class Slot {
    protected Item item;
    String path;
    LoadImage loadImage;
    ImageView view;

    public Slot() {
        this.item = null;
        path = "etc\\empty_slot.gif";
        loadImage = new LoadImage(path, "empty", EmptySlotType.Inv);
    }

    public Slot(ImageView view) {
        this();
        this.view = view;
        view.setImage(loadImage.getImage());
    }

    public Slot(Item item, ImageView view) {
        this.item = FactoryItem.getItem(item);
        String nameImage;
        if (item == null) {
            nameImage = "empty";
            path = "etc\\empty_slot.gif";
        } else {
            nameImage = item.getName();
            path = item.getPath();
        }
        loadImage = new LoadImage(path, nameImage, EmptySlotType.Inv);
        this.view = view;
        view.setImage(loadImage.getImage());
    }

    public void updateSlot() {
        if (item == null) {
            path = "etc\\empty_slot.gif";
            loadImage = new LoadImage(EmptySlotType.Inv);
        } else {
            path = item.getPath();
            loadImage = new LoadImage(path, "test", EmptySlotType.Inv);
        }
        view.setImage(loadImage.getImage());
        InsertTread insertTread = new InsertTread(this);
        insertTread.start();
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        if (item == null || item.getItemType() == ItemType.empty) {
            return null;
        }
        return item;
    }

    public ItemType getItemType() {
        if (item == null) {
            return ItemType.empty;
        } else {
            return item.getItemType();
        }
    }

    public Image getImage() {
        return loadImage.getImage();
    }

    public void setLoadImage(LoadImage loadImage) {
        this.loadImage = loadImage;
    }

    public ImageView getImageView() {
        return view;
    }

    public void setImage() {
        view.setImage(loadImage.getImage());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
