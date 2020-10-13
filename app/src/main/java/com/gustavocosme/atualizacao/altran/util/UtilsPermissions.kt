package gustavocosme.util

import android.Manifest
import android.content.Context
import com.gustavocosme.atualizacao.altran.util.Dialogs
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import java.util.ArrayList

class UtilsPermissions {

    interface Call{

        fun onOK()
        fun onNo()


    }

    companion object{

        fun onPermission(context:Context,call:Call)
        {

            val permissions = arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            Permissions.check(
                context ,
                permissions,
                null ,
                null,
                object : PermissionHandler() {

                    override fun onGranted() {

                        call.onOK();

                    }

                    override fun onDenied(
                        contextq: Context?,
                        deniedPermissions: ArrayList<String>?
                    ) {

                        val array = arrayOf(
                            "Fechar"
                        );

                        Dialogs.addList(context, "É necessario aceitar as permissões em 'PERMITIR' para a utilização do APP! Vamos fechar o aplicativo! ao reabrir clique em 'PERMITIR' ;)", array, Dialogs.CALL { value, position ->

                            if (position == 0) {


                                System.exit(0);

                            } else {


                            }


                        })




                    }


                })

        }

    }

}