package com.gustavocosme.atualizacao.altran.extensions

import android.app.Activity
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.view.View
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File


fun Activity.setColorStatusBar(color:Int = Color.WHITE)
{

    try {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = color;
        }

    } catch (e: java.lang.Exception) {

    }

}


fun Activity.initVideoUploadPhoto(
    onComplete:(File, Uri, patch:String) -> Unit,
    onErro: (String) -> Unit
)
{

    var x = 1f;
    var y = 1f;

    ImagePicker.with(this)
        .cameraOnly()
        .compress(1024)
        .crop(x, y)
        .start { resultCode, data ->
            if (resultCode == Activity.RESULT_OK) {

                try {

                    val fileUri = data?.data
                    val file: File? = ImagePicker.getFile(data);
                    val filePath: String? = ImagePicker.getFilePath(data)
                    onComplete(file!!,fileUri!!,filePath!!);

                }catch (e:Exception)
                {

                    onErro("ERRO initGaleriaUploadPhoto");

                }

            } else if (resultCode == ImagePicker.RESULT_ERROR) {

                onErro("ERRO initGaleriaUploadPhoto");

            } else {

                onErro("ERRO initGaleriaUploadPhoto");

            }
        }

}



fun Activity.initGaleriaUploadPhoto(
    onComplete:(File, Uri, patch:String) -> Unit,
    onErro: (String) -> Unit
)
{

    var x = 1f;
    var y = 1f;

    ImagePicker.with(this)
        .galleryOnly()
        .compress(1024)
        .crop(x, y)
        .start { resultCode, data ->
            if (resultCode == Activity.RESULT_OK) {

                try {

                    val fileUri = data?.data
                    val file: File? = ImagePicker.getFile(data);
                    val filePath: String? = ImagePicker.getFilePath(data)
                    onComplete(file!!,fileUri!!,filePath!!);

                }catch (e:Exception)
                {

                    onErro("ERRO initGaleriaUploadPhoto");

                }

            } else if (resultCode == ImagePicker.RESULT_ERROR) {

                onErro("ERRO initGaleriaUploadPhoto");

            } else {

                onErro("ERRO initGaleriaUploadPhoto");

            }
        }

}
