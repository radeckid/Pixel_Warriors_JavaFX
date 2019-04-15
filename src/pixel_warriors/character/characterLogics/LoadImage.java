package pixel_warriors.character.CharacterLogics;

import javafx.scene.image.Image;
import pixel_warriors.character.Staffs.Slots.EmptySlotType;

public class LoadImage {

    private Image image;
    private String name;

    public LoadImage(EmptySlotType emptySlotType)
    {
        String path = null;
        switch (emptySlotType)
        {
            case Inv:
                path = "../../images/etc/empty_slot.gif";
                break;
            case Body:
                path = "../../images/etc/empty_wear.gif";
                break;
            default:
                throw new NullPointerException();
        }
        image = new Image(this.getClass().getResource(path).toString());
        this.name = name;
    }

    public LoadImage(String path, String name, EmptySlotType emptySlotType)
    {
        path = "../../images/"+path;
        try
        {
            image = new Image(this.getClass().getResource(path).toString());
        }
        catch (NullPointerException ex)
        {
            switch (emptySlotType)
            {
                case Inv:
                    path = "../../images/etc/empty_slot.gif";
                    break;
                case Body:
                    path = "../../images/etc/empty_wear.gif";
                    break;
                default:
                    throw new NullPointerException();
            }
            image = new Image(this.getClass().getResource(path).toString());
        }
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String path) {
        image = new Image(this.getClass().getResource("images\\" + path).toString());
    }
}
