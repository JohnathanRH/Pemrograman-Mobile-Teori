package com.example.recyclerviewexcercise

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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
            )
        )

        val adapter = Adapter(items)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.adapter = adapter
    }
}