package us.buddman.ava

import android.net.Network
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.activity_ah_detail_view.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.buddman.ava.databinding.ContentAhCommentBinding
import us.buddman.ava.models.Ah
import us.buddman.ava.models.Comment
import us.buddman.ava.utils.BaseActivity
import us.buddman.ava.utils.CredentialsManager
import us.buddman.ava.utils.NetworkHelper

class AhDetailViewActivity : BaseActivity() {

    lateinit var item: Ah
    var commentArr: ArrayList<Comment> = ArrayList()
    override fun setDefault() {

        NetworkHelper.instance.getAhPostDetail(intent.getStringExtra("postToken")).enqueue(object : Callback<Ah> {
            override fun onFailure(call: Call<Ah>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Ah>?, response: Response<Ah>?) {
                when (response!!.code()) {
                    200 -> run {
                        item = response.body()!!
                        initUI()
                    }
                    else -> {
                        Toast.makeText(applicationContext, "에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        NetworkHelper.instance.getAhCommentList(intent.getStringExtra("postToken")).enqueue(object : Callback<ArrayList<Comment>> {
            override fun onFailure(call: Call<ArrayList<Comment>>?, t: Throwable?) {
                Log.e("asdf", t!!.message)
            }

            override fun onResponse(call: Call<ArrayList<Comment>>?, response: Response<ArrayList<Comment>>?) {
                when (response!!.code()) {
                    200 -> run {
                        commentArr = response.body()!!
                        initComment()
                    }
                    else -> {
                        Toast.makeText(applicationContext, "에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        ahDetailViewRV.layoutManager = LinearLayoutManager(applicationContext)
        commentSendBtn.setOnClickListener {
            if (commentInput.text.toString().trim() == "")
                Toast.makeText(applicationContext, "빈칸 없이 입력해주세요", Toast.LENGTH_SHORT).show()
            else {
                NetworkHelper.instance.addCommentAh(
                        CredentialsManager.instance.activeUser.second!!.username,
                        CredentialsManager.instance.activeUser.second!!.user_token,
                        commentInput.text.toString().trim(),
                        item.post_token
                ).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                        when (response!!.code()) {
                            200 -> {
                                initComment()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                        Toast.makeText(applicationContext, "에러", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    fun initUI() {
        contentImage.setImageURI(Uri.parse(item.photo))
        profileImage.setImageURI(Uri.parse(item.profile_img))
        postTitle.text = item.title
        date.text = item.dateString
        author.text = item.author
        content.text = item.text
    }

    fun initComment() {
        LastAdapter(commentArr, BR.content)
                .map<Comment, ContentAhCommentBinding>(R.layout.content_ah_comment)
                .into(ahDetailViewRV)

    }

    override val viewId: Int = R.layout.activity_ah_detail_view
    override val toolbarId: Int = R.id.toolbar

}
