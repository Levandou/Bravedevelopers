package com.example.bravedevelopers.presentation


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bravedevelopers.R
import com.example.bravedevelopers.api.ApiService
import com.example.bravedevelopers.appComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var apiService: ApiService
    lateinit var retrofit: Retrofit
    private val compositeDisposable= CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)

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
    }
}