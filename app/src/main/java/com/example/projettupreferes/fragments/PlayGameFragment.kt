package com.example.projettupreferes.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.projettupreferes.R
import com.example.projettupreferes.presenters.PlayGamePresenter

class PlayGameFragment : Fragment() {

    lateinit var presenter: PlayGamePresenter;

    private lateinit var choiceOne: ConstraintLayout
    private lateinit var textChoiceOne: TextView
    private lateinit var imageChoiceOne : ImageView


    private lateinit var choiceTwo: ConstraintLayout
    private lateinit var textChoiceTwo : TextView
    private lateinit var imageChoiceTwo : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onChoiceSelected()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_play_game, container, false)

        //Init des composants
        choiceOne = view.findViewById(R.id.choiceOne)
        textChoiceOne = view.findViewById(R.id.textChoiceOne)
        imageChoiceOne = view.findViewById((R.id.imageChoiceOne))

        choiceTwo = view.findViewById(R.id.choiceTwo)
        textChoiceTwo = view.findViewById(R.id.textChoiceTwo)
        imageChoiceTwo = view.findViewById((R.id.imageChoiceTwo))

        //Ajout des listeners
        choiceOne.setOnClickListener {
            presenter.onChoiceSelected();
        }

        choiceTwo.setOnClickListener {
            presenter.onChoiceSelected();
        }

        return view
    }

    fun displayChoiceOne(choiceInformation : String, isText : Boolean){
        if(isText){
            textChoiceOne.isVisible = true
            textChoiceOne.text = choiceInformation
            imageChoiceOne.isVisible = false
        }else{
            textChoiceOne.isVisible = false
            imageChoiceOne.setImageURI(Uri.parse(choiceInformation))
            imageChoiceOne.isVisible = true
        }
    }

    fun displayChoiceTwo(choiceInformation : String, isText : Boolean){
        if(isText){
            textChoiceTwo.isVisible = true
            textChoiceTwo.text = choiceInformation
            imageChoiceTwo.isVisible = false
        }else{
            textChoiceTwo.isVisible = false
            imageChoiceTwo.setImageURI(Uri.parse(choiceInformation))
            imageChoiceTwo.isVisible = true
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            PlayGameFragment().apply {
                arguments = Bundle().apply {
                }
            }

    }
}