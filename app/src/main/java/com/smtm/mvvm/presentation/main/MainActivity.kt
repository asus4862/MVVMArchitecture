package com.smtm.mvvm.presentation.main


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.smtm.mvvm.R
import com.smtm.mvvm.presentation.base.RxBaseActivity
import com.smtm.mvvm.databinding.ActivityMainBinding
import com.smtm.mvvm.util.Strings
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 */
class MainActivity : RxBaseActivity<ActivityMainBinding>() {

    private val mainViewModel: MainViewModel by viewModel()
    private val PERMISSION = 111;
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermission()
        binding.viewPager.apply {
            adapter = MainAdapter(this@MainActivity, supportFragmentManager).apply {
            }
        }
        binding.tabs.apply {
            setupWithViewPager(binding.viewPager)
        }

        binding.vm = mainViewModel
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED

        ) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)) ||
                (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))||
                (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))||
                        (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO))
            ) {

            }
        } else {
            ActivityCompat.requestPermissions(this, Strings.checkList, PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION) {
            if (grantResults.isNotEmpty()) {
                for (i in 0..grantResults.size - 1) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                        return
                    }
                }
            }
        }
    }
}

