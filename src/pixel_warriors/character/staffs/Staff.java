package pixel_warriors.character.staffs;

import javafx.scene.image.ImageView;

import java.util.List;

public abstract class Staff <T>
{
    protected List<T> slots;
    protected ImageView[] views;

    public Staff()
    {
        this.views = null;
    }

    public Staff(ImageView[] views)
    {
        this.views = views;
    }

    public List<T> getList()
    {
        return slots;
    }

    public abstract <K> T find(K goal);

    public T getElementById(int i)
    {
        return slots.get(i);
    }

    public abstract void update();
}
