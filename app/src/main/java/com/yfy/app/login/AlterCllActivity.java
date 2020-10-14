package com.yfy.app.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.login.AlterTellReq;
import com.yfy.base.activity.BaseActivity;
import com.yfy.db.UserPreferences;
import com.yfy.final_tag.AppLess;
import com.yfy.final_tag.RegexUtils;
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

//修改电话
public class AlterCllActivity extends BaseActivity implements Callback<ResEnv> {
    private static final String TAG = AlterCllActivity.class.getSimpleName();
    @Bind(R.id.call_edit_first)
    EditText first;
    @Bind(R.id.call_edit_again)
    EditText again;
    private String first_editor,again_editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_call_edit);
        initSQToolbar();
        if (StringJudge.isEmpty(UserPreferences.getInstance().getTell())){
        }else{
            first.setText(UserPreferences.getInstance().getTell());
        }
    }

    public void initSQToolbar(){
        assert toolbar!=null;
        toolbar.setTitle("联系电话");
        toolbar.addMenuText(1,R.string.ok);
        toolbar.setOnMenuClickListener(new SQToolBar.OnMenuClickListener() {
            @Override
            public void onClick(View view, int position) {
                closeKeyWord();
                isSend();

            }
        });
    }

    public void  isSend(){
        first_editor=first.getText().toString().trim();
        again_editor=again.getText().toString().trim();
        if (StringJudge.isEmpty(first_editor)){
            toast("请输入电话号码");
            return ;
        }
        if (StringJudge.isEmpty(first_editor)){
            toast("请再次输入电话号码");
            return ;
        }
        if (first_editor.equals(again_editor)){
            alterTell(first_editor);
        }else{
            toast("确认号码是否一致！");
        }
    }





    /**
     * ----------------------------retrofit-----------------------
     */



    private void alterTell(String no) {

        if (RegexUtils.isMobilePhoneNumber(no)){
        }else{
            toast("支持：13，14，15，17，18，19 .手机号段");
            return;
        }
        ReqEnv evn = new ReqEnv();
        ReqBody body = new ReqBody();
        AlterTellReq req = new AlterTellReq();

        req.setNo(no);
        body.alterTellReq = req;
        evn.body = body;
        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().alter_phone(evn);
        call.enqueue(this);
        showProgressDialog("");
        Logger.e(evn.toString());
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
            if (b.alter_tell_res!=null){
                String result=b.alter_tell_res.result;
                Logger.e( call.request().headers().toString()+result);
                UserRes userinfor= gson.fromJson(result,UserRes.class);
                if (userinfor.getResult().equals(TagFinal.TRUE)) {
                    toast("联系号码设置成功！");
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
