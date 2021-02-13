package com.robosoft.photosvideosapp.di.module

import com.robosoft.photosvideosapp.data.network.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(apiHelper = get())
    }
}
