package com.prianshuprasad.aimcp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.scheduleview.*
import org.json.JSONArray
import org.json.JSONObject

class ScheduleviewActivity : AppCompatActivity() {

    private lateinit var mAdapter:contestAdapter
    private var mtoday:Int=0
    private var url:String=""
    private var siteidex:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.scheduleview)
   retrybutton.setVisibility(View.GONE)
        url= intent.getStringExtra("url").toString()
//        mtoday=0;0
   siteidex=intent.getStringExtra("siteindex").toString()
todaybutton.setText("View Toady's Contest")

//         todaybutton.setOnClickListener(ti:view.setOn)

        emptyimage.setVisibility(View.GONE)


       recycleview.layoutManager= LinearLayoutManager(this)

        if (url != null) {
            fetchcondata(url)
        }

        mAdapter=  contestAdapter(this)
        recycleview.adapter= mAdapter






    }



    fun fetchcondata( url:String)
    {
        retrybutton.setVisibility(View.GONE)
        recycleview.setVisibility(View.VISIBLE)
//        val url="https://saurav.tech/NewsAPI/everything/bbc-news.json"

        val jore = JsonArrayRequest(
            Request.Method.GET, url,null,
            { response ->

                val jsonarray: JSONArray = response
                val contestArray= ArrayList<contest>()
//                Log.d("my log", "${jsonarray.length()}");


                for(i in 0 until jsonarray.length())
                {
                    val tempjsonobjct= jsonarray.getJSONObject(i)
                    val tempnews= contest(
                        tempjsonobjct.getString("name" ),
                        tempjsonobjct.getString("url" ),
                        tempjsonobjct.getString("start_time" ),
                        tempjsonobjct.getString("end_time" ),


                            "rp"


                        ,
                        tempjsonobjct.getString("in_24_hours" )
//                         "rp", "rp","rp","rp"


                    )

                    if(siteidex=="0")
                        tempnews.site= tempjsonobjct.getString("site")
                    else
                    {
                        when(siteidex){

                            "1"-> tempnews.site= "CodeChef"
                            "2"-> tempnews.site= "CodeForces"
                            "3"-> tempnews.site= "TopCoder"
                            "4"-> tempnews.site= "AtCoder"
                            "5"-> tempnews.site= "CS Academy"
                            "6"-> tempnews.site= "HackerRank"
                            "7"-> tempnews.site= "HackerEarth"
                            "8"-> tempnews.site= "Kick Start"
                            "9"-> tempnews.site= "LeetCode"



                        }




                    }



                   if(mtoday==1){

                       if(tempnews.today=="Yes")
                           contestArray.add(tempnews)

                   }else
                     contestArray.add(tempnews)
                }

                if(contestArray.isEmpty()) {
                    emptyimage.setVisibility(View.VISIBLE)
                    emptyimage.setImageResource(R.drawable.nocontestimage)
                }
                mAdapter.updatenews(contestArray)
//                 return NewsArray

            },




            {

            emptyimage.setImageResource(R.drawable.internetissueimage)

                retrybutton.setVisibility(View.VISIBLE)
                recycleview.setVisibility(View.GONE)
                emptyimage.setVisibility(View.VISIBLE)
                Toast.makeText(this,"Something went wrong!", Toast.LENGTH_LONG).show()
            })
        MySingleton.getInstance(this).addToRequestQueue(jore)
// Add the request to the RequestQueue.
//        queue.add(jore)












    }

    fun onitemclicked(contest: contest) {

//        Toast.makeText(this, "cliked",Toast.LENGTH_LONG).show()
       val coustomtabs= CustomTabsIntent.Builder()
        val coustomtabintent= coustomtabs.build()
     coustomtabintent.launchUrl(this,Uri.parse(contest.url))


    }

    fun todaybuttonclicked(view: View){

        emptyimage.setVisibility(View.GONE)
        if(mtoday==0){
        mtoday=1
        fetchcondata(url)
        todaybutton.setText("VIEW ALL CONTESTS")
        }
        else
        {
            mtoday=0;
            fetchcondata(url)
            todaybutton.setText("View Toady's Contest")

        }

    }


    fun retrybuttonclicked(view: View){
//       val intent= Intent(this,ScheduleviewActivity::class.java)
//       intent.putExtra("url",url)
//       intent.putExtra("siteindex",siteidex)
//       startActivity(intent)
        fetchcondata(url)
        retrybutton.setVisibility(View.GONE)
        emptyimage.setVisibility(View.GONE)

    }


}