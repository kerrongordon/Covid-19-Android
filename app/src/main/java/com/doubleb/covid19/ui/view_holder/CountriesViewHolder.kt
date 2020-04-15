package com.doubleb.covid19.ui.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.doubleb.covid19.R
import com.doubleb.covid19.model.Country
import com.doubleb.covid19.ui.listener.ClickListener
import kotlinx.android.synthetic.main.view_world_country_item.view.*
import java.text.NumberFormat

class CountriesViewHolder(
    itemView: View,
    listener: ClickListener<Country?>
) : RecyclerView.ViewHolder(itemView) {

    private val margin = itemView.resources.getDimension(R.dimen.world_bottom_spacing).toInt()
    private var country: Country? = null

    init {
        itemView.setOnClickListener {
            listener.onItemClicked(country, adapterPosition)
        }
    }

    fun bind(country: Country?, isLastPosition: Boolean) {
        this.country = country

        val layoutParams = (itemView.layoutParams as? RecyclerView.LayoutParams)
        layoutParams?.setMargins(
            layoutParams.leftMargin,
            layoutParams.topMargin,
            layoutParams.rightMargin,
            if (isLastPosition) margin else 0
        )

        val countryName = country?.name
        val countryCases = country?.cases

        countryName?.let {
            itemView.country_item_text_view_name.setLoadedText(it)
            itemView.country_item_text_view_cases.setLoadedText(NumberFormat.getInstance().format(countryCases))
        } ?: run {
            itemView.country_item_text_view_name.loading()
            itemView.country_item_text_view_cases.loading()
        }
    }
}