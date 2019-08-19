package pixel_warriors.character.Attack;

import pixel_warriors.character.IPlayerAttackForm;
import pixel_warriors.character.TypeCharacter;
import pixel_warriors.character.Warrior;

import java.util.Random;

public class LightAttack extends Attack
{
    public LightAttack(IPlayerAttackForm player, IPlayerAttackForm opponent)
    {
        super(player,opponent);
        multiplier = 1;
        chanceAttack = 3;
    }

    public int attack()
    {
        try
        {
            playerStatistic.getStatistic().setMana(playerStatistic.getStatistic().getMana() + 7);
        }
        catch (InterruptedException ex)
        {

        }
        return super.attack();
    }
}
