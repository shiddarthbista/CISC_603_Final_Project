package bista.shiddarth

class Main {

}

fun main() {
    while (true) {
        println("Choose the program you want to execute.")
        println("Press 1 for DFA Implementation")
        println("Press 2 for NFA Implementation")
        println("Press 3 for PDA Implementation")
        println("Press exit for exiting the program")

        val input = readln()

        when (input) {
            "1" -> {
                println("Odd number of a's followed by even number of b's but at least 2 bs.")
                val dfa = DFA()
                dfa.dfaImplementation()
            }
            "2" -> {
                println("strings {p,i,z,a} that contain the substring pizza")
                val nfa = NFA()
                nfa.nfaImplementation()
            }
            "3" -> {
                println("L = {0^n1^m | n >= 1, m >= 1, m > n+2}")
                val pda = PDA()
                pda.pdaImplementation()
            }
            "exit" -> {
                println("Exiting the program ...")
                return
            }
            else -> {
                println("Invalid input. Please try again.")
            }
        }

    }
}


