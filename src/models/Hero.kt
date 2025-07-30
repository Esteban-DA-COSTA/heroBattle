package models

data class Hero(
    val name: String,
    val age: Int,
    val job: Job,
    val equipments: MutableList<Equipment> = mutableListOf(),
) :
    Fighter {
    val equippedList: MutableList<Equipment> = mutableListOf()
    val weapons = equippedList.filterIsInstance<Weapon>()
    val armors = equippedList.filterIsInstance<Armor>()
    val consumables = equippedList.filterIsInstance<Consumable>()

    override var damage: Int = when (job.type) {
        Job.JobType.BARBARIAN -> 15
        Job.JobType.THIEF -> 5
        Job.JobType.MAGE -> 20
        Job.JobType.CLERIC -> 10
    }

    override var lifePoints: Int = when (job.type) {
        Job.JobType.BARBARIAN -> 200
        Job.JobType.THIEF -> 115
        Job.JobType.MAGE -> 90
        Job.JobType.CLERIC -> 100
    }

    override fun attack(fighter: Fighter): Fighter {
        val totalAttack = weapons.sumOf { it.damageBonus } + this.damage
        fighter.lifePoints -= totalAttack
        return fighter
    }

    fun equip(equipment: Equipment) {
        when (equipment) {
            is Weapon -> equipWeapon(equipment)
            is Armor -> equipArmor(equipment)
            is Consumable -> equipConsumable(equipment)
        }
    }
    
    private fun equipConsumable(consumable: Consumable) {
        val nbConsumable = equippedList.filterIsInstance<Consumable>().size
        if (nbConsumable == 5) {
            equippedList.remove(equippedList.filterIsInstance<Consumable>().first())
        }
        equippedList.add(consumable)
    }

    private fun equipArmor(armor: Armor) {
        val alreadyEquipped = equippedList.filterIsInstance<Armor>().find { it.armorType == armor.armorType }
        if (alreadyEquipped != null) {
            equippedList.remove(alreadyEquipped)
        }
        equippedList.add(armor)
    }

    private fun equipWeapon(weapon: Weapon) {
        when (weapon.handHolder) {
            Weapon.WeaponHand.MAIN_HAND -> {
                val toUnequip = equippedList.filterIsInstance<Weapon>().filter {
                    it.handHolder == weapon.handHolder || it.handHolder == Weapon.WeaponHand.TWO_HAND
                }
                equippedList.removeAll(toUnequip)
                equippedList.add(weapon)
            }
            Weapon.WeaponHand.OFF_HAND -> {
                val toUnequip = equippedList.filterIsInstance<Weapon>().filter {
                    it.handHolder == weapon.handHolder || it.handHolder == Weapon.WeaponHand.TWO_HAND
                }
                equippedList.removeAll(toUnequip)
                equippedList.add(weapon)
            }
            Weapon.WeaponHand.TWO_HAND -> {
                val toUnequip = equippedList.filterIsInstance<Weapon>()
                equippedList.removeAll(toUnequip)
                equippedList.add(weapon)
            }
        }
    }

}

data class Job(val name: String, val type: JobType) {
    enum class JobType {
        BARBARIAN,
        THIEF,
        MAGE,
        CLERIC
    }
}