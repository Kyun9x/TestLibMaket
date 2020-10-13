package com.lib.marketplace.activity.marketplace

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Response
import com.android.volley.VolleyError
import com.asksira.bsimagepicker.BSImagePicker
import com.bumptech.glide.Glide
import com.lib.marketplace.adapter.marketplace.ImageOrderAdapter
import com.ipos.iposmanage.model.marrketplace.*
import com.ipos.iposmanage.model.orderonline.ImageOrderModel
import com.ipos.iposmanage.util.DateTimeUtil
import com.ipos.saler.extension.NumberTextWatcher
import com.ipos.saler.extension.StringExt
import com.ipos.saler.extension.gone
import com.ipos.saler.extension.visible
import com.lib.marketplace.R
import com.lib.marketplace.activity.BaseActivity
import com.lib.marketplace.app.ApplicationIpos
import com.lib.marketplace.app.Constants
import com.lib.marketplace.app.Constants.ArticlesStatus.CANCELED
import com.lib.marketplace.app.Constants.ArticlesStatus.SOLD
import com.lib.marketplace.restful.WSRestFull
import com.lib.marketplace.util.Log
import com.lib.marketplace.util.ToastUtil
import com.soyagarden.android.common.extension.showStatusBar
import kotlinx.android.synthetic.main.activity_article_create.*
import kotlinx.android.synthetic.main.include_header2.*
import java.io.*


class ArticleCreateActivity : BaseActivity(), BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate {

