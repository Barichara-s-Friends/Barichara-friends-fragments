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
import com.edw88.baricharafriends.ui.main.MainActivity
import com.edw88.baricharafriends.model.SitiosItem
import com.edw88.baricharafriends.ui.splash.SplashFragmentDirections

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listViewModel : ListViewModel
    private lateinit var poiAdapter: PoiAdapter
    private var listPoi: ArrayList<SitiosItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        return listBinding.root
    }

    override fun onStart() {
        super.onStart()
        listViewModel.checkUserConnected()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()

      //  listViewModel.loadMockJson(context?.assets?.open("poi.json"))
      //  listViewModel.getSitioFromServer()
        listBinding.progressBar.visibility = View.VISIBLE
        listViewModel.getSitioFromFirebase()
        listViewModel.onUserLoggedIn.observe(viewLifecycleOwner, { result ->
            onUserLoogedInSubscribe(result)
        })

        listViewModel.onListpoiLoaded.observe(viewLifecycleOwner,{ result ->
            onListpoidLoadedSubscribe(result)
        })

        poiAdapter =
            PoiAdapter(listPoi, onItemClicked = { this.onPoiClicked(it) })

        listBinding.poiRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = poiAdapter
            setHasFixedSize(false)
        }

    }

    private fun onUserLoogedInSubscribe(result: Boolean?) {
        result?.let { isUserLoggedIn ->
            if (!isUserLoggedIn)
                findNavController().navigate(ListFragmentDirections.actionNavigationListToLoginFragment())
        }

    }

    private fun onListpoidLoadedSubscribe(result: ArrayList<SitiosItem>?) {
        result?.let { listPoi ->
            poiAdapter.appendItems(listPoi)
            listBinding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun onPoiClicked(sitios: SitiosItem) {
       // findNavController().navigate(ListFragmentDirections.actionListFragmentToSettingsFragment())
       findNavController().navigate(
           ListFragmentDirections.actionListFragmentToDetailFragment(
               sitio = sitios
           )
       )

    }

   /*  private fun loadMockJson(): ArrayList<SitiosItem> {
        val poiString: String = context?.assets?.open("poi.json")?.bufferedReader().use { it!!.readText() }
        val gson = Gson()
        val poiList = gson.fromJson(poiString, Sitios::class.java)
        return poiList
    } */
}