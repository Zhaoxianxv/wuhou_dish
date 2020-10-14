package com.yfy.app.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.yfy.app.net.ReqBody;
import com.yfy.app.net.ReqEnv;
import com.yfy.app.net.ResBody;
import com.yfy.app.net.ResEnv;
import com.yfy.app.net.RetrofitGenerator;
import com.yfy.app.net.login.UserLoginReq;
import com.yfy.base.Base;
import com.yfy.base.activity.BaseActivity;
import com.yfy.db.GreenDaoManager;
import com.yfy.db.User;
import com.yfy.db.UserPreferences;
import com.yfy.dialog.ConfirmAlbumWindow;
import com.yfy.final_tag.AppLess;
import com.yfy.final_tag.RxCaptcha;
import com.yfy.final_tag.StringJudge;
import com.yfy.final_tag.TagFinal;
import com.yfy.final_tag.Utils;
import com.yfy.jpush.Logger;
import com.yfy.json.JsonParser;
import com.yfy.keyboard.password.KeyboardTouchListener;
import com.yfy.keyboard.password.KeyboardUtil;
import com.yfy.wuhoudish.MainActivity;
import com.yfy.wuhoudish.R;
import com.yfy.wuhoudish_stu.MainStuActivity;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yfy.final_tag.RxCaptcha.TYPE.NUMBER;

/**
 * A login screen that offers login via email/password.
 *
 *
 */
public class LoginActivity extends BaseActivity implements Callback<ResEnv> {

    private final static String TAG = LoginActivity.class.getSimpleName();

    @Bind(R.id.login_password)
    EditText password;
    @Bind(R.id.login_phone)
    EditText phone;

    private String account, passwords;


    private String userType;

    @Bind(R.id.login_code_image)
    ImageView code_icon;
    @Bind(R.id.login_code)
    EditText code;
    private String edit_code = "";
    private String code_s="";
    private RxCaptcha rxCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
//        if (App.isServiceRunning(mActivity,"UploadDataService")){
//            Logger.e("UploadDataService: " );
//        }else{
//            startService(new Intent(mActivity,UploadDataService.class));//开启更新
//        }
        initToolbar();
        initDialog();
        rxCaptcha=RxCaptcha.build();
        rxCaptcha
                .codeLength(4)
                .fontSize(60)
                .lineNumber(2)
                .size(200, 70)
                .type(NUMBER)
                .into(code_icon);
        code_s=rxCaptcha.getCode();

