package com.example.snaptospeech.presentation


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(drawerIcon: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(80.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFA97E7),
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "SNAP  TO  SPEECH",
                fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, fontSize = 26.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
       )
    }
}

