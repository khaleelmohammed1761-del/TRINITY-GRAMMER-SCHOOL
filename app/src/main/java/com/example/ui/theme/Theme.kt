package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val TrinityLightColorScheme = lightColorScheme(
    primary = TrinityNavy,
    onPrimary = TrinitySurfaceWhite,
    primaryContainer = TrinityLightBlue,
    onPrimaryContainer = TrinityNavyDark,
    secondary = TrinityRoyalBlue,
    onSecondary = TrinitySurfaceWhite,
    secondaryContainer = TrinityChipBg,
    onSecondaryContainer = TrinityNavyDark,
    tertiary = TrinityGold,
    onTertiary = TrinitySurfaceWhite,
    tertiaryContainer = TrinityGoldLight,
    onTertiaryContainer = TrinityAmber,
    background = TrinityBackground,
    onBackground = TrinityTextPrimary,
    surface = TrinitySurfaceWhite,
    onSurface = TrinityTextPrimary,
    surfaceVariant = TrinityLightBlue,
    onSurfaceVariant = TrinityTextSecondary,
    outline = TrinityCardBorder,
    outlineVariant = TrinityChipBg,
    error = TrinityDanger,
    onError = TrinitySurfaceWhite,
    errorContainer = TrinityDangerContainer,
    onErrorContainer = TrinityDanger
)

private val TrinityDarkColorScheme = darkColorScheme(
    primary = TrinityBlueAccent,
    onPrimary = TrinitySurfaceWhite,
    primaryContainer = TrinityNavy,
    onPrimaryContainer = TrinitySurfaceWhite,
    secondary = TrinityRoyalBlue,
    onSecondary = TrinitySurfaceWhite,
    secondaryContainer = TrinityNavyDark,
    onSecondaryContainer = TrinityLightBlue,
    tertiary = TrinityGold,
    onTertiary = TrinitySurfaceWhite,
    background = TrinityNavyDark,
    onBackground = TrinitySurfaceWhite,
    surface = TrinityNavy,
    onSurface = TrinitySurfaceWhite,
    surfaceVariant = TrinityNavyDark,
    onSurfaceVariant = TrinityChipBg,
    outline = TrinityRoyalBlue,
    error = TrinityDanger,
    onError = TrinitySurfaceWhite
)

@Composable
fun TrinityGrammarSchoolTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // For school apps, the classic crisp blue and white theme is preferred
    val colorScheme = if (darkTheme) TrinityDarkColorScheme else TrinityLightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = colorScheme.primary.toArgb()
                window.navigationBarColor = colorScheme.surface.toArgb()
                val controller = WindowCompat.getInsetsController(window, view)
                controller.isAppearanceLightStatusBars = false
                controller.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TrinityTypography,
        content = content
    )
}
