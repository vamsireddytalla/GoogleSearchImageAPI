package com.example.googlesearchengineapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import com.example.googlefilechunks.data.ApiInterface
import com.example.googlefilechunks.data.ServiceInstance
import com.example.googlesearchengineapi.adapter.CustomAdapter
import com.example.googlesearchengineapi.databinding.ActivityMainBinding
import com.example.googlesearchengineapi.models.GooglePhotosSearchResponse
import com.example.googlesearchengineapi.models.Image
import com.example.googlesearchengineapi.models.Item
import com.example.googlesearchengineapi.utills.UtilKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson;
import org.json.JSONArray


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity()
{
    lateinit var binding: ActivityMainBinding
    lateinit var apiInterface: ApiInterface
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.b1.setOnClickListener {
            apiInterface = ServiceInstance.getInstance().create(ApiInterface::class.java)
           val result = binding.editText.text.toString().trim()
            if (result.isNotEmpty()){
                makeServerCall(searchQuery = result)
            }else{
                binding.et1.error="empty"
            }
        }
    }

    fun makeServerCall(searchQuery:String)
    {
        val selectedId = binding.radioGroup.getCheckedRadioButtonId()
        val selectedRadioButton = findViewById<RadioButton>(selectedId)
        val position = binding.radioGroup.indexOfChild(selectedRadioButton)
      GlobalScope.launch(Dispatchers.IO) {
          apiInterface.searchItem(UtilKeys.apikeys[position],UtilKeys.cxKeys[position],searchQuery,"image","1").enqueue(object :Callback<GooglePhotosSearchResponse>{
              override fun onResponse(call: Call<GooglePhotosSearchResponse>, response: Response<GooglePhotosSearchResponse>) {
                  if(response.isSuccessful){
                      Toast.makeText(this@MainActivity, "success", Toast.LENGTH_SHORT).show()
                      val result=response.body()
                      result?.let {
                          val customAdapter = CustomAdapter(it.items,this@MainActivity)
                          binding.rcv.adapter=customAdapter
                      }

                      println(response.body().toString())
                  }
              }
              override fun onFailure(call: Call<GooglePhotosSearchResponse>, t: Throwable) {
                  Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
              }


          })
      }
    }

}