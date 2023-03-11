package com.example.projettupreferes.presenters

import com.example.projettupreferes.database.repository.TuPreferesRepository
import com.example.projettupreferes.fragments.FragmentsName
import com.example.projettupreferes.fragments.PersonnalFragment
import com.example.projettupreferes.models.Category
import com.example.projettupreferes.models.GameManager
import com.example.projettupreferes.presenters.viewsInterface.fragments.IPersonnalFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class PersonnelPresenter(private val personnel: IPersonnalFragment, private val CategoryListScreen: ICategoryListScreen, private val mainPresenter : MainActivityPresenter, private val gameManager: GameManager) {
    init {
        personnel.setPersonnelPresenter(this);
    }

    interface ICategoryItemScreen {
        fun showCategory(id: UUID?, name: String?, imagePath: String?)
    }

    interface ICategoryListScreen {
        fun loadView()
    }


    fun goToCreateCategory(desiredFragment: FragmentsName) {
        mainPresenter.requestSwitchView(desiredFragment);
    }


    fun loadCategories() {
        GlobalScope.launch(Main) {
            TuPreferesRepository.getInstance()?.getCategoriesWithPairesList()
                ?.collect { categoriesWithPaires ->
                    val categories = categoriesWithPaires.map { it.category }
                    this@PersonnelPresenter.gameManager.categoriesMap = categories.associateBy { it.categoryName }.toMutableMap()
                    CategoryListScreen.loadView()
                }
        }
    }

    fun getItemCount(): Int {
        if(gameManager.categoriesMap == null){
            return 0
        }
        return gameManager.categoriesMap.size
    }

    fun showCategoryOn(
        holder: ICategoryItemScreen,
        position: Int
    ) {
        val p: Category = gameManager.categoriesMap.values.toList()[position]
        holder.showCategory(p.idCategory, p.categoryName, p.pathImage)
    }
}