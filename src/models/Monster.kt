package models

class Monster( override var lifePoints: Int, override var damage: Int): Fighter {
    override fun attack(fighter: Fighter): Fighter {
        fighter.lifePoints -= damage
        return fighter
    }
}