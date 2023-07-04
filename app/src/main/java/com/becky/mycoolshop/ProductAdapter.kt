package com.becky.mycoolshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.becky.mycoolshop.databinding.ProductListBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

 class ProductAdapter( private var productsList: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productsList[position]
        val binding = holder.binding
        binding.tvId.text = currentProduct.id.toString()
        binding.tvTitle.text = currentProduct.title
        binding.tvPrice.text = currentProduct.price.toString()
        binding.tvCategory.text = currentProduct.category
        binding.tvRatings.text = currentProduct.rating.toString()
        binding.tvStock.text = currentProduct.stock.toString()
        binding.tvDescription.text = currentProduct.description


        Picasso
            .get()
            .load(currentProduct.thumbnail)
            .transform(CropCircleTransformation())
            .into(binding.ivThumbnail)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductViewHolder(var binding: ProductListBinding) :
        RecyclerView.ViewHolder(binding.root)
}