package rs.raf.vezbe11.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.MealCategoryRepository
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import timber.log.Timber

class MainViewModel(
    private val mealCategoryRepository: MealCategoryRepository,
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val mealCategoryState: MutableLiveData<MealCategoryState> = MutableLiveData()

    override fun fetchAllMealCategories() {
        var subscription = mealCategoryRepository
            .fetchAllMealCategories()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> mealCategoryState.value = MealCategoryState.Loading
                        is Resource.Success -> mealCategoryState.value = MealCategoryState.Success(it.data)
                        is Resource.Error -> mealCategoryState.value = MealCategoryState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    mealCategoryState.value = MealCategoryState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun printMealCategoryState(): String {
        val state = mealCategoryState.value
        println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
        println(state.toString())

        if (state is MealCategoryState.Success) {
            val mealCategories = state.mealCategories

            val stringBuilder = StringBuilder()
            for (category in mealCategories) {
                stringBuilder.append("ID: ${category.idCategory}\n")
                stringBuilder.append("Category: ${category.strCategory}\n")
                stringBuilder.append("Thumb: ${category.strCategoryThumb}\n")
                stringBuilder.append("Description: ${category.strCategoryDescription}\n")
                stringBuilder.append("\n")
            }

            val result = stringBuilder.toString()
            return result
        }
        return "Nije successs"
    }

    override fun getMealCategoryListFromMealCategoryState(): List<MealCategory> {
        val state = mealCategoryState.value

        if (state is MealCategoryState.Success) {
            val mealCategories = state.mealCategories
            val mealCategoryList = mutableListOf<MealCategory>()

            for (category in mealCategories) {
                val mealCategory = MealCategory(
                    idCategory = category.idCategory,
                    strCategory = category.strCategory,
                    strCategoryThumb = category.strCategoryThumb,
                    strCategoryDescription = category.strCategoryDescription
                )
                mealCategoryList.add(mealCategory)
            }

            return mealCategoryList
        }

        return emptyList() // Return an empty list if the state is not success

    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}