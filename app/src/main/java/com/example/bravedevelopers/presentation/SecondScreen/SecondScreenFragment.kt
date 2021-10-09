package com.example.bravedevelopers.presentation.SecondScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.bravedevelopers.R
import com.example.bravedevelopers.domain.InformationAboutPokemon
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.internal.aggregatedroot.codegen._com_example_bravedevelopers_App
import kotlinx.android.synthetic.main.fragment_second_screen.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class SecondScreenFragment : Fragment() {
    var isFirst=true
    val offsetAndOffsetPlusLimit: IntRange = 1..20
    var list:MutableList<InformationAboutPokemon> = mutableListOf()
    private val secondViewModel: SecondScreenViewModel by viewModels()



    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_second_screen, container, false)
    }

    var a=offsetAndOffsetPlusLimit.random()
    fun randomniu(){
        a= offsetAndOffsetPlusLimit.random()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val name=view.findViewById<TextView>(R.id.name)
        val base_experience=view.findViewById<TextView>(R.id.base_experience)
        val height=view.findViewById<TextView>(R.id.height)
        val weight=view.findViewById<TextView>(R.id.weight)
        val iv=view.findViewById<ImageView>(R.id.ivPokemon)
        val button=view.findViewById<Button>(R.id.button)
        val ivIsFavorite=view.findViewById<ImageView>(R.id.ivIsFavorite)
        val id=view.findViewById<TextView>(R.id.id)

        observeViewModel(view,name,a,base_experience,height,weight,iv,ivIsFavorite,id)

        button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                randomniu()
                isFirst=false
                Log.d("lkjpoi", a.toString())
                observeViewModel(view,name,a,base_experience,height,weight,iv,ivIsFavorite,id)
            }

        })

        ivIsFavorite.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
             onListener(view,ivIsFavorite)
            }

        })
    }

    fun observeViewModel(
        view: View,
        name:TextView,
        a:Int,
        base_experience: TextView,
        height: TextView,
        weight: TextView,
        iv:ImageView,
        ivIsFavorite:ImageView,
        id:TextView
    ){

        secondViewModel.pokemonsList.observe(viewLifecycleOwner, {
            var isFavoritePokemon=false

            if(isFirst) {
                list.add(it[0])
            }
            if(isFirst && list.size==20) {

                name.setText(it[0].name)
                base_experience.setText("Base experience: "+it[0].base_experience.toString())
                height.setText("Height: "+it[0].height.toString())
                weight.setText("Wight: "+it[0].weight.toString())
                id.setText("id: "+it[0].id.toString())
                Picasso.get()
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+it[0].id+".png")
                    .into(iv)
                var a=it[0]

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

                name.setText(list[randomPokemon].name)
                base_experience.setText("Base experience: "+list[randomPokemon].base_experience.toString())
                height.setText("Height: "+list[randomPokemon].height.toString())
                weight.setText("Wight: "+list[randomPokemon].weight.toString())
                id.setText("id: "+list[randomPokemon].id.toString())

                Picasso.get()
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+list[randomPokemon].id+".png")
                    .into(iv)


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
            Log.d("tyuj", it.toString())
        })
    }



    fun onListener(view: View,ivIsFavorite: ImageView){
        Log.d("dullname", name.text.toString())
        val nameCHeck:String= name.text.toString()
        var listForFavorites:MutableList<InformationAboutPokemon> = mutableListOf()


        secondViewModel.favoritesList.observe(viewLifecycleOwner,{
            var isFavorite=false

        listForFavorites= it as MutableList<InformationAboutPokemon>
           Log.d("228335", listForFavorites.toString())
            Log.d("228336", it.toString())

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
            isFavorite=false
        })

        secondViewModel.favoritesList.observe(viewLifecycleOwner,{
            Log.d("asdewq", it.toString())

        })
        name.setText(nameCHeck)
    }


}