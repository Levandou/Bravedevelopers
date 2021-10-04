package com.example.bravedevelopers.presentation


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.bravedevelopers.R
import com.example.bravedevelopers.api.ApiService
import com.example.bravedevelopers.data.AppDatabase
import com.example.bravedevelopers.domain.InformationAboutPokemon
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


private val mainViewModel:MainViewModel by viewModels()



    @Inject
    lateinit var apiService: ApiService
    lateinit var retrofit: Retrofit
    private val compositeDisposable= CompositeDisposable()








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


           mainViewModel.favoritesList.observe(this, {

        Log.d("qwertyu", it.toString())


    })


        mainViewModel.pokemonsList.observe(this ,{
            Log.d("zxcvb", it.toString())
        })
        Log.d("asdf", null.toString())


    }
/*
         val disposable=apiService.getPokemons()
        .subscribeOn(Schedulers.io())
        .subscribe({
            Log.d("zxcvb", it.toString())
        },{
            Log.d("ASDFG", it.message.toString())
        })
        compositeDisposable.add(disposable)
    }

   override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()

    }*/

}