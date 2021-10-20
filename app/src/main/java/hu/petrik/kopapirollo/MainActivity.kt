package hu.petrik.kopapirollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import hu.petrik.kopapirollo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    private fun jatszma(mi: Int) {
        when (mi) {
            0 ->
                bind.imageViewFelhasznalo.setImageResource(R.drawable.rock)
            1 ->
                bind.imageViewFelhasznalo.setImageResource(R.drawable.paper)
            2 ->
                bind.imageViewFelhasznalo.setImageResource(R.drawable.scissors)
        }
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