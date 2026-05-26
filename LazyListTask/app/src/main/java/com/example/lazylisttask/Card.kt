package com.example.lazylisttask

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Card(item: ItemModel, position: Int){
    var checked by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val backgroundColor = if (position % 2 == 0) {
        colorResource(R.color.white)
    } else {
        colorResource(R.color.greenish)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable{
                Toast.makeText(
                    context,
                    "Item telah ditekan untuk $position",
                    Toast.LENGTH_SHORT
                ).show()
            }
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Image(
                painter = painterResource(item.imgResourceId),
                contentDescription = "Product Image"
            )

            Spacer(Modifier.width(15.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.subTitle,
                    fontSize = 13.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it

                        Toast.makeText(
                            context,
                            "Switch hidup pada item $position",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Tombol telah ditekan untuk tombol $position",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text("Aksi")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test(){
    val item = ItemModel(R.drawable.happy,"Title", "subTitle")
    Card(item, 0)
}