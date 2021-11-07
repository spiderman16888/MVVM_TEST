package xyz.markapp.markmvvmtest1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import xyz.markapp.markmvvmtest1.databinding.ActivityMainNewBinding
import xyz.markapp.markmvvmtest1.ui.mine.MineFragment
import xyz.markapp.markmvvmtest1.ui.userlist.UserListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainNewBinding
    lateinit var toolbar: Toolbar;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager2: ViewPager2 = binding.viewPager2
        toolbar = binding.toolbar
        val bottomNavigationView: BottomNavigationView = binding.navView

        /*      val navController = findNavController(R.id.nav_host_fragment_activity_main)
              // Passing each menu ID as a set of Ids because each
              // menu should be considered as top level destinations.
              val appBarConfiguration = AppBarConfiguration(
                  setOf(
                      R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
                  )
              )*/

        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 2
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> UserListFragment()
                    else -> MineFragment()
                }
            }
        }

        // 當ViewPager切換頁面時，改變底部導航欄的狀態
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).isChecked = true
                supportActionBar?.setDisplayHomeAsUpEnabled(false);
                when (position) {
                    0 -> {
                        toolbar.title = getString(R.string.title_dashboard)
                    }
                    1 -> {
                        toolbar.title = getString(R.string.title_home)
                    }

                }
            }
        })

        // 當ViewPager切換頁面時，改變ViewPager的顯示
        bottomNavigationView.setOnItemSelectedListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(false);

            when (it.itemId) {
                R.id.navigation_dashboard -> {
                    viewPager2.setCurrentItem(0, true)
                    toolbar.title = getString(R.string.title_dashboard)
                }
                R.id.navigation_home -> {
                    viewPager2.setCurrentItem(1, true)
                    toolbar.title = getString(R.string.title_home)
                }

            }
            true
        }

        //---

        setSupportActionBar(toolbar)

//        setupActionBarWithNavController(navController, appBarConfiguration)
//        bottomNavigationView.setupWithNavController(navController)

        //---


    }
}
