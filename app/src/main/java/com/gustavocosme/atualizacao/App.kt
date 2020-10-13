package com.gustavocosme.atualizacao


import android.content.Context

import com.gustavocosme.atualizacao.altran.model.Preference
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType

class App : android.app.Application() {

    lateinit var pref: Preference


    override fun onCreate() {

        super.onCreate()
        init();




        initImageLoader(applicationContext)

    }

    fun initImageLoader(context: Context?) {




        val config = ImageLoaderConfiguration.Builder(context)
        config.threadPriority(Thread.MAX_PRIORITY)
        config.denyCacheImageMultipleSizesInMemory()
        config.diskCacheFileNameGenerator(Md5FileNameGenerator())
        config.diskCacheSize(500000 * 1024 * 1024) // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO)
        //config.writeDebugLogs()
        ImageLoader.getInstance().init(config.build())

        //ImageLoader.getInstance().diskCache.clear();
        //ImageLoader.getInstance().memoryCache.clear();
        //ImageLoader.getInstance().destroy();

    }

    fun init() {

        pref = Preference(
            applicationContext
        )
        App.APP = this
    }


    companion object {

        lateinit var APP:App;
        var IS_TEST                              = true;


    }

}// END CLASS
