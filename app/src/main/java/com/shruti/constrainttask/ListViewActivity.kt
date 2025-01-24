package com.shruti.constrainttask

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ArrayAdapter
import android.widget.Toast
import com.shruti.constrainttask.databinding.ActivityListViewBinding
import com.shruti.constrainttask.databinding.CustomDialogBinding
import com.shruti.constrainttask.databinding.CustomDialogNumBinding
import com.shruti.constrainttask.databinding.CustomLayoutListBinding

class ListViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityListViewBinding
    lateinit var arrayAdapter : ArrayAdapter<String>
    var array_list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,array_list)
        binding.listview.adapter = arrayAdapter
        binding.fab.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog)
            val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnadd.setOnClickListener {
                if (dialogBinding.etname.text.isNullOrEmpty()) {
                    dialogBinding.etname.error = "Enter name"
                    Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show()
                } else {
                    val input = dialogBinding.etname.text.toString()
                    array_list.add(input)
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
        binding.listview.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem  = parent.getItemAtPosition(position) as String
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog)
            val dialogBinding = CustomLayoutListBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.etname.setText(selectedItem)
            dialogBinding.btnadd.setOnClickListener {
                if (dialogBinding.etname.text.isNullOrEmpty()) {
                    dialogBinding.etname.error = "Enter name"
                    Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show()
                } else {
                    val input = dialogBinding.etname.text.toString()
                    array_list[position] = input
                    dialog.dismiss()
                }
            }
            dialogBinding.btndelete.setOnClickListener {
                delete(position)
                dialog.dismiss()
            }
            dialog.show()
        }
    }
    fun delete(position:Int){
        array_list.removeAt(position)
        arrayAdapter.notifyDataSetChanged()
    }
}