package com.decadev.autocheck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decadev.autocheck.Adapter.MediaAdapter
import com.decadev.autocheck.ViewModel.MediaViewModel
import com.decadev.autocheck.databinding.FragmentMediaBinding


class MediaFragment : Fragment() {

    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!

    lateinit var mediaRecyclerView: RecyclerView
    lateinit var mediaAdapter: MediaAdapter

    private val mediaViewModel: MediaViewModel by viewModels()

    private val args: MediaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMediaBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val carMediaId = args.carMediaId
        mediaViewModel.getCarMedia(carMediaId)

        val mediaRecyclerView = binding.mediaFragmentRecyclerView

        /** The carmedia is being observed after the "getCarMedia()" is being called above*/
        mediaViewModel.carMediaDetails.observe(requireActivity(), Observer {
            mediaAdapter = MediaAdapter(it)
            mediaRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            mediaRecyclerView.adapter = mediaAdapter
        })



        return view
    }


}