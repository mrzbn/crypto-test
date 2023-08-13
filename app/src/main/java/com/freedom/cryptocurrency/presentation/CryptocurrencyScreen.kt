package com.freedom.cryptocurrency.presentation

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun CryptocurrencyScreen(
    viewModel: CryptocurrencyViewModel = viewModel(),
) {
    Content(
        pagingItems = viewModel.getCryptocurrencyPagingFlow().collectAsLazyPagingItems(),
    )
}

@Composable
private fun Content(
    pagingItems: LazyPagingItems<CryptocurrencyListItemState>,
) {
    if (pagingItems.itemCount == 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
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
            text = state.symbol,
        )

        Text(
            text = state.nameFa,
        )

        Text(
            text = state.price + " usd",
        )
    }
}