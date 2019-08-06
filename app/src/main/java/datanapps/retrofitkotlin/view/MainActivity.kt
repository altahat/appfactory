package datanapps.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import datanapps.retrofitkotlin.services.network.RetrofitEventListener
import datanapps.retrofitkotlin.services.network.ApiUserRestClient
import datanapps.retrofitkotlin.services.users.model.BaseUser
import datanapps.retrofitkotlin.services.users.model.User
import retrofit2.Call
//import android.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(datanapps.retrofitkotlin.R.layout.activity_main)
        getUserListData();
    }

    internal fun getUserListData() {

        ApiUserRestClient.instance.getUserList( object : RetrofitEventListener {
            override  fun onSuccess(call: Call<*>, response: Any) {
                if (response is BaseUser) {
                    Log.d("NETWORK", "Request is made, response count: ${response.data!!.size}")
                    setRecycleViewList(response.data!!);
                }
            }

            override fun onError(call: Call<*>, t: Throwable) {
                Log.e("NETWORK", "Unable to Connect to ${t.message}")
            }
        })
    }

    private fun setRecycleViewList(userList: List<User>) {
        val recyclerView = findViewById<RecyclerView>(datanapps.retrofitkotlin.R.id.recycle_view_book)
        val mAdapter = UsersAdapter(this@MainActivity, userList)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = mAdapter
    }
}
