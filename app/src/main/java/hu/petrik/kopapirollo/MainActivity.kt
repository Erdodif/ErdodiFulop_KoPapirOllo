package hu.petrik.kopapirollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import hu.petrik.kopapirollo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    private var gep = 0;
    private var random : Random = Random()

    private fun kepModosit(mire : Int, mit :ImageView){
        when (mire) {
            0 ->
                mit.setImageResource(R.drawable.rock)
            1 ->
                mit.setImageResource(R.drawable.paper)
            2 ->
                mit.setImageResource(R.drawable.scissors)
        }
    }

    private fun jatszma(mi: Int) {
        kepModosit(mi, bind.imageViewFelhasznalo)
        gep = random.nextInt(3)
        kepModosit(gep,bind.imageViewGep)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)
        bind.imageViewDontesKo.setOnClickListener{
            jatszma(0);
        }
        bind.imageViewDontesPapir.setOnClickListener{
            jatszma(1);
        }
        bind.imageViewDontesOllo.setOnClickListener{
            jatszma(2);
        }
    }
}