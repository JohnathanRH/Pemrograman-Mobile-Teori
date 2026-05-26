package com.example.lazylisttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazylisttask.ui.theme.LazyListTaskTheme

class MainActivity : ComponentActivity() {
    val items = listOf<ItemModel>(
        ItemModel(
            imgResourceId = R.drawable.happy,
            title = "Item Pertama",
            subTitle = "Deskripsi item kesatu"
        ),
        ItemModel(
            imgResourceId = R.drawable.frown,
            title = "Item Kedua",
            subTitle = "Deskripsi item kedua"
        ),
        ItemModel(
            imgResourceId = R.drawable.happy,
            title = "Item Ketiga",
            subTitle = "Deskripsi item ketiga"
        ),
        ItemModel(
            imgResourceId = R.drawable.frown,
            title = "Item Keempat",
            subTitle = "Deskripsi item keempat"
        ),
        ItemModel(
            imgResourceId = R.drawable.happy,
            title = "Item Ketiga",
            subTitle = "Deskripsi item kelima"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyListTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Scrollable(
                        items,
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Scrollable(items: List<ItemModel>, modifier : Modifier) {
    LazyColumn() {
        items(items.size){ i ->
            Card(items[i], i)
            Spacer(Modifier.padding(bottom = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val items = listOf<ItemModel>(
        ItemModel(
            imgResourceId = R.drawable.happy,
            title = "Item Pertama",
            subTitle = "Deskripsi item kesatu"
        ),
        ItemModel(
            imgResourceId = R.drawable.frown,
            title = "Item Kedua",
            subTitle = "Deskripsi item kedua"
        ),
        ItemModel(
            imgResourceId = R.drawable.happy,
            title = "Item Ketiga",
            subTitle = "Deskripsi item ketiga"
        ),
        ItemModel(
            imgResourceId = R.drawable.frown,
            title = "Item Keempat",
            subTitle = "Deskripsi item keempat"
        ),
        ItemModel(
            imgResourceId = R.drawable.happy,
            title = "Item Ketiga",
            subTitle = "Deskripsi item kelima"
        )
    )

    LazyListTaskTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Scrollable(
                items,
                Modifier.padding(innerPadding)
            )
        }
    }
}