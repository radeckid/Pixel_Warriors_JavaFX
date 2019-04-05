package pixel_warriors.character.characterLogics;

import javafx.scene.image.Image;

public class LoadImage {

    private Image image;
    private String name;

    public LoadImage()
    {
        String path = "../../images/etc/empty_slot.gif";
        image = new Image(this.getClass().getResource(path).toString());
        this.name = name;
    }

    public LoadImage(String path, String name)
    {
        path = "../../images/"+path;
        image = new Image(this.getClass().getResource(path).toString());
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String path) {
        image = new Image(this.getClass().getResource("images\\" + path).toString());
    }
}
