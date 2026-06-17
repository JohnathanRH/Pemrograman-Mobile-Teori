package com.example.mockup_test

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.launch
import com.example.mockup_test.ApiResponse
import com.example.mockup_test.UserProfile

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
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val tvCode = findViewById<TextView>(R.id.tvCode)
        val tvData = findViewById<TextView>(R.id.tvData)

        // Fetch data asynchronously
        lifecycleScope.launch {
            try {
                val response: ApiResponse<UserProfile> = MockApiClient.client
                    .get("https://mobile-yeah.free.beeceptor.com").body()

                tvMessage.text = "Message: ${response.message}"
                tvCode.text = "Code: ${response.code}"

                tvData.text = """
                    User Details:
                    - ID: ${response.data.id}
                    - Name: ${response.data.name}
                    - Email: ${response.data.email}
                """.trimIndent()

            } catch (e: Exception) {
                e.printStackTrace()
                tvMessage.text = "Error: ${e.localizedMessage}"
            }
        }
    }
}