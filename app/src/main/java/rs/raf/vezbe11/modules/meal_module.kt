package rs.raf.vezbe11.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.vezbe11.data.datasources.remote.MealCategoryService
import rs.raf.vezbe11.data.repositories.MealCategoryRepository
import rs.raf.vezbe11.data.repositories.MealCategoryRepositoryImpl
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

val mealModule = module {

    viewModel { MainViewModel(mealCategoryRepository = get()) }

    single<MealCategoryRepository> {MealCategoryRepositoryImpl(remoteDataSource = get())}
    single<MealCategoryService> {create(retrofit = get())}
}


