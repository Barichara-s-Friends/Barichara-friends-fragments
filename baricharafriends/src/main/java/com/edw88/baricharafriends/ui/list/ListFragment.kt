package com.edw88.baricharafriends.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.edw88.baricharafriends.databinding.FragmentListBinding
import com.edw88.baricharafriends.main.MainActivity
import com.edw88.baricharafriends.model.SitiosItem


class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listViewModel : ListViewModel
    private lateinit var poiAdapter: PoiAdapter
    private var listPoi: ArrayList<SitiosItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]

        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()

//        listViewModel.loadMockJson(context?.assets?.open("poi.json"))

        listViewModel.getSitiosFromServer()
        listBinding.progressBar.visibility = View.VISIBLE

        listViewModel.onListpoiLoaded.observe(viewLifecycleOwner,{ result ->
            onListpoiLoadedSubscribe(result)
        })

        poiAdapter = PoiAdapter(listPoi, onItemClicked = { this.onPoiClicked(it) })

        listBinding.poiRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = poiAdapter
            setHasFixedSize(false)
        }

    }

    private fun onListpoiLoadedSubscribe(result: ArrayList<SitiosItem>?) {
        result?.let { listPoi ->
            poiAdapter.appendItems(listPoi)
            listBinding.progressBar.visibility = View.INVISIBLE
            /*
            this.listPoi = listPoi
            poiAdapter.notifyDataSetChanged()*/

        }
    }

    private fun onPoiClicked(sitios: SitiosItem) {
       // findNavController().navigate(ListFragmentDirections.actionListFragmentToSettingsFragment())
       findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(sitio = sitios))

    }

   /*  private fun loadMockJson(): ArrayList<SitiosItem> {
        val poiString: String = context?.assets?.open("poi.json")?.bufferedReader().use { it!!.readText() }
        val gson = Gson()
        val poiList = gson.fromJson(poiString, Sitios::class.java)
        return poiList
    } */
}