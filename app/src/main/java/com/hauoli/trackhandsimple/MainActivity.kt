package com.hauoli.trackhandsimple;

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.hauoli.trackhand.HauoliConst
import com.hauoli.trackhand.HauoliTracker
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.String

import android.widget.TextView;
import android.widget.Toast
import com.hauoli.trackhand.HauoliTracker.getDist
import com.hauoli.trackhand.HauoliTracker.getDists


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        abc.text = "Hello abc!"
        name.text="dists[0]"
        name2.setText("gest")
        //abc.setText("hello abc")
        //abc.setText("hello abc")
   //     val textView: TextView = findViewById(R.id.abc) as TextView
        //textView.setOnClickListener {
    //        textView.text = "hello abc"
        //}

        //setSupportActionBar(toolbar)

        //fab.setOnClickListener { view ->
        //    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show()
        //}


        /***********************
         * Initialization
         ***********************/
        /***********************
         * Initialization
        */
        val nDim = 2
        val nSpk = 1
        val nMic = 1
// set the initial distance from hand to mic = 100mm
        // set the initial distance from hand to mic = 100mm
        val initPos = doubleArrayOf(0.0, -100.0)
// the position of the speaker on phone: [0, 0]
        // the position of the speaker on phone: [0, 0]
        val spkPos = doubleArrayOf(0.0, 0.0)
// the position of the microhpone on phone: [0, 0]
        // the position of the microhpone on phone: [0, 0]
        val micPos = doubleArrayOf(0.0, 0.0)
        Log.d("Hauoli", "hauoli init ");
        HauoliTracker.initTracker(nSpk, nMic, initPos, spkPos, micPos)

        /***********************
         * Other Configuration (don't need to change)
         ***********************/

        HauoliTracker.setDistEstMethod(HauoliConst.METHOD_TAP.toInt())
        HauoliTracker.setAudioSource(HauoliConst.AUDIO_SOURCE_JAVA.toInt())
        HauoliTracker.setSeq(30, 240, 6000)
        HauoliTracker.setMinTap(10)
        HauoliTracker.setMaxTap(250)
        HauoliTracker.setTapStep(20)
        //HauoliTracker.setAllowReset(false)
        HauoliTracker.setAutoThrd(HauoliConst.TAP_SMOOTH_THRD_FIX.toInt(), 0.00001)
        HauoliTracker.setRecordAudio(false)
        HauoliTracker.setSaveDist(false)
        HauoliTracker.setUseFile(false)
        HauoliTracker.enableGesture(true)


        /***********************
         * Start tracking
         ***********************/
        val dists = DoubleArray(nMic)
        Log.d("Hauoli", "before start")
        HauoliTracker.start()
        Log.d("Hauoli", "after start")
        var i = 0;

        //name.setText("22")
        //abc.setText(i.toString()+"22")
       // abc2.setText(dists[0].toInt())
        //abc2.setText(gest.toString() + "")

        //while (i < 10) {
        while (true) {
                val state = HauoliTracker.getState()
                if (state != HauoliConst.TRACKER_STATE_TRACKING.toInt()) {
                    Log.d("Hauoli", "Calibrating: Keep Stationary")
                    continue
                }
                val gest = HauoliTracker.getGesture()
                val gestCnt = HauoliTracker.getGestCnt()
                //val dist = HauoliTracker.getDist()

                HauoliTracker.getDists(dists)
                //val dist0 = dists[0]
                //abc.setText(dists[0].toInt())
                //abc2.setText(gest)
               // Toast.makeText(applicationContext, gest.toString()+"ddddd",
               // Toast.LENGTH_LONG).show();
            val myToast = Toast.makeText(applicationContext,gest.toString()+"ddddd",Toast.LENGTH_SHORT)
            myToast.setGravity(Gravity.LEFT,200,200)
            myToast.show()
            Log.d(
                    "Hauoli",
                    String.format(
                        "gesture = %d (%d), est dist = %f",
                        gest,
                        gestCnt,
                        dists[0]
                    )
                )
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                    Log.e("Error", "run: $e")
                }
                i++;
            }


            /***********************
         * Stop tracking
         */
        HauoliTracker.stop()
//*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
