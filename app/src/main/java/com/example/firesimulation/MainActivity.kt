package com.example.firesimulation

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.firesimulation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Fire())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.fire -> replaceFragment(Fire())
                R.id.salary -> replaceFragment(Salary())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }



//    private lateinit var listView: ListView
//    private lateinit var adapter: ArrayAdapter<String>
//    private val items = mutableListOf<String>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        val initialAmountEditText: EditText = findViewById<EditText?>(R.id.initialAmount)
//        val savePerMonthEditText: EditText = findViewById(R.id.savePerMonth)
//        val rateEditText: EditText = findViewById(R.id.rate)
//        val ageEditText: EditText = findViewById(R.id.age)
//
//        listView = findViewById(R.id.listView)
//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
//        listView.adapter = adapter
//
//        val calculateButton: Button = findViewById(R.id.button)
//        calculateButton.setOnClickListener {
//            clearListView()
//            onCalculateButtonClick(
//                initialAmountEditText.text.toString().toDouble(),
//                savePerMonthEditText.text.toString().toDouble(),
//                rateEditText.text.toString().toDouble(),
//                ageEditText.text.toString().toDouble(),
//            )
//        }
//    }
//
//    private fun onCalculateButtonClick(initialAmount: Double, savePerMonth: Double, rate: Double, age: Double) {
//        var result: Double
//        var patrimoine: Double = initialAmount
//
//        var resultWithoutRate: Double
//        var patrimoineWithoutRate: Double = initialAmount
//
//        for (i in age.toInt()..age.toInt()+45) {
//            result = (patrimoine + 12*savePerMonth)*rate
//            patrimoine = result
//
//            resultWithoutRate = (patrimoineWithoutRate + 12*savePerMonth)*1
//            patrimoineWithoutRate = resultWithoutRate
//
//
//            addItem("$i ans , ${patrimoine.toInt()}, $patrimoineWithoutRate")
//        }
//    }
//
//    private fun addItem(item: String) {
//        items.add(item)
//        adapter.notifyDataSetChanged()
//    }
//
//    private fun clearListView() {
//        items.clear()
//        adapter.notifyDataSetChanged()
//    }
}