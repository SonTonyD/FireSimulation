package com.example.firesimulation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fire.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fire : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val items = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun onCalculateButtonClick(initialAmount: Double, savePerMonth: Double, rate: Double, age: Double) {
        var result: Double
        var patrimoine: Double = initialAmount

        var resultWithoutRate: Double
        var patrimoineWithoutRate: Double = initialAmount

        for (i in age.toInt()..age.toInt()+45) {
            result = (patrimoine + 12*savePerMonth)*rate
            patrimoine = result

            resultWithoutRate = (patrimoineWithoutRate + 12*savePerMonth)*1
            patrimoineWithoutRate = resultWithoutRate


            addItem("$i ans , ${patrimoine.toInt()}, $patrimoineWithoutRate")
        }
    }

    private fun addItem(item: String) {
        items.add(item)
        adapter.notifyDataSetChanged()
    }

    private fun clearListView() {
        items.clear()
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fire, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val initialAmountEditText: EditText = view.findViewById<EditText?>(R.id.initialAmount)
        val savePerMonthEditText: EditText = view.findViewById(R.id.savePerMonth)
        val rateEditText: EditText = view.findViewById(R.id.rate)
        val ageEditText: EditText = view.findViewById(R.id.age)

        listView = view.findViewById(R.id.listView)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        val calculateButton: Button = view.findViewById(R.id.button)
        calculateButton.setOnClickListener {
            clearListView()
            onCalculateButtonClick(
                initialAmountEditText.text.toString().toDouble(),
                savePerMonthEditText.text.toString().toDouble(),
                rateEditText.text.toString().toDouble(),
                ageEditText.text.toString().toDouble(),
            )
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fire.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fire().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}