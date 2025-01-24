package com.shruti.constrainttask

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.shruti.constrainttask.databinding.ActivitySpinnerPosBinding
import com.shruti.constrainttask.databinding.CustomDialogBinding
import com.shruti.constrainttask.databinding.CustomDialogNumBinding

class SpinnerPosActivity : AppCompatActivity() {
    lateinit var binding : ActivitySpinnerPosBinding
    var list = arrayListOf<String>("Item1" , "Item2" , "Item3", "Item4", "Item5")
    private var isSpinnerInitialized = false
    var count = 0
    lateinit var arrayAdapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerPosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = arrayAdapter
        binding.spinner.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

//                if (isSpinnerInitialized) {
//                    customNum(true, p2)
//                } else {
//                    isSpinnerInitialized = true // Set the flag after the first initialization
//                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        })

        binding.btnadd.setOnClickListener {
            customShow()
        }
        binding.btnpos.setOnClickListener {
            customNum()
        }
    }
    fun customShow(){
        val dialog = Dialog(this)
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogBinding.btnadd.setOnClickListener {
            val input = dialogBinding.etname.text.toString()
            if (dialogBinding.etname.text.isNullOrEmpty()){
                dialogBinding.etname.error = "Enter name"
            }
            else{
                list.add(input)
                arrayAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
        dialog.show()
    }
    fun customNum(){
        val dialog = Dialog(this)
        val dialogBinding = CustomDialogNumBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogBinding.btnupdate.setOnClickListener {
            val numberText = dialogBinding.etnumb.text.toString()
            val name = dialogBinding.etname.text.toString()
            if (name.isEmpty()) {
                dialogBinding.etname.error = "Enter name"
                return@setOnClickListener
            }
            if (numberText.isEmpty()) {
                dialogBinding.etnumb.error = "Enter number"
                return@setOnClickListener
            }
            val number = numberText.toIntOrNull()
            if (number == null) {
                dialogBinding.etnumb.error = "Enter a valid number"
                return@setOnClickListener
            }
            if (number < 0 || number >= list.size) {
                dialogBinding.etnumb.error = "Index out of range"
                return@setOnClickListener
            }
            list[number] = name
            arrayAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        dialogBinding.btndelete.setOnClickListener {
            val numberText = dialogBinding.etnumb.text.toString()
            if (numberText.isEmpty()) {
                dialogBinding.etnumb.error = "Enter a number to delete"
            } else {
                val number = numberText.toIntOrNull()
                if (number != null) {
                    delete(number)
                    dialog.dismiss()
                } else {
                    dialogBinding.etnumb.error = "Enter a valid number to delete"
                }
            }
        }

        dialog.show()
    }


    fun delete(position: Int) {
        list.removeAt(position)
        arrayAdapter.notifyDataSetChanged()

    }


}


