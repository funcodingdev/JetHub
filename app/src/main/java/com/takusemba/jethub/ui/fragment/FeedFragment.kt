package com.takusemba.jethub.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.takusemba.jethub.R
import com.takusemba.jethub.databinding.FragmentFeedBinding
import com.takusemba.jethub.ui.adapter.FeedAdapter
import com.takusemba.jethub.viewmodel.FeedViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FeedFragment : DaggerFragment(R.layout.fragment_feed) {

  companion object {

    fun newInstance() = FeedFragment()
  }

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val feedViewModel: FeedViewModel by viewModels(
    ownerProducer = { requireParentFragment() },
    factoryProducer = { viewModelFactory }
  )

  private val userViewModel: UserViewModel by activityViewModels { viewModelFactory }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding = FragmentFeedBinding.bind(view)

    val feedAdapter = FeedAdapter(userViewModel, feedViewModel, this)
    binding.pager.adapter = feedAdapter
    binding.tabLayout.setupWithViewPager(binding.pager)
  }
}