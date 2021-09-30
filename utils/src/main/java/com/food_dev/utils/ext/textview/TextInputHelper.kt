package com.food_dev.utils.ext.textview

import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText

class TextInputHelper {

    fun getText(editText: EditText?): String {
        val textInputEditText = editText as TextInputEditText
        return textInputEditText.text.toString()
    }

    fun clearText(editText: EditText?){
        val textInputEditText = editText as TextInputEditText
        editText.text?.clear()
    }

}