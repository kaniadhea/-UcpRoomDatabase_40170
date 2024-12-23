package com.example.ucp2.ui.navigation

interface AlamatNavigasi {
    val route : String
}

object DestinasiHomeDosen : AlamatNavigasi {
    override val route = "homeDosen"
}

object DestinasiHomeMk : AlamatNavigasi {
    override val route = "homeMk"
}



object DestinasiDetailMatkul : AlamatNavigasi{
    override  val route = "detail"
    const val KODE ="kode"
    val routesWithArg = "$route/{$KODE}"
}

object  DestinasiUpdate : AlamatNavigasi{
    override val route ="update"
    const val KODE ="kode"
    val routesWithArg = "$route/{$KODE}"
}