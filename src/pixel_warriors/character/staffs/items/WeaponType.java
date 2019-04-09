package pixel_warriors.character.Staffs.Items;

import java.util.HashMap;
import java.util.Map;

public enum WeaponType {
    OneHanded(1),
    TwoHanded(2);

    private final int value;
    private static Map map = new HashMap<>();

    WeaponType(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }

    public static WeaponType valueOf(int pageType) {
        switch (pageType) {
            case 1:
                return WeaponType.OneHanded;
            case 2:
                return WeaponType.TwoHanded;
            default:
                return null;
        }
    }
}
