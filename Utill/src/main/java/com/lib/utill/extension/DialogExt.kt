package com.example.utill.extension

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.text.Html
import android.text.InputType
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.google.android.material.textfield.TextInputLayout
import com.ipos.saler.MyApplication
import com.ipos.saler.R
import com.ipos.saler.app.DateTime
import com.ipos.saler.app.StatusGroupLeadStore
import com.ipos.saler.model.ChoiceRejectModel
import com.ipos.saler.structer.home_other.other_commission.AccountingsAdapter
import com.ipos.saler.structer.home_other.other_commission.ChoiceRejectAdapter
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment
import com.soyagarden.android.common.util.DateTimeUtil
import kotlinx.android.synthetic.main.activity_accountings.*
import kotlinx.android.synthetic.main.activity_review_history.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors


object DialogUtil {

    fun showAlert(
        context: Context,
        textMessage: Any,
        textTitle: Any? = null,
        editNote2: Any? = null,
        editNote: Any? = null,
        textNote: Any? = null,
        noteIsNumber: Any? = null,
        textOk: Any = context.getString(R.string.ok),
        textCancel: Any? = null,
        cancelable: Boolean = true,
        okListener: ((String?) -> Unit)? = null,
        cancelListener: (() -> Unit)? = null,
        styleAlert: Boolean? = null
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(R.color.black)
        styleAlert?.run {
            dialog.setContentView(R.layout.dialog_confirmation2)
        } ?: run {
            dialog.setContentView(R.layout.dialog_confirmation)
        }
        dialog.setCancelable(cancelable)

        val lblTitle = dialog.findViewById<TextView>(R.id.lblTitle)
        val lblMessage = dialog.findViewById<TextView>(R.id.lblMessage)
        val edNote = dialog.findViewById<EditText>(R.id.edNote)
        val edNote2 = dialog.findViewById<EditText>(R.id.edNote2)
        val inputLayoutNote = dialog.findViewById<TextInputLayout>(R.id.userIDTextInputLayout)
        val inputLayoutNote2 = dialog.findViewById<TextInputLayout>(R.id.userIDTextInputLayout2)
        val lblOk = dialog.findViewById<TextView>(R.id.lblOk)
        val lblCancel = dialog.findViewById<TextView>(R.id.lblCancel)

        textTitle?.let {
            lblTitle.visible()
            lblTitle.text = when (it) {
                is String -> it
                is CharSequence -> it
                is Int -> context.getString(it)
                else -> ""
            }
        }

        lblMessage.text = when (textMessage) {
            is String -> textMessage
            is CharSequence -> textMessage
            is Int -> context.getString(textMessage)
            else -> ""
        }

        noteIsNumber?.run {
            edNote.addTextChangedListener(NumberTextWatcher(edNote))
            edNote.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
        }
        textNote?.run {
            Timber.e("aa:" + toString())
            edNote.setText(toString())
        }
        editNote?.let {
            inputLayoutNote.visible()
            edNote.hint = it.toString()
        }
        editNote2?.let {
            inputLayoutNote2.visible()
            edNote2.hint = it.toString()
        }

        lblOk.text = when (textOk) {
            is String -> textOk
            is CharSequence -> textOk
            is Int -> context.getString(textOk)
            else -> ""
        }
        lblOk.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                okListener?.let { it1 -> it1(edNote.text.toString()) }
            }
        }

        val strCancel = when (textCancel) {
            is String -> textCancel
            is CharSequence -> textCancel
            is Int -> context.getString(textCancel)
            else -> ""
        }

        if (strCancel.isNullOrEmpty() || strCancel.isNullOrBlank()) {
            lblCancel.visibility = View.GONE
        } else {
            lblCancel.text = strCancel
            lblCancel.setOnClickListener {
                if (dialog.isShowing) {
                    dialog.dismiss()
                    cancelListener?.invoke()
                }
            }
        }

        if (!dialog.isShowing) {
            dialog.show()
        }
    }


    fun showAlertChangeService(
        context: Context,
        textMessage: Any,
        textTitle: Any? = null,
        editNote2: Any? = null,
        editNote: Any? = null,
        textNote: Any? = null,
        noteIsNumber: Any? = null,
        textOk: Any = context.getString(R.string.ok),
        textCancel: Any? = null,
        cancelable: Boolean = true,
        okListener: ((String?, String?) -> Unit)? = null,
        cancelListener: (() -> Unit)? = null
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(R.color.black)
        dialog.setContentView(R.layout.dialog_price_change_ro)
        dialog.setCancelable(cancelable)

        val lblTitle = dialog.findViewById<TextView>(R.id.lblTitle)
        val lblMessage = dialog.findViewById<TextView>(R.id.lblMessage)
        val edNote = dialog.findViewById<EditText>(R.id.edNote)
        val edNote2 = dialog.findViewById<EditText>(R.id.edNote2)
        val inputLayoutNote = dialog.findViewById<TextInputLayout>(R.id.userIDTextInputLayout)
        val inputLayoutNote2 = dialog.findViewById<TextInputLayout>(R.id.userIDTextInputLayout2)
        val lblOk = dialog.findViewById<TextView>(R.id.lblOk)
        val lblCancel = dialog.findViewById<TextView>(R.id.lblCancel)

        textTitle?.let {
            lblTitle.visible()
            lblTitle.text = when (it) {
                is String -> it
                is CharSequence -> it
                is Int -> context.getString(it)
                else -> ""
            }
        }

        lblMessage.text = when (textMessage) {
            is String -> textMessage
            is CharSequence -> textMessage
            is Int -> context.getString(textMessage)
            else -> ""
        }

        noteIsNumber?.run {
            edNote.addTextChangedListener(NumberTextWatcher(edNote))
        }
        textNote?.run {
            Timber.e("aa:" + toString())
            edNote.setText(toString())
        }
        editNote?.let {
            inputLayoutNote.visible()
            edNote.hint = it.toString()
        }
        editNote2?.let {
            inputLayoutNote2.visible()
            edNote2.hint = it.toString()
        }

        lblOk.text = when (textOk) {
            is String -> textOk
            is CharSequence -> textOk
            is Int -> context.getString(textOk)
            else -> ""
        }
        lblOk.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                okListener?.let { it1 -> it1(edNote.text.toString(), edNote2.text.toString()) }
            }
        }

        val strCancel = when (textCancel) {
            is String -> textCancel
            is CharSequence -> textCancel
            is Int -> context.getString(textCancel)
            else -> ""
        }

        if (strCancel.isNullOrEmpty() || strCancel.isNullOrBlank()) {
            lblCancel.visibility = View.GONE
        } else {
            lblCancel.text = strCancel
            lblCancel.setOnClickListener {
                if (dialog.isShowing) {
                    dialog.dismiss()
                    cancelListener?.invoke()
                }
            }
        }

        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun showAlertImage(
        context: Context,
        cancelable: Boolean = true,
        textTitle: Any? = null,
        textOk: Any = context.getString(R.string.ok),
        editNote: Any? = null,
        drawable: Any? = null,
        okListener: ((String?) -> Unit)? = null,
        cancelListener: (() -> Unit)? = null
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(R.color.black)
        dialog.setContentView(R.layout.dialog_lead_cancel)
        dialog.setCancelable(cancelable)

        val inputLayout = dialog.findViewById<TextInputLayout>(R.id.inputLayout)
        val lblTitle = dialog.findViewById<TextView>(R.id.lblTitle)
        val mNote = dialog.findViewById<EditText>(R.id.mNote)
        val lblOk = dialog.findViewById<TextView>(R.id.lblOk)
        val lblCancel = dialog.findViewById<TextView>(R.id.lblCancel)
        val mAvatar = dialog.findViewById<ImageView>(R.id.mAvatar)

        textTitle?.let {
            lblTitle.text = Html.fromHtml(it.toString())
        }

        drawable?.let {
            initIcon(mAvatar, drawable)
        }

        editNote?.let {
            inputLayout.visible()
        } ?: run {
            inputLayout.gone()
        }

        lblOk.text = when (textOk) {
            is String -> textOk
            is CharSequence -> textOk
            is Int -> context.getString(textOk)
            else -> ""
        }
        lblOk.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                okListener?.let { it1 -> it1(mNote.text.toString()) }
            }
        }

        lblCancel.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                cancelListener?.invoke()
            }
        }

        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun showAlertReject(
        context: Context,
        cancelable: Boolean = true,
        textTitle: Any? = null,
        textOk: Any = context.getString(R.string.ok),
        drawable: Any? = null,
        okListener: ((String?, String?) -> Unit)? = null,
        cancelListener: (() -> Unit)? = null
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(R.color.black)
        dialog.setContentView(R.layout.dialog_lead_reject)
        dialog.setCancelable(cancelable)

        val inputLayout = dialog.findViewById<TextInputLayout>(R.id.inputLayout)
        val lblTitle = dialog.findViewById<TextView>(R.id.lblTitle)
        val mNote = dialog.findViewById<EditText>(R.id.mNote)
        val lblOk = dialog.findViewById<TextView>(R.id.lblOk)
        val lblCancel = dialog.findViewById<TextView>(R.id.lblCancel)
        val mAvatar = dialog.findViewById<ImageView>(R.id.mAvatar)
        val mRecyclerView = dialog.findViewById<RecyclerView>(R.id.mRecyclerView)
         var mResult: ArrayList<ChoiceRejectModel> = ArrayList()
        lateinit var mAdapter: ChoiceRejectAdapter

        MyApplication.config?.run {
            getUnqualifyRejectCategory().run {
                StringExt.convertJsonToArray(value.toString()).forEachIndexed { index, s ->
                    mResult.add(ChoiceRejectModel(name = s, selected = false))
                }
            }
        }

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            mAdapter = ChoiceRejectAdapter(context, mResult) { item ->
                mResult.forEach {
                    it.selected = false
                }
                item.selected = true
                if(mResult[mResult.size.minus(1)].name == item.name){
                    inputLayout.visible()
                }else{
                    inputLayout.gone()
                }
                mAdapter.notifyDataSetChanged()
            }
            adapter = mAdapter
        }

        textTitle?.let {
            lblTitle.text = Html.fromHtml(it.toString())
        }

        drawable?.let {
            initIcon(mAvatar, drawable)
        }

        lblOk.text = when (textOk) {
            is String -> textOk
            is CharSequence -> textOk
            is Int -> context.getString(textOk)
            else -> ""
        }
        lblOk.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                okListener?.let { it1 ->
                    var rejectTitle = ""
                    mResult.forEach {
                        if(it.selected){
                            rejectTitle = it.name.toString()
                        }
                    }
                    it1(rejectTitle, mNote.text.toString())
                }
            }
        }

        lblCancel.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                cancelListener?.invoke()
            }
        }

        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun showAlertUnFollow(
        context: Context,
        cancelable: Boolean = true,
        textTitle: Any? = null,
        textOk: Any = context.getString(R.string.ok),
        drawable: Any? = null,
        okListener: ((String?, String?) -> Unit)? = null,
        cancelListener: (() -> Unit)? = null
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(R.color.black)
        dialog.setContentView(R.layout.dialog_lead_unfollow)
        dialog.setCancelable(cancelable)

        val inputLayout = dialog.findViewById<TextInputLayout>(R.id.inputLayout)
        val lblTitle = dialog.findViewById<TextView>(R.id.lblTitle)
        val mNote = dialog.findViewById<EditText>(R.id.mNote)
        val lblOk = dialog.findViewById<TextView>(R.id.lblOk)
        val lblCancel = dialog.findViewById<TextView>(R.id.lblCancel)
        val mAvatar = dialog.findViewById<ImageView>(R.id.mAvatar)
        val mResion1 = dialog.findViewById<RadioButton>(R.id.mResion1)
        val mResion2 = dialog.findViewById<RadioButton>(R.id.mResion2)
        val mResion3 = dialog.findViewById<RadioButton>(R.id.mResion3)
        val mResion4 = dialog.findViewById<RadioButton>(R.id.mResion4)
        val mResion5 = dialog.findViewById<RadioButton>(R.id.mResion5)
        val mResion6 = dialog.findViewById<RadioButton>(R.id.mResion6)
        val radioGroup = dialog.findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = dialog.findViewById(checkedId)
            if (radio == mResion6) {
                inputLayout.visible()
            } else {
                inputLayout.gone()
            }
        }

        textTitle?.let {
            lblTitle.text = Html.fromHtml(it.toString())
        }

        drawable?.let {
            initIcon(mAvatar, drawable)
        }

        lblOk.text = when (textOk) {
            is String -> textOk
            is CharSequence -> textOk
            is Int -> context.getString(textOk)
            else -> ""
        }
        lblOk.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                okListener?.let { it1 ->
                    var category = if (mResion1.isChecked) {
                        mResion1.text.toString()
                    } else if (mResion2.isChecked) {
                        mResion2.text.toString()
                    } else if (mResion3.isChecked) {
                        mResion3.text.toString()
                    } else if (mResion4.isChecked) {
                        mResion4.text.toString()
                    } else if (mResion5.isChecked) {
                        mResion5.text.toString()
                    } else {
                        mResion6.text.toString()
                    }
                    it1(category, mNote.text.toString())
                }
            }
        }

        lblCancel.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                cancelListener?.invoke()
            }
        }

        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun showAlertLeadMove(
        context: Context,
        cancelable: Boolean = true,
        okListener: ((String?) -> Unit)? = null
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(R.color.black)
        dialog.setContentView(R.layout.dialog_lead_move)
        dialog.setCancelable(cancelable)

        val mResion1 = dialog.findViewById<RadioButton>(R.id.mResion1)
        val mResion2 = dialog.findViewById<RadioButton>(R.id.mResion2)
        val lblOk = dialog.findViewById<TextView>(R.id.lblOk)

        mResion1.isChecked = true
        lblOk.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
                okListener?.let { it1 ->
                    var content = if (mResion1.isChecked) {
                        StatusGroupLeadStore.OTHER_AREA
                    } else {
                        StatusGroupLeadStore.LEADER
                    }
                    it1(content)
                }
            }
        }

        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun showBiometric(activity: FragmentActivity, okListener: (() -> Unit)? = null) {
        val executor = Executors.newSingleThreadExecutor()
        val biometricPrompt =
            BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        // user clicked negative button
                    } else {
                        // ("Called when an unrecoverable error has been encountered and the operation is complete.")
                    }
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    okListener?.invoke()
                }

            })

