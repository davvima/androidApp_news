package com.example.myapplication
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.myapplication.model.News
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ListScreen (
     navController: NavController,
     viewModel: ListScreenViewModel = hiltViewModel()
     ){
    val newsList by viewModel.getNews().observeAsState(initial = emptyList())
    ListScreen(navController, newsList )
}

@Composable
fun ListScreen(
    navController: NavController,
    news: List<News>
){
    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("Top News") },
            )
        }
    )
    {
        LazyColumn{
            items(news){ new ->
                Card (
                    shape= RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                           navController.navigate("${Destinations.DETAILS_SCREEN}/${new.title}")
                        },
                    )
                {
                    Column {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f),
                            painter = rememberImagePainter(
                                data = new.urlToImage,
                                builder = {
                                  placeholder(R.drawable.placeholder)
                                  error(R.drawable.placeholder)
                               }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                        Column(Modifier.padding(8.dp)){
                            Text(new.title, fontSize=18.sp, fontWeight = FontWeight.Bold)
                            Text(new.content?:"", maxLines = 3)
                        }
                    }



                }
            }
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun ListPreview(){
    MyApplicationTheme{
        ListScreen(
            navController = rememberNavController(),
            news = arrayListOf(
                News(
                    "Tesla to build the world's biggest CCS-compatible Supercharger locations with Magic Docks",
                    "After being snubbed by Texas for EV charger subsidies, Tesla managed to win the proposed US$6.4 million in grants from the California Energy Commission for building four Supercharger locations, three… [+1935 chars]",
                    "Daniel Zlatev",
                    "https://www.notebookcheck.net/Tesla-to-build-the-world-s-biggest-CCS-compatible-Supercharger-locations-with-Magic-Docks.649468.0.html",
                    "https://www.notebookcheck.net/fileadmin/Notebooks/News/_nc3/tesla_superchargers.jpg"
                ),
                News(
                    "Tesla’s battery supplier in China is hanging by a thread, with a ‘factory bubble’ the only way it’s still working",
                    "After being snubbed by Texas for EV charger subsidies, Tesla managed to win the proposed US$6.4 million in grants from the California Energy Commission for building four Supercharger locations, three… [+1935 chars]",
                    "Emma O'Brien, Bloomberg",
                    "https://fortune.com/2022/09/10/tesla-battery-supplier-in-china-factory-bubble-amid-covid-lockdown/",
                    "https://content.fortune.com/wp-content/uploads/2022/09/Tesla-battery-maker-COVID-lockdown-factory-bubble-GettyImages-1393761091-e1662827711417.jpg?resize=1200,600",
                )
            )
        )
    }
}