package id.asad.tugas2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import id.asad.tugas2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Game"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val judul = intent.getStringExtra("judul")
        val peringkat = intent.getDoubleExtra("peringkat", 0.0)
        val rilis = intent.getStringExtra("rilis")
        val image = intent.getStringExtra("gambar")

        binding.tvJudulDetail.text = judul
        binding.tvPeringkatDetail.text = peringkat.toString()
        binding.tvRilisDetail.text = rilis
        Glide.with(this).load(image).error(R.drawable.ic_launcher_background).into(binding.imgDetail)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}