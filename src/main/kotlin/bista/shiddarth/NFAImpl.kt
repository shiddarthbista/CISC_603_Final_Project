package bista.shiddarth

sealed class NfaState {
    abstract fun next(input: Char): NfaState
}

//Using N0 since S0 is already used for DFA
data object N0 : NfaState() {
    override fun next(input: Char): NfaState = when (input) {
        'p' -> N1
        else -> N0
    }
}
data object N1 : NfaState() {
    override fun next(input: Char): NfaState = when (input) {
        'i' -> N2
        'p' -> N1
        else -> N0
    }
}
data object N2 : NfaState() {
    override fun next(input: Char): NfaState = when (input) {
        'z' -> N3
        'p' -> N1
        else -> N0
    }
}
data object N3 : NfaState() {
    override fun next(input: Char): NfaState = when (input) {
        'z' -> N4
        'p' -> N1
        else -> N0
    }
}
data object N4 : NfaState() {
    override fun next(input: Char): NfaState = when (input) {
        'a' -> N5
        'p' -> N1
        else -> N0
    }
}
data object N5 : NfaState() {
    override fun next(input: Char): NfaState = N5
}

class NFA {
    private val startState: NfaState = N0
    private val acceptingState: NfaState = N5

    private fun isAccepted(input: String): Boolean {
        var currentState = startState
        for (char in input) {
            currentState = currentState.next(char)
        }
        return currentState == acceptingState // Check if we are in the accepting state
    }

    fun nfaImplementation() {
        while (true) {
            println("Enter a word to check if it is accepted by the NFA:")
            val input = readlnOrNull() ?: ""

            if (input.equals("exit", ignoreCase = true)) {
                println("Exiting the program.")
                break
            }

            if (input.any { it !in "piza" }) {
                println("Error: Input string contains invalid characters. Only 'p' and 'i' are allowed.")
            } else {
                val isAccepted = isAccepted(input)
                println("$input is ${if (isAccepted) "accepted" else "not accepted"} by the NFA.")
                println()
            }
        }
    }
}

fun main() {
    val nfa = NFA()
    nfa.nfaImplementation()
}

