package com.hfad.lettervault

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.hfad.lettervault.databinding.FragmentAddLetterBinding
import java.text.DateFormat
import java.util.*
import android.app.*
import android.graphics.Color
import android.os.Build
import android.view.*
import android.widget.Toast
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.hfad.lettervault.data.Letter
import com.hfad.lettervault.dialog.DatePickerFragmentDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddLetterFragment : Fragment(), DatePickerFragmentDialog.OnExpiryDateSelectedListener {

    private lateinit var binding: FragmentAddLetterBinding
    private val viewModel: LetterViewModel by viewModels()
    private var saveExpiryDate: Date? = null
    private var saveExpiryTime: Long? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddLetterBinding.inflate(layoutInflater, container, false)
        val toolbar = binding.addFragToolBar
        val calendar = Calendar.getInstance()
        val currentDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)

        toolbar.title = "Created on: $currentDate"
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_close)
        setHasOptionsMenu(true)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        createChannel(
            getString(R.string.letter_notification_channel_id),
            getString(R.string.letter_notification_channel_name)
        )
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.hfad.lettervault.R.menu.add_letter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.hfad.lettervault.R.id.expiry_date -> showDatePickerDialog(binding.root)
            com.hfad.lettervault.R.id.done -> addLetter()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addLetter() {
//        val calendar = Calendar.getInstance()
//        val currentDate: String = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)

        val title = binding.addLetterTitle.text.toString()
        val subTitle = binding.addLetterDescription.text.toString()
        if (title.isEmpty() && subTitle.isEmpty() && saveExpiryDate != null) {
            Toast.makeText(context, "Please enter Title and Subtitle", Toast.LENGTH_SHORT).show()
        }
        val letter = Letter(title, subTitle, created = Date()  , expiryDate = saveExpiryDate  )
        viewModel.upsert(letter)
        findNavController().navigate(R.id.homeFragment)

//        val context = requireContext().applicationContext
//        val pendingIntent = NavDeepLinkBuilder(context)
//            .setGraph(com.hfad.lettervault.R.navigation.nav_graph)
//            .setDestination(com.hfad.lettervault.R.id.addLetterFragment)
//            .createPendingIntent()

    }

    private fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragmentDialog(this)

        newFragment.show(childFragmentManager, "datePicker")
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Time to open the letter"

            val notificationManager =
                requireActivity().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    override fun onExpiryDateSelected(expiryDate: Date) {
        this.saveExpiryDate = expiryDate
    }

    override fun onExpiryTimeSelected(expiryTime: Long) {
        this.saveExpiryTime = expiryTime
    }
}