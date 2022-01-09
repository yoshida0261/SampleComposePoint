package com.stah.samplecomposepoint.point

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.stah.samplecomposepoint.component.PointTopBar
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*


@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun PointMonthlyScreen(
    selectedCat: (Int, Int) -> Unit,
    navigateUp: () -> Unit) {
    val date = Date()
    val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
    val year = yearFormat.format(date).toInt()

    val tabs = mutableListOf<TabItem>()
    for (y in year downTo year - 3) {
        tabs.add(TabItem(y.toString(), screen = { PointMonthly(Year.of(y), selectedCat) }))
    }

    val pagerState = rememberPagerState()
    Scaffold(
        topBar = { PointTopBar("ポイントの利用履歴", navigateUp) },
        content = {
            Column {
                Tabs(tabs = tabs, pagerState = pagerState)
                TabsContent(tabs = tabs, pagerState = pagerState)
            }
        }
    )

}


@ExperimentalMaterialApi
@Composable
fun PointMonthly(year: Year, selectedCat: (Int, Int) -> Unit) {

    val list = createMonthlyData(year)
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(list.size) { i ->
            ListItem(text = { Text(list[i].label) }, modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick =  {selectedCat(year.value, i + 1)} )
            )
            Divider(color = Color.Blue)
        }
    }

}


fun createMonthlyData(year: Year) : ArrayList<MonthListData> {
    val data = ArrayList<MonthListData>()
    val cal = Calendar.getInstance(Locale.JAPANESE)

    val thisMonth = if (Year.now() == year) {
        cal.get(Calendar.MONTH) + 1
    }else{
        12
    }
    for( m in thisMonth downTo 1){
        data.add(
            MonthListData(year.value, m, "${year.value}年${m}月")
        )
        cal.add(Calendar.MONTH, -1)
    }
    return data
}

data class MonthListData(val year: Int, val month: Int, val label: String) {
    override fun toString() = label
}

