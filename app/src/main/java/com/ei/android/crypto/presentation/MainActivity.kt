package com.ei.android.crypto.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.crypto.R
import com.ei.android.crypto.databinding.ActivityMainBinding
import com.ei.android.crypto.domain.CoinInfo
import com.ei.android.crypto.presentation.adapters.CoinInfoAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object :CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinCLick(coinPriceInfo: CoinInfo) {
                if(binding.fragmentContainer == null){
                    launchDetailActivity(coinPriceInfo.fromSymbol)
                }else{
                    launchDetailFragment(coinPriceInfo.fromSymbol)
                }
            }
        }
        binding.coinListRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun launchDetailActivity(fromSymbol:String){
        val intent = CoinDetailActivity.newIntent(
            this@MainActivity,
            fromSymbol)
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol:String){
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }
}