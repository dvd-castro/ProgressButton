package br.com.davidcastro.progressbutton

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Vibrator
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Referenciando os elementos a serem manipulados
//      //Referencing the elements to be manipulated
        val btn = this.findViewById<androidx.cardview.widget.CardView>(R.id.btnProgress)
        val bar = btn.findViewById<ProgressBar>(R.id.progressBar)
        val toVibrate = getSystemService(VIBRATOR_SERVICE) as Vibrator

        //adicionando evento de clique longo ao botão
        // adding a long click event to the button
        btn.setOnLongClickListener {
            object : CountDownTimer(2000, 1) {
                override fun onTick(millisUntilFinished: Long) {
                    //Essa condição verificará se o botão ainda está sendo pressionado
                    // This condition will check if the button is still being pressed
                    if (!btn.isPressed){
                        bar.progress = 0
                        toVibrate.cancel()
                        //Cancelar a contagem | Cancel the count
                        cancel()
                    }else{
                        bar.progress = 1 + bar.progress
                        toVibrate.vibrate(millisUntilFinished)
                    }
                }
                override fun onFinish() {
                    //TODO("Action after finish | Ação ao finalizar")
                    bar.progress = 0
                    Toast.makeText(applicationContext, "Done!", Toast.LENGTH_SHORT).show()
                }
            }.start()
            true
        }


    }

}