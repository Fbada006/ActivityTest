package com.disruption.activitytest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


/**
 * The app contains two activities and sends messages
 * (intents) between them.
 */
class MainActivity : AppCompatActivity() {
    // EditText view for the message
    private lateinit var mMessageEditText: EditText
    // TextView for the reply header
    private lateinit var mReplyHeadTextView: TextView
    // TextView for the reply body
    private lateinit var mReplyTextView: TextView

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize all the view variables.
        mMessageEditText = findViewById(R.id.editText_main)
        mReplyHeadTextView = findViewById(R.id.text_header_reply)
        mReplyTextView = findViewById(R.id.text_message_reply)
    }

    /**
     * Handles the onClick for the "Send" button. Gets the value of the main EditText,
     * creates an intent, and launches the second activity with that intent.
     *
     *
     * The return intent from the second activity is onActivityResult().
     *
     * @param view The view (Button) that was clicked.
     */
    fun launchSecondActivity(view: View?) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        val message = mMessageEditText.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    /**
     * Handles the data in the return intent from SecondActivity.
     *
     * @param requestCode Code for the SecondActivity request.
     * @param resultCode  Code that comes back from SecondActivity.
     * @param data        Intent data sent back from SecondActivity.
     */
    public override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // Test for the right intent reply.
        if (requestCode == TEXT_REQUEST) { // Test to make sure the intent reply result was good.
            if (resultCode == Activity.RESULT_OK) {
                val reply = data!!.getStringExtra(SecondActivity.EXTRA_REPLY)
                // Make the reply head visible.
                mReplyHeadTextView.visibility = View.VISIBLE
                // Set the reply and make it visible.
                mReplyTextView.text = reply
                mReplyTextView.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        // Class name for Log tag
        private const val LOG_TAG = "MainActivity"
        // Unique tag required for the intent extra
        const val EXTRA_MESSAGE = "com.disruption.activitytest.extra.MESSAGE"
        // Unique tag for the intent reply
        const val TEXT_REQUEST = 1
    }
}

