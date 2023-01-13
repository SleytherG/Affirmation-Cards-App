package com.example.dailyaffirmationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailyaffirmationapp.data.Datasource
import com.example.dailyaffirmationapp.model.Affirmation
import com.example.dailyaffirmationapp.ui.theme.DailyAffirmationAppTheme

class MainActivity : ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContent {
   DailyAffirmationAppTheme {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
     AffirmationApp()
    }
   }
  }
 }
}

@Composable
fun AffirmationApp() {
 AffirmationList(affirmationList = Datasource().loadAffirmations())
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
 Card(
  modifier = modifier.padding(8.dp),
  elevation = 4.dp
 ) {
  Column() {
   Image(
    painter = painterResource(affirmation.imageResourceId),
    contentDescription = stringResource(affirmation.stringResourceId),
    modifier = Modifier
     .fillMaxWidth()
     .height(194.dp),
    contentScale = ContentScale.Crop
   )
   Text(
    text = stringResource(affirmation.stringResourceId),
    modifier = Modifier.padding(16.dp),
    style = MaterialTheme.typography.h6
   )
  }
 }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
 LazyColumn {
  items(affirmationList) { affirmation ->
   AffirmationCard(affirmation = affirmation)
  }
 }
}

@Preview(showSystemUi = true)
@Composable
fun AffirmationCardPreview() {
 DailyAffirmationAppTheme {
  AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
 }
}