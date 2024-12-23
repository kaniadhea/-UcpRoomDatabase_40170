package com.example.ucp2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ucp2.R
import com.example.ucp2.ui.navigation.AlamatNavigasi

object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

@Composable
fun HomeAwalView(
    onMk: () -> Unit,
    onDsn: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.umy),
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = {
                onMk() // Pastikan fungsi dipanggil
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Mata Kuliah")
        }
            Button(
                onClick = {
                    onDsn() // Pastikan fungsi dipanggil
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Dosen")
            }
    }
}


//viewmodel untuk menghubungkan antar view dengan model