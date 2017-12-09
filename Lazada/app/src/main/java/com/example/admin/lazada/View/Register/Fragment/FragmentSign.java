package com.example.admin.lazada.View.Register.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.lazada.R;
import com.example.admin.lazada.View.Home.HomeActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

/**
 * Created by ADMIN on 12/1/2017.
 */

public class FragmentSign extends Fragment implements View.OnClickListener {

    Button btnDangNhapFacebook,btnDangNhapGoogle,btnDangNhap;
    CallbackManager callbackManager;

    //GoogleApiClient mGoogleApiClient;
    public static int SIGN_IN_GOOGLE_PLUS = 111;
    ProgressDialog progressDialog;
    EditText edTenDangNhap,edMatKhau;

    private void init(View view){

        btnDangNhapFacebook = view.findViewById(R.id.btnLoginFacebook);
        btnDangNhapGoogle = view.findViewById(R.id.btnLoginGoogle);
        btnDangNhap =  view.findViewById(R.id.btnLogin);
        edTenDangNhap = view.findViewById(R.id.txtEmail);
        edMatKhau = view.findViewById(R.id.txtPassword);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign, container, false);
        init(view);
        /*try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.example.admin.lazada",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), HomeActivity.class);
               startActivity(iTrangChu);
                Log.d("Status","Thanh cong");
            }

            @Override
            public void onCancel() {
                Log.d("Status","Thoat ra");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Status","Loi");
            }
        });

        btnDangNhapFacebook.setOnClickListener(this);

        return  view;
    }


    @Override
    public void onClick(View view) {
        LoginManager.getInstance().logInWithReadPermissions(FragmentSign.this, Arrays.asList("public_profile"));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
