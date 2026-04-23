package ug.ac.ndejje.cbc_teachers_toolkit

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ug.ac.ndejje.cbc_teachers_toolkit.navigation.Routes
import ug.ac.ndejje.cbc_teachers_toolkit.ui.about.AboutScreen
import ug.ac.ndejje.cbc_teachers_toolkit.ui.favorites.FavoritesScreen
import ug.ac.ndejje.cbc_teachers_toolkit.ui.home.HomeScreen
import ug.ac.ndejje.cbc_teachers_toolkit.ui.planner.PlannerScreen
import ug.ac.ndejje.cbc_teachers_toolkit.ui.resource.ResourceDetailScreen
import ug.ac.ndejje.cbc_teachers_toolkit.ui.splash.SplashScreen
import ug.ac.ndejje.cbc_teachers_toolkit.ui.subjects.SubjectsScreen
import ug.ac.ndejje.cbc_teachers_toolkit.ui.topics.TopicListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash) {
        composable(Routes.Splash) {
            SplashScreen(onFinished = {
                navController.navigate(Routes.Home) {
                    popUpTo(Routes.Splash) { inclusive = true }
                }
            })
        }
        composable(Routes.Home) {
            HomeScreen(
                onOpenSubjects = { navController.navigate(Routes.Subjects) },
                onOpenFavorites = { navController.navigate(Routes.Favorites) },
                onOpenPlanner = { navController.navigate(Routes.Planner) },
                onOpenAbout = { navController.navigate(Routes.About) },
                onOpenResource = { topicId -> navController.navigate(Routes.resource(topicId)) }
            )
        }
        composable(Routes.Subjects) {
            SubjectsScreen(onSubjectClick = { id -> navController.navigate(Routes.topics(id)) })
        }
        composable(
            Routes.Topics,
            arguments = listOf(navArgument("subjectId") { type = NavType.IntType })
        ) {
            TopicListScreen(onTopicClick = { topicId -> navController.navigate(Routes.resource(topicId)) })
        }
        composable(
            Routes.Resource,
            arguments = listOf(navArgument("topicId") { type = NavType.IntType })
        ) { ResourceDetailScreen() }
        composable(Routes.Favorites) {
            FavoritesScreen(onOpenResource = { topicId -> navController.navigate(Routes.resource(topicId)) })
        }
        composable(Routes.Planner) { PlannerScreen() }
        composable(Routes.About) { AboutScreen() }
    }
}