//Authored by: Shiddarth
sealed class PdaState

data object Q0 : PdaState()
data object Q1 : PdaState()
data object Q2 : PdaState()
data object Q3 : PdaState()
data object Q4 : PdaState()

class PDA {
    private val stack = ArrayDeque<Char>()

    fun isAccepted(input: String): Boolean {
        var state: PdaState = Q0 // Initial state
        stack.clear() // Clear stack before processing
        stack.addFirst('Z') // Initialize stack with a bottom marker

        for (symbol in input) {
            when (state) {
                Q0 -> { // Step-1: Push 0s onto stack, ignore the first 1
                    when (symbol) {
                        '0' -> {
                            stack.addFirst('0') // Push 0 onto stack
                        }

                        '1' -> {
                            state = Q1 // Move to Q1 after the first 1
                        }
                    }
                }

                Q1 -> { // Step-2: Ignore the second 1
                    if (symbol == '1') {
                        state = Q2 // Move to Q2 after the second 1
                    }
                }

                Q2 -> { // Step-3: Ignore the third 1
                    if (symbol == '1') {
                        state = Q3 // Move to Q3 after the third 1
                    }
                }

                Q3 -> { // Step-3: Pop a 0 for every 1
                    if (symbol == '1' && stack.first() == '0') {
                        stack.removeFirst() // Pop a 0 for the current 1
                        if (stack.first() == 'Z') state = Q4 // Transition to Q4 when stack is empty
                    } else if (symbol == '1' && stack.first() == 'Z') {
                        state = Q4 // Transition to Q4 when stack is empty
                    } else {
                        return false // Invalid sequence
                    }
                }

                Q4 -> { // Step-4: Ignore all remaining 1s when the stack is empty
                    if (symbol != '1') return false // Input should only contain 1s here
                }

            }
        }

        // Ensure the PDA ends in a valid state
        return (state == Q4 || state == Q3) && stack.first() == 'Z'
    }
}

fun main() {
    val pda = PDA()

    while (true) {
        println("Enter a word to check if it is accepted by the PDA:")
        val input = readlnOrNull() ?: ""

        if (input.equals("exit", ignoreCase = true)) {
            println("Exiting the program.")
            break
        }

        if (input.any { it !in "01" }) {
            println("Error: Input string contains invalid characters. Only '0' and '1' are allowed.")
        } else {
            val isAccepted = pda.isAccepted(input)
            println("$input is ${if (isAccepted) "accepted" else "not accepted"} by the PDA.")
            println()
        }
    }
}
