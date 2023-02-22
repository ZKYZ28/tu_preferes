package com.example.projettupreferes.presenters

import android.content.Context
import android.net.Uri
import com.example.projettupreferes.database.repository.TuPreferesRepository
import com.example.projettupreferes.fragments.EditCategoryFragment
import com.example.projettupreferes.models.Category
import com.example.projettupreferes.models.GameManager
import com.example.projettupreferes.models.ImageManager

class EditCategoryPresenter(private val editCategoryFragment: EditCategoryFragment, val mainPresenter : MainActivityPresenter, val gameManager: GameManager) {
    init {
        editCategoryFragment.presenter = this
    }


    fun validateModification(categoryName : String, selectedImageUri: Uri?){
        if (categoryName.isEmpty()) {
            editCategoryFragment.showErrorMessage("Le nom de la catégorie ne peut pas être vide")
        } else if (selectedImageUri == null) {
            editCategoryFragment.showErrorMessage("Vous devez sélectionner une image")
        } else {
            editCategory(categoryName, selectedImageUri)
        }
    }

    private fun editCategory(categoryName: String, selectedImageUri: Uri) {
        val imagePath = ImageManager.saveImage(editCategoryFragment.requireContext(), selectedImageUri)
        if (imagePath == null) {
            editCategoryFragment.showErrorMessage("Une erreur s'est produite lors de l'enregistrement de l'image.")
        }

        gameManager.currentCategory.categoryName = categoryName
        gameManager.currentCategory.pathImage = imagePath.toString()
        TuPreferesRepository.getInstance()?.updateCategory(gameManager.currentCategory)
        editCategoryFragment.close()

        //TODO CHANGER DE VUE POUR RETOURNER EN ARRIERE
    }

    fun temporarySelectedImageUri(context: Context, uri: Uri) {
        editCategoryFragment.showSelectedImage(uri)
    }

}