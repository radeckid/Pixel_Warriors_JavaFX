package pixel_warriors.character.Staffs.Items;

import java.sql.SQLException;

public class AdditionalWeapon extends Item<AdditionalWeapon> {
    int attack;

    public AdditionalWeapon() {

    }

    public AdditionalWeapon(int idItem, String name, String path, int attack) {
        super(idItem, name, path, ItemType.AdditionalWeapons);
        this.attack = attack;
    }

    public AdditionalWeapon(AdditionalWeapon armor) {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.AdditionalWeapons);
        this.attack = armor.getAttack();
    }

    public void setItem(AdditionalWeapon item) {
        super.setItem(item);
        this.attack = item.getAttack();
    }

    public void validation() throws SQLException {
        super.validation();
        if (attack == 0) throw new SQLException();
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
