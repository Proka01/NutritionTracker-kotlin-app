package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.local.db.MealDataBase
import rs.raf.vezbe11.data.datasources.remote.MealAPIService
import rs.raf.vezbe11.data.repositories.MealAPIRepository
import rs.raf.vezbe11.data.repositories.MealAPIRepositoryImpl
import rs.raf.vezbe11.data.repositories.MealDBRepository
import rs.raf.vezbe11.data.repositories.MealDBRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

val mealModule = module {

    viewModel { MainViewModel(mealAPIRepository = get(), mealDBRepository = get()) }

    single<MealAPIRepository> {MealAPIRepositoryImpl(remoteDataSource = get())}
    single<MealAPIService> {create(retrofit = get())}

    single<MealDBRepository> { MealDBRepositoryImpl(get()) }
    single { get<MealDataBase>().getMealDao() }
}


