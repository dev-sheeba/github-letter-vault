package com.hfad.lettervault

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hfad.lettervault.databinding.FragmentAddLetterBinding
import com.hfad.lettervault.databinding.FragmentDetailLetterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailLetterFragment : Fragment() {

    private lateinit var binding: FragmentDetailLetterBinding
    private val viewModel: LetterViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailLetterBinding.inflate(layoutInflater, container, false)
        val toolbar = binding.detailAddFragToolBar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_close)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_letter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.detail_done -> findNavController().navigate(R.id.homeFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}