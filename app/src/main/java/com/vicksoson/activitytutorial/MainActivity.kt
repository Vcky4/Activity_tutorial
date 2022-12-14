package com.vicksoson.activitytutorial

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.vicksoson.activitytutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // View Binding
    // https://developer.android.com/topic/libraries/view-binding
    private lateinit var binding: ActivityMainBinding

    // onCreate() is the first method that is called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding
        //assign the binding variable to the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)
        //set the content view to the root of the binding object
        setContentView(binding.root)
        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // assign the result to the result text view
                    binding.result.text = result.data?.extras?.get("result").toString()
                }
            }

        // get the text from the edit text
        val text = binding.inputField.text

        // set click listener on button
        binding.button.setOnClickListener {
            // create an intent
            // Intent is an abstract description of an operation to be performed
            // https://developer.android.com/reference/android/content/Intent
            val intent =
                Intent(this, SecondActivity::class.java)
            // put the text from the edit text to the intent
            intent.putExtra("text", text)
            // start activity
            // https://developer.android.com/reference/android/content/Context#startActivity(android.content.Intent)
            startForResult.launch(intent)

        }

        //set click listener on button
        binding.externalIntent.setOnClickListener {
            // create an intent
            // Intent is an abstract description of an operation to be performed
            // https://developer.android.com/reference/android/content/Intent

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "text.toString()")
                type = "text/plain"
            }



            val viewIntent = Intent(Intent.ACTION_VIEW)
            // put the text from the edit text to the intent
            viewIntent.data = Uri.parse("https://www.google.com")
            // start activity
            // https://developer.android.com/reference/android/content/Context#startActivity(android.content.Intent)
            startActivity(viewIntent)
        }
    }


    override fun onStart() {
        super.onStart()
        // The activity is about to become visible.
    }

    override fun onResume() {
        super.onResume()
        // The activity has become visible (it is now "resumed").
    }

    override fun onPause() {
        super.onPause()
        // Another activity is taking focus (this activity is about to be "paused").
    }

    override fun onStop() {
        super.onStop()
        // The activity is no longer visible (it is now "stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        // The activity is about to be destroyed.
    }

    override fun onRestart() {
        super.onRestart()
        // The activity is about to be restarted.
    }
}