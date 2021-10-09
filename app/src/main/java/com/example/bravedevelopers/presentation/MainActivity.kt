package com.example.bravedevelopers.presentation



import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.bravedevelopers.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    


    override fun onCreate(savedInstanceState: Bundle?) {

        Thread.sleep(3000)
        setTheme(R.style.Theme_AppCompat_DayNight_DarkActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0,0,0,"Поиск покемонов")
        menu?.add(0,1,1,"Случайный покемон")
        menu?.add(0,2,2,"Избранные покемоны")

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController= Navigation.findNavController(this,R.id.search_container)
        if(item.itemId==0){
            navController.navigate(R.id.firstScreenFragment)
        }

        if(item.itemId==1){
            navController.navigate(R.id.secondScreenFragment)
        }

        if(item.itemId==2){
            navController.navigate(R.id.thirdScreenFragment)
        }

        return super.onOptionsItemSelected(item)
    }

}