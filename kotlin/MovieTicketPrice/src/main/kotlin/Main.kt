fun main(args: Array<String>) {
    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is  \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    var ticketPrice = 0

    if(age <= 12) {
        ticketPrice = 15
    } else if(age <= 60) {
        ticketPrice = if(isMonday) {
            25
        } else {
            30
        }
    } else if(age <= 100) {
        ticketPrice = 20
    } else {
        ticketPrice = -1
    }

    return ticketPrice
}