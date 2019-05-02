package pixel_warriors.character.staffs.items;

public class MainWeapon extends Weapon<MainWeapon>
{
    int attack;
    WeaponType weaponType;

    public MainWeapon()
    {

    }

    public MainWeapon(int idItem, String name, String path, int attack, WeaponType weaponType, String pathBody)
    {
        super(idItem, name, path, attack, pathBody, ItemType.MainWeapons);
        this.weaponType = weaponType;
    }

    public MainWeapon(MainWeapon armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), armor.getAttack(), armor.getPathBody(), ItemType.MainWeapons);
        this.weaponType = armor.getWeaponType();
    }

    public WeaponType getWeaponType(){return weaponType;}
    public void setWeaponType(WeaponType weaponType){this.weaponType = weaponType;}

}
