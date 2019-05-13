package pixel_warriors.character;

import java.util.HashMap;
import java.util.Map;

public enum TypeCharacter
{
    Warrior('W');

    private final char value;
    private static Map map = new HashMap<>();

    TypeCharacter(char i) {
        value = i;
    }

    public char getValue() {
        return value;
    }

    public static TypeCharacter valueOf(char pageType) {

        switch (pageType)
        {
            case 'W':
                return TypeCharacter.Warrior;
            default:
                return null;
        }
    }
}
