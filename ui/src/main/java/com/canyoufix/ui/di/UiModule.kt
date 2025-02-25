package com.canyoufix.ui.di


import com.canyoufix.ui.screens.generator.GeneratorViewModel
import com.canyoufix.ui.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

import com.canyoufix.ui.screens.storage.StorageViewModel


var UiModule = module {

    viewModel {
        SettingsViewModel()
    }
    viewModel {
        GeneratorViewModel()
    }
    viewModel {
        StorageViewModel()
    }

}