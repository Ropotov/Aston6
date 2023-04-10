package com.example.aston6.screens.listFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.aston6.databinding.FragmentListBinding
import com.example.aston6.repository.ListItem
import com.example.aston6.screens.Navigator

class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var newItem: ListItem
    private var listItem = ArrayList<ListItem>()
    private var searchText = ""
    private val adapter by lazy { ContactAdapter() }
    private val viewModel by lazy { ViewModelProvider(this)[ListViewModel::class.java] }

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
        val navigator = requireActivity() as Navigator
        initRecyclerView()
        setListeners(navigator)
        setObservers()
    }

    private fun search(searchText: String) {
        this.searchText = searchText
        val filteredList: ArrayList<ListItem> = ArrayList()
        for (item in listItem) {
            if (item.firstName.lowercase()
                    .contains(searchText.lowercase()) || item.lastName.lowercase()
                    .contains(searchText.lowercase())
            ) {
                filteredList.add(item)
            }
            adapter.submitList(filteredList)
        }
    }

    private fun setObservers() {
        viewModel.list.observe(viewLifecycleOwner) {
            listItem = it
            search(searchText)
        }
    }

    private fun setListeners(navigator: Navigator) {
        adapter.onLongClickListener = {
            viewModel.deleteItem(it)
        }
        adapter.onClickListener = {
            navigator.navigateToDetailFragment(it)
        }
        binding.searchView.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL))
        val itemAnimator = binding.recyclerView.itemAnimator
        if (itemAnimator is DefaultItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            search(newText)
        }
        return false
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}
