package id.asad.tugas2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.asad.tugas2.model.ResultsItem

class GameAdapter(val results: List<ResultsItem?>?) : RecyclerView.Adapter<GameAdapter.MyViewHolder>() {

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val imageGame = view.findViewById<ImageView>(R.id.item_image)
        val judulGame = view.findViewById<TextView>(R.id.item_tv_judul)
        val peringkatGame = view.findViewById<TextView>(R.id.item_tv_peringkat)
        val rilisGame = view.findViewById<TextView>(R.id.item_tv_tgl_rilis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_row_game, parent, false)
        return MyViewHolder(layout)
    }

    override fun getItemCount(): Int {
        if (results != null){
            return results.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.judulGame.text = results?.get(position)?.name
        holder.peringkatGame.text = results?.get(position)?.rating.toString()
        holder.rilisGame.text = results?.get(position)?.released
        Glide.with(holder.itemView)
            .load(results?.get(position)?.backgroundImage)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageGame)

        val context = holder.itemView.context
        holder.itemView.setOnClickListener {

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("judul", results?.get(position)?.name)
            intent.putExtra("gambar", results?.get(position)?.backgroundImage)
            intent.putExtra("peringkat", results?.get(position)?.rating)
            intent.putExtra("rilis", results?.get(position)?.released)
            context.startActivity(intent)
        }
    }
}