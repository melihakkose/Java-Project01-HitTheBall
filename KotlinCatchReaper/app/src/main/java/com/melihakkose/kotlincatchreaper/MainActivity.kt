package com.melihakkose.kotlincatchreaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var score=0

    //Gorselleri sakladigimiz dizi
    var imageArray = ArrayList<ImageView>()

    var handler=Handler(Looper.myLooper()!!)
    var runnable= Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ImageArray
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        hideImages()


        //CountDown Timer
        object : CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                textViewTime.text="Time: "+millisUntilFinished/1000
            }

            override fun onFinish() {
                textViewTime.text="Time: 0"

                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                //Alert
                var alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes"){dialog,which->
                    //Restart

                    val intent= intent
                    finish()
                    startActivity(intent)

                }
                alert.setNegativeButton("No"){dialog,which->
                    //Finish
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()

                }
                alert.show()
            }

        }.start()


    }

    fun hideImages(){

        runnable=object :Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random = java.util.Random()
                val randomIndex =random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)
            }

        }

        handler.post(runnable)
    }

    fun clickScore(view: View){
        score=score+1
        textViewScore.text="Score: $score"
    }
}