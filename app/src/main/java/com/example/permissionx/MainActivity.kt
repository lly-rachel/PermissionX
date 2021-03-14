package com.example.permissionx

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.permissionx.databinding.ActivityMainBinding
import com.permissionx.liyingdev.PermissionX
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        binding.callButton.setOnClickListener {
            PermissionX.request(this,
            android.Manifest.permission.CALL_PHONE){ allGranted,deniedList ->
                if(allGranted){
                    call()
                }else{
                    Toast.makeText(this,"You denied $deniedList",Toast.LENGTH_SHORT).show()
                }

            }
        }

        setContentView(binding.root)


    }

    fun call(){
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }catch(e:SecurityException){
            e.printStackTrace()
        }
    }
}