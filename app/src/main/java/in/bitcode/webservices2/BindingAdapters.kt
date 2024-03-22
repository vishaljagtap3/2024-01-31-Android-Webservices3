package `in`.bitcode.webservices2

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun loadWebImageUrlToImageView(
    img : ImageView,
    imageUrl : String
) {
    Glide.with(img)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(img)
}

