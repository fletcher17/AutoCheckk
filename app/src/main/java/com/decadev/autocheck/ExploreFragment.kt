package com.decadev.autocheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decadev.autocheck.Adapter.ExplorerCarImageAdapter
import com.decadev.autocheck.Adapter.ExplorerLogoAdapter
import com.decadev.autocheck.ClickListener.CarClickListener
import com.decadev.autocheck.Model.Cars.CarsClass
import com.decadev.autocheck.Model.Cars.Result
import com.decadev.autocheck.Utils.Constants
import com.decadev.autocheck.ViewModel.ExploreViewModel
import com.decadev.autocheck.ViewModel.ProductViewModel
import com.decadev.autocheck.databinding.FragmentExploreBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList


class ExploreFragment : Fragment(), CarClickListener {

    lateinit var logosRecyclerview: RecyclerView
    lateinit var explorerLogoAdapter: ExplorerLogoAdapter
    lateinit var explorerCarAdapter: ExplorerCarImageAdapter
    lateinit var carImageRecyclerView: RecyclerView

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    val viewModel: ExploreViewModel by viewModels()
    val productViewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        logosRecyclerview = binding.carLogoRecyclerview
        carImageRecyclerView = binding.carImagesRecyclerview

        /** all logos is being observed from the view model and result is being projected to the recyclerview*/
        viewModel.allLogos.observe(requireActivity(), Observer {
            explorerLogoAdapter = ExplorerLogoAdapter(it.makeList)
            logosRecyclerview.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            logosRecyclerview.adapter = explorerLogoAdapter
        })

        viewModel.allCars.observe(requireActivity(), Observer {
            explorerCarAdapter = ExplorerCarImageAdapter(it.result, this)
            carImageRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            carImageRecyclerView.adapter = explorerCarAdapter
        })

        /**Search Bar searches through the the data as text is being typed*/
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchBar.clearFocus()
                if (query != null) {
                    startSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    startSearch(newText)
                }
                return false
            }

        })

        return view
    }


    /** This function searches the car list data using the the title of the name/title of the car*/
    fun startSearch(text: String) {
        if (Constants.CarListData != null) {
            val resultOfCars = ArrayList<Result>()
            for (cars in Constants.CarListData!!.result) {
                if (cars.title.lowercase().contains(text.lowercase()))
                    resultOfCars.add(cars)
                explorerCarAdapter = ExplorerCarImageAdapter(resultOfCars, this)
                carImageRecyclerView.adapter = explorerCarAdapter
            }
        } else {
            Snackbar.make(binding.root, "That model isn't available", Snackbar.LENGTH_LONG).show()
        }
    }

    /**When an item is clicked, the item id is passed to the product fragment to get the details of the clicked item*/
    override fun onClickItem(detailItem: Result) {
        productViewModel.getCarDetails(detailItem.id)

        val action = ExploreFragmentDirections.actionExploreFragmentToProductFragment(detailItem.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
