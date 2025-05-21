package com.pritomdutta.bs23taskpritom.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.chip.Chip
import com.pritom.dutta.movie.domain.models.ShowDisplayStockData
import com.pritomdutta.bs23taskpritom.R
import com.pritomdutta.bs23taskpritom.base_adapter.BaseViewHolder
import com.pritomdutta.bs23taskpritom.databinding.ItemStockBinding
import com.pritomdutta.bs23taskpritom.extensions.loadImage
import java.text.NumberFormat

class StockViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder<ItemStockBinding>(
    ItemStockBinding.inflate(inflater, parent, false)
) {

    fun bind(data: ShowDisplayStockData) = with(binding) {
        imgThumbnail.loadImage(data.imageUrl)

        tvName.text = data.title
        tvStockLeft.text = data.stockStatus
        txtRating.text = data.rating

        updatePriceViews(data)
        populateCategoryChips(data.category)
    }

    private fun ItemStockBinding.updatePriceViews(data: ShowDisplayStockData) {
        val priceFormatted = data.price.toCurrencyFormat()
        tvPrice.text = priceFormatted

        if (data.isDiscountAvailable) {
            tvPrice.applyStrikeThrough()
            tvDiscountPrice.isVisible = true
            tvDiscountPrice.text = data.discountPrice.toCurrencyFormat()
        } else {
            tvPrice.removeStrikeThrough()
            tvDiscountPrice.isVisible = false
        }
    }

    private fun ItemStockBinding.populateCategoryChips(categories: List<String>) {
        chipCategory.removeAllViews()

        categories.forEach { category ->
            val chip = createCategoryChip(category)
            chipCategory.addView(chip)
        }
    }

    private fun ItemStockBinding.createCategoryChip(text: String): Chip {
        val context = itemView.context
        return (LayoutInflater.from(context).inflate(
            R.layout.item_chip_category,
            chipCategory,
            false
        ) as Chip).apply {
            this.text = text.uppercase()
            isClickable = false
            isCheckable = false
        }
    }

    // Extension functions for reusability and clarity
    private fun String.toCurrencyFormat(): String {
        val amount = toDoubleOrNull() ?: return "-"
        return NumberFormat.getCurrencyInstance().format(amount)
    }

    private fun TextView.applyStrikeThrough() {
        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun TextView.removeStrikeThrough() {
        paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}
