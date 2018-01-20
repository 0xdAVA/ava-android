package us.buddman.ava

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import us.buddman.ava.models.User
import us.buddman.ava.utils.BaseActivity
import us.buddman.ava.utils.CredentialsManager
import us.buddman.ava.utils.NetworkHelper

class LoginActivity : BaseActivity() {

    lateinit var callbackManager: CallbackManager
    override val viewId: Int = R.layout.activity_login
    override val toolbarId: Int = 0

    override fun setDefault() {
        configureFacebook()
        configureLocalLogin()
        registerButton.text = Html.fromHtml("아이디가 없으신가요? <b>회원가입</b>")
        registerButton.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }
        if (CredentialsManager.instance.activeUser.first!!) {
            NetworkHelper.instance.autoLogin(CredentialsManager.instance.activeUser.second!!.user_token).enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    when (response!!.code()) {
                        200 -> run {
                            CredentialsManager.instance.updateUserInfo(response.body()!!)
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            Toast.makeText(applicationContext, response.body()!!.username + "님 환영합니다!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        else -> {
                            progress.visibility = View.GONE
                            container.visibility = View.VISIBLE
                        }
                    }
                }

            })
        } else {
            progress.visibility = View.GONE
            container.visibility = View.VISIBLE
        }
    }

    fun configureFacebook() {
        callbackManager = CallbackManager.Factory.create()
        realFacebookButton.setReadPermissions("email")
        realFacebookButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.e("asdf", loginResult.accessToken.token)
                NetworkHelper.instance.facebookLogin(loginResult.accessToken.token).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        when (response!!.code()) {
                            200 -> {
                                CredentialsManager.instance.saveUserInfo(response.body()!!, 0)
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                                Toast.makeText(applicationContext, response.body()!!.username + "님 환영합니다!", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            else -> {
                                Toast.makeText(applicationContext, "로그인할 수 없습니다.", Toast.LENGTH_SHORT).show()
                                Log.e("asdf", response.code().toString())
                            }
                        }
                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Log.e("asdf", t!!.localizedMessage)
                    }
                })

            }

            override fun onCancel() {
                Log.e("asdf", "Canceled")

            }

            override fun onError(error: FacebookException) {
                Log.e("asdf", error.localizedMessage)

            }
        })
    }

    fun configureLocalLogin() {
        loginButton.setOnClickListener {
            if (emailInput.text.toString().trim() == "" || passwordInput.text.toString().trim() == "")
                Toast.makeText(applicationContext, "빈칸 없이 입력해주세요!", Toast.LENGTH_SHORT).show()
            else {

            }
        }
        fbButton.setOnClickListener {
            realFacebookButton.performClick()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
