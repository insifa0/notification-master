package com.notificationmaster.data.local.mapper

import com.notificationmaster.data.local.db.entity.RuleEntity
import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.FilterType
import com.notificationmaster.domain.model.Rule

/**
 * RuleEntity ↔ Rule (Domain) dönüşüm fonksiyonları.
 *
 * allowedContacts → virgülle ayrılmış string olarak saklanır.
 * "Annem,Babam" ↔ listOf("Annem", "Babam")
 */
fun RuleEntity.toDomain(): Rule = Rule(
    id = id,
    packageName = packageName,
    filterType = FilterType.valueOf(filterType),
    keyword = keyword,
    action = FilterAction.valueOf(action),
    timeStart = timeStart,
    timeEnd = timeEnd,
    allowedContacts = allowedContacts
        ?.split(",")
        ?.map { it.trim() }
        ?.filter { it.isNotBlank() }
        ?: emptyList(),
    isEnabled = isEnabled,
    createdAt = createdAt
)

fun Rule.toEntity(): RuleEntity = RuleEntity(
    id = id,
    packageName = packageName,
    filterType = filterType.name,
    keyword = keyword,
    action = action.name,
    timeStart = timeStart,
    timeEnd = timeEnd,
    allowedContacts = allowedContacts
        .takeIf { it.isNotEmpty() }
        ?.joinToString(","),
    isEnabled = isEnabled,
    createdAt = createdAt
)
