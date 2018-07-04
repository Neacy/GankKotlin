package com.neacy.kotlin

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.neacy.kotlin.constant.LogUtil
import kotlinx.android.synthetic.main.activity_index.*

class MainActivity : AppCompatActivity() {

    var tempFragment: Fragment? = null
    var articleFragment: ArticleFragment? = null
    var girlFragment: GirlFragment? = null
    var aboutFragment: AboutFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        var transaction = supportFragmentManager.beginTransaction()

        mNavigationView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                transaction = supportFragmentManager.beginTransaction()

                if (tempFragment != null) {
                    transaction.hide(tempFragment)
                }
                when (item.itemId) {
                    R.id.id_home -> {
                        showDefaultFragment(item.itemId, transaction)
                    }
                    R.id.id_girl -> {
                        LogUtil.w("Jayuchou", "==== start Navigation  2  = " + item.itemId)
                        if (girlFragment == null) {
                            girlFragment = GirlFragment.newInstance()
                            transaction.add(R.id.id_frame, girlFragment, item.itemId.toString())
                        } else {
                            transaction.show(girlFragment)
                        }
                        tempFragment = girlFragment
                    }
                    R.id.id_about -> {
                        LogUtil.w("Jayuchou", "==== start Navigation  3  = " + item.itemId)
                        if (aboutFragment == null) {
                            aboutFragment = AboutFragment.newInstance()
                            transaction.add(R.id.id_frame, aboutFragment, item.itemId.toString())
                        } else {
                            transaction.show(aboutFragment)
                        }
                        tempFragment = aboutFragment
                    }
                }
                transaction.commit()
                return true
            }
        })

        showDefaultFragment(R.id.id_home, transaction)
        transaction.commit()
    }

    fun showDefaultFragment(id: Int, transaction: FragmentTransaction) {
        LogUtil.w("Jayuchou", "==== start Navigation  1  = ${id}")
        if (tempFragment != null) {
            transaction.hide(tempFragment)
        }
        if (articleFragment == null) {
            articleFragment = ArticleFragment.newInstance()
            transaction.add(R.id.id_frame, articleFragment, id.toString())
        } else {
            transaction.show(articleFragment)
        }
        tempFragment = articleFragment
    }
}
