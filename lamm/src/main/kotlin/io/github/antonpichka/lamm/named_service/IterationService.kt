package io.github.antonpichka.lamm.named_service

class IterationService private constructor() {
    companion object {
        val instance = IterationService()
    }

    private var iteration: Int = -1

    fun newValueParameterIteration(): Int {
        iteration++
        return iteration
    }
}