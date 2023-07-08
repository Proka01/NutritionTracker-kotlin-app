package rs.raf.vezbe11.presentation.view.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Meal
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class SaveMealActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleTV : TextView
    private lateinit var datePicker: DatePicker
    private lateinit var spinner: Spinner
    private lateinit var saveMealBtn : Button
    private lateinit var meal : Meal

    val REQUEST_CODE = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_meal)

        meal = (intent.getSerializableExtra("meal") as? Meal)!!
        Toast.makeText(applicationContext, meal.strMeal, Toast.LENGTH_SHORT).show()
        init()

        if (ContextCompat.checkSelfPermission(this@SaveMealActivity,
                Manifest.permission.ACCESS_FINE_LOCATION) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@SaveMealActivity,
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this@SaveMealActivity,
                    arrayOf(Manifest.permission.CAMERA), 1)
            } else {
                ActivityCompat.requestPermissions(this@SaveMealActivity,
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
                    if ((ContextCompat.checkSelfPermission(this@SaveMealActivity,
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
    }

    private fun initUI() {
        imageView = findViewById(R.id.imageViewS)
        titleTV = findViewById(R.id.titleTextViewS)
        spinner = findViewById(R.id.spinnerS)
        datePicker = findViewById(R.id.datePickerS)
        saveMealBtn = findViewById(R.id.saveMealBtnS)

    }

    private fun initSpinner() {
        val items = listOf("Breakfast", "Lunch", "Snack", "Dinner")
        val adapter = ArrayAdapter(this.applicationContext, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun fillUI() {
        // Load image from URL using Glide
        Glide.with(this)
            .load(meal.strMealThumb)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_launcher_background) // Optional placeholder image
            //.error(R.drawable.error_image) // Optional error image
            .into(imageView)

        titleTV.text = meal.strMeal ?: "Not available"
    }

    private fun initListeners() {
        imageView.setOnClickListener {
            Toast.makeText(applicationContext, "USOO", Toast.LENGTH_SHORT).show()
            capturePhoto()
        }
    }

}