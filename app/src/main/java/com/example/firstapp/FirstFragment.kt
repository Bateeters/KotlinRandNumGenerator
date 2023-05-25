package com.example.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.firstapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun countMe(view: View) {
        // Get the text view
        val showCountTextView = view.findViewById<TextView>(R.id.textview_first)

        // Get the value of the text view.
        val countString = showCountTextView.text.toString()

        // Convert value to a number and increment it
        var count = countString.toInt()
        count++

        // Display the new value in the text view.
        showCountTextView.text = count.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find the toast_button by its ID and set a click listener
        view.findViewById<Button>(R.id.toast_button).setOnClickListener {
            // create a Toast with some text, to appear for a short time
            // use getString() to pull defined text from res/values/strings.xml file
            val myToast = Toast.makeText(context, getString(R.string.toast_msg), Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()
        }

        // find the count_button by its ID and set a click listener
        view.findViewById<Button>(R.id.count_button).setOnClickListener {
            // run countMe() function
            countMe(view)
        }

        // find the random_button by its ID and set a click listener
        view.findViewById<Button>(R.id.random_button).setOnClickListener{
            // changing the counter to an int and moving the info
            // from first fragment to second fragment

            // find the count TextView
            val showCountTextView = view.findViewById<TextView>(R.id.textview_first)
            // get and convert the count text to an int
            val currentCount = showCountTextView.text.toString().toInt()
            // create action to actionFirstFramgentToSecondFragment() using currentCount as arg
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount)
            // find the nav controller and navigate with action
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}