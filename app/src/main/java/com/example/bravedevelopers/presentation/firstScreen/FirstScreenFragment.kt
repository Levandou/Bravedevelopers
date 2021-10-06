package com.example.bravedevelopers.presentation.firstScreen

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.domain.useCase.AddToFavoritesUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_pokemon.view.*
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class FirstScreenFragment  : Fragment() {

    private lateinit var adapter: PokemonsListAdapter
    private val mainViewModel: MainViewModel by viewModels()
    var list:MutableList<InformationAboutPokemon> = mutableListOf()

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_first_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        observeViewModel()

    }


    fun observeViewModel(){
        mainViewModel.pokemonsList.observe(viewLifecycleOwner ,{
            list.add(it[0])
            Log.d("zxcvb", it.toString())
            adapter.pokemonsList=list
        })


    }


         fun setupRecyclerView(view: View){
        val rvPokemonsList=view.findViewById<RecyclerView>(R.id.rv_pokemon_list)
//             rvPokemonsList.ivAddFavorites.callOnClick()
        adapter= PokemonsListAdapter()
        rvPokemonsList.adapter=adapter

    }


    companion object {
    /*    @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FirstScreenFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }

    */
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.first_screen_menu,menu)

        val search=menu?.findItem(R.id.menu_search)
        val searcView=search.actionView as? SearchView
        searcView?.isSubmitButtonEnabled=true
        searcView?.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                    searchList(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!=null){
                    searchList(newText)
                }
                return true
            }
        })
    }

    private fun searchList(query: String){
          val searchQuery="$query"
          var listSearch:MutableList<InformationAboutPokemon> = mutableListOf()
        for(i in list){
            if(i.name.contains(searchQuery)){
                listSearch.add(i)
            }
        }
        adapter.pokemonsList=listSearch
    }



}
