enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

data class Event(
    val title: String,
    var description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

val Event.durationOfEvent: String
    get() = if(this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

fun main(args: Array<String>) {
    val events = listOf(
        Event(
            title = "Wake up",
            description = "Time to get up",
            daypart = Daypart.MORNING,
            durationInMinutes = 0
        ),
        Event(
            title = "Eat breakfast",
            daypart = Daypart.MORNING,
            durationInMinutes = 15
        ),
        Event(
            title = "Learn about Kotlin",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 30
        ),
        Event(
            title = "Practice Compose",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 60
        ),
        Event(
            title = "Watch latest DevBytes video",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 10
        ),
        Event(
            title = "Check out latest Android Jetpack library",
            daypart = Daypart.EVENING,
            durationInMinutes = 45
        ),
    )

    val eventsLessThenSixty = events.filter { it.durationInMinutes < 60 }
    val eventsGroupByDaypart = events.groupBy { it.daypart }

    println(
        Event(
            title = "Study Kotlin",
            description = "Commit to studying Kotlin at least 15 minutes per day.",
            daypart = Daypart.EVENING,
            durationInMinutes = 15
        )
    )
    println("You have ${ eventsLessThenSixty.size } short events.")

    eventsGroupByDaypart.forEach {
        println("${it.key}: ${ it.value.size } events")
    }

    println("Last event of the day: ${ events.last().title }")
    println("Duration of first event of the day: ${ events[0].durationOfEvent }")
}