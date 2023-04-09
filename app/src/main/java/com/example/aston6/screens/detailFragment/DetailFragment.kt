package com.example.aston6.screens.detailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.aston6.databinding.FragmentDetailBinding
import com.example.aston6.repository.ListItem

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private var item: ListItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable("listItem")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.save.setOnClickListener {
            clickSave()
        }
    }

    private fun clickSave() {
        val newItem = item?.copy(
            firstName = binding.firstName.text.toString(),
            lastName = binding.lastName.text.toString(),
            phone = binding.phone.text.toString()
        )
        setFragmentResult("requestKey", bundleOf("listItem" to newItem))
        parentFragmentManager.popBackStack()
    }

    private fun initViews() {
        binding.firstName.setText(item?.firstName ?: "")
        binding.lastName.setText(item?.lastName ?: "")
        binding.phone.setText(item?.phone ?: "")
    }


    companion object {
        @JvmStatic
        fun newInstance(item: ListItem) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("listItem", item)
                }
            }
    }
}