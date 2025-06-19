package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun initial(number: String): UiState
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
            if (max <= 0) throw IllegalStateException("max should be positive, but was $max")
            if (step >= max) throw IllegalStateException("max should be more than step")
            if (max <= min) throw IllegalStateException("max should be more than min")
        }

        override fun initial(number: String): UiState {
            val int = number.toInt()
            return when (int) {
                min -> UiState.Min(min.toString())
                max -> UiState.Max(max.toString())
                else -> UiState.Base(int.toString())
            }
        }

        override fun increment(number: String): UiState {
            val int = number.toInt()
            val result = int + step
            return if (result >= max) UiState.Max(max.toString())
            else UiState.Base(result.toString())
        }

        override fun decrement(number: String): UiState {
            val int = number.toInt()
            val result = int - step
            return if (result <= min) UiState.Min(min.toString())
            else UiState.Base(result.toString())
        }
    }
}