package pixel_warriors.character.staffs.items;

public abstract class ItemBody<T> extends Item<T>
{
    String pathBody;

    public ItemBody()
    {

    }
    public ItemBody(int idItem, String name, String path, ItemType itemType,String pathBody)
    {
        super(idItem, name, path, itemType);
        this.pathBody = pathBody;
    }

    public ItemBody(Item armor, ItemType itemType)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), itemType);
        this.pathBody = pathBody;
    }

    public void setItem(T item)
    {
        super.setItem(item);
        pathBody = ((ItemBody) item).getPathBody();
    }

    public String getPathBody() {
        return pathBody;
    }

    public void setPathBody(String pathBody) {
        this.pathBody=pathBody;
    }
}
