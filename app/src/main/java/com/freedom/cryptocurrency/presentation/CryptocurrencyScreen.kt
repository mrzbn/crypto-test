package com.freedom.cryptocurrency.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.freedom.cryptocurrency.connection.ConnectionStateModel
import com.freedom.cryptocurrency.connection.connectivityState

@Composable
fun CryptocurrencyScreen(
    viewModel: CryptocurrencyViewModel = viewModel(),
) {
    Content(
        pagingItems = viewModel.getCryptocurrencyPagingFlow().collectAsLazyPagingItems(),
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Content(
    pagingItems: LazyPagingItems<CryptocurrencyListItemState>,
) {
    Box(Modifier.fillMaxSize()) {
        if (pagingItems.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            val refreshing = pagingItems.loadState.refresh == LoadState.Loading
            val pullRefreshState = rememberPullRefreshState(
                refreshing = refreshing,
                onRefresh = { pagingItems.refresh() }
            )

            Box(
                Modifier.pullRefresh(pullRefreshState)
            ) {
                List(pagingItems = pagingItems)

                PullRefreshIndicator(
                    refreshing,
                    pullRefreshState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
        }

        val connection by connectivityState()
        val isConnected = connection === ConnectionStateModel.Available
        if (!isConnected) {
            Text(
                text = "no internet!",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(12.dp)
            )
        }
    }
}

@Composable
private fun List(
    pagingItems: LazyPagingItems<CryptocurrencyListItemState>,
) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(
            count = pagingItems.itemCount,
        ) { index ->
            val item = pagingItems[index]
            if (item != null) {
                CryptocurrencyItem(
                    state = item,
                )
            }
        }
    }
}

@Composable
private fun CryptocurrencyItem(
    state: CryptocurrencyListItemState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = state.nameFa + " (${state.symbol})",
        )

        Text(
            text = state.price + " usd",
        )
    }
}