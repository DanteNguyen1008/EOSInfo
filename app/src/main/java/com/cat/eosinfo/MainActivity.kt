package com.cat.eosinfo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cat.eosinfo.adapter.BlockAdapter
import com.cat.eosinfo.databinding.ActivityMainBinding
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : BlockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        this.binding.viewModel!!.newBlockLiveData.observe(this, Observer { this.onNewBlockAdded(it) })

        rl_list.layoutManager = LinearLayoutManager(this)
        this.adapter = BlockAdapter(object : BlockAdapter.BlockViewListener {
            override fun onClicked(block: Block, pos: Int) {
                this@MainActivity.onBlockClicked(block)
            }
        })

        rl_list.adapter = this.adapter
        setSupportActionBar(my_toolbar)
    }

    /**
     * When new block get added
     *
     * @param block Block?
     */
    private fun onNewBlockAdded(block: Block?) {
        this.adapter.add(block)
        rl_list.scrollToPosition(this.adapter.itemCount - 1)
    }

    private fun onBlockClicked(selectedBlock: Block?) {
        // Open detail screen
        Intent(this, DetailActivity::class.java).also {
            it.putExtra(DetailActivity.EXTRA_SELECTED_BLOCK_DETAIL_VIEW, selectedBlock)
            startActivity(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item!!.itemId) {
        R.id.action_sync -> {
            // Clear data when syncing start
            this.adapter.clearData()
            this.binding.viewModel!!.sync()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}
