package com.manyan.mystoryapp.view.dashboard.home

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.manyan.mystoryapp.R
import com.manyan.mystoryapp.ViewModelFactory
import com.manyan.mystoryapp.databinding.HomeFragmentBinding
import com.manyan.mystoryapp.view.dashboard.profile.AboutDev


class HomeFragment : Fragment() {

    private lateinit var factory: ViewModelFactory
    private val viewModel : HomeViewModel by activityViewModels{factory}
    private var _homebinding: HomeFragmentBinding? = null
    private val binding get() = _homebinding!!
    private var hideNavView = false
    private lateinit var adapter : ListStoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _homebinding = HomeFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
//        new
//        initUI()
        myProfile()
    }

//    private fun initUI() {
//        binding.rvStory.layoutManager = LinearLayoutManager(activity)
//        binding.tvGreetingName.text = getString(string.label_greeting_user, pref.getUserName)
//    }

    private fun myProfile() {
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(activity, AboutDev::class.java))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        view?.let { super.onViewCreated(it, savedInstanceState) }
        factory = ViewModelFactory.getInstance(requireActivity())
        binding.refreshLayout.isRefreshing = true
        binding.refreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
        fetchUserStories()
        initAction()
        val navView = requireActivity().findViewById<View>(R.id.nav_view)
        if (navView != null) {
            hideShowBottomNavigation(navView)
        }
    }
    private fun hideShowBottomNavigation(navView: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.rvStory.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                val height = (navView.height + 32).toFloat()

                if (!hideNavView && scrollY > oldScrollY) {
                    hideNavView = true
                    ObjectAnimator.ofFloat(navView, "translationY", 0f, height).apply {
                        duration = 200
                        start()
                    }
                }

                if (hideNavView && scrollY < oldScrollY) {
                    hideNavView = false
                    ObjectAnimator.ofFloat(navView, "translationY", height, 0f).apply {
                        duration = 200
                        start()
                    }
                }
            }
        }
    }

    private fun fetchUserStories() {
        viewModel.getUserToken().observe(viewLifecycleOwner) {
            binding.refreshLayout.isRefreshing = true
            viewModel.getUserStories(it)
            initRecycler()
            Log.e("Home", "Token: $it")

        }
    }
    private fun initRecycler(){
        viewModel.getName().observe(viewLifecycleOwner){ name ->
            binding.tvGreetingName.text = name
        }

        binding.rvStory.layoutManager = LinearLayoutManager(activity)
        adapter = ListStoryAdapter()
        viewModel.userStories.observe(viewLifecycleOwner){
            binding.refreshLayout.isRefreshing = false
            adapter.submitData(lifecycle,it)
        }
        binding.rvStory.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateListStoryAdapter { adapter.retry() }
        )

    }
    private fun initAction(){
        binding.toolbar.apply {
            inflateMenu(R.menu.nav_setting)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menuSettings -> {
                        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                        true
                    }
                    R.id.aboutDeveloper -> {
                        startActivity(Intent(activity, AboutDev::class.java))
                        true
                    }
                    else -> false
                }
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _homebinding = null
        adapter.submitData(lifecycle, PagingData.empty())
    }

}