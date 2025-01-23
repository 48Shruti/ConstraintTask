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
    val list = arrayListOf<String>("Item1", "Item2")
    lateinit var arrayAdapter : ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,list)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = arrayAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val dialog = Dialog(this@SpinnerActivity)
                val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
                dialog.setContentView(dialogBinding.root)
                dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                dialogBinding.etname.setText(list[p2])
                dialogBinding.btnadd.setOnClickListener {
                    if (dialogBinding.etname.text.isNullOrEmpty()){
                        dialogBinding.etname.error = "Enter name"
                        Toast.makeText(this@SpinnerActivity,"Enter name", Toast.LENGTH_SHORT).show()
                    }else{
                        val inputData = dialogBinding.etname.text.toString()
                        list.set(p2,inputData)
                        arrayAdapter.notifyDataSetChanged()
                        dialog.dismiss()
                    }

                }
                dialogBinding.btndelete.setOnClickListener {
                    delete(p2)
                    dialog.dismiss()
                }
                dialog.show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        binding.fab.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog)
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnadd.setOnClickListener {
                if (dialogBinding.etname.text.isNullOrEmpty()){
                    dialogBinding.etname.error = "Enter name"
                    Toast.makeText(this@SpinnerActivity,"Enter name", Toast.LENGTH_SHORT).show()
                }else{
                    val inputData = dialogBinding.etname.text.toString()
                    list.add(inputData)
                    arrayAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                    }

                }
            dialog.show()
        }
    }
//    fun customDialog(isUpdated :Boolean,position:Int){
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.custom_dialog)
//        val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
//        dialog.setContentView(dialogBinding.root)
//        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
//        if (position >= 0 && isUpdated){
//            System.out.println("is updated $isUpdated")
//            dialogBinding.etname.setText(list[position])
//        }
//        dialogBinding.btnadd.setOnClickListener {
//            if (dialogBinding.etname.text.isNullOrEmpty()){
//                dialogBinding.etname.error = "Enter name"
//                Toast.makeText(this@SpinnerActivity,"Enter name", Toast.LENGTH_SHORT).show()
//            }else{
//                val inputData = dialogBinding.etname.text.toString()
//                    list[position] = inputData
//                    list.add(inputData)
//
//                    Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show()
//                }
//                arrayAdapter.notifyDataSetChanged()
//                dialog.dismiss()
//            }
//
//        dialogBinding.btndelete.setOnClickListener {
//            if(position >= 0){
//                list.removeAt(position)
//                Toast.makeText(this, "item is deleted", Toast.LENGTH_SHORT).show()
//                arrayAdapter.notifyDataSetChanged()
//                dialog.dismiss()
//            }
//        }
//        dialog.show()
//    }

    fun delete(position:Int){
        list.removeAt(position)
        arrayAdapter.notifyDataSetChanged()
    }
}