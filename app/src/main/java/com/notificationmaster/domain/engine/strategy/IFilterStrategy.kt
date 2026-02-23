package com.notificationmaster.domain.engine.strategy

import com.notificationmaster.domain.model.FilterAction
import com.notificationmaster.domain.model.NotificationInfo
import com.notificationmaster.domain.model.Rule

/**
 * Filtre stratejisi interface'i (Strategy Pattern).
 * Her filtre türü (keyword, app, time) bu interface'i implement eder.
 */
interface IFilterStrategy {

    /**
     * Bu strateji verilen kuralı işleyebilir mi?
     * Örn: KeywordFilterStrategy sadece KEYWORD türündeki kuralları işler.
     */
    fun canHandle(rule: Rule): Boolean

    /**
     * Bildirimi verilen kurala göre değerlendir.
     *
     * @return Eşleşme varsa FilterAction, yoksa null
     */
    fun apply(notification: NotificationInfo, rule: Rule): FilterAction?
}
