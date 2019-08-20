package info.camposha.krecyclerviewdiffutil

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val stars = listOf(
        "Aldebaran",
        "Rigel",
        "Canopus",
        "Bellatrix",
        "Polaris",
        "Regulus",
        "VY Canis Majoris",
        "UY Scuti",
        "Deneb",
        "Wezen",
        "Arcturus"
    )
    private val galaxies = listOf(
        "Circunus",
        "Milky Way",
        "Andromeda",
        "StarBust",
        "Sombrero",
        "Pinwheel",
        "Cartwheel",
        "Large Magellonic Cloud",
        "Hoags Object",
        "Centaurus A",
        "Leo",
        "Virgo Stellar Stream"
    )

    private fun show(name: String) {
        Toast.makeText(this, "$name clicked!", Toast.LENGTH_SHORT).show()
    }

    private fun useNotifyDataSet() {
        val adapter = MyNotifyDataSetAdapter(stars)
        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager= LinearLayoutManager(this)
        myRecyclerView.setHasFixedSize(true)

        adapter.ItemClickListener = { position, name ->
            show(name)
        }
        adapter.updateList(stars)

    }

    private fun useDiffUtil() {
        val adapter = MyDiffUtilAdapter(galaxies)
        myRecyclerView.adapter = adapter
        myRecyclerView.layoutManager= GridLayoutManager(this,2)
        myRecyclerView.setHasFixedSize(true)

        adapter.ItemClickListener = { position, name ->
            show( name)
        }
        adapter.updateList(galaxies)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        useNotifyDataSet()

        starsBtn.setOnClickListener {
            useNotifyDataSet()
            starsBtn.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            galaxiesBtn.setBackgroundColor(resources.getColor(R.color.colorAccent))
        }
        galaxiesBtn.setOnClickListener {
            useDiffUtil()
            galaxiesBtn.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            starsBtn.setBackgroundColor(resources.getColor(R.color.colorAccent))

        }
    }


}
//end