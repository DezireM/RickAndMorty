package com.example.aruuke_hw2_6m.module

import com.example.aruuke_hw2_6m.data.repositories.repository.CartoonRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule : Module = module {
    factory {
        CartoonRepository(get())
    }
}