package com.example.recycler_view2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.recycler_view2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ThingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = ThingAdapter()

        val onItemClick = { thing: Thing ->
            Toast.makeText(this, "Item ${thing.name} is like", Toast.LENGTH_SHORT).show()
            thing.likeElement = true
            adapter.submitList(things.toList())
        }

        val onDeleteClick = { thing: Thing ->

            Toast.makeText(this, "Item ${thing.name} deleted", Toast.LENGTH_SHORT).show()
            things.remove(thing)
            adapter.submitList(things.toList())
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)

        adapter.onItemClick = onItemClick
        adapter.onDeleteClick = onDeleteClick

        binding.recyclerView.adapter = adapter
        adapter.submitList(things)

    }

}

