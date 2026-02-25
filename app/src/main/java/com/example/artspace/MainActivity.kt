package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

/**
 * Creamos una clase de datos que va a contener la información de nuestros objetos "Obra"
 * Cuenta con un ID de la imagen, el nombre de la obra, el nombre del autor, el año de
 * publicación y por último un valor "color" el cual va a ser el color que tomará el fondo
 * cuando se cambie a dicha obra
 */
data class Obra (var imagenID: Int, var nObra: String, var nAutor: String, var añoPublicacion: String, var color: Long) {}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Preview
@Composable
fun ArtSpaceApp(){
    GaleriaArte(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun GaleriaArte(modifier: Modifier = Modifier){
    // Variable Global con la imagen
    var nObra by remember { mutableStateOf(0) }

    // Asignamos imagenes a los posibles valores de nObra
    val imagenObra = when (nObra){
        0 -> R.drawable.we2
        1 -> R.drawable.we3
        2 -> R.drawable.we4
        3 -> R.drawable.we5
        4 -> R.drawable.we6
        else -> R.drawable.we7
    }

    //  Variable Global con el Objeto de Obra
    var papuObra = when (nObra) {
        0 -> Obra(imagenObra, "Spongibob", "Ni idea de quien fue", "2010",0xFF20ba4b)
        1 -> Obra(imagenObra, "Bro is NOT crying", "A seal", "2011", 0xFFe01f56)
        2 -> Obra(imagenObra, "Especimen 1", "A kid", "2012", 0xFFb8b158)
        3 -> Obra(imagenObra, "Especimen 2", "A cat", "2013", 0xFF451c87)
        4 -> Obra(imagenObra, "Especimen 3", "A streamer", "2014", 0xFF2b8767)
        else -> Obra(imagenObra, "Especimen 4", "A guy", "2015", 0xFFffb8f9)
    }

    // Esto es para evitar que el valor de la variable global suba a un valor No-valido
    if (nObra >= 6){
        nObra = 0
    } else if (nObra < 0) {
        nObra = 5
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(papuObra.color)
    ) {

        Column(
            modifier = Modifier
                .padding(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
                    .padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(papuObra.imagenID),
                    contentDescription = null,
                    modifier = Modifier.shadow(4.dp)
                )
            }

            Row(horizontalArrangement = Arrangement.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = papuObra.nObra,
                        textAlign = TextAlign.Center,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${papuObra.nAutor} (${papuObra.añoPublicacion})",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.Center) {
                // Boton para regresar
                Button(onClick = { nObra-- }) {
                    Text(
                        text = stringResource(R.string.btn_regresar),
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Boton para avanzar
                Button(onClick = { nObra++ }) {
                    Text(
                        text = stringResource(R.string.btn_avanzar),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
