package com.pritomdutta.bs23taskpritom.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.chip.Chip
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritomdutta.bs23taskpritom.R
import com.pritomdutta.bs23taskpritom.base_adapter.BaseViewHolder
import com.pritomdutta.bs23taskpritom.databinding.ItemStockBinding
import com.pritomdutta.bs23taskpritom.extensions.loadImage

class StockViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<ItemStockBinding>(
    binding = ItemStockBinding.inflate(inflater, parent, false)
) {

    fun bind(data: ShowDisplayStockData) {

        binding.apply {
            imgThumbnail.loadImage(data.imageUrl)
            tvName.text = data.title
            tvPrice.text = "$" + data.price

            if (data.price != data.discountPrice) {
                tvPrice.paintFlags = tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                tvDiscountPrice.isVisible = true
            } else {
                tvDiscountPrice.isVisible = false
            }

            tvDiscountPrice.text =
                if (data.discountPrice == "0.00") "$" + data.price else "$" + data.discountPrice

            tvStockLeft.text = data.stockStatus
            txtRating.text = data.rating

            chipCategory.removeAllViews()
            for (index in data.category) {
                val chip = LayoutInflater.from(itemView.context).inflate(
                    R.layout.item_chip_category,
                    chipCategory,
                    false
                ) as Chip
                chip.text = index.uppercase()
                // necessary to get single selection working
                chip.isClickable = false
                chip.isCheckable = false
                chipCategory.removeView(chip)
                chipCategory.addView(chip)
            }
        }
    }
}