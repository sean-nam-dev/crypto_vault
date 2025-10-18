package com.sean.cryptovault.core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getDp(id: Int): Dp = dimensionResource(id).value.dp