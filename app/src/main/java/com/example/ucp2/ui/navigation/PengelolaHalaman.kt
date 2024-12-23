package com.example.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.DestinasiHome
import com.example.ucp2.ui.HomeAwalView
import com.example.ucp2.ui.view.dosenview.DestinasiInsertDsn
import com.example.ucp2.ui.view.dosenview.HomeDsnView
import com.example.ucp2.ui.view.dosenview.InserDsnView
import com.example.ucp2.ui.view.matakuliahView.DestinasiInsertMatkul
import com.example.ucp2.ui.view.matakuliahView.DetailMatkulView
import com.example.ucp2.ui.view.matakuliahView.HomeMatkulView
import com.example.ucp2.ui.view.matakuliahView.InserMatkulView
import com.example.ucp2.ui.view.matakuliahView.UpdateMatkulView


@Composable
fun PengelolaHalaman (
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(navController = navController, startDestination = DestinasiHome.route) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeAwalView(
                onDsn = {
                    navController.navigate(DestinasiHomeDosen.route)
                },
                onMk = {
                    navController.navigate(DestinasiHomeMk.route)
                },
            )
        }
        composable(
            route = DestinasiHomeDosen.route
        ){
            HomeDsnView(
                onBack = {
                    navController.popBackStack()
                },
                onAddDsn = {
                    navController.navigate(DestinasiInsertDsn.route)
                },

                modifier = modifier,
            )
        }

        composable(
            route = DestinasiInsertDsn.route
        ){
            InserDsnView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            route = DestinasiHomeMk.route
        ){
            HomeMatkulView(
                onBack = {
                    navController.popBackStack()
                },
                onAddMatkul = {
                    navController.navigate(DestinasiInsertMatkul.route)
                },
                onDetailClick = {kode -> navController.navigate("${DestinasiDetailMatkul.route}/$kode")},
                modifier = modifier,
            )
        }
        composable(
            DestinasiDetailMatkul.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.KODE) {
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(DestinasiDetailMatkul.KODE)
            kode.let { kode ->
                DetailMatkulView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.KODE){
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMatkulView(

                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier,
            )
        }

        composable(
            route = DestinasiInsertMatkul.route
        ){
            InserMatkulView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                
                modifier = modifier,
            )
        }

    }
}


//        composable(
//            route = DestinasiInsert.route
//        ){
//            (
//                onBack = {
//                    navController.popBackStack()
//                },
//                onNavigate = {
//                    navController.popBackStack()
//                },
//                modifier = modifier,
//            )
//        }
//        composable(
//            DestinasiDetail.routesWithArg,
//            arguments = listOf(
//                navArgument(DestinasiUpdate.NIM) {
//                    type = NavType.StringType
//                }
//            )
//        ){
//            val nim = it.arguments?.getString(DestinasiDetail.NIM)
//            nim.let { nim ->
//                DetailMhsView(
//                    onBack = {
//                        navController.popBackStack()
//                    },
//                    onEditClick = {
//                        navController.navigate("${DestinasiUpdate.route}/$it")
//                    },
//                    modifier = modifier,
//                    onDeleteClick = {
//                        navController.popBackStack()
//                    }
//                )
//            }
//        }

//        composable(
//            DestinasiUpdate.routesWithArg,
//            arguments = listOf(
//                navArgument(DestinasiUpdate.NIM){
//                    type = NavType.StringType
//                }
//            )
//        ) {
//            UpdateMhsView(
//                onBack = {
//                    navController.popBackStack()
//                },
//                onNavigate = {
//                    navController.popBackStack()
//                },
//                modifier = modifier,
//            )
//        }
//    }
//}