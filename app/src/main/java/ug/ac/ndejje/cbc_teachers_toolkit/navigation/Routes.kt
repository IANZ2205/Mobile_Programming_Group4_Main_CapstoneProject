package ug.ac.ndejje.cbc_teachers_toolkit.navigation

object Routes {
    const val Splash = "splash"
    const val Home = "home"
    const val Subjects = "subjects"
    const val Topics = "topics/{subjectId}"
    const val Resource = "resource/{topicId}"
    const val Favorites = "favorites"
    const val Planner = "planner"
    const val About = "about"

    fun topics(subjectId: Int): String = "topics/$subjectId"
    fun resource(topicId: Int): String = "resource/$topicId"
}
