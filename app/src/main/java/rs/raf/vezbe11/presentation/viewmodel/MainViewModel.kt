package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.MealAPIRepository
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.FilteredMealState
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.view.states.MealState
import timber.log.Timber

class MainViewModel(
    private val mealAPIRepository: MealAPIRepository,
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val mealCategoryState: MutableLiveData<MealCategoryState> = MutableLiveData()
    override val mealsByFirstLetterState: MutableLiveData<MealState> = MutableLiveData()
    override val filteredMealsByCategoryState: MutableLiveData<FilteredMealState> = MutableLiveData()
    override val filteredMealsByAreaState: MutableLiveData<FilteredMealState> = MutableLiveData()
    override val filteredMealsByMainIngredientState: MutableLiveData<FilteredMealState> = MutableLiveData()
    override fun fetchAllMealsByFirstLetter(letter : String) {
        var subscription = mealAPIRepository
            .fetchAllMealsByFirstLetter(letter)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> mealsByFirstLetterState.value = MealState.Loading
                        is Resource.Success -> mealsByFirstLetterState.value = MealState.Success(it.data)
                        is Resource.Error -> mealsByFirstLetterState.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    mealsByFirstLetterState.value = MealState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllMealCategories() {
        var subscription = mealAPIRepository
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

    override fun fetchAllMealsByCategory(category: String) {
        var subscription = mealAPIRepository
            .fetchAllMealsByCategory(category)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> filteredMealsByCategoryState.value = FilteredMealState.Loading
                        is Resource.Success -> filteredMealsByCategoryState.value = FilteredMealState.Success(it.data)
                        is Resource.Error -> filteredMealsByCategoryState.value = FilteredMealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    filteredMealsByCategoryState.value = FilteredMealState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllMealsByArea(area: String) {
        var subscription = mealAPIRepository
            .fetchAllMealsByArea(area)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> filteredMealsByAreaState.value = FilteredMealState.Loading
                        is Resource.Success -> filteredMealsByAreaState.value = FilteredMealState.Success(it.data)
                        is Resource.Error -> filteredMealsByAreaState.value = FilteredMealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    filteredMealsByAreaState.value = FilteredMealState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllMealsByMainIngredient(mainIngredient: String) {
        var subscription = mealAPIRepository
            .fetchAllMealsByMainIngredient(mainIngredient)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> filteredMealsByMainIngredientState.value = FilteredMealState.Loading
                        is Resource.Success -> filteredMealsByMainIngredientState.value = FilteredMealState.Success(it.data)
                        is Resource.Error -> filteredMealsByMainIngredientState.value = FilteredMealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    filteredMealsByMainIngredientState.value = FilteredMealState.Error("Error happened while fetching data from the server")
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