package com.example.theocafeteria.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theocafeteria.data.repository.ProductRepository
import com.example.theocafeteria.databinding.FragmentProductListBinding
import com.example.theocafeteria.viewmodel.CartViewModel
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProductListFragment : Fragment() {


    private val cartViewModel: CartViewModel by activityViewModels()

    @Inject
    lateinit var repository: ProductRepository

    private lateinit var binding: FragmentProductListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val category = arguments?.getString("category") ?: ""

        val products = repository.getProductsByCategory(category)

        val adapter = ProductAdapter(products) { product ->
            cartViewModel.addItem(product)
        }

        binding.recyclerProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerProducts.adapter = adapter
    }

    companion object {
        fun newInstance(category: String): ProductListFragment {
            val fragment = ProductListFragment()
            val bundle = Bundle()
            bundle.putString("category", category)
            fragment.arguments = bundle
            return fragment
        }
    }
}