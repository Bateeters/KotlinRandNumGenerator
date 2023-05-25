package com.example.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.firstapp.databinding.FragmentSecondBinding

// importing navArgs for use
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    // defining where the arguments are
    val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        // get count argument
        val count = args.myArg

        // get the heading string on screen 2 and format it with the count
        val countText = getString(R.string.random_heading, count)

        // set text to formatted heading string
        view.findViewById<TextView>(R.id.textview_header).text = countText

        // get random number between 0 and count
        val random = java.util.Random()
        var randomNumber = 0
        if(count>0){
            randomNumber = random.nextInt(count+1)
        }

        // convert random number to a string
        view.findViewById<TextView>(R.id.textview_random).text = randomNumber.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}