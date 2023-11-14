package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = checkNotNull(_binding)
    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): B

    private var _listener: ButtonListener.Base? = null
    protected val buttonListener get() = checkNotNull(_listener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _listener = ButtonListener.Base(parentFragmentManager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _listener = null
    }
}