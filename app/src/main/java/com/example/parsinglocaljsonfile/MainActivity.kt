package com.example.parsinglocaljsonfile

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    lateinit var Adapter:RecyclerViewAdapter
    private lateinit var photos:ArrayList<Image>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photos = arrayListOf()

        rv = findViewById(R.id.rv)
        Adapter = RecyclerViewAdapter(this, photos)
        rv.adapter = Adapter
        rv.layoutManager = LinearLayoutManager(this)

        Log.d("tag", "onCreate")
      //  val file =  application.assets.open("data.json").bufferedReader().use { it.readText() }
     //   showImages(readFile())
        showImages()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showImages() {
        var json: String?
        try {
            val inputStream: InputStream = assets.open("data.json")
            Log.d("tag", "InputStream")
           // json = applicationContext.assets.open("data.json").bufferedReader().use { it.readText() }
            json = inputStream.bufferedReader().use { it.readText() }
            Log.d("tag", "json")

            val jsonArr = JSONArray(json)
            for (i in 0 until jsonArr.length()) {
                val url = jsonArr.getJSONObject(i).getString("url")
                photos.add(Image(url))
            }
            Adapter.notifyDataSetChanged()
        } catch (e: IOException) {
            Log.d("tag","error")
        }
    }

}