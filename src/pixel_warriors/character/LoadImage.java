package pixel_warriors.character;

import javafx.scene.image.Image;

public class LoadImage {

    private Image image;
    private String name;

    public LoadImage(String path, String name)
    {
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
