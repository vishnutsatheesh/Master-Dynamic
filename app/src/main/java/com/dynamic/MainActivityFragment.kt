package com.dynamic


import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_dynamic.*
import org.json.JSONException
import org.json.JSONObject
import com.dynamic.view.DynamicHelper
import com.dynamic.view.DynamicId
import com.dynamic.view.DynamicView


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dynamic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }


        var jsonObject: JSONObject?

        try {

            val file_name = "viewJson.json"
            val json_string = context!!.resources.assets.open(file_name).bufferedReader().use {
                it.readText()
            }
            Log.e(">>>", "json_string- $json_string")

            jsonObject = JSONObject(json_string)

        } catch (je: JSONException) {
            je.printStackTrace()
            jsonObject = null
        }


        if (jsonObject != null) {

            /* create dynamic view and return the view with the holder class attached as tag */

            var json_array = jsonObject.getJSONArray("views")


            val layout = LinearLayout(context)

            layout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            layout.orientation = LinearLayout.VERTICAL
            linlay_main.addView(layout)
            DynamicHelper.mContext = context
            for (i in 0..json_array.length() - 1) {
                val sampleView =
                    DynamicView.createView(this!!.context!!, json_array.getJSONObject(i), TempViewHolder::class.java)
                layout.addView(sampleView)
            }


            // sampleView.layoutParams.width=ViewGroup.LayoutParams.MATCH_PARENT
            /* get the view with id "testClick" and attach the onClickListener */
//            (sampleView.getTag() as TempViewHolder).clickableView!!.setOnClickListener(this)

//            /* add Layout Parameters in just created view and set as the contentView of the activity */

//            setContentView(sampleView)


        } else {
            Log.e("Json2View", "Could not load valid json file")
        }
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * Keeps Class of UI Component from the Dynamic View
     */
    class TempViewHolder {
        @DynamicId(id = "testClick")
        var clickableView: View? = null
    }
}
