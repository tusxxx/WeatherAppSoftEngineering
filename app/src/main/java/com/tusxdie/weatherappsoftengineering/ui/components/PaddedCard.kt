package com.tusxdie.weatherappsoftengineering.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tusxdie.weatherappsoftengineering.ui.theme.SmallPadding

@Composable
fun PaddedCard(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Card {
        Column(
            verticalArrangement = Arrangement.spacedBy(SmallPadding),
            modifier = modifier.padding(SmallPadding),
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }
    }
}