package com.example.sergeikostinapp.ui.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sergeikostinapp.R

@Composable
fun WeatherStationsScreen() {

    val vm = viewModel<WeatherStationViewModel>()

    val state = vm.screenState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Список станций", style = MaterialTheme.typography.titleMedium)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            when {
                state.value.isLoading -> {
                    item { CircularProgressIndicator(modifier = Modifier.padding(top = 96.dp)) }
                }

                state.value.error != null -> {
                    item {
                        Icon(
                            painter = painterResource(R.drawable.ic_error),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    item {
                        Text(text = state.value.error?.message.orEmpty())
                    }
                }

                else -> {
                    items(state.value.list) {
                        StationItem(it, vm::onEvent)
                    }
                }
            }
        }
    }

}

@Composable
private fun StationItem(station: Station, onClick: (Event) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp)
            .clickable { onClick.invoke(Event.OnItemClick(station.id)) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(station.img).build(),
            contentDescription = null,
            modifier = Modifier
                .size(74.dp, 96.dp)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(text = station.name, style = MaterialTheme.typography.titleMedium)
            AnimatedVisibility(visible = station.isExpanded) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = station.description, style = MaterialTheme.typography.bodyLarge)
                    Text(text = station.openDate, style = MaterialTheme.typography.bodyLarge)
                    Text(text = station.location, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }


        Icon(
            painter = painterResource(
                id = if (station.isExpanded) {
                    R.drawable.ic_chevron_top
                } else {
                    R.drawable.ic_chevron_bottom
                }
            ),
            contentDescription = null,
        )
    }
}