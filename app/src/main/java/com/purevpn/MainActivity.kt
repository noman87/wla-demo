package com.purevpn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.purevpn.database.ApplicationDatabase
import com.purevpn.database.Database
import com.purevpn.database.DatabaseRepository
import com.purevpn.service.users.User
import com.purevpn.service.users.UserService

class MainActivity : AppCompatActivity() {


    //val users: Users by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // Log.e("result", "" + users.getResult())

        val database:Database = ApplicationDatabase.getInstance(this)?.let {
            DatabaseRepository(it) }!!

        var service: User = UserService(database)

        //service.getAllUsers()



    }
}
