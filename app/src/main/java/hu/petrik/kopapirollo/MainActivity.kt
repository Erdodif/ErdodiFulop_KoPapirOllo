package hu.petrik.kopapirollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import hu.petrik.kopapirollo.databinding.ActivityMainBinding
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    private var dontetlenek = 0
    private var gepElet = 3
    private var felhasznaloElet = 3
    private var gep = 0
    private var random: Random = Random()

    private fun eletAllit(elet: Int, jatekos: Boolean) {
        val elet3 = if (elet < 3) R.drawable.heart1 else R.drawable.heart2
        val elet2 = if (elet < 2) R.drawable.heart1 else R.drawable.heart2
        val elet1 = if (elet < 1) R.drawable.heart1 else R.drawable.heart2
        if (jatekos) {
            bind.imageFelhasznalo1.setImageResource(elet1)
            bind.imageFelhasznalo2.setImageResource(elet2)
            bind.imageFelhasznalo3.setImageResource(elet3)
        } else {
            bind.imageGep1.setImageResource(elet1)
            bind.imageGep2.setImageResource(elet2)
            bind.imageGep3.setImageResource(elet3)
        }
    }

    private fun kepModosit(mire: Int, mit: ImageView) {
        when (mire) {
            0 ->
                mit.setImageResource(R.drawable.rock)
            1 ->
                mit.setImageResource(R.drawable.paper)
            2 ->
                mit.setImageResource(R.drawable.scissors)
        }
    }

    private fun init(){
        dontetlenek = 0
        gepElet = 3
        felhasznaloElet = 3
        gep = 0
        eletAllit(3, true)
        eletAllit(3, false)
        val kiad = getString(R.string.dontetlenek)
        bind.textViewDontetlenek.setText(kiad)
    }
    private fun ujra(gyozelem: Boolean) {
        val fejlec = if (gyozelem) "Győzelem" else "Vereség"
        var dialogus = AlertDialog.Builder(this)
        dialogus.setTitle(fejlec)
        dialogus.setMessage("Szeretne Újból játszani?")
        dialogus.setPositiveButton("Igen") { _, _ ->
            init()
        }
        dialogus.setNegativeButton("Nem") { _, _ ->
            exitProcess(0)
        }
        dialogus.setOnDismissListener {
            init()
        }
        dialogus.show()
    }

    private fun jatszma(mi: Int) {
        kepModosit(mi, bind.imageViewFelhasznalo)
        gep = random.nextInt(3)
        kepModosit(gep, bind.imageViewGep)
        val eredmeny = mi - gep
        if (eredmeny == 0) {
            dontetlenek++
            val kiad = "${getString(R.string.dontetlenek)} ${dontetlenek}"
            bind.textViewDontetlenek.setText(kiad)
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show()
        } else if (eredmeny == 1 || eredmeny == -2) {
            //játékos győzelem az alábbiak szerint:
            //p1 - k0 = 1   Győzelem
            //o2 - k0 = 2
            //k0 - o2 = -2  Győzelem
            //p1 - o2 = -1
            //k0 - p1 = -1
            //o2 - p1 = 1   Győzelem
            gepElet--
            eletAllit(gepElet, false)
            if (gepElet == 0) {
                Toast.makeText(
                    this, "Győzelem a gépek felett",
                    Toast.LENGTH_SHORT
                ).show()
                ujra(true)
            } else {
                Toast.makeText(this, "Gőzelem", Toast.LENGTH_SHORT).show()
            }
        } else {
            //gép győzelem
            felhasznaloElet--
            eletAllit(felhasznaloElet, true)
            if (felhasznaloElet == 0) {
                Toast.makeText(
                    this, "Vereség a gép ellen",
                    Toast.LENGTH_SHORT
                ).show()
                ujra(false)
            } else {
                Toast.makeText(this, "Vereség", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)
        bind.imageViewDontesKo.setOnClickListener {
            jatszma(0);
        }
        bind.imageViewDontesPapir.setOnClickListener {
            jatszma(1);
        }
        bind.imageViewDontesOllo.setOnClickListener {
            jatszma(2);
        }
    }
}