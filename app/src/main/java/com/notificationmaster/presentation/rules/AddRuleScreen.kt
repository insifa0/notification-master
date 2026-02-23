package com.notificationmaster.presentation.rules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.AppBlocking
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.ui.theme.CardBorder

// ═══════════════════════════════════════════════════════
//  Create New Rule Screen
// ═══════════════════════════════════════════════════════

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddRuleScreen(
    onBack: () -> Unit,
    viewModel: AddRuleViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel.isSaved) {
        if (viewModel.isSaved) onBack()
    }

    var showAppPicker by remember { mutableStateOf(false) }

    val isFormValid = viewModel.packageName.isNotBlank() &&
            (viewModel.filterType != FilterType.KEYWORD || viewModel.keyword.isNotBlank()) &&
            (viewModel.filterType != FilterType.CONTACT_WHITELIST || viewModel.allowedContactsText.isNotBlank())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // ─── Top Bar: Cancel / Title / Save ───
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBack) {
                Text(
                    text = "Cancel",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
            Text(
                text = if (viewModel.isEditMode) "Edit Rule" else "Create New Rule",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            TextButton(
                onClick = { viewModel.saveRule() },
                enabled = isFormValid
            ) {
                Text(
                    text = "Save",
                    color = if (isFormValid) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }

        HorizontalDivider(color = CardBorder, thickness = 1.dp)

        // ─── Scrollable content ───
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // ═══ SECTION: TARGET APP ═══
            SectionLabel("TARGET APP")
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showAppPicker = true },
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
                    // App ikonu
                    val selectedApp = viewModel.installedApps.find {
                        it.packageName == viewModel.packageName
                    }
                    if (selectedApp?.icon != null) {
                        Image(
                            painter = rememberDrawablePainter(drawable = selectedApp.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Spacer(modifier = Modifier.width(14.dp))
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        if (viewModel.selectedAppName.isNotBlank()) {
                            Text(
                                text = viewModel.selectedAppName,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = viewModel.packageName,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            Text(
                                text = "Select an app...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Select",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ═══ SECTION: WHEN TO BLOCK? — Segmented Picker ═══
            SectionLabel("WHEN TO BLOCK?")
            Spacer(modifier = Modifier.height(8.dp))

            SegmentedFilterPicker(
                selected = viewModel.filterType,
                onSelect = { viewModel.filterType = it }
            )

            Spacer(modifier = Modifier.height(28.dp))

            // ═══ SECTION: CONFIGURATION ═══
            when (viewModel.filterType) {
                FilterType.KEYWORD -> KeywordConfiguration(viewModel)
                FilterType.APP_BLOCK -> AppBlockConfiguration(viewModel)
                FilterType.TIME_BASED -> TimeBasedConfiguration(viewModel)
                FilterType.WHITELIST -> WhitelistConfiguration()
                FilterType.CONTACT_WHITELIST -> ContactWhitelistConfiguration(viewModel)
            }
        }

        // ─── Bottom CTA Button ───
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Button(
                onClick = { viewModel.saveRule() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = isFormValid,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                )
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (viewModel.isEditMode) "Update Rule" else "Create Rule",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }

    // App Picker Bottom Sheet
    if (showAppPicker) {
        AppPickerBottomSheet(
            apps = viewModel.installedApps,
            isLoading = viewModel.isAppsLoading,
            onAppSelected = { app ->
                viewModel.selectApp(app)
                showAppPicker = false
            },
            onDismiss = { showAppPicker = false }
        )
    }
}

// ═══════════════════════════════════════════════════════
//  Section Label
// ═══════════════════════════════════════════════════════

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        letterSpacing = 1.5.sp
    )
}

// ═══════════════════════════════════════════════════════
//  Segmented Filter Picker (3-tab)
// ═══════════════════════════════════════════════════════

private data class SegmentItem(
    val type: FilterType,
    val label: String,
    val icon: ImageVector
)

@Composable
private fun SegmentedFilterPicker(
    selected: FilterType,
    onSelect: (FilterType) -> Unit
) {
    val segments = listOf(
        SegmentItem(FilterType.APP_BLOCK, "Block All", Icons.Default.AppBlocking),
        SegmentItem(FilterType.KEYWORD, "Keyword", Icons.Default.TextFields),
        SegmentItem(FilterType.TIME_BASED, "Schedule", Icons.Default.Schedule)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        segments.forEach { segment ->
            val isSelected = selected == segment.type
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.surfaceVariant
                        else Color.Transparent
                    )
                    .clickable { onSelect(segment.type) }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = segment.icon,
                        contentDescription = segment.label,
                        modifier = Modifier.size(20.dp),
                        tint = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = segment.label,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

// ═══════════════════════════════════════════════════════
//  Configuration Panels
// ═══════════════════════════════════════════════════════

/**
 * KEYWORD — keyword input + chips + info yazısı
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun KeywordConfiguration(viewModel: AddRuleViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SectionLabel("CONFIGURATION")
        Text(
            text = "Keywords Active",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
    Spacer(modifier = Modifier.height(8.dp))

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = Brush.linearGradient(listOf(CardBorder, CardBorder))
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Block notifications containing",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Keyword input field
            var currentInput by remember { mutableStateOf("") }
            OutlinedTextField(
                value = currentInput,
                onValueChange = { currentInput = it },
                placeholder = {
                    Text(
                        "e.g. 'sale', 'promo', 'live'",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        if (currentInput.isNotBlank()) {
                            // Virgülle ayrılmışları ekle
                            val existing = viewModel.keyword
                            val new = if (existing.isBlank()) currentInput.trim()
                            else "$existing, ${currentInput.trim()}"
                            viewModel.keyword = new
                            currentInput = ""
                        }
                    }) {
                        Icon(
                            Icons.Default.AddCircleOutline,
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CardBorder,
                    unfocusedBorderColor = CardBorder,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )

            // Keyword chips
            val keywords = viewModel.keyword.split(",").map { it.trim() }.filter { it.isNotBlank() }
            if (keywords.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    keywords.forEach { keyword ->
                        KeywordChip(
                            text = keyword,
                            onRemove = {
                                val updated = keywords.filter { it != keyword }.joinToString(", ")
                                viewModel.keyword = updated
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = CardBorder)
            Spacer(modifier = Modifier.height(12.dp))

            // 🔥 WARNING TEXT FOR MESSAGING APPS 🔥
            val messagingApps = setOf(
                "com.whatsapp", "com.whatsapp.w4b", "org.telegram.messenger",
                "org.telegram.messenger.web", "com.instagram.android", "com.twitter.android",
                "com.google.android.apps.messaging", "com.samsung.android.messaging",
                "com.android.mms", "com.android.messaging", "com.discord",
                "com.snapchat.android", "com.facebook.orca", "com.facebook.mlite",
                "com.viber.voip", "jp.naver.line.android"
            )

            if (viewModel.packageName in messagingApps) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEF5350).copy(alpha = 0.1f))
                        .border(1.dp, Color(0xFFEF5350).copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Icon(
                        Icons.Default.Info, // Use appropriate warning icon here if available, Info for now, tinting it red
                        contentDescription = "Warning",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFFEF5350)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Android hides the content of messages for privacy on this app. Keyword filters may fail. Use 'Block All' (APP_BLOCK) instead for better results.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFEF5350).copy(alpha = 0.9f),
                        lineHeight = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Info text
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Notifications matching these words will be silently blocked. You can review them later in the 'History' tab.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    lineHeight = 18.sp
                )
            }
        }
    }

    // Eylem seçimi
    Spacer(modifier = Modifier.height(20.dp))
    ActionPicker(viewModel)
}

/**
 * APP_BLOCK — sadece action seçimi
 */
@Composable
private fun AppBlockConfiguration(viewModel: AddRuleViewModel) {
    SectionLabel("CONFIGURATION")
    Spacer(modifier = Modifier.height(8.dp))

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = Brush.linearGradient(listOf(CardBorder, CardBorder))
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "All notifications from this app will be blocked. No keywords or schedule needed.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    lineHeight = 18.sp
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    ActionPicker(viewModel)
}

/**
 * TIME_BASED — zaman aralığı giriş + action
 */
@Composable
private fun TimeBasedConfiguration(viewModel: AddRuleViewModel) {
    SectionLabel("CONFIGURATION")
    Spacer(modifier = Modifier.height(8.dp))

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = Brush.linearGradient(listOf(CardBorder, CardBorder))
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Block notifications between",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.timeStart,
                    onValueChange = { viewModel.timeStart = it },
                    label = { Text("Start") },
                    placeholder = { Text("22:00") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CardBorder,
                        unfocusedBorderColor = CardBorder
                    )
                )
                OutlinedTextField(
                    value = viewModel.timeEnd,
                    onValueChange = { viewModel.timeEnd = it },
                    label = { Text("End") },
                    placeholder = { Text("07:00") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CardBorder,
                        unfocusedBorderColor = CardBorder
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = CardBorder)
            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Notifications from this app will only be blocked during this time window.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    lineHeight = 18.sp
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
    ActionPicker(viewModel)
}

/**
 * WHITELIST — açıklama
 */
@Composable
private fun WhitelistConfiguration() {
    SectionLabel("CONFIGURATION")
    Spacer(modifier = Modifier.height(8.dp))

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = Brush.linearGradient(listOf(CardBorder, CardBorder))
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Notifications from this app will always be shown, regardless of other rules.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    lineHeight = 18.sp
                )
            }
        }
    }
}

/**
 * CONTACT_WHITELIST — kişi girişi
 */
@Composable
private fun ContactWhitelistConfiguration(viewModel: AddRuleViewModel) {
    SectionLabel("CONFIGURATION")
    Spacer(modifier = Modifier.height(8.dp))

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = CardDefaults.outlinedCardBorder().copy(
            brush = Brush.linearGradient(listOf(CardBorder, CardBorder))
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Allow notifications only from",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = viewModel.allowedContactsText,
                onValueChange = { viewModel.allowedContactsText = it },
                placeholder = { Text("Mom, Dad, Ali") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                minLines = 2,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CardBorder,
                    unfocusedBorderColor = CardBorder
                )
            )

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = CardBorder)
            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Only notifications from these contacts will be allowed. All others will be blocked.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    lineHeight = 18.sp
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════════
//  Keyword Chip
// ═══════════════════════════════════════════════════════

@Composable
private fun KeywordChip(
    text: String,
    onRemove: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
            .padding(start = 12.dp, end = 6.dp, top = 6.dp, bottom = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                Icons.Default.Close,
                contentDescription = "Remove $text",
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onRemove() },
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
            )
        }
    }
}

