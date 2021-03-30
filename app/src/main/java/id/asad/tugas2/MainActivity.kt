package id.asad.tugas2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.asad.tugas2.api.ApiConfig
import id.asad.tugas2.databinding.ActivityMainBinding
import id.asad.tugas2.model.ResponseGame
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Data Games"

        ApiConfig.service().getDataGames().enqueue(object : Callback<ResponseGame> {
            override fun onFailure(call: Call<ResponseGame>, t: Throwable) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = t.localizedMessage
            }
            override fun onResponse(call: Call<ResponseGame>, response: Response<ResponseGame>) {
                if (response.isSuccessful){

                    val responseGame = response.body()
                    val result = responseGame?.results

                    val gameAdapter = GameAdapter(result)

                    binding.rvGame.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        gameAdapter.notifyDataSetChanged()
                        adapter = gameAdapter
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_profile){
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


}