package com.example.appforphone

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment_test)

        val fragmentA = FragmentA()
        val fragmentB = FragmentB()
        val button = findViewById<Button>(R.id.buttonFragmentA)
        val fragmentASet = true;

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragmentA)
            commit()
        }

        button.setOnClickListener{
            val currentFragment = supportFragmentManager.findFragmentById(R.id.flFragment)
            if(currentFragment==fragmentA)
                replaceFragment(fragmentB)
            else
                replaceFragment(fragmentA)
        }
    }
    fun replaceFragment(id: Fragment){
        supportFragmentManager.beginTransaction().apply {

            replace(R.id.flFragment, id)
            commit()
        }
    }
}