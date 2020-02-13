package com.disruption.activitytest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


/**
 * SecondActivity defines the second activity in the app. It is
 * launched from an intent with a message, and sends an intent
 * back with a second message.
 */
class SecondActivity : AppCompatActivity() {
    // EditText for the reply.
    private var mReply: EditText? = null

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // Initialize view variables.
        mReply = findViewById(R.id.editText_second)
        // Get the intent that launched this activity, and the message in
        // the intent extra.
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        // Put that message into the text_message TextView
        val textView = findViewById<TextView>(R.id.text_message)
        textView.text = message
    }

    /**
     * Handles the onClick for the "Reply" button. Gets the message from the
     * second EditText, creates an intent, and returns that message back to
     * the main activity.
     *
     * @param view The view (Button) that was clicked.
     */
    fun returnReply(view: View?) {
        // Get the reply message from the edit text.
        val reply = mReply!!.text.toString()
        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        val replyIntent = Intent()
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(Activity.RESULT_OK, replyIntent)
        finish()
    }

    companion object {
        // Unique tag for the intent reply.
        const val EXTRA_REPLY = "com.disruption.activitytest.extra.REPLY"
    }
}

