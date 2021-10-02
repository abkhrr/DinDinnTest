package com.food_dev.utils.ext.textview

import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText

fun getStringTextFromTextInput(editText: EditText?): String {
    val textInputEditText = editText as TextInputEditText
    return textInputEditText.text.toString()
}

fun clearInputEditText(editText: EditText?){
    val textInputEditText = editText as TextInputEditText
    editText.text?.clear()
}