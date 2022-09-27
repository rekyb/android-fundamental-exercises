package me.rekyb.edittextcustom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding

class EditTextCustom : AppCompatEditText {

    companion object {
        private const val DEFAULT_PADDING = 48f
        private const val NAME_INPUT_TYPE = 97
        private const val EMAIL_INPUT_TYPE = 33
        private const val PASSWORD_INPUT_TYPE = 129
    }

    private lateinit var showPasswordButton: Drawable
    private lateinit var hidePasswordButton: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun setDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    private fun handleInputDecor() {
        val personIcon = ContextCompat.getDrawable(context, R.drawable.ic_name) as Drawable
        val emailIcon = ContextCompat.getDrawable(context, R.drawable.ic_email) as Drawable
        val passwordIcon = ContextCompat.getDrawable(context, R.drawable.ic_password) as Drawable

        when (inputType) {
            NAME_INPUT_TYPE -> {
                hint = "Name"
                setDrawables(startOfTheText = personIcon)
            }
            EMAIL_INPUT_TYPE -> {
                hint = "Email"
                setDrawables(startOfTheText = emailIcon)
            }
            PASSWORD_INPUT_TYPE -> {
                hint = "Password"
                setDrawables(startOfTheText = passwordIcon)
            }
        }
    }

    private fun checkInputValidity(input: CharSequence?) {
        if (input.isNullOrEmpty()) {
            error = "Required field"
        } else {
            val s = input.toString()

            when (inputType) {
                EMAIL_INPUT_TYPE -> {
                    if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) error =
                        "Invalid email address"
                }
                PASSWORD_INPUT_TYPE -> {
                    if (s.length <= 6) error = "Password too short"
                }
            }
        }
    }

    private fun init() {
        background = ContextCompat.getDrawable(context, R.drawable.text_container) as Drawable

        handleInputDecor()
        setPadding(DEFAULT_PADDING.toInt())

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkInputValidity(p0)
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}
