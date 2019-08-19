package pixel_warriors.character.Attack;

import pixel_warriors.character.IPlayerAttackForm;

public class NormalAttack extends Attack
{
    public NormalAttack(IPlayerAttackForm player, IPlayerAttackForm opponent)
    {
        super(player,opponent);
        multiplier = 2;
        chanceAttack = 2;
    }

    public int attack()
    {
        try
        {
            playerStatistic.getStatistic().setMana(playerStatistic.getStatistic().getMana() - 5);
        }
        catch (InterruptedException ex)
        {

        }
        return super.attack();
    }
}
