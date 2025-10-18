package com.sean.cryptovault.core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun getSp(id: Int): TextUnit = dimensionResource(id).value.sp