package com.example.aston6.screens.listFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.aston6.screens.detailFragment.DetailFragment
import com.example.aston6.repository.ListItem
import com.example.aston6.R
import com.example.aston6.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }
    private val adapter by lazy {
        ContactAdapter()
    }
    private lateinit var newItem: ListItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { _, bundle ->
            newItem = bundle.getParcelable("listItem")!!
            viewModel.editItem(newItem)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        adapter.onLongClickListener = {
            viewModel.deleteItem(it)
        }
        adapter.onClickListener = {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(it))
                .addToBackStack("null")
                .commit()
        }

        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.listItem = it
        })
    }

    private fun initRecyclerView() {
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.razd))
        recyclerView.addItemDecoration(dividerItemDecoration)
        val itemAnimator = binding.recyclerView.itemAnimator
        if (itemAnimator is DefaultItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}