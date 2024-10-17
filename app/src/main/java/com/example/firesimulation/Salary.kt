package com.example.firesimulation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Salary.newInstance] factory method to
 * create an instance of this fragment.
 */
class Salary : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clearInput: Button = view.findViewById(R.id.clearInputButton)

        // Récupération des EditText à partir des TextInputLayout
        val mensuelBrutLayout: TextInputLayout? = view.findViewById(R.id.mensuelBrut)
        val mensuelNetLayout: TextInputLayout? = view.findViewById(R.id.mensuelNet)
        val annuelBrutLayout: TextInputLayout? = view.findViewById(R.id.annuelBrut)
        val annuelNetLayout: TextInputLayout? = view.findViewById(R.id.annuelNet)

        val mensuelBrut = mensuelBrutLayout?.editText
        val mensuelNet = mensuelNetLayout?.editText
        val annuelBrut = annuelBrutLayout?.editText
        val annuelNet = annuelNetLayout?.editText

        // Variable pour bloquer la boucle de TextWatcher
        var isUpdating = false

        // Fonction pour calculer et remplir les champs
        fun updateFields(source: EditText) {
            if (isUpdating) return

            var taxeValue = 1-0.22

            if (view.findViewById<Switch>(R.id.cadreSwitch).isChecked) {
                taxeValue = (1-0.25)
            }

            isUpdating = true  // Bloquer les autres updates

            val sourceValue = source.text.toString().toDoubleOrNull() ?: return

            when (source) {
                mensuelBrut -> {
                    val netMensuel = sourceValue * taxeValue
                    mensuelNet?.setText("%.2f".format(netMensuel))

                    val brutAnnuel = sourceValue * 12
                    annuelBrut?.setText("%.2f".format(brutAnnuel))

                    val netAnnuel = netMensuel * 12
                    annuelNet?.setText("%.2f".format(netAnnuel))
                }

                mensuelNet -> {
                    val brutMensuel = sourceValue / taxeValue
                    mensuelBrut?.setText("%.2f".format(brutMensuel))

                    val brutAnnuel = brutMensuel * 12
                    annuelBrut?.setText("%.2f".format(brutAnnuel))

                    val netAnnuel = sourceValue * 12
                    annuelNet?.setText("%.2f".format(netAnnuel))
                }

                annuelBrut -> {
                    val mensuelBrutValue = sourceValue / 12
                    mensuelBrut?.setText("%.2f".format(mensuelBrutValue))

                    val netMensuel = mensuelBrutValue * taxeValue
                    mensuelNet?.setText("%.2f".format(netMensuel))

                    val netAnnuel = sourceValue * taxeValue
                    annuelNet?.setText("%.2f".format(netAnnuel))
                }

                annuelNet -> {
                    val netMensuel = sourceValue / 12
                    mensuelNet?.setText("%.2f".format(netMensuel))

                    val brutMensuel = netMensuel / taxeValue
                    mensuelBrut?.setText("%.2f".format(brutMensuel))

                    val brutAnnuel = brutMensuel * 12
                    annuelBrut?.setText("%.2f".format(brutAnnuel))
                }
            }

            isUpdating = false  // Permettre à nouveau les updates
        }

        // Ajoute des TextWatcher pour chaque champ
        mensuelBrut?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateFields(mensuelBrut)
            }
        })

        mensuelNet?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateFields(mensuelNet)
            }
        })

        annuelBrut?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateFields(annuelBrut)
            }
        })

        annuelNet?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateFields(annuelNet)
            }
        })

        clearInput.setOnClickListener({
            mensuelBrut?.text?.clear()
            mensuelNet?.text?.clear()
            annuelBrut?.text?.clear()
            annuelNet?.text?.clear()
            isUpdating = false
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Salary.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Salary().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}