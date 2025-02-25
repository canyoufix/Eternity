package com.canyoufix.ui.di


import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

import com.canyoufix.ui.screens.storage.StorageViewModel


var UiModule = module {

//    viewModel {
//        SettingsViewModel()
//    }
//    viewModel {
//        GenPassViewModel()
//    }
    viewModel {
        StorageViewModel()
    }

}