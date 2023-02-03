package com.example.android.marsphotos

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.PhotoGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let{
        // convert the URL string to a Uri object using the toUri()
        // To use the HTTPS scheme, append buildUpon.scheme("https") to the toUri builder
        // Call build() to build the object.
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

//Using a BindingAdapter to set the RecyclerView data causes data binding
// to automatically observe the LiveData for the list of MarsPhoto objects.
// Then the binding adapter is called automatically when the MarsPhoto list changes.
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data) //This tells the RecyclerView when a new list is available.
}