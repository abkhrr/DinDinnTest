package com.food_dev.utils.ext.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatDrawableManager
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.food_dev.utils.ext.other.isAvailable
import com.food_dev.utils.R
import java.io.ByteArrayOutputStream

object ImageUtils {

    fun loadImageWithoutPlaceholder(view: ImageView?, imagePath: String?, context: Context, @DimenRes radius: Int? = null) {
        if (!context.isAvailable()) return
        val multiTransformation =  MultiTransformation<Bitmap>(FitCenter(), RoundedCorners(context.resources.getDimensionPixelSize(radius ?: 0)))
        val safeImagePath = if (!imagePath.isNullOrBlank()) imagePath else "https://www.btklsby.go.id/images/placeholder/basic.png"
        view?.let {
            Glide.with(context)
                .load(safeImagePath)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .placeholder(R.drawable.placeholder)
                .into(view)
        }
    }

    fun loadImageWithPlaceHolder(view: ImageView?, imageUrl: String?, context: Context, placeHolder: Int, @DimenRes radius: Int? = null) {
        if (!context.isAvailable()) return
        val multiTransformation =  MultiTransformation<Bitmap>(CenterCrop(), RoundedCorners(context.resources.getDimensionPixelSize(radius ?: 0)))
        view?.let {
            Glide.with(context)
                .load(imageUrl)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .placeholder(placeHolder)
                .into(view)
        }
    }

    @SuppressLint("RestrictedApi")
    fun loadImageWithCirclePlaceholder(view: ImageView?, imagePath: String?, context: Context, @DrawableRes placeholder: Int) {
        if (!context.isAvailable()) return
        val safeImagePath = if (!imagePath.isNullOrBlank()) imagePath else "https://www.btklsby.go.id/images/placeholder/basic.png"
        val drawable = context.let { AppCompatDrawableManager.get().getDrawable(it, placeholder) }
        val multiTransformation =  MultiTransformation<Bitmap>(CenterCrop(), CircleCrop())
        view?.let {
            Glide.with(context)
                .load(safeImagePath)
                .apply(RequestOptions.circleCropTransform().circleCrop().diskCacheStrategy(
                    DiskCacheStrategy.ALL))
                .placeholder(drawable)
                .error(drawable)
                .into(it) }
    }

    fun loadImageWithPlaceHolder(view: ImageView?, imagePath: String?, context: Context, placeHolder: Int) {
        if (!context.isAvailable()) return
        val safeImagePath = if (!imagePath.isNullOrBlank()) imagePath else "https://www.btklsby.go.id/images/placeholder/basic.png"
        view?.let {
            Glide.with(context)
                .load(safeImagePath)
                .transform(CenterCrop()).placeholder(placeHolder)
                .into(view)
        }
    }

    fun loadImageWithSquareRatio(view: ImageView?, imagePath: String?, context: Context) {
        if (!context.isAvailable()) return
        val safeImagePath = if (!imagePath.isNullOrBlank()) imagePath else "https://www.btklsby.go.id/images/placeholder/basic.png"
        val multiTransformation =  MultiTransformation<Bitmap>(CenterCrop(), CircleCrop())
        view?.let {
            Glide.with(context)
                .load(safeImagePath)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .placeholder(R.drawable.placeholder)
                .into(it)
        }
    }

    @SuppressLint("RestrictedApi")
    fun loadImageWithCirclePlaceholderNoCache(view: ImageView?, imagePath: String?, context: Context, @DrawableRes placeholder: Int) {
        if (!context.isAvailable()) return
        val safeImagePath = if (!imagePath.isNullOrBlank()) imagePath else "https://www.btklsby.go.id/images/placeholder/basic.png"
        val drawable = context.let { AppCompatDrawableManager.get().getDrawable(it, placeholder) }
        val multiTransformation =  MultiTransformation<Bitmap>(CenterCrop(), CircleCrop())
        view?.let {
            Glide.with(context)
                .load(safeImagePath)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .placeholder(drawable)
                .into(it)
        }
    }

    @SuppressLint("RestrictedApi")
    fun loadImageWithCirclePlaceholder(view: ImageView?, imagePath: Uri?, context: Context, @DrawableRes placeholder: Int) {
        if (!context.isAvailable()) return
        val drawable = context.let { AppCompatDrawableManager.get().getDrawable(it, placeholder) }
        val multiTransformation =  MultiTransformation<Bitmap>(CenterCrop(), CircleCrop())
        view?.let {
            Glide.with(context)
                .load(imagePath)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .placeholder(drawable)
                .circleCrop()
                .into(it)
        }
    }

    fun loadImageCenterWithoutPlaceholder(view: ImageView?, imagePath: String?, context: Context) {
        if (!context.isAvailable()) return
        val safeImagePath = if (!imagePath.isNullOrBlank()) imagePath else "https://www.btklsby.go.id/images/placeholder/basic.png"
        view?.let {
            Glide.with(context)
                .load(safeImagePath)
                .transform(CenterCrop())
                .placeholder(R.drawable.placeholder)
                .into(view)
        }
    }

    fun loadImageCenterWithoutPlaceholder(view: ImageView?, imagePath: Int?, context: Context) {
        checkNotNull(imagePath) { return }
        view?.let {
            Glide.with(context)
                .load(imagePath)
                .transform(CenterCrop())
                .placeholder(R.drawable.placeholder)
                .into(view) }
    }

    fun getAlwaysLandScapeImage(bitmap: Bitmap, isLandScape: Boolean) : ByteArrayOutputStream {
        val stream = ByteArrayOutputStream()
        if(bitmap.height > bitmap.width && isLandScape) {
            val matrix = Matrix()
            matrix.postRotate(-90F)
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true).compress(Bitmap.CompressFormat.JPEG, 60, stream)
        }
        else { bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream) }
        return stream
    }

    fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
        val drawable = ContextCompat.getDrawable(context, drawableId)
        val bitmap = Bitmap.createBitmap(drawable?.intrinsicWidth ?: 0, drawable?.intrinsicHeight ?: 0, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable?.setBounds(0, 0, canvas.width, canvas.height)
        drawable?.draw(canvas)
        return bitmap
    }

}