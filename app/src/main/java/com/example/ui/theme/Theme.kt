package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val AcademicLightColorScheme = lightColorScheme(
    primary = AcademicBluePrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD6E4FF),
    onPrimaryContainer = Color(0xFF001A41),
    secondary = AcademicCyan,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFC3E7FE),
    onSecondaryContainer = Color(0xFF001E2E),
    tertiary = AcademicTeal,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFBCEBE5),
    onTertiaryContainer = Color(0xFF00201C),
    background = CleanBackground,
    onBackground = CleanTextPrimary,
    surface = CleanSurface,
    onSurface = CleanTextPrimary,
    surfaceVariant = CleanSurfaceVariant,
    onSurfaceVariant = CleanTextSecondary,
    outline = CleanOutline,
    outlineVariant = Color(0xFFE2E8F0)
)

private val AcademicDarkColorScheme = darkColorScheme(
    primary = AcademicBlueDark,
    onPrimary = Color(0xFF002F6C),
    primaryContainer = Color(0xFF004396),
    onPrimaryContainer = Color(0xFFD6E4FF),
    secondary = Color(0xFF7DD3FC),
    onSecondary = Color(0xFF003549),
    secondaryContainer = Color(0xFF004D68),
    onSecondaryContainer = Color(0xFFC3E7FE),
    tertiary = Color(0xFF80CBC4),
    onTertiary = Color(0xFF003730),
    background = DarkBackground,
    onBackground = DarkTextPrimary,
    surface = DarkSurface,
    onSurface = DarkTextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkTextSecondary,
    outline = DarkOutline,
    outlineVariant = Color(0xFF334155)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // We intentionally keep the Academic Blue brand scheme consistent
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> AcademicDarkColorScheme
        else -> AcademicLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
