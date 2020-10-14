package com.yfy.app.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.login.AlterPasswordReq;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.db.GreenDaoManager;
import com.yfy.db.UserPreferences;
import com.yfy.final_tag.AppLess;
import com.yfy.final_tag.StringJudge;
import com.yfy.final_tag.TagFinal;
import com.yfy.jpush.Logger;
import com.yfy.json.JsonParser;
import com.yfy.wuhoudish.R;
import com.yfy.view.SQToolBar;

import java.io.IOException;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//修改密码
public class AlterActivity extends BaseActivity implements Callback<ResEnv> {
    private static final String TAG = AlterActivity.class.getSimpleName();

    @Bind(R.id.alter_old_password)
    EditText old;
    @Bind(R.id.alter_new_first_password)
    EditText first;
    @Bind(R.id.alter_new_again_password)
    EditText again;
    private String oldpass,firstpass,againpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_alter);
        initSQToolbar();
    }


    public void initSQToolbar(){
        assert toolbar!=null;
        TextView titlebar=toolbar.setTitle("修改密码");
        TextView menuOne=toolbar.addMenuText(1,R.string.ok);
        toolbar.setOnMenuClickListener(new SQToolBar.OnMenuClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (isSend()){
                    alterpass();
                }

            }
        });
    }

    public boolean isSend(){
        oldpass=old.getText().toString().trim();
        firstpass=first.getText().toString().trim();
        againpass=again.getText().toString().trim();
        if (StringJudge.isEmpty(oldpass)){
            toast("请输入密码");
            return false;
        }
        if (StringJudge.isEmpty(firstpass)){
            toast("请输入新密码");
            return false;
        }
        if (StringJudge.isEmpty(againpass)){
            toast("请再次输入新密码");
            return false;
        }
        if (firstpass.equals(againpass)){

        }else{
            toast("新密码输入不一致");
//            Log.e("zxx","alter "+firstpass+"  "+againpass);
            return false;
        }
        return true;
    }





    /**
     * ----------------------------retrofit-----------------------
     */



    private void alterpass() {

        ReqEnv evn = new ReqEnv();
        ReqBody body = new ReqBody();
        AlterPasswordReq req = new AlterPasswordReq();

        req.setNewPassword(firstpass);
        req.setOldPassword(oldpass);
        body.alterPasswordReq = req;
        evn.body = body;
        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().alter_password(evn);
        call.enqueue(this);
        showProgressDialog("");
    }
    @Override
    public void onResponse(Call<ResEnv> call, Response<ResEnv> response) {
        if (response.code()==500){
            try {
                String s=response.errorBody().string();
                Logger.e(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            toastShow("数据出差了");
        }
        if (!isActivity())return;
        dismissProgressDialog();
        ResEnv respEnvelope = response.body();
        if (respEnvelope != null) {
            ResBody b=respEnvelope.body;
            if (b.alterPasswordRes !=null){
                String result=b.alterPasswordRes.result;
                Logger.e( call.request().headers().toString()+result);
                UserRes userinfor= gson.fromJson(result,UserRes.class);
                if (userinfor.getResult().equals(TagFinal.TRUE)) {
                    Base.user=null;
                    UserPreferences.getInstance().clearUserData();
                    GreenDaoManager.getInstance().clearUser();
                    toast("密码修改成功，请重新登录");
                    setResult(RESULT_OK);
                    onPageBack();
                } else {
                    toast(JsonParser.getErrorCode(result));
                }
            }
        }else{
            toastShow(R.string.fail_do_not);
        }

    }

    @Override
    public void onFailure(Call<ResEnv> call, Throwable t) {
        if (!isActivity())return;
        dismissProgressDialog();
        Logger.e( "onFailure  "+call.request().headers().toString() );
        toast(R.string.fail_do_not);

    }

    @Override
    public boolean isActivity() {
        return AppLess.isTopActivy(TAG);
    }
}
