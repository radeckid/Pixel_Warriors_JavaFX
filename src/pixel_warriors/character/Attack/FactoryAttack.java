package pixel_warriors.character.Attack;

import pixel_warriors.character.IPlayerAttackForm;
import pixel_warriors.character.Player;

public class FactoryAttack
{
    public static Attack getAttack(TypeAttack typeAttack, IPlayerAttackForm player, IPlayerAttackForm opponent)
    {
        switch (typeAttack)
        {
            case ligthAttack:
                return new LightAttack(player, opponent);
            case normalAttack:
                return new NormalAttack(player, opponent);
            case strongAttack:
                return new StrongAttack(player, opponent);
        }
        return null;
    }
}
