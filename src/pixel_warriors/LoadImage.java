package pixel_warriors;

import javafx.scene.image.Image;

public class LoadImage {

    private Image image;
    private String name;
    private int id;

    public LoadImage(String path, String name, int id) {
        image = new Image(this.getClass().getResource("images/" + path).toString());
        this.name = name;
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String path) {
        image = new Image(this.getClass().getResource("images/" + path).toString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
