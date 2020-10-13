package gustavocosme.util

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.gustavocosme.atualizacao.presentation.main.Main
import com.gustavocosme.atualizacao.altran.util.Dialogs
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


enum class SOCIAL_TYPES(val value: Int) {

    GLOBAL(1),
    INSTAGRAM(2),
    ZAP(3)


}

object ShareSocialG10 {


    fun SHARE_SAVE(image: Bitmap, type: SOCIAL_TYPES) {


        var pd: ProgressDialog = ProgressDialog(Main.INSTANCE);
        pd.setMessage("Compartilhando... Rapidinho ;)");
        pd.show();






            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())

            var uri: Uri? = null


            try {

                var uniqueID = UUID.randomUUID().toString();

                //val file = File(
                //Main.INSTANCE.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                //  Main.INSTANCE.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                //"$uniqueID.png"
                //)

                val root = Environment.getExternalStorageDirectory()
                val file = File(root.absolutePath + "/DCIM/Camera/$uniqueID.png")

                val stream = FileOutputStream(file)
                image.compress(Bitmap.CompressFormat.PNG, 100, stream)
                stream.close()
                uri = Uri.fromFile(file);

                if (type == SOCIAL_TYPES.GLOBAL) {

                    //Dialogs.add(Main.INSTANCE, "Imagem salva com sucesso! ;)");
                    SHARE_GLOBAL(uri);
                }

                if (type == SOCIAL_TYPES.INSTAGRAM) {
                    SHARE_INSTAGRAM_FEED(uri);
                }

                if (type == SOCIAL_TYPES.ZAP) {
                    SHARE_ZAP(uri);
                }

                pd.dismiss();

            } catch (e: IOException) {

                pd.dismiss();
                Dialogs.add(
                    Main.INSTANCE,
                    "Ops houve algum problema! Mais calma! Tente novamente!"
                );

                //Log.e("ERRO", "IOException while trying to write file for sharing: " + e.message)

            }



    }


    private fun SHARE_GLOBAL(uri: Uri) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "image/png"
        Main.INSTANCE.startActivity(intent)

    }

    private fun SHARE_INSTAGRAM_FEED(uri: Uri) {

        val share = Intent(Intent.ACTION_SEND)
        share.setPackage("com.instagram.android")
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        share.type = "image/*"
        share.putExtra(Intent.EXTRA_STREAM, uri)
        Main.INSTANCE.startActivity(Intent.createChooser(share, "Premium construtora"))
    }

    private fun SHARE_ZAP(uri: Uri) {

        val share = Intent(Intent.ACTION_SEND)
        share.setPackage("com.whatsapp")
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        share.type = "image/jpeg"
        share.putExtra(Intent.EXTRA_TEXT, "Premium construtora");

        share.putExtra(Intent.EXTRA_STREAM, uri)
        Main.INSTANCE.startActivity(Intent.createChooser(share, "Premium construtora"))
    }


}