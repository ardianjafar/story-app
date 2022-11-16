package com.manyan.mystoryapp.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.manyan.mystoryapp.R
import com.manyan.mystoryapp.databinding.FragmentStoryBinding
import com.manyan.mystoryapp.ui.AboutDev
import com.manyan.mystoryapp.ui.AddStoryActivity
import com.manyan.mystoryapp.ui.adapter.LoadingStateAdapter
import com.manyan.mystoryapp.ui.adapter.StoryAdapter
import com.manyan.mystoryapp.utils.PrefsManager
import com.manyan.mystoryapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@ExperimentalPagingApi
class StoryFragment : Fragment() {
    private var _binding: FragmentStoryBinding? = null
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var prefsManager: PrefsManager
    private lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStoryBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefsManager = PrefsManager(requireContext())
        storyAdapter = StoryAdapter()
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding?.toolbar)
            supportActionBar?.title = resources.getString(R.string.mystoryapp)
        }
        binding?.toolbar?.apply {
            setTitleTextColor(Color.WHITE)
            setSubtitleTextColor(Color.WHITE)
        }
        setHasOptionsMenu(true)
        setPagingData()
        getData()
        binding?.fabAddStory?.setOnClickListener {
            startActivity(Intent(requireContext(), AddStoryActivity::class.java))
        }
    }

    private fun getData() {
        viewModel.getStories(prefsManager.token).observe(viewLifecycleOwner) {
            storyAdapter.submitData(lifecycle, it)
        }
    }

    private fun setPagingData() {
        binding?.apply {
            rvStory.setHasFixedSize(true)
            rvStory.layoutManager = LinearLayoutManager(requireContext())
            rvStory.adapter = storyAdapter.withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter { storyAdapter.retry() },
                footer = LoadingStateAdapter { storyAdapter.retry() }
            )
            swipeRefresh.setOnRefreshListener {
                storyAdapter.refresh()
                swipeRefresh.isRefreshing = false
                rvStory.visibility = View.GONE
            }
            btnTry.setOnClickListener {
                storyAdapter.retry()
            }
        }

        storyAdapter.addLoadStateListener {
            binding?.apply {
                progressBar.isVisible = it.source.refresh is LoadState.Loading
                rvStory.isVisible = it.source.refresh is LoadState.NotLoading
                tvError.isVisible = it.source.refresh is LoadState.Error
                btnTry.isVisible = it.source.refresh is LoadState.Error

                //not found
                if (it.source.refresh is LoadState.NotLoading &&
                    it.append.endOfPaginationReached &&
                    storyAdapter.itemCount < 1
                ) {
                    tvNotFound.isVisible = true
                    rvStory.isVisible = false
                } else {
                    tvNotFound.isVisible = false
                    rvStory.isVisible = true
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_setting, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menuSettings -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            R.id.aboutDeveloper -> {
                startActivity(Intent(activity, AboutDev::class.java))
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}