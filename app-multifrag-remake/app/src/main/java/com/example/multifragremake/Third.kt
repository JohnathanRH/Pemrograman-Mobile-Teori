package com.example.multifragremake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Third.newInstance] factory method to
 * create an instance of this fragment.
 */
class Third : Fragment() {
    val args : ThirdArgs by navArgs()
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
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        val tv = view.findViewById<TextView>(R.id.message)
        val btnBackward = view.findViewById<Button>(R.id.btn_backward)
        val btnForward = view.findViewById<Button>(R.id.btn_forward)
        val textField = view.findViewById<EditText>(R.id.textField)

        tv.setText("Wow ${args.nama}! kamu dari ${args.asal}? Keren! Terus hobi mu apa?")

        btnBackward.setOnClickListener {
            val action = ThirdDirections.actionThirdToSecond(args.nama)
            Navigation.findNavController(view).navigate(action)
        }

        btnForward.setOnClickListener {
            val str = textField.getText().toString()
            val action = ThirdDirections.actionThirdToFourth(args.nama, str, args.asal)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Third.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Third().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}