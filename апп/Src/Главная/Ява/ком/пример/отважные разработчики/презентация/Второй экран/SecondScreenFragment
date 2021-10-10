package com.example.bravedevelopers.presentation.SecondScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_second_screen.*


@AndroidEntryPoint
class SecondScreenFragment : Fragment() {
    var isFirst=true
    val offsetAndOffsetPlusLimit: IntRange = 0..19
    var list:MutableList<InformationAboutPokemon> = mutableListOf()
    val secondViewModel: SecondScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id=view.findViewById<TextView>(R.id.id)
        observeViewModel(id)
        button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                    isFirst = false
                    observeViewModel(id)
            }
        })

        ivIsFavorite.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                reactionToOnClick(ivIsFavorite)
            }
        })
    }

    fun observeViewModel(
        id:TextView
    ){

        secondViewModel.pokemonsList.observe(viewLifecycleOwner, {
            var isFavoritePokemon=false

            if(isFirst) {
                list.add(it[0])
            }
            if(isFirst && list.size==20) {
                id.setText("id: "+it[0].id.toString())
                getViewElements(it[0].id,ivPokemon,it[0])
                val a=it[0]

                secondViewModel.favoritesList.observe(viewLifecycleOwner,{
                    for (i in it) {
                        if (a.id == i.id) {
                            ivIsFavorite.setImageResource(R.drawable.star1)
                            isFavoritePokemon=true
                        }
                    }
                    if (!isFavoritePokemon){
                        ivIsFavorite.setImageResource(R.drawable.star2)
                    }
                    isFavoritePokemon=false
                })
            }

            if(list.size==20 && !isFirst){
                val randomPokemon=offsetAndOffsetPlusLimit.random()
                id.setText("id: "+list[randomPokemon].id.toString())
                getViewElements(list[randomPokemon].id,ivPokemon,list[randomPokemon])

                secondViewModel.favoritesList.observe(viewLifecycleOwner,{
                    for (i in it) {
                        if (list[randomPokemon].id ==i.id) {
                            ivIsFavorite.setImageResource(R.drawable.star1)
                            isFavoritePokemon=true
                        }
                    }
                    if (!isFavoritePokemon) ivIsFavorite.setImageResource(R.drawable.star2)
                })
            }
        })
    }



    fun reactionToOnClick(ivIsFavorite: ImageView){
        val nameCHeck:String= name.text.toString()
        var listForFavorites:MutableList<InformationAboutPokemon> = mutableListOf()
        secondViewModel.favoritesList.observe(viewLifecycleOwner,{
            var isFavorite=false

            listForFavorites= it as MutableList<InformationAboutPokemon>
            if(listForFavorites.size>0) {
                for (i in listForFavorites) {
                    if (i.name.equals(nameCHeck)) {
                        ivIsFavorite.setImageResource(R.drawable.star2)
                        secondViewModel.deleteFromFavorites(i)
                        listForFavorites.remove(i)
                        isFavorite = true
                        break
                    }
                }
            }
            if (!isFavorite){
                ivIsFavorite.setImageResource(R.drawable.star1)
                secondViewModel.pokemonsList.observe(viewLifecycleOwner,{
                    for (i in list){
                        if (i.name==nameCHeck){
                            i.isFavorite=true
                            secondViewModel.addToFavorites(i)
                            listForFavorites.add(i)
                        }
                    }
                })
            }
        })
        name.setText(nameCHeck)
    }

    fun getViewElements(idOfPokemon:Int?,ivPokemon: ImageView,informationAboutPokemon: InformationAboutPokemon){
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$idOfPokemon.png")
            .into(ivPokemon)

        name.setText(informationAboutPokemon.name)
        base_experience.setText("Base experience: "+informationAboutPokemon.base_experience.toString())
        height.setText("Height: "+informationAboutPokemon.height.toString())
        weight.setText("Wight: "+informationAboutPokemon.weight.toString())
    }
}
