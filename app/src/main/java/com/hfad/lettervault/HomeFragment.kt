package com.hfad.lettervault

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.lettervault.adapter.LetterAdapter
import com.hfad.lettervault.data.Letter
import com.hfad.lettervault.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: LetterViewModel by viewModels()
    private val myAdapter: LetterAdapter by lazy { LetterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val toolbar = binding.toolBar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        recyclerView()
        observeAllLetters()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_letter -> findNavController().navigate(R.id.addLetterFragment)
            R.id.sort_by_all -> {
                viewModel.onSortOrderSelected(SortOrder.BY_ALL)
            }
            R.id.sort_by_recent -> {
                viewModel.onSortOrderSelected(SortOrder.BY_RECENT)
            }
            R.id.sort_by_future -> {
                viewModel.onSortOrderSelected(SortOrder.BY_REMINDER)
            }
            R.id.notification -> Toast.makeText(
                requireContext(),
                "You clicked on more",
                Toast.LENGTH_SHORT
            ).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun recyclerView() {
        binding.apply {
            letterRecyclerView.apply {
                adapter = myAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }

    private fun observeAllLetters() {
        viewModel.letters.observe(viewLifecycleOwner) {
            myAdapter.submitList(it)
        }
    }
}