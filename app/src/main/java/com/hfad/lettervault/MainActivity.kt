package com.hfad.lettervault

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.hfad.lettervault.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolBar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_letter -> Toast.makeText(this,"You clicked on add contact", Toast.LENGTH_SHORT).show()
            R.id.sort -> Toast.makeText(this,"You clicked on add sort", Toast.LENGTH_SHORT).show()
            R.id.sort_by_all -> Toast.makeText(this,"You clicked on sort by all", Toast.LENGTH_SHORT).show()
            R.id.sort_by_recent -> Toast.makeText(this,"You clicked on sort by recent", Toast.LENGTH_SHORT).show()
            R.id.sort_by_future -> Toast.makeText(this,"You clicked on sort by future", Toast.LENGTH_SHORT).show()
            R.id.more -> Toast.makeText(this,"You clicked on sort by more", Toast.LENGTH_SHORT).show()
            R.id.notification -> Toast.makeText(this,"You clicked on sort by notification", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}