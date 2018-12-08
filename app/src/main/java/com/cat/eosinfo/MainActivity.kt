package com.cat.eosinfo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cat.eosinfo.databinding.ActivityMainBinding
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        rl_list.layoutManager = LinearLayoutManager(this)
        setSupportActionBar(my_toolbar)
    }

    override fun onStart() {
        super.onStart()
        this.binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        this.binding.viewModel!!.scrollTo.observe(this, Observer { this.scrollTo(it) })
        this.binding.viewModel!!.blockClicked.observe(this, Observer { this.onBlockClicked(it) })
    }

    private fun onBlockClicked(selectedBlock: Block?) {
        // Open detail screen
        Intent(this, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_SELECTED_BLOCK_DETAIL_VIEW, selectedBlock)
            startActivity(it)
        }
    }

    private fun scrollTo(pos : Int) {
        rl_list.scrollToPosition(pos)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item!!.itemId) {
        R.id.action_sync -> {
            this.binding.viewModel!!.sync()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}
