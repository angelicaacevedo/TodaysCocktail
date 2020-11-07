package com.example.todayscocktail.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todayscocktail.network.Cocktail
import com.example.todayscocktail.network.CocktailsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailsListRepository() {
    // segurador de dados da classe
        private val cocktailListResponse = MutableLiveData<List<Cocktail>>()

    // expoe os dados retornados pelo servico
        val  cocktailList: LiveData<List<Cocktail>>
            get() = cocktailListResponse

        init {
            getCocktailsList()
        }
    // requisicao dos dados dos coqueteis atraves do retrofit
        private fun getCocktailsList() {
        // executando tarefas fora da main thread
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val listResult = CocktailsApi.retrofitService.getNonAlcoholicCocktails().cocktailsList
                    cocktailListResponse.postValue(listResult)

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        cocktailListResponse.postValue(listOf())
                    }
                }
            }
        }

}
