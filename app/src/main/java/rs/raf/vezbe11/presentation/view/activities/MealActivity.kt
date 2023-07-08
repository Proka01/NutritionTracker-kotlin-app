package rs.raf.vezbe11.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.MealState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel

class MealActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()
    private lateinit var meal : Meal

    private lateinit var imageView: ImageView
    private lateinit var categoryTV : TextView
    private lateinit var areaTV : TextView
    private lateinit var titleTV : TextView
    private lateinit var instructionsTV : TextView
    private lateinit var tagsTV : TextView
    private lateinit var youTubeLinkTV : TextView
    private lateinit var ingredientsTV : TextView
    private lateinit var saveBtn : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        var id = intent.getStringExtra("id").toString()
        if (id != null) {
            println("FECOVO SAM ID")
            mainViewModel.fetchMealById(id)
        }

        init()
    }

    private fun init() {
        initUI()
        //initListeners()
        initObservers()
    }

    private fun initObservers() {
        mainViewModel.fetchedMealById.observe(this, Observer {
            when(it){
                is MealState.Success ->{
                    meal = mainViewModel.getFetchedMeal()!!

                    Glide.with(this)
                        .load(meal.strMealThumb)
                        .apply(RequestOptions.circleCropTransform())
                        .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
                        //.error(R.drawable.error_image) // Optional error image
                        .into(imageView)

                    categoryTV.text = ("Category: " + meal.strCategory) ?: "Not available"
                    areaTV.text = ("Area: " + meal.strArea) ?: "Not available"
                    titleTV.text = meal.strMeal ?: "Not available"
                    instructionsTV.text =
                        ("Instructions:\n" + meal.strInstructions) ?: "Not available"
                    tagsTV.text = ("Tags: " + meal.strTags) ?: "Not available"
                    youTubeLinkTV.text = ("Video: " + meal.strYoutube) ?: "Not available"
                    ingredientsTV.text =
                        ("Ingredients: \n" + meal.concatenateStrings(meal.getAllIngredients()))
                            ?: "Not available"
                }
                is MealState.Loading ->{
                    println("State je loading")
                }
                else -> {
                    println("CEKAJ $it")
                }
            }
        })
    }

//    private fun initListeners() {
//        TODO("Not yet implemented")
//    }

    private fun initUI() {
        imageView = findViewById(R.id.mealImageViewM)
        categoryTV = findViewById(R.id.categoryTextViewM)
        areaTV = findViewById(R.id.areaTextViewM)
        titleTV = findViewById(R.id.titleTextViewM)
        instructionsTV = findViewById(R.id.instructionsTextViewM)
        tagsTV = findViewById(R.id.tagsTextViewM)
        youTubeLinkTV = findViewById(R.id.youtubeLinkTextViewM)
        ingredientsTV = findViewById(R.id.ingredientsTextViewM)
        saveBtn = findViewById(R.id.saveButtonM)
    }
}