package gustavocosme.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment

import org.json.JSONObject


/**
 * Created by niccapdevila on 3/26/16.
 */
open class BaseFragment : Fragment() {


    lateinit var mFragmentNavigation: FragmentNavigation
    var mInt = 0
    var cachedView: View? = null;
    var layoutA:Int = 0;
    var isLogo: Boolean? = false
    var title = ""
    var isInit: Boolean = false;




    fun setLayout(layoutA: Int) {
        this.layoutA = layoutA
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE)
        }
    }

    override fun onAttach(context:Context) {

        super.onAttach(context)

        if (context is FragmentNavigation) {
            mFragmentNavigation = context
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (cachedView == null) {


            cachedView = inflater.inflate(layoutA, container, false)
            setHasOptionsMenu(true)



            if ((!isInit)!!) {


                init()

                //ViewHelper.setAlpha(cachedView!!, 0f)
                //com.nineoldandroids.view.ViewPropertyAnimator.animate(cachedView).setDuration(1200)
                    //.alpha(1f)
            }


        }




        return cachedView
    }


    open fun init() {


    }




    interface FragmentNavigation {
        fun pushFragment(fragment: Fragment)
    }

    companion object {


        val ARGS_INSTANCE = "com.ncapdevi.sample.argsInstance"
    }

    /*
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if(!isLogo)
        menu.clear();
    }
    */


    //****************************//
    //MODEL
    //****************************//




    //****************************//
    //DIALOGS
    //****************************//

    fun setTransparentStatusBar() {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        //activity?.window?.statusBarColor =
            //ContextCompat.getColor(activity?.applicationContext!!, R.color.toolkit_status_bar)


    }

    private fun setWindowFlag(
        bits: Int,
        on: Boolean
    ) {
        val win = activity?.window
        val winParams = win?.attributes

        winParams?.let {
            if (on) {
                it.flags = it.flags or bits
            } else {
                it.flags = it.flags and bits.inv()
            }
            win.attributes = winParams
        }

    }
}
