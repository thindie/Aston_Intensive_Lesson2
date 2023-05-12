package com.example.thindie.aston_intensive_lesson2


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.lang.String.format


class IntentActivity : AppCompatActivity() {

    private var websiteEditText: EditText? = null
    private var locationEditText: EditText? = null
    private var shareTextEditText: EditText? = null

    private var buttonWeb: Button? = null
    private var buttonLocation: Button? = null
    private var buttonChooser: Button? = null
    private var buttonTakePhoto: Button? = null


    private fun initTempUri(): Uri {
        val tempImagesDir = File(
            applicationContext.filesDir,
            getString(R.string.temp_image_catalog)
        )
        tempImagesDir.mkdir()
        val tempImage = File(
            tempImagesDir,
            getString(R.string.temp_image)
        )
        return FileProvider.getUriForFile(
            applicationContext,
            getString(R.string.authorities),
            tempImage
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        buttonTakePhoto = findViewById(R.id.button_take_picture)
        val tempImageUri = initTempUri()
        registerForTakePicture(tempImageUri)

        websiteEditText = findViewById(R.id.edittext_website)
        locationEditText = findViewById(R.id.edittext_location)
        shareTextEditText = findViewById(R.id.edittext_share)
        buttonWeb = findViewById(R.id.button_open_website)
        buttonLocation = findViewById(R.id.button_open_location)
        buttonChooser = findViewById(R.id.button_share_text)


        buttonWeb { openWebsite() }
        buttonChooser { shareText() }
        buttonLocation { openLocation() }
    }


    private fun openWebsite() {
        try {
            val url = requireNotNull(websiteEditText) { "Can't handle this view!" }.text.toString()
            val webpage = Uri.parse(url)
            startActivity(Intent(Intent.ACTION_VIEW, webpage))
        } catch (_: IllegalArgumentException) {
            return
        }
    }

    private fun openLocation() {
        try {
            val location =
                requireNotNull(locationEditText) { "Can't handle this view!" }.text.toString()
            val addressUri = Uri.parse(format(GEO_ENDPOINT, location))
            Intent(Intent.ACTION_VIEW, addressUri).apply {
                startActivity(this)
            }
        } catch (_: IllegalArgumentException) {
            return
        }

    }

    private fun shareText() {
        val txt = requireNotNull(shareTextEditText) { "Can't handle this view!" }.text.toString()
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, txt)
            setTitle(R.id.edittext_share)
            startActivity(Intent.createChooser(this, title))
        }
    }

    private fun registerForTakePicture(path: Uri) {
        val imageView = findViewById<ImageView>(R.id.imageView)
        val photoResultLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) {
                requireNotNull(imageView).setImageURI(null)
                imageView.setImageURI(path)
            }

        buttonTakePhoto {
            photoResultLauncher.launch(path)
        }

    }
}