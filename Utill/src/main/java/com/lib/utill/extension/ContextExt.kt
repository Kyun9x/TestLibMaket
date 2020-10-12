package com.example.utill.extension

//fun Context.networkIsConnected():Boolean {
//    try {
//        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (!conMgr.activeNetworkInfo.isConnected) {
//            Toast.makeText(
//                MyApplication.instance,
//                MyApplication.instance.getText(R.string.error_network),
//                Toast.LENGTH_SHORT
//            ).show()
//            return false
//        }
//    } catch (e: Exception) {
//        Toast.makeText(
//            MyApplication.instance,
//            MyApplication.instance.getText(R.string.error_network),
//            Toast.LENGTH_SHORT
//        ).show()
//        e.printStackTrace()
//        return false
//    }
//    return true
//}

//fun Context.openActivity(
//    clz: Class<*>, bundle: Bundle? = null, clearTop: Boolean = false,
//    enterAnim: Int? = null, exitAnim: Int? = null, flags: IntArray? = null
//) {
//    val intent = Intent(ctx, clz)
//    if (flags?.isNotEmpty() == true) {
//        for (flag in flags) {
//            intent.addFlags(flag)
//        }
//    }
//    if (clearTop) {
//        setResult(Activity.RESULT_CANCELED)
//        finishAffinity()
//    }
//    if (bundle != null) {
//        intent.putExtras(bundle)
//    }
//    startActivity(intent)
//    enterAnim?.also { enter ->
//        exitAnim?.also { exit ->
//            overridePendingTransition(enter, exit)
//        }
//    }
//}