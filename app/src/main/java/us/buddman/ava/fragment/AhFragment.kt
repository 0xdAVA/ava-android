package us.buddman.ava.fragment

import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.fragment_ah.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.buddman.ava.AhDetailViewActivity
import us.buddman.ava.BR
import us.buddman.ava.R
import us.buddman.ava.databinding.ContentAhBinding
import us.buddman.ava.databinding.ContentAhHeaderBinding
import us.buddman.ava.databinding.ContentAhHeaderInsideBinding
import us.buddman.ava.models.Ah
import us.buddman.ava.utils.NetworkHelper
import java.util.*

/**
 * Created by Junseok on 2017-09-21.
 */
class AhFragment : Fragment() {

    var dataArray: ArrayList<Any> = ArrayList()
    var adapter: LastAdapter? = null
    var layoutManager: GridLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_ah, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = GridLayoutManager(context, 2)
        layoutManager!!.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) 2 else 1
            }

        }
        mainAhRV.layoutManager = layoutManager
        updateData()

    }

    fun updateData() {
        NetworkHelper.instance.getAh5thList().enqueue(object : Callback<ArrayList<Ah>> {
            override fun onFailure(call: Call<ArrayList<Ah>>, t: Throwable) {
                Log.e("asdfFail", t.toString())
            }

            override fun onResponse(call: Call<ArrayList<Ah>>, response: Response<ArrayList<Ah>>) {
                Log.e("asdf", response.body()!!.size.toString())
                when (response.code()) {
                    200 -> run {
                        dataArray.add(response.body()!!)
                        NetworkHelper.instance.getAhPostList().enqueue(object : Callback<ArrayList<Ah>> {
                            override fun onFailure(call: Call<ArrayList<Ah>>, t: Throwable) {
                                Log.e("asdfFail", t.message)
                            }

                            override fun onResponse(call: Call<ArrayList<Ah>>, response: Response<ArrayList<Ah>>) {
                                Log.e("asdf", response.body()!!.size.toString())
                                when (response.code()) {
                                    200 -> run {
                                        for (a in response.body()!!) {
                                            dataArray.add(a)
                                        }
                                        updateList()

                                    }
                                    else -> {

                                    }
                                }
                            }
                        })
                    }
                    else -> {

                    }
                }
            }
        })
    }

    fun updateList() {
        adapter = LastAdapter(dataArray, BR.content)
                .map<ArrayList<Ah>, ContentAhHeaderBinding>(R.layout.content_ah_header) {
                    onBind {
                        var manager = LinearLayoutManager(context)
                        manager.orientation = LinearLayout.HORIZONTAL
                        it.binding.mainAhHeaderRV.layoutManager = manager
                        LastAdapter(dataArray[0] as ArrayList<Ah>, BR.content)
                                .map<Ah, ContentAhHeaderInsideBinding>(R.layout.content_ah_header_inside) {
                                    onBind {
                                        it.binding.image.setImageURI(Uri.parse((dataArray[0] as ArrayList<Ah>)[it.layoutPosition].photo))
                                        it.binding.profileImage.setImageURI(Uri.parse((dataArray[0] as ArrayList<Ah>)[it.layoutPosition].profile_img))
                                    }
                                    onClick {
                                        startActivity(Intent(context, AhDetailViewActivity::class.java).putExtra("postToken", (dataArray[0] as ArrayList<Ah>)[it.layoutPosition].post_token))
                                    }
                                }
                                .into(it.binding.mainAhHeaderRV)
                    }
                }
                .map<Ah, ContentAhBinding>(R.layout.content_ah){
                    onBind {
                        it.binding.contentImage.setImageURI(Uri.parse((dataArray[it.layoutPosition] as Ah).photo))
                        it.binding.profileImage.setImageURI(Uri.parse((dataArray[it.layoutPosition] as Ah).profile_img))
                    }
                    onClick {
                        startActivity(Intent(context, AhDetailViewActivity::class.java).putExtra("postToken", (dataArray[it.layoutPosition] as Ah).post_token))
                    }
                }
                .into(mainAhRV)
    }

}