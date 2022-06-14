package com.example.moviesdirector.presenter.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesdirector.R
import com.example.moviesdirector.databinding.ActivityAboutScreenBinding
import com.example.moviesdirector.domain.utils.Constants

class AboutScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAboutScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickMenu()
    }

    private fun clickMenu() {
        binding.included.ibMenu.setOnClickListener {
            val popupMenu = PopupMenu(this, binding.included.ibMenu)
            popupMenu.menuInflater.inflate(R.menu.itens_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
                if (menuItem.itemId == R.id.it_sobre) {
                    Toast.makeText(this, getString(R.string.screen_about), Toast.LENGTH_LONG).show()
                } else if (menuItem.itemId == R.id.netflix) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(Constants.NETFLIX)
                    startActivity(intent)
                }
                true
            }
            popupMenu.show()
        }
    }
}
