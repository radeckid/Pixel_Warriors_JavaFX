package pixel_warriors.character.staffs.items;

import java.util.HashMap;
import java.util.Map;

public enum TypeFirstBonus
{
    Strenght('S'),
    Agility('A');
    private final char value;
    private static Map map = new HashMap<>();

    TypeFirstBonus(char i) {
        value = i;
    }

    public char getValue() {
        return value;
    }

    public static TypeFirstBonus valueOf(char pageType) {

        switch (pageType)
        {
            case 'S':
                return TypeFirstBonus.Strenght;
            case 'A':
                return TypeFirstBonus.Agility;
            default:
                return null;
        }
    }
}
