package pixel_warriors.character.Attack;

import pixel_warriors.character.IPlayerAttackForm;

public class StrongAttack extends Attack
{
    public StrongAttack(IPlayerAttackForm player, IPlayerAttackForm opponent)
    {
        super(player,opponent);
        multiplier = 3;
        chanceAttack = 1;
    }

    public int attack()
    {
        try
        {
            playerStatistic.getStatistic().setMana(playerStatistic.getStatistic().getMana() - 10);
        }
        catch (InterruptedException ex)
        {

        }
        return super.attack();
    }
}
