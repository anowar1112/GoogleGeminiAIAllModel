package com.schedule.aibaseproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.schedule.aibaseproject.chatgpt.ChatGPTActivity
import com.schedule.aibaseproject.databinding.ActivityMainBinding
import com.schedule.aibaseproject.gemini.AIContentGenerativeActivity
import com.schedule.aibaseproject.highLevelMethod.HighLevelFunction

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.contentGenerationButton.setOnClickListener { view ->
           // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            goToAnotherActivity()
           // highLevelFunctionPractice()
        }
        binding.chatGptGenerationButtonNext.setOnClickListener {
            goToChatGPTActivity()
        }
    }

    private fun goToChatGPTActivity() {
        val intent = Intent(this, ChatGPTActivity::class.java)
        startActivity(intent)
    }

    private fun goToAnotherActivity() {
        val intent = Intent(this, AIContentGenerativeActivity::class.java)
        startActivity(intent)
    }


    private fun highLevelFunctionPractice(){
        val result = HighLevelFunction.getInstance()?.sendToFunction(add(2,3),::add)
        val resultq = HighLevelFunction.getInstance()?.sendToHighFunction(addForHigh(2,3,::add),::addForHigh)
        Log.d("mainactivity", "$result $resultq")
    }

    private fun add(a:Int,b:Int): Int{
        return a + b
    }

    private fun addForHigh(a:Int,b:Int,funType:(Int,Int)->Int): Int{
        return funType(a,b)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}