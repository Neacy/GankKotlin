package com.neacy.kotlin.adapter

import android.content.Context
import android.graphics.drawable.Animatable
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.ControllerListener
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo
import com.neacy.kotlin.R
import com.neacy.kotlin.bean.GirlResult
import kotlinx.android.synthetic.main.item_girl.view.*


/**
 * 美女adapter
 * @author yuzongxu <yuzongxu@xiaoyouzi.com>
 * @since 2018/7/4
 */
class GirlAdapter(var datas: MutableList<GirlResult>) : RecyclerView.Adapter<GirlAdapter.GirlViewHolder>() {

    var realWidth: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlViewHolder {
        if (realWidth == 0) {
            val wm = parent.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            wm.defaultDisplay.getMetrics(dm)
            realWidth = dm.widthPixels
        }
        return GirlViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_girl, parent, false))
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: GirlViewHolder, position: Int) {
        var controller: DraweeController = Fresco.newDraweeControllerBuilder()
                .setControllerListener(object : ControllerListener<ImageInfo> {
                    override fun onFailure(id: String?, throwable: Throwable?) {
                    }

                    override fun onRelease(id: String?) {
                    }

                    override fun onSubmit(id: String?, callerContext: Any?) {
                    }

                    override fun onIntermediateImageSet(id: String?, imageInfo: ImageInfo?) {
                    }

                    override fun onIntermediateImageFailed(id: String?, throwable: Throwable?) {
                    }

                    override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
                        val width: Int = if (imageInfo == null) 1 else imageInfo.width
                        val height: Int = if (imageInfo == null) 1 else imageInfo?.height
                        var finalHeight = (height * realWidth / 2) / width

                        var lp: ViewGroup.LayoutParams = holder.image.layoutParams
                        lp.width = realWidth / 2
                        lp.height = finalHeight
                        holder.image.layoutParams = lp
                    }

                })
                .setUri(Uri.parse(datas.get(position).url))
                .build()
        holder.image.setController(controller)
    }


    class GirlViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var image: SimpleDraweeView = view.image
    }
}