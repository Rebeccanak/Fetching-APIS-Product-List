package com.becky.mycoolshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.becky.mycoolshop.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var productAdapter: ProductAdapter
    var productsList: List<Products> = emptyList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        productAdapter = productAdapter
//        productAdapter = ProductAdapter(emptyList())
//        binding.rvproducts.adapter = productAdapter
    }

    override fun onResume() {
        super.onResume()
        getProducts()
    }
    fun getProducts(){
        val apiClient = ApiClient.buildClient(ApiInterface ::class.java)
         val request = apiClient.getProducts()
        request.enqueue(object : Callback<ProductResponse>{
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>) {

                if (response.isSuccessful) {

                    var productResponse = response.body()?.products
                    var productsAdapter = ProductAdapter(productResponse ?: emptyList())
                    binding.rvproducts.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.rvproducts.adapter = productsAdapter



                    Toast
                        .makeText(
                            baseContext,
                            "fetched ${productsList?.size} products",
                            Toast.LENGTH_LONG
                        )
                        .show()

            }
       else{
                    Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG)
                        .show()
                }


                }
            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()


            }
        })
    }

}