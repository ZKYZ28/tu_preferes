package com.example.projettupreferes.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.projettupreferes.R
import com.example.projettupreferes.presenters.CategoryPresenter
import java.util.*


class CategoryFragment : Fragment() {
    private var categoryId: UUID? = null
    lateinit var categoryPresenter: CategoryPresenter

    lateinit var imageCategoryIv : ImageView
    lateinit var edtiCategoryButton : Button
    lateinit var addPaireButton : Button
    lateinit var seePairesButton : Button
    lateinit var deleteCategoryButton : Button
    lateinit var categoryNameEt : TextView

    lateinit var playGameCl : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_category, container, false)
        categoryPresenter.setCategoryFragment(this);

        imageCategoryIv = view.findViewById(R.id.imageCategory)
        edtiCategoryButton = view.findViewById(R.id.edtiCategory)
        addPaireButton = view.findViewById(R.id.addPaire)
        seePairesButton = view.findViewById(R.id.seePaires)
        deleteCategoryButton = view.findViewById(R.id.deleteCategory)
        categoryNameEt = view.findViewById(R.id.categoryName)
        playGameCl = view.findViewById(R.id.playGame)

        edtiCategoryButton.setOnClickListener {
            categoryPresenter.editCategory()
        }

        addPaireButton.setOnClickListener {
            categoryPresenter.goToPair()
        }

        seePairesButton.setOnClickListener {

        }

        deleteCategoryButton.setOnClickListener {
            categoryPresenter.deleteCategory()
        }

        playGameCl.setOnClickListener {

        }

        categoryPresenter.loadCategory(categoryId)

        return view
    }

    fun displayCategoryInformation(categoryName : String, categoryImagePath : String){
        imageCategoryIv.setImageURI(Uri.parse(categoryImagePath))
        categoryNameEt.text = categoryName
    }

    fun close() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    companion object {
        @JvmStatic
        fun newInstance(categoryUUID: UUID?, categoryPresenterInit: CategoryPresenter) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    categoryPresenter = categoryPresenterInit;
                    categoryId = categoryUUID
                    if (categoryId != null) {
                        categoryPresenter.loadCategory(categoryUUID)
                    }
                }
            }
    }
}