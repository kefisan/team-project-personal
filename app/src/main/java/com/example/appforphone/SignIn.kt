package com.example.appforphone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sing_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val registerNowLabel = findViewById<TextView>(R.id.register_now)
        registerNowLabel.setOnClickListener {
            Log.d("Onboarding", "Register now pressed")

            val goToRegisterIntent = Intent(this, RegisterNow::class.java)
            startActivity(goToRegisterIntent)
        }

        val loginButton = findViewById<Button>(R.id.login_button)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordText)
        val emailEditText = findViewById<TextInputEditText>(R.id.usernameText)
        val emailLayout = findViewById<TextInputLayout>(R.id.username)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordSignIn)
        val credentialsManager = CredentialsManager()
        val loginErrorPopup = Snackbar.make(loginButton,"Wrong email or password",10000)

        loginButton.setOnClickListener{
            Log.d("Credentials","Login button pressed")
            val inputPassword = passwordEditText.text.toString()
            val inputEmail = emailEditText.text.toString()

            if(!credentialsManager.isPasswordValid(inputPassword)){
                passwordLayout.error = "Invalid Password"
                return@setOnClickListener
            }
            else{
                passwordLayout.error = null
            }

            if(!credentialsManager.isEmailValid(inputEmail)){
                emailLayout.error = "Invalid Email"
                return@setOnClickListener
            }
            else{
                emailLayout.error = null
            }

            if(!credentialsManager.doesPasswordMatchEmail(inputEmail,inputPassword)) {
                loginErrorPopup.show()
            }
            else{
                startActivity(Intent(this@SignIn,MainActivity::class.java))
            }
        }
    }
}