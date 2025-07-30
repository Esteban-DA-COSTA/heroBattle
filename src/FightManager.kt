import models.Fighter
import models.Hero

class FightManager(val heros: MutableList<Hero>, val ennemies: MutableList<Hero>, var turn: Int = 0) {
    var allFighters: MutableList<Fighter> = mutableListOf()
    
    private fun startRound() {
        turn++
        allFighters = mutableListOf(*heros.toTypedArray(), *ennemies.toTypedArray())
        allFighters.shuffle()
    }
    
    private fun nextFighter() {
        if (allFighters.isEmpty()) {
            startRound()
            return
        }
        val activeFighter = allFighters.removeFirst()
        
        for (potentialTarget in allFighters) {
            if (isHero(activeFighter) != isHero(potentialTarget)) {
                activeFighter.attack(potentialTarget)
                if (potentialTarget.isDead()) {
                    allFighters.remove(potentialTarget)
                    heros.remove(potentialTarget)
                    ennemies.remove(potentialTarget)
                }
                break
            }
        }
    }
    
    fun launchFight() {
        startRound()
        while (!isFightOver()) {
            nextFighter()
        }
    }
    
    private fun isFightOver() = heros.isEmpty() || ennemies.isEmpty() || turn > 100
    
    private fun isHero(fighter: Fighter) = heros.contains(fighter)
}