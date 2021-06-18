package com.alfilippov.junetechsample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alfilippov.junetechsample.ListCatAdapter
import com.alfilippov.junetechsample.R
import com.alfilippov.junetechsample.data.ApiHelper
import com.alfilippov.junetechsample.data.RetrofitBuilder
import com.alfilippov.junetechsample.data.User
import com.alfilippov.junetechsample.dto.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListNameFragment : Fragment(R.layout.fragment_list) {

    @Inject
    lateinit var apiHelper: ApiHelper
    private lateinit var viewModel: ListViewModel
    private lateinit var adapter: ListCatAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_list)
        setUpViewModel()
        setUpUi()
        setupObservers()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(
            this,
            ListViewModelFactory(apiHelper)
        ).get(ListViewModel::class.java)
    }

    private fun setUpUi() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = ListCatAdapter() { userClick -> clickItemUser(userClick) }
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        resource.data?.let { users -> adapter.submitList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        Toast.makeText(this.context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun clickItemUser(user: User) {
        val action = ListNameFragmentDirections.nextAction(user.year)
        findNavController().navigate(action)
    }
}