    private lateinit var mAdapterImageOrder: ImageOrderAdapter
    private val mResultImageOrder: ArrayList<ImageOrderModel> = ArrayList()
    private var areaId = ""
    private var tagsList = ArrayList<String>()
    private var idArticles: String? = null
    private var articlesModel: ArticlesModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_create)
        initExtra()
        initAdapter()
        initData()
        initClick()
    }

    private fun initExtra() {
        idArticles = intent?.extras?.let {
            if (it.containsKey(Constants.OBJECT)) {
                it.getSerializable(Constants.OBJECT) as String?
            } else {
                null
            }
        }

        idArticles?.run {
            apiDetail(this)
        } ?: run {
            mCreate.text = getString(R.string.dang_tin)
        }
    }

    private fun apiDetail(id: String?) {
        showProgressHub(this)
        WSRestFull(this).apiSCMArticlesDetail(id.toString(), Response.Listener { response -> apiDetailDone(response.data) }, Response.ErrorListener { error ->
            apiDetailDone(null)
            error.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        })
    }

    private fun apiDetailDone(data: ArticlesModel?) {
        dismissProgressHub()
        data?.run {
            articlesModel = this
            city_uid?.run {
                areaId = this
            }
            listTag?.first()?.id?.run {
                tagsList.add(this)
            }
            mImage_urls?.forEach {
                var imageOrderModel = ImageOrderModel()
                imageOrderModel.img_url = it.url
                imageOrderModel.img_url_thumb = it.url_thumb
                mResultImageOrder.add(imageOrderModel)
            }
            mAdapterImageOrder.notifyDataSetChanged()

            mTieuDe.setText(StringExt.isTextEmpty(title))
            price.setText(StringExt.isTextEmpty(mPrice))
            mDesc.setText(StringExt.isTextEmpty(mContent))
            mNguoiDang.setText(StringExt.isTextEmpty(mAuthor_name))
            mSdt.setText(StringExt.isTextEmpty(mAuthor_phone))
            mTag.setText(StringExt.isTextEmpty(listTag?.first()?.tag_name))
            mKhuVuc.setText(StringExt.isTextEmpty(city?.city_name))

            mTieuDe.isEnabled = false
            price.isEnabled = false
            mDesc.isEnabled = false
            mTag.isEnabled = false
            mKhuVuc.isEnabled = false
            mSdt.isEnabled = false
            mNguoiDang.isEnabled = false

            when (status) {
                Constants.ArticlesStatus.PENDING -> {
                    mHuyTin.visible()
                    mCapNhat.visible()
                    mCreate.gone()
                    mAnTin.gone()
                    mDaBan.gone()
                    mTaoLaiTin.gone()
                }
                Constants.ArticlesStatus.CONFIRMED -> {
                    mAnTin.visible()
                    mDaBan.visible()
                    mHuyTin.gone()
                    mCapNhat.gone()
                    mTaoLaiTin.gone()
                    mCreate.gone()
                    if (active == 0) {
                        mAnTin.text = getString(R.string.hien_tin)
                    } else {
                        mAnTin.text = getString(R.string.an_tin)
                    }
                    if (expire_time != null) {
                        DateTimeUtil.compareTimeGet(DateTimeUtil.convertTimeStampToDate(expire_time, Constants.Date.Format.YYYY_MM_DD_HH_MM_SS_2)) { month, day, hour, minus, second ->
                            if(second > 0){
                                mAnTin.gone()
                                mDaBan.gone()
                                mTaoLaiTin.visible()
                            }
                        }
                    }
                }
                Constants.ArticlesStatus.CANCELED -> {
                    mAnTin.gone()
                    mDaBan.gone()
                    mHuyTin.gone()
                    mCapNhat.gone()
                    mCreate.gone()
                    mTaoLaiTin.gone()
                }
                Constants.ArticlesStatus.SOLD -> {
                    mAnTin.gone()
                    mDaBan.gone()
                    mHuyTin.gone()
                    mCapNhat.gone()
                    mCreate.gone()
                    mTaoLaiTin.gone()
                }
            }
        }
    }

    private fun initAdapter() {
        mRecyclerViewImage.apply {
            layoutManager = GridLayoutManager(this@ArticleCreateActivity, 3)
            mAdapterImageOrder = ImageOrderAdapter(mResultImageOrder, itemClickListener = { it, potision ->
                openSelectImage()
            }, deleteClickListener = {
                mResultImageOrder.remove(it)
                mAdapterImageOrder.notifyDataSetChanged()
            })
            adapter = mAdapterImageOrder
        }

        mResultImageOrder.add(ImageOrderModel(img_url_thumb = null))
        mAdapterImageOrder.notifyDataSetChanged()
    }

    private fun openSelectImage() {
        val pickerDialog =
                BSImagePicker.Builder("com.asksira.imagepickersheetdemo.fileprovider")
                        .setMaximumDisplayingImages(Integer.MAX_VALUE)
                        .setMinimumMultiSelectCount(1)
                        .setMaximumMultiSelectCount(0)
                        .build()
        pickerDialog.show(supportFragmentManager, "picker")
    }

    private fun initClick() {
        mIconLeft.setOnClickListener { onBackPressed() }
        success.setOnClickListener { checkSuccess() }
        mCreate.setOnClickListener { create() }
        mKhuVuc.setOnClickListener { choiceKhuVuc() }
        mTag.setOnClickListener { tags() }
        mHuyTin.setOnClickListener { apiHuyTin() }
        mCapNhat.setOnClickListener { capnhat() }
        mAnTin.setOnClickListener { apiAnTin() }
        mDaBan.setOnClickListener { apiDaBan() }
        mTaoLaiTin.setOnClickListener { apiTaoLaiTin() }
    }

    private fun apiTaoLaiTin() {
        var newArticlesModel = ArticlesModel(id = articlesModel?.id, type = "renew")
        apiEdit(newArticlesModel)
    }

    private fun apiHuyTin() {
        var newArticlesModel = ArticlesModel(id = articlesModel?.id, status = CANCELED)
        apiEdit(newArticlesModel)
    }

    private fun apiAnTin() {
        if (articlesModel?.active == 0) {
            var newArticlesModel = ArticlesModel(id = articlesModel?.id, active = 1)
            apiEdit(newArticlesModel)
        } else {
            var newArticlesModel = ArticlesModel(id = articlesModel?.id, active = 0)
            apiEdit(newArticlesModel)
        }
    }

    private fun apiDaBan() {
        var newArticlesModel = ArticlesModel(id = articlesModel?.id, status = SOLD)
        apiEdit(newArticlesModel)
    }

    private fun checkSuccess() {
        onBackPressed()
    }

    private fun capnhat() {

    }

    private fun tags() {
        showProgressHub(this)
        WSRestFull(this).apiSCMTags({ (data) -> tagsDone(data) }) { error: VolleyError ->
            tagsDone(null)
            error.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        }
    }

    private fun tagsDone(data: ArrayList<TagsModel>?) {
        dismissProgressHub()
        data?.run {
            val popupMenu = PopupMenu(this@ArticleCreateActivity, mTag, Gravity.RIGHT)

            forEachIndexed { index, team ->
                popupMenu.menu.add(0, index.plus(1), index.plus(1), team.tag_name)
            }

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                tagsList.clear()
                val cityName = item.title.toString()
                mTag.setText(cityName)
                tagsList.add(data[item.itemId.minus(1)].id.toString())
                false
            }
            popupMenu.show()
        }
    }

    private fun choiceKhuVuc() {
        showProgressHub(this)
        WSRestFull(this).apiSCMCity({ (data) -> areaDone(data) }) { error: VolleyError ->
            areaDone(null)
            error.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        }
    }

    private fun areaDone(data: ArrayList<AddressModel>?) {
        dismissProgressHub()
        data?.run {
            val popupMenu = PopupMenu(this@ArticleCreateActivity, mKhuVuc, Gravity.RIGHT)

            forEachIndexed { index, team ->
                popupMenu.menu.add(0, index.plus(1), index.plus(1), team.city_name)
            }

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                val cityName = item.title.toString()
                mKhuVuc.setText(cityName)
                areaId = data[item.itemId.minus(1)].id.toString()
                false
            }
            popupMenu.show()
        }
    }

    private fun create() {
        if (TextUtils.isEmpty(mTieuDe.text.toString())) {
            ToastUtil.makeText(this, getString(R.string.articles_title_valid))
            return
        }
        if (TextUtils.isEmpty(price.text.toString())) {
            ToastUtil.makeText(this, getString(R.string.articles_price_valid))
            return
        }
        if (TextUtils.isEmpty(mKhuVuc.text.toString())) {
            ToastUtil.makeText(this, getString(R.string.articles_khuvuc_valid))
            return
        }
        if (TextUtils.isEmpty(mNguoiDang.text.toString())) {
            ToastUtil.makeText(this, getString(R.string.articles_nguoidang_valid))
            return
        }
        if (TextUtils.isEmpty(mSdt.text.toString())) {
            ToastUtil.makeText(this, getString(R.string.articles_sdt_valid))
            return
        }
        if (TextUtils.isEmpty(mTag.text.toString())) {
            ToastUtil.makeText(this, getString(R.string.articles_tag_valid))
            return
        }

        var expectedValue = 0.0
        if (!TextUtils.isEmpty(price.text.toString())) {
            expectedValue =
                    price.text.toString().replace(".", "").replace(",", "").toDouble()
        }
        var newImageChoice = ArrayList<ArticlesImageModel>()
        mResultImageOrder?.forEachIndexed { index, imageOrderModel ->
            if (index.plus(1) < mResultImageOrder.size) {
                var newArticles = ArticlesImageModel(url = imageOrderModel.img_url, url_thumb = imageOrderModel.img_url_thumb)
                newImageChoice.add(newArticles)
            }
        }
        var newArticlesModel = ArticlesModel(mTitle = mTieuDe.text.toString(),
                mPrice = expectedValue,
                city_uid = areaId,
                tags_uid = tagsList,
                mAuthor_name = mNguoiDang.text.toString(),
                mAuthor_phone = mSdt.text.toString(),
                mImage_urls = newImageChoice,
                author_id = mCartBussiness.userId,
                brand_id = mCartBussiness.brandId,
                company_id = mCartBussiness.companyId,
                mContent = mDesc.text.toString())
        apiCreate(newArticlesModel)
    }

    private fun apiCreate(articlesModel: ArticlesModel) {
        showProgressHub(this)
        WSRestFull(this).apiSCMArticlesCreate(articlesModel.toJson(), { (data) -> createDone(data) }) { error: VolleyError ->
            createDone(null)
            error.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        }
    }

    private fun apiEdit(articlesModel: ArticlesModel) {
        showProgressHub(this)
        WSRestFull(this).apiSCMArticlesEdit(articlesModel.toJson(), { (data) -> editDone(data) }) { error: VolleyError ->
            editDone(null)
            error.printStackTrace()
            ToastUtil.makeText(this, getString(R.string.error_network2))
        }
    }

    private fun createDone(data: ArticlesModel?) {
        dismissProgressHub()
        data?.run {
            layout_success.visible()
        }
    }

    private fun editDone(data: ArticlesModel?) {
        dismissProgressHub()
        data?.run {
            when (status) {
                Constants.ArticlesStatus.PENDING -> {
                }
                Constants.ArticlesStatus.CONFIRMED -> {
                    if (active == 0) {
                        text_success.text = getString(R.string.an_tin_success)
                        text_success2.text = getString(R.string.an_tin_lb)
                    } else {
                        text_success.text = getString(R.string.mo_tin_success)
                        text_success2.text = getString(R.string.mo_tin_lb)
                    }
                }
                Constants.ArticlesStatus.SOLD -> {
                    text_success.text = getString(R.string.da_ban_success)
                    text_success2.text = getString(R.string.da_ban_lb)
                }
                Constants.ArticlesStatus.CANCELED -> {
                    text_success.text = getString(R.string.huytin_success)
                    text_success2.text = getString(R.string.lb_huytin)
                }
            }
            layout_success.visible()
        }
    }

    private fun initData() {
        showStatusBar(statusColor = true, color = R.color.grayF8)
        price.addTextChangedListener(NumberTextWatcher(price))
        mlbTitle.text = getString(R.string.create_articles)
        text_success.text = getString(R.string.create_articles)
    }

    override fun onSingleImageSelected(uri: Uri?, tag: String?) {
        uri?.run {
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, this)
            showProgressHub(this@ArticleCreateActivity)
            WSRestFull(this@ArticleCreateActivity).apiSCMUpload(getFile(bitmap), Response.Listener {
                uploadDone(it.data)
            })
        }
    }

    override fun onMultiImageSelected(uriList: MutableList<Uri>?, tag: String?) {

    }

    fun getFile(bitmap: Bitmap): File? {
        val f: File = File(cacheDir, "image.jpg")
        try {
            f.createNewFile()
            val bm: Bitmap = bitmap
            val bos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            val bitmapdata: ByteArray = bos.toByteArray()
            var fos: FileOutputStream? = null
            fos = FileOutputStream(f)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return f
    }

    private fun uploadDone(data: UploadImageModel?) {
        dismissProgressHub()
        data?.run {
            Log.e("data", "data: " + data.toJson())
            mResultImageOrder.add(ImageOrderModel(img_url_thumb = img_url_thumb, img_url = img_url))

            with(mResultImageOrder.iterator()) {
                forEach {
                    if (it.img_url_thumb == null) {
                        remove()
                    }
                }
            }
        }
        runOnUiThread {
            mResultImageOrder.add(ImageOrderModel(img_url_thumb = null))
            mAdapterImageOrder.notifyDataSetChanged()
        }
    }

    override fun loadImage(imageUri: Uri?, ivImage: ImageView?) {
        Glide.with(this).load(imageUri).into(ivImage!!)
    }
}