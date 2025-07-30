package models

data class Hero(
    val name: String,
    val age: Int,
    val job: Job,
    val equipments: MutableList<Equipment> = mutableListOf(),
) :
    Fighter {
    val weapons = equipments.filterIsInstance<Weapon>()
    val armors = equipments.filterIsInstance<Armor>()
    val consumables = equipments.filterIsInstance<Consumable>()
    

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
        fighter.lifePoints -= damage
        return fighter
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