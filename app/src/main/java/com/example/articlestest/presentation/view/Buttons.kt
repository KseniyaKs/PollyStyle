package com.example.articlestest.presentation.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.articlestest.R
import com.example.articlestest.presentation.theme.Pink

@Composable
fun ButtonMaxWidthWithText(
    onClick: () -> Unit,
    background: Color,
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        shape = RoundedCornerShape(37.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = background,
            disabledBackgroundColor = Pink.copy(alpha = 0.5f)
        ),
        enabled = enabled
    ) {
        Text(
            text = text,//stringResource(id = R.string.restore_subscription),
            fontFamily = FontFamily(Font(R.font.gilroy_semibold_600)),
            fontSize = 17.sp,
            color = textColor//Color.Black
        )
    }
}