sealed class DfaState {
    abstract fun next(input:Char) : DfaState
}

data object S0 : DfaState() {
    override fun next(input: Char): DfaState = when (input) {
        'a' -> S1
        'b' -> S4
        else -> S4
    }
}
data object S1 : DfaState() {
    override fun next(input: Char): DfaState = when (input) {
        'a' -> S0
        'b' -> S2
        else -> S4
    }
}
data object S2 : DfaState() {
    override fun next(input: Char): DfaState = when (input) {
        'a' -> S4
        'b' -> S3
        else -> S4
    }
}
data object S3 : DfaState() {
    override fun next(input: Char): DfaState = when (input) {
        'a' -> S4
        'b' -> S2
        else -> S4
    }
}
data object S4 : DfaState() {
    override fun next(input: Char): DfaState = when (input) {
        'a' -> S4
        'b' -> S4
        else -> S4
    }
}

class DFA {
    private val startState: DfaState = S0
    private val acceptingState: DfaState = S3

    fun isAccepted(input: String): Boolean {
        var currentState = startState
        for (char in input) {
            currentState = currentState.next(char)
        }
        return currentState == acceptingState // Check if we are in the accepting state

    }
}

fun main() {
    val dfa = DFA()

    while(true) {
        println("Enter a word to check if it is accepted by the DFA:")
        val input = readlnOrNull() ?: ""

        if (input.equals("exit", ignoreCase = true)) {
            println("Exiting the program.")
            break
        }

        if (input.any { it !in "ab" }) {
            println("Error: Input string contains invalid characters. Only 'a' and 'b' are allowed.")
        } else {
            val isAccepted = dfa.isAccepted(input)
            println("$input is ${if (isAccepted) "accepted" else "not accepted"} by the DFA.")
            println()
        }
    }
}