        initMoveKeyBoard();
    }


    @OnClick(R.id.login_code_image)
    void setImage(){
        rxCaptcha=RxCaptcha.build();
        rxCaptcha
                .codeLength(4)
                .fontSize(60)
                .lineNumber(2)
                .size(200, 70)
                .type(NUMBER)
                .into(code_icon);
        code_s=rxCaptcha.getCode();
    }



    private void initToolbar() {
        assert toolbar!= null;
        toolbar.setTitle(R.string.app_logining);
    }

    @OnClick( R.id.login_buttom)
    void setlogin(){
        closeKeyWord();
        account=phone.getText().toString();
        passwords=password.getText().toString();
        if (Utils.isEmpty(passwords)) {
            toast( R.string.app_login_password);
            return;
        }
        if (Utils.isEmpty(account)) {
            toast(R.string.app_login_phone);
            return;
        }


        edit_code=code.getText().toString();
        if (StringJudge.isEmpty(edit_code)) {
            toastShow(R.string.please_edit_code);
            return ;
        }
        if (!edit_code.equals(code_s)){
            toastShow(R.string.please_edit_yse_code);
            return ;
        }

        album_select.showAtBottom();
    }



    ConfirmAlbumWindow album_select;
    private void initDialog() {
        album_select = new ConfirmAlbumWindow(mActivity);
        album_select.setOne_select("教师");
        album_select.setTwo_select("学生");
        album_select.setName("请选择账号类型");
        album_select.setOnPopClickListenner(new ConfirmAlbumWindow.OnPopClickListenner() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.popu_select_one:
                        userType = "2";
                        startLogin();
                        break;
                    case R.id.popu_select_two:
                        userType = "1";
                        startLogin();
                        break;
                }

            }
        });
    }






    @OnClick(R.id.forget_login_password)
    void setforget(){
        Intent intent=new Intent(mActivity,PhoneCodectivity.class);
        startActivity(intent);
    }



    /**
     * ----------------------------retrofit-----------------------
     */


    private void startLogin() {
        //登陆时传的Constants.APP_ID：
        String apikey=JPushInterface.getRegistrationID(mActivity);
        if(apikey==null){
            apikey="";
        }



        ReqEnv evn = new ReqEnv();
        ReqBody reqBody = new ReqBody();
        UserLoginReq request = new UserLoginReq();
        //获取参数

        request.setUsername(account);
        request.setPassword(passwords);
        request.setRole_id(userType);
        request.setAppid(apikey);

        reqBody.userLoginReq = request;
        evn.body = reqBody;
        Call<ResEnv> call = RetrofitGenerator.getWeatherInterfaceApi().login_in(evn);
        call.enqueue(this);
        showProgressDialog("");

        Logger.e(evn.toString());


    }

    @Override
    public void onResponse(Call<ResEnv> call, Response<ResEnv> response) {
        if (response.code()==500){
            toastShow("数据出差了");
        }
        if (!isActivity())return;
        dismissProgressDialog();
        ResEnv respEnvelope = response.body();
        if (respEnvelope != null) {
            ResBody b=respEnvelope.body;
            if (b.userLoginRes !=null){
                String result=b.userLoginRes.result;
                Logger.e( call.request().headers().toString()+result);
                UserRes res= gson.fromJson(result,UserRes.class);
                if (res.getResult().equals(TagFinal.TRUE)) {
                    toast("登录成功");
                    User user= gson.fromJson(result,User.class);
                    user.setPass_word(passwords);
                    user.setUser_type(userType);
                    Base.user=user;
                    UserPreferences.getInstance().saveSession_key(user.getSession_key());
                    GreenDaoManager.getInstance().clearUser();
                    GreenDaoManager.getInstance().saveNote(user);
//                    setResult(RESULT_OK);
//                    finish();
                    Intent intent;
                    if (user.getUser_type().equalsIgnoreCase("1")){
                        intent=new Intent(mActivity,MainStuActivity.class);
                    }else{
                        intent = new Intent(mActivity,MainActivity.class);
                    }
                    startActivity(intent);
                    finish();
                } else {
                    toast(JsonParser.getErrorCode(result));
                }
            }
        }else{
            Logger.e("evn:null----"+response.code());
            toastShow("网络配置出错");
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




    //password edit keyboard
    @Bind(R.id.all_ed)
    LinearLayout rootView;
    @Bind(R.id.sv_main)
    ScrollView scrollView;
    private KeyboardUtil keyboardUtil;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 ) {
            if(keyboardUtil.isShow){
                keyboardUtil.hideSystemKeyBoard();
                keyboardUtil.hideAllKeyBoard();
                keyboardUtil.hideKeyboardLayout();
            }else {
                return super.onKeyDown(keyCode, event);
            }

            return false;
        } else
            return super.onKeyDown(keyCode, event);
    }

    private void initMoveKeyBoard() {
        keyboardUtil = new KeyboardUtil(this, rootView, scrollView);
        keyboardUtil.setOtherEdittext(phone,code);
        // monitor the KeyBarod state
        keyboardUtil.setKeyBoardStateChangeListener(new KeyBoardStateListener());
        // monitor the finish or next Key
        keyboardUtil.setInputOverListener(new inputOverListener());
        password.setOnTouchListener(new KeyboardTouchListener(keyboardUtil, KeyboardUtil.INPUTTYPE_ABC, -1));
    }

    class KeyBoardStateListener implements KeyboardUtil.KeyBoardStateChangeListener {

        @Override
        public void KeyBoardStateChange(int state, EditText editText) {
//            System.out.println("state" + state);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

    class inputOverListener implements KeyboardUtil.InputFinishListener {

        @Override
        public void inputHasOver(int onclickType, EditText editText) {
//            System.out.println("onclickType" + onclickType);
//            System.out.println("editText" + editText.getText().toString());
        }
    }

}

