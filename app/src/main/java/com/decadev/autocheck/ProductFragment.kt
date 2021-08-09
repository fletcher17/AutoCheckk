package com.decadev.autocheck

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.decadev.autocheck.Model.Cars.CarsClass
import com.decadev.autocheck.ViewModel.ProductViewModel
import com.decadev.autocheck.databinding.FragmentProductBinding


class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by viewModels()
    private val args: ProductFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        val view = binding.root


        val cardId = args.cardDetail
        productViewModel.getCarDetails(cardId)

        /**car details is being observed and result is placed in the respective views*/
        productViewModel.carDetails.observe(requireActivity(), Observer {
            binding.productFragmentTitle.text =  it.model.name
            binding.productFragmentModelTextView.text =  it.model.make.name
            binding.productFragmentStateTextView.text = it.state
            binding.productFragmentPriceTextView.text = getString(R.string.model_id, it.model.id)
            binding.productFragmentButton.text = getString(R.string.price_in_figure, it.price)
            binding.productFragmentBodyType.text = it.bodyType.name
            binding.oldPriceTextView.text = getString(R.string.old_price, it.marketplaceOldPrice)
            binding.newPriceTextView.text = getString(R.string.new_price, it.marketplacePrice)
            binding.mileageTextView.text = getString(R.string.total_mileage, it.mileage)
            binding.productFragmentEngineNumberTextView.text = it.engineNumber
            binding.productFragmentExteriorColorTextView.text = it.exteriorColor


            Glide.with(view).load(it.imageUrl).apply(
                RequestOptions().placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            ).into(binding.productFragmentCarImageView)
        })

        binding.productFragmentAddToCartButton.setOnClickListener {
         val action = ProductFragmentDirections.actionProductFragmentToMediaFragment2(cardId)
            findNavController().navigate(action)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}