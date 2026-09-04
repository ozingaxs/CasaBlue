package chat.schildi.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.element.android.compound.annotations.CoreColorToken
import io.element.android.compound.tokens.generated.SemanticColors
import io.element.android.compound.tokens.generated.internal.LightColorTokens

val scl_fgPrimary = Color.White
val scl_fgSecondary = Color.White.copy(alpha = 0.7f)
val scl_fgTertiary = Color.White.copy(alpha = 0.5f)
val scl_fgHint = Color.White.copy(alpha = 0.5f)
val scl_fgDisabled = Color.White.copy(alpha = 0.5f)
val scl_bg = ScColors.colorNavyBlue
val scl_bgFloating = ScColors.colorNavyBlue
val scl_bgDarker = ScColors.colorNavyBlue
val scl_bgBlack = Color.Black
val scl_divider = Color.White.copy(alpha = 0.2f)
val scl_accent = ScColors.colorFbYellow
const val scl_icon_alpha = 0.5f

internal val sclMaterialColorScheme = lightColorScheme(
    primary = ScColors.colorFbYellow,
    onPrimary = ScColors.colorNavyBlue,
    primaryContainer = scl_bg,
    onPrimaryContainer = scl_fgPrimary,
    inversePrimary = scl_bg,

    secondary = scl_fgSecondary,
    onSecondary = ScColors.colorNavyBlue,
    secondaryContainer = scl_bgBlack,
    onSecondaryContainer = scl_fgSecondary,

    tertiary = scl_fgSecondary,
    onTertiary = scl_fgTertiary,
    tertiaryContainer = scl_bgBlack,
    onTertiaryContainer = scl_fgTertiary,

    background = scl_bg,
    onBackground = scl_fgPrimary,
    surface = scl_bg,
    onSurface = scl_fgPrimary,
    surfaceVariant = scl_bgFloating,
    onSurfaceVariant = scl_fgSecondary,
    surfaceTint = scl_bgFloating,
    inverseSurface = scd_bg,
    inverseOnSurface = scd_fgPrimary,

    error = ScColors.colorAccentRed,
    onError = scl_fgPrimary,
    errorContainer = ScColors.colorAccentRed,
    onErrorContainer = scl_fgPrimary,
    outline = scl_fgTertiary,
    outlineVariant = scl_divider, // This is the divider color, as per androidx.compose.material3.DividerTokens (propagated to androidx.compose.material3.DividerDefaults.color)
    scrim = ScColors.colorBlackAlpha_1f,
)

internal val sclExposures = ScThemeExposures(
    isScTheme = true,
    colorOnAccent = ScColors.colorWhite,
    bubbleBgIncoming = ScColors.colorWhite_ee,
    bubbleBgOutgoing = scl_accent.fakeAlpha(0.12f),
    unreadBadgeColor = ScColors.colorGray_73,
    unreadBadgeOnToolbarColor = ScColors.colorGray_73,
    appBarBg = scl_bg,
    bubbleRadius = 10.dp,
    commonLayoutRadius = 10.dp,
    timestampRadius = 6.dp,
    timestampOverlayBg = ScColors.colorWhiteAlpha_b3,
    unreadIndicatorLine = ScColors.colorAccentGreen,
    unreadIndicatorThickness = 2.dp,
    mentionFgLegacy = ScColors.colorWhite,
    mentionBgLegacy = ScColors.colorAccentRed,
    mentionBgOtherLegacy = ScColors.colorWhite_cf,
    mentionFg = ScColors.colorBlack,
    mentionBg = ScColors.colorAccentGreenAlpha_80,
    mentionFgHighlight = ScColors.colorWhite,
    mentionBgHighlight = ScColors.colorAccentRed,
    greenFg = ScColors.colorAccentGreen,
    greenBg = ScColors.colorAccentGreenAlpha_21,
    messageHighlightBg = ScColors.colorAccentGreenAlpha_80,
    timelineBg = ScColors.colorPaleYellow,
    composerBlockBg = null,
    composerBlockFg = null,
    spaceBarBg = scl_bg,
    tertiaryFgNoAlpha = ScColors.colorGray_80,
)

