package com.edw88.baricharafriends.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.edw88.baricharafriends.databinding.FragmentDetailBinding
import com.edw88.baricharafriends.main.MainActivity
import com.edw88.baricharafriends.model.SitiosItem


class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity?)?.showIcon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)


        return detailBinding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sitios = args.sitio
        val cal = sitios.calificacion

        with(detailBinding) {
            cementerioTextView.text = sitios.nombre
            cementerioDescripcionTextView.text = sitios.descripcionlarga
            com.squareup.picasso.Picasso.get().load(sitios.urlFoto).into(imageView5)
            ratingBarDetalle.rating = sitios.calificacion

            mapButton.setOnClickListener{
                findNavController().navigate(DetailFragmentDirections.actionNavigationDetailToMapsFragment(sitio = sitios))
            }
        }

    }
}





