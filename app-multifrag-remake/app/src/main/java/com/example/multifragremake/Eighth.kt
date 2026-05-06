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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Eighth.newInstance] factory method to
 * create an instance of this fragment.
 */
class Eighth : Fragment() {
    val args : EighthArgs by navArgs()
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
        val view = inflater.inflate(R.layout.fragment_fifth, container, false)
        val tv = view.findViewById<TextView>(R.id.message)
        val btnBackward = view.findViewById<Button>(R.id.btn_backward)
        val btnForward = view.findViewById<Button>(R.id.btn_forward)
        val textField = view.findViewById<EditText>(R.id.textField)

        tv.setText("${args.favoriteMapel} memang terdengar asik dan juga bermanfaat. Kamu mau jadi apa di masa depan ${args.name}?")

        btnBackward.setOnClickListener {
            val action = EighthDirections.actionEighthToSeventh(
                args.name,
                args.hobby,
                args.asal,
                args.favorite,
                args.bestFriend,
                args.school
            )
        }

        btnForward.setOnClickListener {
            val str = textField.getText().toString()
            val action = EighthDirections.actionEighthToNinth(
                args.name,
                args.asal,
                args.hobby,
                args.favorite,
                args.bestFriend,
                args.school,
                args.favoriteMapel,
                str
            )
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
         * @return A new instance of fragment Eighth.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Eighth().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}