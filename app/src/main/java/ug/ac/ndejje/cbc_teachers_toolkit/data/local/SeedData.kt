package ug.ac.ndejje.cbc_teachers_toolkit.data.local

object SeedData {
    val subjects = listOf(
        SubjectEntity(1, "Biology"),
        SubjectEntity(2, "Mathematics"),
        SubjectEntity(3, "English"),
        SubjectEntity(4, "Chemistry")
    )

    val topics = listOf(
        TopicEntity(1, 1, "Classification of Living Things", "S1"),
        TopicEntity(2, 1, "Photosynthesis", "S2"),
        TopicEntity(3, 1, "Human Reproduction", "S3"),
        TopicEntity(4, 2, "Set Theory and Venn Diagrams", "S1"),
        TopicEntity(5, 2, "Linear Equations", "S2"),
        TopicEntity(6, 2, "Statistics and Probability", "S4"),
        TopicEntity(7, 3, "Grammar and Tenses", "S1"),
        TopicEntity(8, 3, "Composition Writing", "S2"),
        TopicEntity(9, 3, "Oral Communication", "S4"),
        TopicEntity(10, 4, "States of Matter", "S1"),
        TopicEntity(11, 4, "Chemical Bonding", "S2"),
        TopicEntity(12, 4, "Acids, Bases and Salts", "S3")
    )

    val resources = topics.map { topic ->
        ResourceEntity(
            id = topic.id,
            topicId = topic.id,
            lessonPlan = "Introduction, activity-based learning, group work, assessment and reflection for ${topic.title}.",
            projectIdeas = "Create a practical class project around ${topic.title} using locally available materials.",
            rubric = "Assess understanding, collaboration, communication and application of skills for ${topic.classLevel}.",
            teachingTips = "Use learner-centered tasks, peer discussions, and short formative checks throughout the lesson."
        )
    }
}
