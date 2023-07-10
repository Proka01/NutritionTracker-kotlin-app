package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.datasources.local.db.MealEntity
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.data.models.MealCategory
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.MealAPIRepository
import rs.raf.vezbe11.data.repositories.MealDBRepository
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.recycler.MealCardItem
import rs.raf.vezbe11.presentation.view.states.IngrediantState
import rs.raf.vezbe11.presentation.view.states.MealCategoryState
import rs.raf.vezbe11.presentation.view.states.MealDBState
import rs.raf.vezbe11.presentation.view.states.MealState
import timber.log.Timber

class MainViewModel(
    private val mealAPIRepository: MealAPIRepository,
    private val mealDBRepository: MealDBRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val mealCategoryState: MutableLiveData<MealCategoryState> = MutableLiveData()
    override val mealsByFirstLetterState: MutableLiveData<MealState> = MutableLiveData()
    override val filteredMealsByCategoryState: MutableLiveData<MealState> = MutableLiveData()
    override val filteredMealsByAreaState: MutableLiveData<MealState> = MutableLiveData()
    override val filteredMealsByMainIngredientState: MutableLiveData<MealState> = MutableLiveData()
    override val filteredMealsByNameState: MutableLiveData<MealState> = MutableLiveData()

    override val areaState: MutableLiveData<MealState> = MutableLiveData()
    override val ingredientState: MutableLiveData<IngrediantState> = MutableLiveData()
    override val fetchedMealById: MutableLiveData<MealState> = MutableLiveData()

    override val mealsFromDBState: MutableLiveData<MealDBState> = MutableLiveData()

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
                        is Resource.Loading -> filteredMealsByCategoryState.value = MealState.Loading
                        is Resource.Success -> filteredMealsByCategoryState.value = MealState.Success(it.data)
                        is Resource.Error -> filteredMealsByCategoryState.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    filteredMealsByCategoryState.value = MealState.Error("Error happened while fetching data from the server")
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
                        is Resource.Loading -> filteredMealsByAreaState.value = MealState.Loading
                        is Resource.Success -> filteredMealsByAreaState.value = MealState.Success(it.data)
                        is Resource.Error -> filteredMealsByAreaState.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    filteredMealsByAreaState.value = MealState.Error("Error happened while fetching data from the server")
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
                        is Resource.Loading -> filteredMealsByMainIngredientState.value = MealState.Loading
                        is Resource.Success -> filteredMealsByMainIngredientState.value = MealState.Success(it.data)
                        is Resource.Error -> filteredMealsByMainIngredientState.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    filteredMealsByMainIngredientState.value = MealState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchMealsByName(name: String) {
        var subscription = mealAPIRepository
            .fetchMealsByName(name)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> filteredMealsByNameState.value = MealState.Loading
                        is Resource.Success -> filteredMealsByNameState.value = MealState.Success(it.data)
                        is Resource.Error -> filteredMealsByNameState.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    filteredMealsByNameState.value = MealState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllAreas() {
        var subscription = mealAPIRepository
            .fetchAllAreas()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> areaState.value = MealState.Loading
                        is Resource.Success -> areaState.value = MealState.Success(it.data)
                        is Resource.Error -> areaState.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    areaState.value = MealState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllIngredients() {
        var subscription = mealAPIRepository
            .fetchAllIngredients()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> ingredientState.value = IngrediantState.Loading
                        is Resource.Success -> ingredientState.value = IngrediantState.Success(it.data)
                        is Resource.Error -> ingredientState.value = IngrediantState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    ingredientState.value = IngrediantState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchMealById(id : String) {
        var subscription = mealAPIRepository
            .fetchMealById(id)
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> fetchedMealById.value = MealState.Loading
                        is Resource.Success -> fetchedMealById.value = MealState.Success(it.data)
                        is Resource.Error -> fetchedMealById.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    filteredMealsByCategoryState.value = MealState.Error("Error happened while fetching data from the server")
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

    override fun getMealCardItemListFromMealState(mealStateList: LiveData<MealState>): List<MealCardItem> {
        val state = mealStateList.value

        if (state is MealState.Success) {
            val meals = state.meals
            val mealsList = mutableListOf<MealCardItem>()

            for (m in meals) {
                val meal = MealCardItem(
                    idMeal = m.idMeal,
                    strMeal = m.strMeal,
                    strCategory = m.strCategory,
                    strArea = m.strArea,
                    strInstructions = m.strInstructions,
                    strMealThumb = m.strMealThumb,
                    strTags = m.strTags,
                    strYoutube = m.strYoutube,
                    strIngredient1 = m.strIngredient1,
                    strIngredient2 = m.strIngredient2,
                    strIngredient3 = m.strIngredient3,
                    strIngredient4 = m.strIngredient4,
                    strIngredient5 = m.strIngredient5,
                    strIngredient6 = m.strIngredient6,
                    strIngredient7 = m.strIngredient7,
                    strIngredient8 = m.strIngredient8,
                    strIngredient9 = m.strIngredient9,
                    strIngredient10 = m.strIngredient10,
                    strIngredient11 = m.strIngredient11,
                    strIngredient12 = m.strIngredient12,
                    strIngredient13 = m.strIngredient13,
                    strIngredient14 = m.strIngredient14,
                    strIngredient15 = m.strIngredient15,
                    strIngredient16 = m.strIngredient16,
                    strIngredient17 = m.strIngredient17,
                    strIngredient18 = m.strIngredient18,
                    strIngredient19 = m.strIngredient19,
                    strIngredient20 = m.strIngredient20
                    )
                mealsList.add(meal)
            }

            return mealsList
        }

        return emptyList() // Return an empty list if the state is not success
    }

    override fun getArrayOfCategories(): Array<String?> {
        val state = mealCategoryState.value
        var array: Array<String?> = arrayOf()

        if (state is MealCategoryState.Success) {
            val mealCategories = state.mealCategories

            for (category in mealCategories) {
                array += category.strCategory
            }

            return array
        }

        return array // Return an empty list if the state is not success
    }

    override fun getArrayOfAreas(): Array<String?> {
        val state = areaState.value
        var array: Array<String?> = arrayOf()

        if (state is MealState.Success) {
            val meals = state.meals

            for (m in meals) {
                array += m.strArea
            }

            return array
        }

        return array // Return an empty list if the state is not success
    }

    override fun getArrayOfIngredients(): Array<String?> {
        val state = ingredientState.value
        var array: Array<String?> = arrayOf()

        if (state is IngrediantState.Success) {
            val ingredients = state.ingredient

            for (ingr in ingredients) {
                array += ingr.strIngredient
            }

            return array
        }

        return array // Return an empty list if the state is not success
    }

    override fun getFetchedMeal(): Meal? {
        val state = fetchedMealById.value

        if (state is MealState.Success) {
            val meals = state.meals
            val m = meals[0]

            return Meal(
                idMeal = m.idMeal,
                strMeal = m.strMeal,
                strCategory = m.strCategory,
                strArea = m.strArea,
                strInstructions = m.strInstructions,
                strMealThumb = m.strMealThumb,
                strTags = m.strTags,
                strYoutube = m.strYoutube,
                strIngredient1 = m.strIngredient1,
                strIngredient2 = m.strIngredient2,
                strIngredient3 = m.strIngredient3,
                strIngredient4 = m.strIngredient4,
                strIngredient5 = m.strIngredient5,
                strIngredient6 = m.strIngredient6,
                strIngredient7 = m.strIngredient7,
                strIngredient8 = m.strIngredient8,
                strIngredient9 = m.strIngredient9,
                strIngredient10 = m.strIngredient10,
                strIngredient11 = m.strIngredient11,
                strIngredient12 = m.strIngredient12,
                strIngredient13 = m.strIngredient13,
                strIngredient14 = m.strIngredient14,
                strIngredient15 = m.strIngredient15,
                strIngredient16 = m.strIngredient16,
                strIngredient17 = m.strIngredient17,
                strIngredient18 = m.strIngredient18,
                strIngredient19 = m.strIngredient19,
                strIngredient20 = m.strIngredient20
            )
        }

        return null // Return an empty list if the state is not success
    }

    override fun getMealCardItemListFromMealDBState(mealDBStateList: LiveData<MealDBState>): List<MealCardItem> {
        val state = mealDBStateList.value

        if (state is MealDBState.Success) {
            val meals = state.mealsDB
            val mealsList = mutableListOf<MealCardItem>()

            for (m in meals) {
                val meal = MealCardItem(
                    idMeal = m.id.toString(),
                    strMeal = m.mealName,
                    strCategory = m.mealCategory,
                    strArea = null,
                    strInstructions = m.instructions,
                    strMealThumb = m.thumbnailURL,
                    strTags = null,
                    strYoutube = m.youtubeLink,
                    strIngredient1 = null,
                    strIngredient2 = null,
                    strIngredient3 = null,
                    strIngredient4 = null,
                    strIngredient5 = null,
                    strIngredient6 = null,
                    strIngredient7 = null,
                    strIngredient8 = null,
                    strIngredient9 = null,
                    strIngredient10 = null,
                    strIngredient11 = null,
                    strIngredient12 = null,
                    strIngredient13 = null,
                    strIngredient14 = null,
                    strIngredient15 = null,
                    strIngredient16 = null,
                    strIngredient17 = null,
                    strIngredient18 = null,
                    strIngredient19 = null,
                    strIngredient20 = null
                )
                mealsList.add(meal)
            }

            return mealsList
        }

        return emptyList() // Return an empty list if the state is not success
    }

    override fun insertMeal(mealEntity: MealEntity) {
        val subscription = mealDBRepository
            .insertMeal(mealEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("COMPLETE")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertMeals(mealEntities: List<MealEntity>) {
        val subscription = mealDBRepository
            .insertMeals(mealEntities)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("INSERTED STUDENTS WITH IDs:  $it")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllMeals() {
        val subscription = mealDBRepository
            .getAllMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    println("PROCITO SVE IZ DB")
                    mealsFromDBState.value = MealDBState.Success(it)
                    println("ListaDB: $it")
                },
                {
                    mealsFromDBState.value = MealDBState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteMeal(mealEntity: MealEntity) {
        val subscription = mealDBRepository
            .delete(mealEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    println("deleted meal from DB")
                },
                {
                    println("Error deleting meal from DB")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}