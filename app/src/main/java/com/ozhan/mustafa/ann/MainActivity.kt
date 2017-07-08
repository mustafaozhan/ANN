package com.ozhan.mustafa.ann

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var mETxtPosition: EditText? = null
    private var mETxtAngel: EditText? = null
    private var mTxtViewResult: TextView? = null
    private var mNeuron: FuzzyNeuron? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mBttnCalculate = findViewById(R.id.button_calculate) as Button

        mETxtPosition = findViewById(R.id.et_position) as EditText
        mETxtAngel = findViewById(R.id.et_angel) as EditText


        mTxtViewResult = findViewById(R.id.tv_result) as TextView


        //        mNeuron = new FuzzyNeuron(-15, 180);
        //        mNeuron.calculateOutput();
        //        Log.d("Position", " " + mNeuron.getPosition());
        //        Log.d("Position Left", " " + mNeuron.getPositionLeft());
        //        Log.d("Position Middle", " " + mNeuron.getPositionMiddle());
        //        Log.d("Position Right", " " + mNeuron.getPositionRight());
        //        Log.d("-------", "------------------------------------------------");
        //        Log.d("Angel", " " + mNeuron.getAngel());
        //        Log.d("Angel East", " " + mNeuron.getAngelEast());
        //        Log.d("Angel North", " " + mNeuron.getAngelNorth());
        //        Log.d("Angel West", " " + mNeuron.getAngelWest());
        //        Log.d("-------", "------------------------------------------------");
        //        mNeuron.calculateOutput();
        //        Log.d("Outputt", " " + mNeuron.getOutput());

        //        for(double i=-15;i<15.1;i=i+0.1){
        //            for(int j=0;j<181;j++){
        //                mNeuron=new FuzzyNeuron(i,j);
        //                mNeuron.calculateOutput();
        //                if( mNeuron.getOutput()>0.5)
        //                Log.d("Outputt", " " + mNeuron.getOutput());
        //            }
        //        }


        mBttnCalculate.setOnClickListener {
            if (mETxtPosition!!.text.toString().isEmpty() || mETxtAngel!!.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Please fill the requered places", Toast.LENGTH_SHORT).show()

            } else {
                mNeuron = FuzzyNeuron(java.lang.Double.parseDouble(mETxtPosition!!.text.toString()), java.lang.Double.parseDouble(mETxtAngel!!.text.toString()))

                mNeuron!!.calculateOutput()
                mTxtViewResult!!.text = "Car got the center of way at and Tires Angel now 0° in  ${mNeuron!!.iteration} iterations"

            }
            mETxtAngel!!.setText("")
            mETxtPosition!!.setText("")
        }

    }


}
