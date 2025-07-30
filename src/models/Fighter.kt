package models

interface Fighter {
    var lifePoints: Int
    var damage: Int
    fun attack(fighter: Fighter): Fighter
    
    fun isDead() = lifePoints <= 0
}