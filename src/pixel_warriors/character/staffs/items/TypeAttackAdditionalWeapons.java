package pixel_warriors.character.staffs.items;

import java.util.HashMap;
import java.util.Map;

public enum TypeAttackAdditionalWeapons
{
    Attack('A'),
    Defense('D');
    private final char value;
    private static Map map = new HashMap<>();

    TypeAttackAdditionalWeapons(char i) {
        value = i;
    }

    public char getValue() {
        return value;
    }

    public static TypeAttackAdditionalWeapons valueOf(char pageType) {

        switch (pageType)
        {
            case 'A':
                return TypeAttackAdditionalWeapons.Attack;
            case 'D':
                return TypeAttackAdditionalWeapons.Defense;
            default:
                return null;
        }
    }
}
