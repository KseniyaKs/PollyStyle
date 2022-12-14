package com.mindev.mindev_pdfviewer

import android.graphics.Bitmap
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView


class PdfViewHolder(itemView: View, private val core: PdfCore) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(isAnimation: Boolean) = with(itemView) {
        val img = findViewById<SubsamplingScaleImageView>(R.id.img)

        img.recycle()
        core.renderPage(adapterPosition) { bitmap: Bitmap?, currentPage: Int ->
            if (currentPage != adapterPosition) return@renderPage
            bitmap?.let(ImageSource::bitmap)?.let(img::setImage)
            if (isAnimation) {
                img.animation = AlphaAnimation(0F, 1F).apply {
                    interpolator = LinearInterpolator()
                    duration = 500
                }
            }
        }
    }
}