package pixel_warriors.character.Attack;


import java.util.Random;
import pixel_warriors.character.IPlayerAttackForm;

public abstract class Attack {
    Random random = new Random();
    int multiplier;
    int chanceAttack;
    IPlayerAttackForm playerStatistic;
    IPlayerAttackForm opponentStatistic;

    public Attack(IPlayerAttackForm player, IPlayerAttackForm opponent) {
        this.playerStatistic = player;
        this.opponentStatistic = opponent;
    }

    public int attack() {
        if (attackChance()) {
            int attack = playerStatistic.getStatistic().getAttack();
            int randomAttack;
            switch (playerStatistic.getTypeCharacter()) {
                case Warrior:
                    attack = (attack - opponentStatistic.getStatistic().getPhysicalDefense());
                    break;
                default:
                    break;
            }
            if (attack <= 5) {
                return random.nextInt(5);
            } else {

            }
            attack = (random.nextInt(playerStatistic.getStatistic().getAttack()) + attack) * multiplier;
            return attack;
        } else {
            return 0;
        }
    }

    public boolean attackChance() {
        int chance = 0;
        int randomChance;
        switch (opponentStatistic.getTypeCharacter()) {
            case Warrior:
                chance = (playerStatistic.getStatistic().getStrength() * playerStatistic.getStatistic().getLevel()) - (opponentStatistic.getStatistic().getDefenseChance() + playerStatistic.getStatistic().getLevel());
                break;
            default:
                break;
        }
        if (chance <= 0) {
            return false;

        } else {
            randomChance = random.nextInt(chance);
            if (randomChance <= opponentStatistic.getStatistic().getDefenseChance()) {
                return false;
            } else {
                return true;
            }
        }
    }
}
