package com.example.aruuke_hw2_6m.module

import com.example.aruuke_hw2_6m.ui.fragment.cartoon.CartoonViewModel
import com.example.aruuke_hw2_6m.ui.fragment.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule : Module = module {

    viewModel {
        CartoonViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }
}