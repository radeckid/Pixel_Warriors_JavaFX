package pixel_warriors.character.Staffs.Items;

import java.sql.SQLException;

public class MainWeapon extends ItemBody<MainWeapon>
{
    int attack;
    WeaponType weaponType;

    public MainWeapon()
    {

    }

    public MainWeapon(int idItem, String name, String path, int attack, WeaponType weaponType, String pathBody)
    {
        super(idItem, name, path, ItemType.MainWeapons, pathBody);
        this.attack = attack;
        this.weaponType = weaponType;
    }

    public MainWeapon(MainWeapon armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.MainWeapons, armor.getPathBody());
        this.weaponType = armor.getWeaponType();
        this.attack = armor.getAttack();
    }

    public void setItem(MainWeapon item)
    {
        super.setItem(item);
        this.weaponType = item.getWeaponType();
        this.attack = item.getAttack();
    }

    public void validation() throws SQLException
    {
        super.validation();
        if(attack==0 || weaponType == null) throw new SQLException();
    }

    public int getAttack() {return attack;}
    public void setAttack(int attack){this.attack = attack;}

    public WeaponType getWeaponType(){return weaponType;}
    public void setWeaponType(WeaponType weaponType){this.weaponType = weaponType;}

    @Override
    public String getPathBody() {
        return pathBody;
    }

    @Override
    public void setPathBody(String pathBody) {
        this.pathBody = pathBody;
    }
}
