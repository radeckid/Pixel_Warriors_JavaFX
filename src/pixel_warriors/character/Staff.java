package pixel_warriors.character;

import java.util.List;

public abstract class Staff <T>
{
    protected List<T> slots;

    public List<T> GetList()
    {
        return slots;
    }

    public abstract <K> T Find(K goal);


}
