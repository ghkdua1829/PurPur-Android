package com.bibim.purpur.di

import com.bibim.purpur.ui.detail.dialog.quiz.QuizDialogViewModel
import com.bibim.purpur.ui.detail.main.DetailViewModel
import com.bibim.purpur.ui.island.IslandViewModel
import com.bibim.purpur.ui.islandSelect.IslandSelectViewModel
import com.bibim.purpur.ui.name.NameViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelAppModule = module {

    viewModel { DetailViewModel(get()) }
    viewModel { NameViewModel(get()) }
    viewModel { QuizDialogViewModel(get()) }
    viewModel { IslandViewModel(get()) }
    viewModel { IslandSelectViewModel(get()) }
}