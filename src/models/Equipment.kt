package models

abstract class Equipment(var name: String) {
    override fun toString() = name
}

class Weapon(name: String, var damageBonus: Int, val handHolder: WeaponHand = WeaponHand.MAIN_HAND) : Equipment(name) {
    enum class WeaponHand {
        MAIN_HAND, TWO_HAND, OFF_HAND
    }
}

class Armor(name: String, var lifeBonus: Int, val armorType: ArmorType = ArmorType.CHESTPLATE) : Equipment(name) {
    enum class ArmorType {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS
    }
}

class Consumable(name: String, var quantity: Int, var action: (Fighter) -> Unit) : Equipment(name)