package com.example.bravedevelopers.presentation.firstScreen

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.example.bravedevelopers.presentation.MainActivity
import com.example.bravedevelopers.presentation.thirdScreen.ThirdScreenFragment
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "list2"

@AndroidEntryPoint
class FirstScreenFragment  : Fragment() {

    private lateinit var adapter: PokemonsListAdapter
    private val mainViewModel: MainViewModel by viewModels()
    var list:MutableList<InformationAboutPokemon> = mutableListOf()

    private var list2:MutableList<InformationAboutPokemon> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
//        Log.d("muyt", arguments?.getParcelableArrayList<InformationAboutPokemon>("pokemons")!!.toString())//      .toList() as MutableList<InformationAboutPokemon>
  //Log.d("my",requireActivity().intent.hasExtra("asd"))
        return inflater.inflate(R.layout.fragment_first_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
      //  activity?.let { Log.d("poiuyt",(it as MainActivity).observe().toString() )}
        observeViewModel()
    }

    fun observeViewModel(){
        mainViewModel.pokemonsList.observe(viewLifecycleOwner ,{
            list.add(it[0])
            Log.d("zxcvb", it.toString())
            adapter.pokemonsList=list
        })



        mainViewModel.favoritesList.observe(viewLifecycleOwner,{
            adapter.pokemonsListFromDb=it
            Log.d("oplm", it.toString())
           // adapter.pokemonsList=list
        })
    }
         fun setupRecyclerView(view: View){
        val rvPokemonsList=view.findViewById<RecyclerView>(R.id.rv_pokemon_list)
             adapter= PokemonsListAdapter( {
                 mainViewModel.addToFavorites(it)

             },{
                 mainViewModel.deleteFromFavorites(it)
             })
        rvPokemonsList.adapter=adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
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
        adapter.notifyDataSetChanged()
        adapter.pokemonsList=listSearch

    }
    companion object {
        @JvmStatic
        fun newInstance(list2:MutableList<InformationAboutPokemon>) =
            FirstScreenFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
