package com.example.messagingapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.messagingapp.AnalyticsDestination
import com.example.messagingapp.chat
import com.example.messagingapp.service.WebSocketListeners

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AnalyticsDestination.route,
        modifier = modifier
    ) {
        composable(route = AnalyticsDestination.route) {
           
        }
        }
}