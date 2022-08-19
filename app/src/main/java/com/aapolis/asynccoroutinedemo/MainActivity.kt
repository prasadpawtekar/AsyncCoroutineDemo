package com.aapolis.asynccoroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.aapolis.asynccoroutinedemo.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowBalance.setOnClickListener {
            showBalance()
        }
    }

    val eh = CoroutineExceptionHandler{
        context, t ->
        // code to handle exception

    }

    private fun showBalance() {
        val axisAcno = binding.etAxisAcno.text.toString()
        val hdfcAcno = binding.etHdfcAcno.text.toString()

        lifecycleScope.launch(Dispatchers.IO+eh) {
            val axisAsync: Deferred<Int> = lifecycleScope.async {
                val response: Response<BankBalance> = ApiService.getInstance().axisBalance(axisAcno)
                var result = 0
                if(response.isSuccessful) {
                    result = response?.body()?.balance ?: 0
                }
                result   // return value of async execution

            }
            val hdfcAsync: Deferred<Int> = lifecycleScope.async {
                val response: Response<BankBalance> = ApiService.getInstance().hdfcBalance(hdfcAcno)
                var result = 0
                if(response.isSuccessful) {
                    result = response?.body()?.balance ?: 0
                }
                result   // return value of async execution
            }
            val total = axisAsync.await() + hdfcAsync.await()

            withContext(Dispatchers.Main) {
                binding.tvTotalBalance.text = "Total balance = $total"
            }
        }
    }
}