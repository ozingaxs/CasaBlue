package io.element.android.libraries.designsystem.atomic.atoms

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import chat.schildi.theme.ScBrandingColors

@Composable
fun ScLogoAtom(
    size: ElementLogoAtomSize,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    Box(
        modifier = modifier.size(size.outerSize),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(size.logoSize)) {
            val navy = Color(0xff000080)
            val yellow = Color(0xfffbed21)

            // Outer Navy Circle
            drawCircle(
                color = navy,
                radius = size.logoSize.toPx() / 2,
                style = Fill
            )

            // Inner Yellow Circle
            drawCircle(
                color = yellow,
                radius = size.logoSize.toPx() * 0.45f,
                style = Fill
            )

            // Star shape inside
            val center = size.logoSize.toPx() / 2
            val outerRadius = size.logoSize.toPx() * 0.25f
            val innerRadius = size.logoSize.toPx() * 0.1f
            val path = Path().apply {
                for (i in 0 until 10) {
                    val angle = Math.toRadians(i * 36.0 - 90.0)
                    val r = if (i % 2 == 0) outerRadius else innerRadius
                    val x = center + r * Math.cos(angle)
                    val y = center + r * Math.sin(angle)
                    if (i == 0) moveTo(x.toFloat(), y.toFloat())
                    else lineTo(x.toFloat(), y.toFloat())
                }
                close()
            }
            drawPath(
                path = path,
                color = navy,
                style = Fill
            )
        }
    }
}
