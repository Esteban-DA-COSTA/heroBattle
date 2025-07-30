import builders.hero
import models.Job

fun main() {
    val myHero = hero {
        name("My new hero")
        age(30)
        job {
            name("Fighter")
            type(Job.JobType.BARBARIAN)
        }
        equipments {
            weapon("Sword") {
                damage(10)
            }
            armor("Shield") {
                protection(10)
            }
            consumable("Potion") {
                quantity(10)
                consumableAction {
                    this@hero.hero.lifePoints += 10
                }
            }
        }
    }
    println(myHero)
    myHero.consumables.forEach { it.action(myHero) }
}

fun tip(bill: Float, percentage: Int): Float {
    // Verify percentage
    if (percentage < 0 || percentage > 100) {
        throw IllegalArgumentException("Percentage must be between 0 and 100")
    }
    val total = bill + bill * (percentage / 100.0)
    return total.toFloat()
}

fun palindrom(word: String): Boolean {
    return word == word.reversed()
}

fun wordCounter(sentence: String) {
    println("Analyzing word : $sentence")
    val totalChar = sentence.length
    val totalWord = sentence.split(" ").size
    val totalLetter = sentence.replace(Regex("[^A-Za-z]"), "").length
    println("\tTotal characters: $totalChar")
    println("\tTotal words: $totalWord")
    println("\tTotal letters: $totalLetter")
}

class Temperature(val value: Float, val unit: Unit) {
    
    enum class Unit(val symbol: String) {
        CELSIUS("°C"), FAHRENHEIT("°F"), KELVIN("°K")
    }
    
    fun convertTo(otherUnit: Unit): Temperature {
        return when (unit) {
            Unit.CELSIUS -> when (otherUnit) {
                Unit.CELSIUS -> this
                Unit.FAHRENHEIT -> Temperature((value * 9 / 5) + 32, Unit.FAHRENHEIT)
                Unit.KELVIN -> Temperature(value + 273.15f, Unit.KELVIN)
            }
            Unit.FAHRENHEIT -> when (otherUnit) {
                Unit.CELSIUS -> Temperature((value - 32) * 5 / 9, Unit.CELSIUS)
                Unit.FAHRENHEIT -> this
                Unit.KELVIN -> Temperature((value - 32) * 5 / 9 + 273.15f, Unit.KELVIN)
            }

            Unit.KELVIN -> when (otherUnit) {
                Unit.CELSIUS -> Temperature(value - 273.15f, Unit.CELSIUS)
                Unit.FAHRENHEIT -> Temperature((value - 273.15f) * 9 / 5 + 32, Unit.FAHRENHEIT)
                Unit.KELVIN -> this
            }
        }
    }
    
    override fun toString(): String {
        return "$value${unit.symbol}"
    }
    
    infix fun to(unit: Unit) = convertTo(unit)
}

typealias TemperatureUnit = Temperature.Unit


fun Float.celsius() = Temperature(this, Temperature.Unit.CELSIUS)
fun Float.fahrenheit() = Temperature(this, Temperature.Unit.FAHRENHEIT)
fun Float.kelvin() = Temperature(this, Temperature.Unit.KELVIN)