package com.example.utsabkharel.chatserver

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.utsabkharel.chatserver.MySocket.deregisterObserver
import com.example.utsabkharel.chatserver.R.id.*
import com.example.utsabkharel.chatserver.R.menu.option_menu
import kotlinx.android.synthetic.main.botton_navigation.*

class NavigationActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.botton_navigation)

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ChatFragment()).commit()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                chat -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, ChatFragment()).commit()
                    Toast.makeText(this, "chat selected", Toast.LENGTH_SHORT).show()
                }

                users -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, UsersFragment()).commit()
                    Toast.makeText(this, "users selected", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(option_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){

          logout -> {
              val command = ":quit"
              MySocket.send(command)
              var intent = Intent(this, LoginActivity::class.java)
              startActivity(intent)
              finish()


            Toast.makeText(this, "You're logged out", Toast.LENGTH_LONG).show()
            true
        }
            help ->{
              var  intent =  Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(intent)

                Toast.makeText(this, "Google will help you", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)

        }
    }

}


