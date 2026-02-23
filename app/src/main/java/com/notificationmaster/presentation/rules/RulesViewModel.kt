package com.notificationmaster.presentation.rules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notificationmaster.domain.model.Rule
import com.notificationmaster.domain.usecase.ManageRulesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RulesViewModel @Inject constructor(
    private val manageRules: ManageRulesUseCase
) : ViewModel() {

    val rules: StateFlow<List<Rule>> = manageRules.getAllRules()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteRule(ruleId: Long) {
        viewModelScope.launch { manageRules.deleteRule(ruleId) }
    }

    fun toggleRule(ruleId: Long, enabled: Boolean) {
        viewModelScope.launch { manageRules.toggleRule(ruleId, enabled) }
    }
}
