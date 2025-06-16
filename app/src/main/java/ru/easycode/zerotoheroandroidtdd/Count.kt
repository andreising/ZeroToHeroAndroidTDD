package ru.easycode.zerotoheroandroidtdd

sealed interface Count {
    fun increment(number: String): String

    class Base(private val step: Int) : Count {
        override fun increment(number: String): String {
            var count = number.toIntOrNull() ?: throw IllegalStateException("Value must be int")
            repeat(step) { count++ }
            return count.toString()
        }

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
        }
    }

}


