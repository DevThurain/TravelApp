package com.codingtest.travelapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.codingtest.travelapp.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    companion object{
        fun getIntent(context: Context) : Intent{
            return Intent(context, RegisterActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners(){
        binding.edtFirstName.afterTextChanged {
            if(it.isEmpty()){
                binding.edtFirstName.error = "First Name Empty"
            }
        }

        binding.edtLastName.afterTextChanged {
            if(it.isEmpty()){
                binding.edtLastName.error = "Last Name Empty"
            }
        }

        binding.edtEmail.afterTextChanged {
            if(it.isEmpty()){
                binding.edtEmail.error = "Email Empty"
            }
            if(!it.isValidEmail()){
                binding.edtEmail.error = "Invalid Email Format"
            }
        }

        binding.edtNationality.afterTextChanged {
            if(it.isEmpty()){
                binding.edtNationality.error = "Nationality Empty"
            }
        }

        binding.edtCountry.afterTextChanged {
            if(it.isEmpty()){
                binding.edtCountry.error = "Country of Resident Empty"
            }
        }

        binding.llDob.setOnClickListener {
            showCalender()
        }

        binding.btnCreateAccountNow.setOnClickListener {
            if(validate()){
                Toast.makeText(this,"Validated.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun showCalender(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                binding.tvDob.text = "$dayOfMonth/$monthOfYear/$year"

        }, year, month, day)
        dpd.show()
    }

    private fun validate() : Boolean {
        var isValidated = true
        if (binding.edtFirstName.text.isEmpty()) {
            binding.edtFirstName.error = "First Name Empty"
            isValidated = false
        }
        if (binding.edtLastName.text.isEmpty()) {
            binding.edtLastName.error = "Last Name Empty"
            isValidated = false
        }
        if (binding.edtEmail.text.isEmpty()) {
            binding.edtEmail.error = "Email Empty"
            isValidated = false
        }
        if (!binding.edtEmail.text.toString().isValidEmail()) {
            binding.edtEmail.error = "Invalid Email Format"
            isValidated = false
        }

        if(binding.tvDob.text.toString() == "DD/MM/YYYY"){
            Toast.makeText(this,"Please enter dob.", Toast.LENGTH_SHORT).show()
            isValidated = false
        }

        if (binding.edtNationality.text.isEmpty()) {
            binding.edtNationality.error = "Nationality Empty"
            isValidated = false
        }

        if (binding.edtCountry.text.isEmpty()) {
            binding.edtCountry.error = "Country of Resident Empty"
            isValidated = false
        }


        return isValidated
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun String?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()