package com.enesaksoy.goldapp.view

import androidx.fragment.app.Fragment
import com.enesaksoy.goldapp.adapter.IslemAdapter
import com.enesaksoy.goldapp.adapter.ListAdapter
import com.enesaksoy.goldapp.repo.GoldRepository
import javax.inject.Inject

class FragmentFactory@Inject constructor(
    private val adapter: ListAdapter,
    private val islemAdapter : IslemAdapter
) : androidx.fragment.app.FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            FirmaFragment::class.java.name -> FirmaFragment(adapter)
            HomeFragment::class.java.name -> HomeFragment()
            ToplamIscilikFragment::class.java.name -> ToplamIscilikFragment()
            AddFragment::class.java.name -> AddFragment()
            IslemFragment::class.java.name -> IslemFragment(islemAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}