// ═══════════════════════════════════════════════════════
//  Action Picker (eylem seçimi)
// ═══════════════════════════════════════════════════════

@Composable
private fun ActionPicker(viewModel: AddRuleViewModel) {
    SectionLabel("ACTION")
    Spacer(modifier = Modifier.height(8.dp))

    val actions = listOf(
        FilterAction.BLOCK to "Block (remove)",
        FilterAction.SILENT to "Mute (silent)",
        FilterAction.RELAY to "Relay (forward)"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, CardBorder, RoundedCornerShape(14.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        actions.forEach { (action, label) ->
            val isSelected = viewModel.action == action
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.surfaceVariant
                        else Color.Transparent
                    )
                    .clickable { viewModel.action = action }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// ═══════════════════════════════════════════════════════
//  App Picker Bottom Sheet
// ═══════════════════════════════════════════════════════

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPickerBottomSheet(
    apps: List<AppInfo>,
    isLoading: Boolean,
    onAppSelected: (AppInfo) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var searchQuery by remember { mutableStateOf("") }

    val filteredApps = remember(apps, searchQuery) {
        if (searchQuery.isBlank()) apps
        else apps.filter {
            it.appName.contains(searchQuery, ignoreCase = true) ||
                    it.packageName.contains(searchQuery, ignoreCase = true)
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        scrimColor = Color.Black.copy(alpha = 0.5f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Select App",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search apps...") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CardBorder,
                    unfocusedBorderColor = CardBorder
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Loading apps...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.height(400.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(filteredApps, key = { it.packageName }) { app ->
                        AppPickerItem(
                            app = app,
                            onClick = { onAppSelected(app) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AppPickerItem(
    app: AppInfo,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (app.icon != null) {
            Image(
                painter = rememberDrawablePainter(drawable = app.icon),
                contentDescription = app.appName,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = app.appName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = app.packageName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
