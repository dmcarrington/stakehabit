package com.stakehabit.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Bitcoin orange brand color
val BitcoinOrange = Color(0xFFF7931A)
val BitcoinOrangeLight = Color(0xFFFFB74D)
val BitcoinOrangeDark = Color(0xFFE65100)

val StakeGreen = Color(0xFF4CAF50)
val FailRed = Color(0xFFE53935)
val SatsGold = Color(0xFFFFD700)

private val LightColorScheme = lightColorScheme(
    primary = BitcoinOrange,
    onPrimary = Color.White,
    primaryContainer = BitcoinOrangeLight,
    secondary = StakeGreen,
    tertiary = SatsGold,
    error = FailRed,
    background = Color(0xFFFAFAFA),
    surface = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = BitcoinOrangeLight,
    onPrimary = Color.Black,
    primaryContainer = BitcoinOrangeDark,
    secondary = StakeGreen,
    tertiary = SatsGold,
    error = FailRed,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E)
)

@Composable
fun StakeHabitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
