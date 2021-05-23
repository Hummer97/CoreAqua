package com.xpertwebtech.coreaqua.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.xpertwebtech.coreaqua.R
import com.xpertwebtech.coreaqua.SharedPrefManager.SharedPrefManager
import com.xpertwebtech.coreaqua.base.BaseActivity
import com.xpertwebtech.coreaqua.ui.SignIn.SignInActivity

class MainActivity : BaseActivity<MainActivityView,MainActivityPresenter>(),MainActivityView {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var sharedPrefManager: SharedPrefManager
    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun getPresenterClass(): Class<MainActivityPresenter> {
        return MainActivityPresenter::class.java
    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        supportActionBar!!.hide()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        sharedPrefManager = SharedPrefManager.getInstance(applicationContext)
        if(!sharedPrefManager.isLoggedIn)
        {
            var intent = Intent(applicationContext,SignInActivity::class.java)
            startActivity(intent)
        }
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.orderDetailsFragment, R.id.nav_plan, R.id.nav_slideshow))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }
}