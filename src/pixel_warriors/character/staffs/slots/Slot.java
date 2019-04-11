package pixel_warriors.character.Staffs.Slots;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pixel_warriors.character.Staffs.Items.FactoryItem;
import pixel_warriors.character.Staffs.Items.Item;
import pixel_warriors.character.CharacterLogics.LoadImage;

public abstract class Slot {

    protected Item item;
    String path;
    LoadImage loadImage;
    ImageView view;


    public Slot() {
        this.item = null;
        path = "etc\\empty_slot.gif";
        loadImage = new LoadImage(path, "empty");
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
        loadImage = new LoadImage(path, nameImage);
        this.view = view;
        view.setImage(loadImage.getImage());
    }

    public void updateSlot() {
        if (item == null) {
            path = "etc\\empty_slot.gif";
            loadImage = new LoadImage();
        } else {
            path = item.getPath();
            loadImage = new LoadImage(path, "test");
        }
        view.setImage(loadImage.getImage());
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public Image getImage() {
        return loadImage.getImage();
    }

    public ImageView getImageView() {
        return view;
    }
}
