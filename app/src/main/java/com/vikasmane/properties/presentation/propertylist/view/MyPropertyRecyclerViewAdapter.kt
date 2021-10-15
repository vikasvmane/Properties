package com.vikasmane.properties.presentation.propertylist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vikasmane.properties.R
import com.vikasmane.properties.databinding.FragmentPropertyListAreaItemBinding
import com.vikasmane.properties.databinding.FragmentPropertyListHighlightedItemBinding
import com.vikasmane.properties.databinding.FragmentPropertyListItemBinding
import com.vikasmane.properties.domain.entities.Item
import com.vikasmane.properties.presentation.propertydetails.PropertyItemClickListener

/**
 * Renders Recyclerview items with three different ViewHolders.
 */
class MyPropertyRecyclerViewAdapter(
    private val values: List<Item>, private val propertyItemClickListener: PropertyItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            VIEW_TYPE_HIGHLIGHTED_PROPERTY_ID -> ViewHolderHighlighted(
                FragmentPropertyListHighlightedItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_PROPERTY_ID -> ViewHolderProperty(
                FragmentPropertyListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ViewHolderArea(
                FragmentPropertyListAreaItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_HIGHLIGHTED_PROPERTY_ID -> (holder as ViewHolderHighlighted).bind(position)
            VIEW_TYPE_PROPERTY_ID -> (holder as ViewHolderProperty).bind(position)
            VIEW_TYPE_AREA_ID -> (holder as ViewHolderArea).bind(position)
            else -> (holder as ViewHolderArea).bind(position)
        }
        holder.itemView.setOnClickListener {
            propertyItemClickListener.onPropertyClicked(values[position])
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolderHighlighted(binding: FragmentPropertyListHighlightedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image: ImageView = binding.imagePoster
        private val title: TextView = binding.textTitle
        private val address: TextView = binding.textAddress
        private val price: TextView = binding.textPrice
        private val size: TextView = binding.textSize
        private val rooms: TextView = binding.textNoOfRooms
        private val days: TextView = binding.textDaysOnHemnet

        fun bind(position: Int) {
            val item = values[position]
            Picasso.get().load(item.image).into(image)
            title.text = item.area
            address.text = context.getString(
                R.string.address_placeholder,
                item.streetAddress,
                item.municipality
            )
            price.text = item.askingPrice
            size.text = context.getString(R.string.living_area, item.livingArea)
            rooms.text = context.getString(R.string.rooms, item.numberOfRooms)
            days.text = context.getString(R.string.days_on_hemnet, item.daysOnHemnet)
        }
    }

    inner class ViewHolderProperty(binding: FragmentPropertyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image: ImageView = binding.imagePoster
        private val title: TextView = binding.textTitle
        private val address: TextView = binding.textAddress
        private val price: TextView = binding.textPrice
        private val size: TextView = binding.textSize
        private val rooms: TextView = binding.textNoOfRooms
        private val days: TextView = binding.textDaysOnHemnet

        fun bind(position: Int) {
            val item = values[position]
            Picasso.get().load(item.image).into(image)
            title.text = item.area
            address.text = context.getString(
                R.string.address_placeholder,
                item.streetAddress,
                item.municipality
            )
            price.text = item.askingPrice
            size.text = context.getString(R.string.living_area, item.livingArea)
            rooms.text = context.getString(R.string.rooms, item.numberOfRooms)
            days.text = context.getString(R.string.days_on_hemnet, item.daysOnHemnet)
        }
    }

    inner class ViewHolderArea(binding: FragmentPropertyListAreaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val image: ImageView = binding.imagePoster
        private val sectionTitle: TextView = binding.textSectionTitle
        private val title: TextView = binding.textTitle
        private val rating: TextView = binding.textRating
        private val price: TextView = binding.textPrice

        fun bind(position: Int) {
            val item = values[position]
            Picasso.get().load(item.image).into(image)
            title.text = item.area
            rating.text = context.getString(R.string.ratings, item.rating)
            price.text = context.getString(R.string.avg_price, item.averagePrice)
            sectionTitle.text = context.getString(R.string.area_title)
        }
    }

    companion object {
        const val VIEW_TYPE_HIGHLIGHTED_PROPERTY = "HighlightedProperty"
        const val VIEW_TYPE_HIGHLIGHTED_PROPERTY_ID = 1
        const val VIEW_TYPE_PROPERTY = "Property"
        const val VIEW_TYPE_PROPERTY_ID = 2
        const val VIEW_TYPE_AREA = "Area"
        const val VIEW_TYPE_AREA_ID = 3
    }

    override fun getItemViewType(position: Int): Int {
        return when (values[position].type) {
            VIEW_TYPE_HIGHLIGHTED_PROPERTY -> VIEW_TYPE_HIGHLIGHTED_PROPERTY_ID
            VIEW_TYPE_PROPERTY -> VIEW_TYPE_PROPERTY_ID
            VIEW_TYPE_AREA -> VIEW_TYPE_AREA_ID
            else -> VIEW_TYPE_AREA_ID
        }
    }
}