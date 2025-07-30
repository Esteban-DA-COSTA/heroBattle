package builders

import models.Armor
import models.Consumable
import models.Equipment
import models.Fighter
import models.Weapon

@HeroBuilderDsl
class EquipmentBuilder {
    private val equipmentList = mutableListOf<Equipment>()
    fun build() = equipmentList

    fun weapon(equipmentName: String, init: WeaponBuilder.() -> Unit) {
        val equipmentBuilder = WeaponBuilder(equipmentName)
        equipmentBuilder.init()
        equipmentList.add(equipmentBuilder.build())
    }

    fun armor(equipmentName: String, init: ArmorBuilder.() -> Unit) {
        val equipmentBuilder = ArmorBuilder(equipmentName)
        equipmentBuilder.init()
        equipmentList.add(equipmentBuilder.build())
    }

    fun consumable(equipmentName: String, init: ConsumableBuilder.() -> Unit) {
        val equipmentBuilder = ConsumableBuilder(equipmentName)
        equipmentBuilder.init()
        equipmentList.add(equipmentBuilder.build())
    }
}

abstract class CommonEquipmentBuilder(equipmentName: String) {
    abstract val equipment: Equipment
    fun build() = equipment
}

class WeaponBuilder(equipmentName: String) : CommonEquipmentBuilder(equipmentName) {
    override val equipment = Weapon(equipmentName, 0)
    fun damage(damageBonus: Int) {
        equipment.apply { this.damageBonus = damageBonus }
    }
}

class ArmorBuilder(equipmentName: String) : CommonEquipmentBuilder(equipmentName) {
    override val equipment = Armor(equipmentName, 0)
    fun protection(lifeBonus: Int) {
        equipment.apply { this.lifeBonus = lifeBonus }
    }
}

class ConsumableBuilder(equipmentName: String) : CommonEquipmentBuilder(equipmentName) {
    override val equipment = Consumable(equipmentName, 0) {}

    fun quantity(quantity: Int) {
        equipment.apply { this.quantity = quantity }
    }

    fun consumableAction(action: (Fighter) -> Unit) {
        equipment.apply { this.action = action }
    }
}