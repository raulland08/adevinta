package com.adevintaapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.adevintaapp.R
import com.adevintaapp.ui.util.inflate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.beer_info_layout.view.*

class BeerInfoLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private var nameTextSize: Float = 0f
    private var taglineTextSize: Float = 0f
    private var image: String? = null
    private var imgHeight: Int = 0
    private var imgWidth: Int = 0
    private var imgCornerRadius: Int = 0

    private var increaseDecreaseValue = 2f
    private var minTextSizeValue = 12f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {

        context.inflate(R.layout.beer_info_layout, this, true)

        val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.BeerInfoLayout)
        try {
            styledAttrs.let {

            }
        } finally {
            styledAttrs.recycle()
        }
    }

    fun setDefaults(name: String, tagline: String?, imageUrl: String?) {
        tv_name.text = name
        tv_tagline.text = tagline
        image = imageUrl
        setImage(image, imgHeight, imgWidth, imgCornerRadius)
    }

    private fun getTextSizes() {
        nameTextSize = tv_name.textSize.toSp
        taglineTextSize = tv_tagline.textSize.toSp
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        btn_name_add.setOnClickListener {
            addNameTextSize()
        }

        btn_name_minus.setOnClickListener {
            subtractNameTextSize()
        }

        btn_tagline_add.setOnClickListener {
            addTaglineTextSize()
        }

        btn_tagline_minus.setOnClickListener {
            subtractTaglineTextSize()
        }

        sw_tagline.setOnCheckedChangeListener { _, isChecked ->
            tv_tagline.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        btn_apply_image.setOnClickListener {
            applyImageSettings()
        }
    }

    private fun addNameTextSize() {
        nameTextSize += increaseDecreaseValue
        tv_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, nameTextSize)
    }

    private fun subtractNameTextSize() {
        if (nameTextSize > minTextSizeValue) {
            nameTextSize -= increaseDecreaseValue
            tv_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, nameTextSize)
        }
    }

    private fun addTaglineTextSize() {
        taglineTextSize += increaseDecreaseValue
        tv_tagline.setTextSize(TypedValue.COMPLEX_UNIT_SP, taglineTextSize)
    }

    private fun subtractTaglineTextSize() {
        if (taglineTextSize > minTextSizeValue) {
            taglineTextSize -= increaseDecreaseValue
            tv_tagline.setTextSize(TypedValue.COMPLEX_UNIT_SP, taglineTextSize)
        }
    }

    fun getAllSettings(): Info {
        getTextSizes()

        return Info(
            nameTextSize,
            "nameColor",
            taglineTextSize,
            "taglineColor",
            sw_tagline.isChecked,
            imgHeight,
            imgWidth,
            imgCornerRadius
        )
    }

    fun setAllSettings(info: Info) {
        getTextSizes()

        info.nameSize.let { if (it > 0f) tv_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, it) }

        info.taglineSize.let { if (it > 0f) tv_tagline.setTextSize(TypedValue.COMPLEX_UNIT_SP, it) }

        info.taglineVisible.let {
            sw_tagline.isChecked = it
            tv_tagline.visibility = if (it) View.VISIBLE else View.GONE
        }

        imgHeight = info.imageHeight
        imgWidth = info.imageWidth
        imgCornerRadius = info.imageRoundRadius
    }

    private fun setImage(imageUrl: String?, height: Int, width: Int, cornerRadius: Int) {

        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.color.grey)
            .apply {
                this.override(
                    width.let { if (it > 0) it else iv_beer.width },
                    height.let { if (it > 0) it else iv_beer.height }
                )
                if (cornerRadius > 0) this.transform(RoundedCorners(cornerRadius))
            }
            .error(R.color.grey)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(iv_beer)
    }

    private fun applyImageSettings() {
        imgHeight = et_height.text.toString().let { if (it.isNotEmpty()) it.toInt() else 0}
        imgWidth = et_width.text.toString().let { if (it.isNotEmpty()) it.toInt() else 0}
        imgCornerRadius = et_corner_radius.text.toString().let { if (it.isNotEmpty()) it.toInt() else 0}

        setImage(image, imgHeight, imgWidth, imgCornerRadius)

        clearImageInputs()
    }

    private fun clearImageInputs() {
        et_height.setText("")
        et_width.setText("")
        et_corner_radius.setText("")
    }

    private val Float.toSp get() = this / resources.displayMetrics.scaledDensity
}

data class Info(
    val nameSize: Float = 0f,
    val nameColor: String? = null,
    val taglineSize: Float = 0f,
    val taglineColor: String? = null,
    val taglineVisible: Boolean,
    val imageHeight: Int = 0,
    val imageWidth: Int = 0,
    val imageRoundRadius: Int = 0
)