package com.notificationmaster.presentation.rules

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AppBlocking
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.domain.model.Rule
import com.notificationmaster.ui.theme.BlockedRed
import com.notificationmaster.ui.theme.CardBorder
import com.notificationmaster.ui.theme.RelayBlue
import com.notificationmaster.ui.theme.SilentOrange
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun RulesScreen(
    onAddRule: () -> Unit,
    onEditRule: (Long) -> Unit = {},
    viewModel: RulesViewModel = hiltViewModel()
) {
    val rules by viewModel.rules.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddRule,
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add Rule",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            // ─── Header ───
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Active Rules",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            if (rules.isEmpty()) {
                // ─── Empty State ───
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.FilterAlt,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "No rules yet",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Tap + to create your first rule",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                }
            } else {
                // ─── Rules List ───
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(rules, key = { it.id }) { rule ->
                        RevealDeleteRuleCard(
                            rule = rule,
                            onToggle = { viewModel.toggleRule(rule.id, it) },
                            onDelete = { viewModel.deleteRule(rule.id) },
                            onEdit = { onEditRule(rule.id) }
                        )
                    }
                    // Bottom spacing for FAB
                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════════
//  Reveal-to-Delete Pattern
// ═══════════════════════════════════════════════════════

/**
 * Sola kaydırınca silme butonu ortaya çıkar.
 * Silme butonuna tıklamadan silme gerçekleşmez.
 * Tekrar sağa kaydırırsa veya başka yere dokunursa geri kapanır.
 */
@Composable
private fun RevealDeleteRuleCard(
    rule: Rule,
    onToggle: (Boolean) -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }
    val revealWidthPx = with(LocalDensity.current) { 80.dp.toPx() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        // ─── Arka plan: Silme butonu ───
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(BlockedRed)
                .clickable {
                    // Silme butonuna tıklayınca sil
                    onDelete()
                }
                .padding(end = 24.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        // ─── Ön plan: Kural kartı (kaydırılabilir) ───
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            scope.launch {
                                // Eğer yeterince sola çekildiyse → reveal pozisyonuna snap
                                if (offsetX.value < -revealWidthPx * 0.4f) {
                                    offsetX.animateTo(
                                        targetValue = -revealWidthPx,
                                        animationSpec = spring(stiffness = 500f)
                                    )
                                } else {
                                    // Geri kapat
                                    offsetX.animateTo(
                                        targetValue = 0f,
                                        animationSpec = spring(stiffness = 500f)
                                    )
                                }
                            }
                        },
                        onHorizontalDrag = { _, dragAmount ->
                            scope.launch {
                                val newValue = (offsetX.value + dragAmount)
                                    .coerceIn(-revealWidthPx, 0f)
                                offsetX.snapTo(newValue)
                            }
                        }
                    )
                }
        ) {
            RuleCard(
                rule = rule,
                onToggle = onToggle,
                onEdit = onEdit
            )
        }
    }
}

// ═══════════════════════════════════════════════════════
//  Rule Card (ana kart bileşeni)
// ═══════════════════════════════════════════════════════

/**
 * Kural kartı — app ikonu + isim + açıklama + toggle switch.
 * Deaktif kurallar iç renkleri solararak gösterilir (alpha modifier yok).
 */
@Composable
private fun RuleCard(
    rule: Rule,
    onToggle: (Boolean) -> Unit,
    onEdit: () -> Unit
) {
    // Deaktifken iç elemanlar soluk renk kullanır, ancak kart arka planı tam opak kalır
    val contentAlpha = if (rule.isEnabled) 1f else 0.45f

    val textColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.onSurface.copy(alpha = contentAlpha),
        label = "textColor"
    )
    val subtextColor by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = contentAlpha),
        label = "subtextColor"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEdit() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = Brush.linearGradient(listOf(CardBorder, CardBorder))
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // App ikonu (renkli rounded square)
            AppIconBadge(rule = rule, dimmed = !rule.isEnabled)

            Spacer(modifier = Modifier.width(14.dp))

            // Kural detayları
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = rule.packageName
                        .substringAfterLast('.')
                        .replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = buildRuleDescription(rule),
                    style = MaterialTheme.typography.bodySmall,
                    color = subtextColor,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Toggle Switch
            Switch(
                checked = rule.isEnabled,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}

/**
 * Renkli app ikon badge — filtre türüne göre ikon ve renk.
 */
@Composable
private fun AppIconBadge(rule: Rule, dimmed: Boolean = false) {
    val (icon, bgColor, iconTint) = getFilterVisuals(rule)
    val alpha = if (dimmed) 0.45f else 1f

    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(bgColor.copy(alpha = bgColor.alpha * alpha)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint.copy(alpha = iconTint.alpha * alpha),
            modifier = Modifier.size(24.dp)
        )
    }
}

/**
 * Filtre türüne uygun ikon, arka plan rengi ve ikon rengi döndürür.
 */
private fun getFilterVisuals(rule: Rule): Triple<ImageVector, Color, Color> {
    return when (rule.filterType) {
        FilterType.KEYWORD -> Triple(
            Icons.Default.TextFields,
            Color(0xFF2563EB).copy(alpha = 0.15f),
            Color(0xFF2563EB)
        )
        FilterType.APP_BLOCK -> Triple(
            Icons.Default.AppBlocking,
            BlockedRed.copy(alpha = 0.15f),
            BlockedRed
        )
        FilterType.TIME_BASED -> Triple(
            Icons.Default.Schedule,
            SilentOrange.copy(alpha = 0.15f),
            SilentOrange
        )
        FilterType.WHITELIST -> Triple(
            Icons.Default.FilterAlt,
            Color(0xFF10B981).copy(alpha = 0.15f),
            Color(0xFF10B981)
        )
        FilterType.CONTACT_WHITELIST -> Triple(
            Icons.Default.People,
            RelayBlue.copy(alpha = 0.15f),
            RelayBlue
        )
    }
}

/**
 * İngilizce kural açıklaması.
 */
private fun buildRuleDescription(rule: Rule): String {
    val actionLabel = when (rule.action) {
        FilterAction.BLOCK -> "Block"
        FilterAction.SILENT -> "Mute"
        FilterAction.ALLOW -> "Allow"
        FilterAction.RELAY -> "Relay"
    }
    return when (rule.filterType) {
        FilterType.KEYWORD -> "Keyword: '${rule.keyword}'"
        FilterType.APP_BLOCK -> "Block all notifications"
        FilterType.TIME_BASED -> "Time: ${rule.timeStart} - ${rule.timeEnd} → $actionLabel"
        FilterType.WHITELIST -> "Whitelist — always show"
        FilterType.CONTACT_WHITELIST -> "Contacts: ${rule.allowedContacts.joinToString(", ")}"
    }
}
