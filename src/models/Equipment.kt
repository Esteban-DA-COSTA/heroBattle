package models

abstract class Equipment(var name: String)

class Weapon(name: String, var damageBonus: Int) : Equipment(name)

class Armor(name: String, var lifeBonus: Int) : Equipment(name)

class Consumable(name: String, var quantity: Int, var action: (Fighter) -> Unit) : Equipment(name)