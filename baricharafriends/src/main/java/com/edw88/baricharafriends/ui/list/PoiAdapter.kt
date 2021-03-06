package com.edw88.baricharafriends.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edw88.baricharafriends.R
import com.edw88.baricharafriends.model.SitiosItem
import com.squareup.picasso.Picasso


class PoiAdapter(
    private val puntosList: ArrayList<SitiosItem>,
    private val onItemClicked: (SitiosItem) -> Unit
) : RecyclerView.Adapter<PoiAdapter.PoiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_poi_item, parent, false)
        return PoiViewHolder(view)
    }

    override fun onBindViewHolder(holder: PoiViewHolder, position: Int) {
        val poi = puntosList[position]
        holder.itemView.setOnClickListener { onItemClicked(puntosList[position]) }
        holder.bind(poi)
    }

    override fun getItemCount(): Int = puntosList.size

    fun appendItems(newItems: ArrayList<SitiosItem>) {
        puntosList.clear()
        puntosList.addAll(newItems)
        notifyDataSetChanged()

    }


    class PoiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var nombreTextView: TextView = itemView.findViewById(R.id.nombre_card_text_view)
        private var descripcionTextView: TextView = itemView.findViewById(R.id.descripcion_card_text_view)
        private var calificacionTextView: TextView = itemView.findViewById(R.id.calificacion_card_text_view)
        //private var descripcionCardTextView: TextView = itemView.findViewById(R.id.descripcion_card_text_view)
        private var imagenImageView: ImageView = itemView.findViewById(R.id.imagen_card_image_view)
        private var calificacionRating: RatingBar = itemView.findViewById(R.id.ratingBarCardl)

/*
        val ratingBar: RatingBar = RatingBar(this).apply {
            setIsIndicator(true)
            numStars = 5
            stepSize = 1.0f
            rating = randomBetweenOneAndFive()
            max = 5
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { gravity = CENTER }
        }

        val ratingBarIndexInLayout = 3
        root.addView(ratingBar, ratingBarIndexInLayout)
    }
*/

    fun bind(sitios: SitiosItem) {

            nombreTextView.text = sitios.nombre
            descripcionTextView.text = sitios.descripcion
            calificacionTextView.text = sitios.calificacion.toString()
            calificacionRating.rating = sitios.calificacion
            Picasso.get().load(sitios.urlFoto).into(imagenImageView)

        }
    }
}