//                .setSubtitle("Set the subtitle to display.")
//                .setDescription("Set the description to display")
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(activity.getString(R.string.biometric_title))
            .setNegativeButtonText(activity.getString(R.string.cancel))
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    fun pickDateRange(context: Activity, itemClickListener: (String, String) -> Unit) {
        val smoothDateRangePickerFragment =
            SmoothDateRangePickerFragment.newInstance { view, yearStart, monthStart, dayStart, yearEnd, monthEnd, dayEnd ->
                itemClickListener(
                    "" + dayStart + "/" + monthStart.plus(1) + "/" + yearStart,
                    "" + dayEnd + "/" + monthEnd.plus(1) + "/" + yearEnd
                )
            }
        smoothDateRangePickerFragment.isThemeDark = true
        smoothDateRangePickerFragment.show(context.fragmentManager, "smoothDateRangePicker")
    }

    fun pickDate(
        context: Activity,
        past: Any? = null,
        maxDate: Any? = null,
        itemClickListener: (String) -> Unit
    ) {
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        past?.run {
            year = c.get(Calendar.YEAR).minus(toString().toInt())
        } ?: run {
        }
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                itemClickListener("" + dayOfMonth + "/" + monthOfYear.plus(1) + "/" + year)
            }, year, month, day
        )
        maxDate?.run {
            dpd.datePicker.maxDate = Date().time
        }
        dpd.show()
    }

    fun pickDateAndTime(context: AppCompatActivity, itemClickListener: (String) -> Unit) {
        SingleDateAndTimePickerDialog.Builder(context)
            .bottomSheet()
            .curved()
//            .displayMonth(true)
//            .displayYears(true)
//            .displayDaysOfMonth(true)
            //.stepSizeMinutes(15)
//            .displayHours(false)
//            .displayMinutes(false)
//            .mainColor(ContextCompat.getColor(context, R.color.mainColor))
            .mainColor(Color.BLUE)
            .titleTextColor(Color.BLUE)
            .displayListener {
                //retrieve the SingleDateAndTimePicker
            }
            .title("Chọn ngày, thời gian")
            .listener {
                val format = SimpleDateFormat(DateTime.Format.DD_MM_YYYY_HH_MM_SS).format(it?.time)
                itemClickListener(format)
            }.display()
    }

    fun pickDateTime(itemClickListener: (String) -> Unit) {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(
            MyApplication.instance,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                TimePickerDialog(
                    MyApplication.instance,
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(year, month, day, hour, minute)
                        Timber.e("aaaa: " + pickedDateTime.toString())

                    },
                    startHour,
                    startMinute,
                    false
                ).show()
            },
            startYear,
            startMonth,
            startDay
        ).show()
    }

    fun pickDateCustome(
        activity: AppCompatActivity? = null,
        view: View? = null,
        callBack: (String, String, String) -> Unit
    ) {
        val popupMenu = PopupMenu(activity, view, Gravity.RIGHT)

        popupMenu.menu.add(0, 0, 1, MyApplication.instance.getString(R.string.to_day))
        popupMenu.menu.add(0, 1, 2, MyApplication.instance.getString(R.string.yesterday))
        popupMenu.menu.add(0, 2, 3, MyApplication.instance.getString(R.string.day_7))
        popupMenu.menu.add(0, 3, 4, MyApplication.instance.getString(R.string.day_30))
        popupMenu.menu.add(0, 4, 5, MyApplication.instance.getString(R.string.thismonth))
        popupMenu.menu.add(0, 5, 6, MyApplication.instance.getString(R.string.prev_month))
        popupMenu.menu.add(0, 6, 7, MyApplication.instance.getString(R.string.perview_3_month))
        popupMenu.menu.add(0, 7, 8, MyApplication.instance.getString(R.string.tuychinh))

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            val dateStart = Calendar.getInstance()
            dateStart[Calendar.SECOND] = 0
            dateStart[Calendar.MINUTE] = 0
            dateStart[Calendar.HOUR_OF_DAY] = 0
            val dateEnd = Calendar.getInstance()
            dateEnd[Calendar.SECOND] = 59
            dateEnd[Calendar.MINUTE] = 59
            dateEnd[Calendar.HOUR_OF_DAY] = 23

            var startDate = ""
            var endDate = ""
            var lbDate = ""
            when (item.itemId) {
                0 -> {
                    dateStart[Calendar.DATE] = dateEnd[Calendar.DATE]
                    dateEnd[Calendar.DATE] = dateEnd[Calendar.DATE]

                    startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateStart.time)
                    endDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateEnd.time)
                    lbDate = item.title.toString()
                    callBack(lbDate, startDate, endDate)
                }
                1 -> {
                    dateStart[Calendar.DATE] = dateEnd[Calendar.DATE] - 1
                    dateEnd[Calendar.DATE] = dateEnd[Calendar.DATE] - 1

                    startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateStart.time)
                    endDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateEnd.time)
                    lbDate = item.title.toString()
                    callBack(lbDate, startDate, endDate)
                }
                2 -> {
                    dateEnd[Calendar.DATE] = dateEnd[Calendar.DATE] - 1
                    dateStart[Calendar.DATE] = dateEnd[Calendar.DATE] - 7

                    startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateStart.time)
                    endDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateEnd.time)
                    lbDate = item.title.toString()
                    callBack(lbDate, startDate, endDate)
                }
                3 -> {
                    dateEnd[Calendar.DATE] = dateEnd[Calendar.DATE] - 1
                    dateStart[Calendar.DATE] = dateEnd[Calendar.DATE] - 30

                    startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateStart.time)
                    endDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateEnd.time)
                    lbDate = item.title.toString()
                    callBack(lbDate, startDate, endDate)
                }
                4 -> {
                    dateStart[Calendar.DAY_OF_MONTH] = 1
                    dateEnd[Calendar.DAY_OF_MONTH] = dateEnd.getActualMaximum(Calendar.DATE)

                    startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateStart.time)
                    endDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateEnd.time)
                    lbDate = item.title.toString()
                    callBack(lbDate, startDate, endDate)
                }
                5 -> {
                    dateStart[Calendar.DAY_OF_MONTH] = 1
                    dateStart[Calendar.MONTH] = dateStart[Calendar.MONTH] - 1
                    dateEnd[Calendar.DAY_OF_MONTH] = 0

                    startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateStart.time)
                    endDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateEnd.time)
                    lbDate = item.title.toString()
                    callBack(lbDate, startDate, endDate)
                }
                6 -> {
                    dateStart[Calendar.DAY_OF_MONTH] = 1
                    dateStart[Calendar.MONTH] = dateStart[Calendar.MONTH] - 3
                    dateEnd[Calendar.DAY_OF_MONTH] = 0

                    startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateStart.time)
                    endDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateEnd.time)
                    lbDate = item.title.toString()
                    callBack(lbDate, startDate, endDate)
                }
                7 -> {
                    activity?.let {
                        pickDateRange(it) { dateStart, dateEnd ->
                            startDate = DateTimeUtil.convertTimeStampToDate(
                                DateTimeUtil.convertStringToTimeStamp(
                                    dateStart + " 00:00:00",
                                    DateTime.Format.DD_MM_YYYY_HH_MM_SS
                                ),
                                DateTime.Format.YYYY_MM_DD_HH_MM_SS_2
                            )
                            endDate = DateTimeUtil.convertTimeStampToDate(
                                DateTimeUtil.convertStringToTimeStamp(
                                    dateEnd + " 00:00:00",
                                    DateTime.Format.DD_MM_YYYY_HH_MM_SS
                                ),
                                DateTime.Format.YYYY_MM_DD_HH_MM_SS_2
                            )
                            lbDate = dateStart + " - " + dateEnd
                            callBack(lbDate, startDate, endDate)
                        }
                    }
                }
            }

            // lb time, stardate, enddate
            false
        }

        popupMenu.show()
    }

}