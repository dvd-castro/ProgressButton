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
        val btn = this.findViewById<androidx.cardview.widget.CardView>(R.id.btnProgress)
        val bar = btn.findViewById<ProgressBar>(R.id.progressBar)

        btn.setOnClickListener {
            Toast.makeText(applicationContext, "Pressione e segure para aceitar ", Toast.LENGTH_SHORT).show()
        }
        
        //adicionando evento de clique longo ao botão
        btn.setOnLongClickListener {
            object : CountDownTimer(2000, 1) {
                override fun onTick(millisUntilFinished: Long) {
                    if (!btn.isPressed){
                        bar.progress = 0
                    }else{
                        bar.progress = 1 + bar.progress
                    }
                }
                override fun onFinish() {
                    val vibrar = getSystemService(VIBRATOR_SERVICE) as Vibrator
                    val milliseconds: Long = 1000
                    vibrar.vibrate(milliseconds)
                }
            }.start()
            true
        }


    }

}