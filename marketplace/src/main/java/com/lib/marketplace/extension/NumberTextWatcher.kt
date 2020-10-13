package com.ipos.saler.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

open class NumberTextWatcher(private val editText: EditText) : TextWatcher {

    private val otherSymbols by lazy {
        DecimalFormatSymbols(Locale("vi"))
    }
    private val df: DecimalFormat by lazy { DecimalFormat("#,###.#", otherSymbols) }
    private val dfnd: DecimalFormat by lazy { DecimalFormat("#,###", otherSymbols) }
    private var hasFractionalPart: Boolean = false

    override fun afterTextChanged(s: Editable?) {
        editText.removeTextChangedListener(this)

        s?.also { str ->
            if (str.isNotEmpty()) {
                val v =
                        str.toString().replace(df.decimalFormatSymbols.groupingSeparator.toString(), "")
                val number = df.parse(v)
                if (hasFractionalPart) {
                    editText.setText(df.format(number))
                } else {
                    editText.setText(dfnd.format(number))
                }
                editText.setSelection(editText.text.length)
            }
        }

        editText.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        hasFractionalPart = s.toString().contains(df.decimalFormatSymbols.decimalSeparator)
    }
}