@OptIn(CoreColorToken::class)
internal val sclSemanticColors = SemanticColors(
    textPrimary = scl_fgPrimary,
    textSecondary = scl_fgSecondary,
    textDisabled = scl_fgDisabled,
    textActionPrimary = scl_fgPrimary,
    textActionAccent = scl_accent,
    textActionSuccess = scl_accent,
    textLinkExternal = ScColors.colorAccentBlue,
    textCriticalPrimary = ScColors.colorAccentRed,
    textSuccessPrimary = ScColors.colorAccentGreen,
    textWarningPrimary = LightColorTokens.colorOrange900,
    textInfoPrimary = ScColors.colorAccentBlueLight,
    textOnSolidPrimary = ScColors.colorNavyBlue,
    textBadgeInfo = ScColors.colorNavyBlue,
    textBadgeAccent = ScColors.colorNavyBlue,
    bgSubtlePrimary = scl_bgDarker,
    bgSubtleSecondary =  scl_bgBlack,
    bgSubtleSecondaryLevel0 =  scl_bg,
    bgSubtleTertiary = scl_bgBlack,
    bgCanvasDefault = scl_bg,
    bgCanvasDefaultLevel1 = scl_bgFloating,
    bgCanvasDisabled = scl_bgDarker,
    bgActionPrimaryRest = ScColors.colorFbYellow,
    bgActionPrimaryHovered = ScColors.colorFbYellow.copy(alpha = 0.9f),
    bgActionPrimaryPressed = ScColors.colorFbYellow.copy(alpha = 0.8f),
    bgActionPrimaryDisabled = scl_fgHint,
    bgActionSecondaryRest = scl_bg,
    bgActionSecondaryHovered = scl_bgFloating,
    bgActionSecondaryPressed = scl_bgFloating,
    bgActionTertiaryRest = scl_bg, // TODO?
    bgActionTertiaryHovered = scl_bgFloating, // TODO?
    bgActionTertiarySelected = scl_bgFloating, // TODO?
    bgCriticalPrimary = LightColorTokens.colorRed900, // TODO
    bgCriticalHovered = LightColorTokens.colorRed1000, // TODO
    bgCriticalSubtle = LightColorTokens.colorRed200, // TODO
    bgCriticalSubtleHovered = LightColorTokens.colorRed300, // TODO
    borderAccentPrimary = scl_accent,
    borderAccentSubtle = scl_accent,
    bgAccentSubtle = scl_accent.copy(alpha = 0.2f),
    bgSuccessRest = scl_accent,
    bgSuccessHovered = scl_accent,
    bgSuccessPressed = scl_accent,
    bgSuccessSubtle = ScColors.colorAccentGreen.copy(alpha=0.2f),
    bgInfoSubtle = ScColors.colorAccentBluePale,
    borderDisabled = scl_divider,
    borderFocused = LightColorTokens.colorBlue900, // TODO
    borderInteractivePrimary = scl_fgSecondary,
    borderInteractiveSecondary = scl_fgTertiary,
    borderInteractiveHovered = scl_fgPrimary,
    borderCriticalPrimary = LightColorTokens.colorRed900, // TODO
    borderCriticalHovered = LightColorTokens.colorRed1000, // TODO
    borderCriticalSubtle = LightColorTokens.colorRed500, // TODO
    borderSuccessPrimary = scl_accent,
    borderSuccessSubtle = ScColors.colorAccentGreen,
    borderInfoSubtle = ScColors.colorAccentBluePale,
    iconPrimary = scl_fgPrimary,
    iconSecondary = scl_fgSecondary,
    iconTertiary = scl_fgSecondary, // This is used as default in ListItem leading icons, i.e. in PreferencesRootView
    iconQuaternary = scl_fgTertiary,
    iconDisabled = scl_fgDisabled,
    iconPrimaryAlpha = scl_fgPrimary.copy(alpha = scl_icon_alpha),
    iconSecondaryAlpha = scl_fgSecondary.copy(alpha = scl_icon_alpha),
    iconTertiaryAlpha = scl_fgTertiary.copy(alpha = scl_icon_alpha),
    iconQuaternaryAlpha = scl_fgTertiary.copy(alpha = scl_icon_alpha),
    iconAccentPrimary = scl_accent,
    iconAccentTertiary = scl_accent,
    iconCriticalPrimary = ScColors.colorAccentRed, // TODO align with other colorRed900?
    iconSuccessPrimary = ScColors.colorAccentGreen,
    iconWarningPrimary = LightColorTokens.colorOrange900,
    iconInfoPrimary = ScColors.colorAccentBlue,
    iconOnSolidPrimary = ScColors.colorNavyBlue,
    bgAccentRest = scl_accent,
    bgAccentSelected = scl_accent,
    bgAccentHovered = scl_accent,
    bgAccentPressed = scl_accent,
    bgBadgeAccent = ScColors.colorAccentGreenAlpha_21,
    bgBadgeDefault = ScColors.colorWhite_e0,
    bgBadgeInfo = LightColorTokens.colorAlphaBlue1100, // TODO?
    bgBadgeCritical = LightColorTokens.colorRed200, // TODO?
    bgBadgePrimary = scl_bg,
    bgBadgeSecondary = scl_bgFloating,
    separatorPrimary = scl_divider,
    separatorSecondary = scl_divider,
    // TODO-start
    gradientActionStop1 = LightColorTokens.colorGreen500,
    gradientActionStop2 = LightColorTokens.colorGreen700,
    gradientActionStop3 = LightColorTokens.colorGreen900,
    gradientActionStop4 = LightColorTokens.colorGreen1100,
    gradientCriticalStop1 = LightColorTokens.colorRed200, // TODO?
    gradientCriticalStop2 = scl_bg,
    gradientInfoStop1 = LightColorTokens.colorAlphaBlue500,
    gradientInfoStop2 = LightColorTokens.colorAlphaBlue400,
    gradientSubtleStop1 = LightColorTokens.colorAlphaGreen500,
    gradientSubtleStop2 = LightColorTokens.colorAlphaGreen400,
    gradientSubtleStop3 = LightColorTokens.colorAlphaGreen300,
    gradientSubtleStop4 = LightColorTokens.colorAlphaGreen200,
    gradientSubtleStop5 = LightColorTokens.colorAlphaGreen100,
    gradientSubtleStop6 = LightColorTokens.colorTransparent,
    bgDecorative1 = LightColorTokens.colorLime300,
    bgDecorative2 = LightColorTokens.colorCyan300,
    bgDecorative3 = LightColorTokens.colorFuchsia300,
    bgDecorative4 = LightColorTokens.colorPurple300,
    bgDecorative5 = LightColorTokens.colorPink300,
    bgDecorative6 = LightColorTokens.colorOrange300,
    textDecorative1 = LightColorTokens.colorLime1100,
    textDecorative2 = LightColorTokens.colorCyan1100,
    textDecorative3 = LightColorTokens.colorFuchsia1100,
    textDecorative4 = LightColorTokens.colorPurple1100,
    textDecorative5 = LightColorTokens.colorPink1100,
    textDecorative6 = LightColorTokens.colorOrange1100,
    // TODO-end
    isLight = true,
)
