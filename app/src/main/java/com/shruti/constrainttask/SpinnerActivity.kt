package com.shruti.constrainttask

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.shruti.constrainttask.databinding.ActivitySpinnerBinding
import com.shruti.constrainttask.databinding.CustomDialogBinding

class SpinnerActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpinnerBinding
    val list = mutableListOf<String>()
    lateinit var adapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              //  System.out.println("spinner is initilizes $isSpinnerInitialized")
                    Toast.makeText(
                        this@SpinnerActivity,
                        "${list[p2]} is updated",
                        Toast.LENGTH_SHORT
                    ).show()
                    customDialog(true, p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        binding.fab.setOnClickListener {
            customDialog(false,-1)
        }
    }
    fun customDialog(isUpdated :Boolean,position:Int){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        if (position >= 0 && isUpdated){
            System.out.println("is updated $isUpdated")
            dialogBinding.etname.setText(list[position])
        }
        dialogBinding.btnadd.setOnClickListener {
            if (dialogBinding.etname.text.isNullOrEmpty()){
                dialogBinding.etname.error = "Enter name"
                Toast.makeText(this@SpinnerActivity,"Enter name", Toast.LENGTH_SHORT).show()
            }else{
                val inputData = dialogBinding.etname.text.toString()
                if(position >= 0 && isUpdated){
                    list[position] = inputData
                    System.out.println("is updated $isUpdated")
                }else{
                    list.add(inputData)
                    System.out.println("is updated $isUpdated")
                    Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show()
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
        dialogBinding.btndelete.setOnClickListener {
            if(position >= 0){
                list.removeAt(position)
                Toast.makeText(this, "item is deleted", Toast.LENGTH_SHORT).show()
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}