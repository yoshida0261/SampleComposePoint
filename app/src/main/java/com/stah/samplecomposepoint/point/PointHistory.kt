package com.stah.samplecomposepoint.point

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stah.samplecomposepoint.component.PointTopBar


class PointHistory(
    val pointName: String,
    val itemName: String,
    val date: String,
    val point: Int = 0,
)


@Composable
fun PointHistory(
    topTitle:String,
    pointList: List<PointHistory> = emptyList(),
    navigateUp: () -> Unit
) {
    Scaffold(
        topBar = { PointTopBar(topTitle, navigateUp = navigateUp) },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                items(count = pointList.size) {

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            pointList[it].date,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 12.sp, color = Color.Gray
                        )
                        Row() {
                            Text(
                                pointList[it].pointName,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 4.dp, bottom = 4.dp),
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                            if (it == 0) {
                                Text("-${pointList[it].point}P", color = Color.Blue)
                            } else {
                                Text("+${pointList[it].point}P", color = Color.Red)
                            }
                        }
                        if (pointList[it].itemName.isNotEmpty()) {
                            Text(
                                pointList[it].itemName,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                            .height(1.dp)
                    )
                }
            }
        }
    )
}
