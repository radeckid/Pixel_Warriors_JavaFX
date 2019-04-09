package pixel_warriors.character.Staffs.Items;

import java.util.HashMap;
import java.util.Map;

public enum FourthBonusType {
    Critical('C'),
    DefenseChance('D');
    private final char value;
    private static Map map = new HashMap<>();

    FourthBonusType(char i) {
        value = i;
    }

    public char getValue() {
        return value;
    }

    public static FourthBonusType valueOf(char pageType) {

        switch (pageType) {
            case 'C':
                return FourthBonusType.Critical;
            case 'D':
                return FourthBonusType.DefenseChance;
            default:
                return null;
        }
    }
}

