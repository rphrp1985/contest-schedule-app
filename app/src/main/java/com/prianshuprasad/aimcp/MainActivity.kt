package com.prianshuprasad.aimcp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.spinner_item.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"Developed by Prianshu Prasad",Toast.LENGTH_LONG).show()

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val spinner: Spinner = findViewById(R.id.sitelist)

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.sitearray,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        val intent =Intent(this,ScheduleviewActivity::class.java)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {




              when(position){

                  1-> {
                      intent.putExtra("url","https://kontests.net/api/v1/code_chef")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }


                  2-> {
                      intent.putExtra("url","https://kontests.net/api/v1/codeforces")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  3-> {
                      intent.putExtra("url","https://kontests.net/api/v1/top_coder")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  4-> {
                      intent.putExtra("url","https://kontests.net/api/v1/at_coder")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  5-> {
                      intent.putExtra("url","https://kontests.net/api/v1/cs_academy")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  6-> {
                      intent.putExtra("url","https://kontests.net/api/v1/hacker_rank")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  7-> {
                      intent.putExtra("url","https://kontests.net/api/v1/hacker_earth")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  8-> {
                      intent.putExtra("url","https://kontests.net/api/v1/kick_start")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }

                  9-> {
                      intent.putExtra("url","https://kontests.net/api/v1/leet_code")
                      intent.putExtra("siteindex","$position")
                      startActivity(intent)
                  }



              }















//                Toast.makeText(this@MainActivity," $position",Toast.LENGTH_LONG).show()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                // sometimes you need nothing here
            }
        }

//






    }




    fun buttonclicked(view: View){
        val intent =Intent(this,ScheduleviewActivity::class.java)
        intent.putExtra("url","https://kontests.net/api/v1/all")
        var temp=0
        intent.putExtra("siteindex","$temp")
        startActivity(intent)

    }


}