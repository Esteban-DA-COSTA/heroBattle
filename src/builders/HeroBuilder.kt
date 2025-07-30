package builders

import models.Hero
import models.Job

@HeroBuilderDsl
class HeroBuilder {
    var hero = Hero("", 0, Job("", Job.JobType.BARBARIAN))
        private set

    fun build() = hero
    
    fun name(name: String) {
        hero = hero.copy(name = name)
    }
    
    fun age(age: Int) {
        hero = hero.copy(age = age)
    }
    fun job(init: JobBuilder.() -> Unit) {
        val jobBuilder = JobBuilder()
        jobBuilder.init()
        hero = hero.copy(job = jobBuilder.build())
    }
    
    fun equipments(init: EquipmentBuilder.() -> Unit) {
        val equipmentBuilder = EquipmentBuilder()
        equipmentBuilder.init()
        hero = hero.copy(equipments = equipmentBuilder.build())
    }

    class JobBuilder {
        private var job = Job("", Job.JobType.BARBARIAN)
        fun build() = job

        fun name(name: String) {
            job = job.copy(name = name)
        }

        fun type(type: Job.JobType) {
            job = job.copy(type = type)
        }
    }
}

@HeroBuilderDsl
fun hero(init: (HeroBuilder.() -> Unit)): Hero {
    val builder = HeroBuilder()
    builder.init()
    return builder.build()
}


