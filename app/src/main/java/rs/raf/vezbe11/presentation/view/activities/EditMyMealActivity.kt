package rs.raf.vezbe11.presentation.view.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.datasources.local.db.MealEntity
import rs.raf.vezbe11.data.models.Meal
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.MealDBState
import rs.raf.vezbe11.presentation.viewmodel.MainViewModel
import java.io.ByteArrayOutputStream
import java.util.*

class EditMyMealActivity : AppCompatActivity() {

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private lateinit var imageView: ImageView
    private lateinit var titleET : EditText
    private lateinit var datePicker: DatePicker
    private lateinit var spinner: Spinner
    private lateinit var saveMealBtn : Button
    private lateinit var deleteBtn : Button
    private lateinit var mealEntity : MealEntity

    val REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_meal)

        mealEntity = (intent.getSerializableExtra("mealEntity") as? MealEntity)!!

        init()

        if (ContextCompat.checkSelfPermission(this@EditMyMealActivity,
                Manifest.permission.ACCESS_FINE_LOCATION) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@EditMyMealActivity,
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this@EditMyMealActivity,
                    arrayOf(Manifest.permission.CAMERA), 1)
            } else {
                ActivityCompat.requestPermissions(this@EditMyMealActivity,
                    arrayOf(Manifest.permission.CAMERA), 1)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@EditMyMealActivity,
                            Manifest.permission.CAMERA) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        //capturePhoto()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    fun capturePhoto() {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            val bitmap = data.extras?.get("data") as Bitmap
            val imageString = bitmapToBase64(bitmap)
            mealEntity.thumbnailURL = imageString

            // Assuming you have the Base64 encoded image string in the variable "imageString"
            val decodedByteArray = android.util.Base64.decode(imageString, android.util.Base64.DEFAULT)
            val decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)


            // Load image from URL using Glide
            Glide.with(this)
                .load(decodedBitmap)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
                //.error(R.drawable.error_image) // Optional error image
                .into(imageView)

//            imageView.setImageBitmap(data.extras?.get("data") as Bitmap)
//            Toast.makeText(applicationContext, (data.extras?.get("data") as Bitmap).toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }


    private fun init(){
        initUI()
        initSpinner()
        fillUI()
        initListeners()
        //initObservers()
    }


    private fun initUI() {
        imageView = findViewById(R.id.imageViewEdit)
        titleET = findViewById(R.id.titleTextViewEdit)
        spinner = findViewById(R.id.spinnerEdit)
        datePicker = findViewById(R.id.datePickerEdit)
        saveMealBtn = findViewById(R.id.saveMealBtnEdit)
        deleteBtn = findViewById(R.id.deleteBtnEdit)
    }

    private fun initSpinner() {
        val items = listOf("Breakfast", "Lunch", "Snack", "Dinner")
        val adapter = ArrayAdapter(this.applicationContext, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun fillUI() {

        //init ImageView
        var imageString = mealEntity.thumbnailURL

        if (mealEntity.thumbnailURL!!.contains("https://www.themealdb")) {

            Glide.with(this)
                .load(imageString)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)

        } else {
            // Assuming you have the Base64 encoded image string in the variable "imageString"
            val decodedByteArray = android.util.Base64.decode(imageString, android.util.Base64.DEFAULT)
            val decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)

            Glide.with(this)
                .load(decodedBitmap)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }

        //Init title
        titleET.setText(mealEntity.mealName)

        //init Spinner
        val selectedIndex = when (mealEntity.mealType) {
            "Breakfast" -> 0
            "Lunch" -> 1
            "Snack" -> 2
            else -> 3
        }
        spinner.setSelection(selectedIndex)

        //init DatePicker
        val date = mealEntity.date
        val calendar = Calendar.getInstance().apply { time = date }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        datePicker.updateDate(year, month, day)
    }

    private fun initListeners() {
        imageView.setOnClickListener {
            Toast.makeText(applicationContext, "USOO", Toast.LENGTH_SHORT).show()
            capturePhoto()
        }

        saveMealBtn.setOnClickListener{

            var selectedMealType = spinner.selectedItem as String

            val year = datePicker.year
            val month = datePicker.month
            val day = datePicker.dayOfMonth
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            val selectedDate: Date = calendar.time

            //mealEntity.thumbnailURL already set when photo was taken
            mealEntity.mealName = titleET.text.toString()
            mealEntity.mealType = selectedMealType
            mealEntity.date = selectedDate

            //mainViewModel.update
            Toast.makeText(applicationContext, "Meal saved", Toast.LENGTH_SHORT).show()
        }

        deleteBtn.setOnClickListener{
            //mainViewModel.delete
            Toast.makeText(applicationContext, "delete", Toast.LENGTH_SHORT).show()
        }
    }
}