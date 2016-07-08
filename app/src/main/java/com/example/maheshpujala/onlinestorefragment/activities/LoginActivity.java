package com.example.maheshpujala.onlinestorefragment.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maheshpujala.onlinestorefragment.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.util.concurrent.Callable;
/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private String gtext;
    // UI references.
    private SignInButton mGoogleSignInButton;
    private LoginButton mFacebookSignInButton;
    // Vars
    private GoogleApiClient mGoogleApiClient;
    private CallbackManager mFacebookCallbackManager;
    private static final int RC_SIGN_IN = 9001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);

        // Set Up Social Logins
        mFacebookSignInButton = (LoginButton) findViewById(R.id.login_button);
        getLoginDetails(mFacebookSignInButton);

        mGoogleSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        gtext="Login with Google";
        setGooglePlusButtonText(mGoogleSignInButton,gtext);
        mGoogleSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }
    protected void setGooglePlusButtonText(SignInButton signInButton,
                                           String buttonText) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTextSize(15);
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setText(buttonText);
                tv.setGravity(Gravity.CENTER);
                return;
            }
        }
    }


    /*
      Initialize the facebook sdk and then callback manager will handle the login responses.
   */
    protected void facebookSDKInitialize() {

        FacebookSdk.sdkInitialize(getApplicationContext());

        mFacebookCallbackManager = CallbackManager.Factory.create();
    }

    protected void getLoginDetails(LoginButton mFacebookSignInButton){
        // Callback registration
        mFacebookSignInButton.registerCallback(mFacebookCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult login_result) {
                        handleSignInResult(new Callable<Void>() {
                            @Override
                            public Void call() throws Exception {
                                LoginManager.getInstance().logOut();
                                return null;
                            }
                        });
                    }
                    @Override
                    public void onCancel() {
                        handleSignInResult(null);
                    }
                    @Override
                    public void onError(FacebookException error) {
                        Log.d(LoginActivity.class.getCanonicalName(), error.getMessage());
                        handleSignInResult(null);
                    }
                }
        );
    }
    private void signInWithGoogle() {
        if(mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(result.isSuccess()) {
                Log.d( "handleSignInResult:"," + result.isSuccess()");
                final GoogleApiClient client = mGoogleApiClient;
                GoogleSignInAccount acct = result.getSignInAccount();
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                Uri pPhoto = acct.getPhotoUrl();
                String personPhoto =pPhoto.toString();
                Intent googleIntent = getIntent();

                googleIntent.putExtra("pname",personName);
                googleIntent.putExtra("pemail", personEmail);
                googleIntent.putExtra("pphoto", personPhoto);

                setResult(Activity.RESULT_OK, googleIntent);
                finish();
               /* handleSignInResult(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        if (client != null) {

                            Auth.GoogleSignInApi.signOut(client).setResultCallback(
                                    new ResultCallback<Status>() {
                                        @Override
                                        public void onResult(Status status) {
                                            Log.d(LoginActivity.class.getCanonicalName(),
                                                    status.getStatusMessage());

                                        *//* TODO: handle logout failures *//*
                                        }
                                    }
                            );

                        }

                        return null;
                    }
                });*/

            } else {
                handleSignInResult(null);
            }
        }  else {
            mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
            Log.e("data",data.toString());

        }
    }


    private void handleSignInResult(Callable<Void> logout) {
        if (logout == null) {
            /* Login error */
            Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
        } else {
            /* Login success */
            /*
       To get the facebook user's own profile information via  creating a new request.
       When the request is completed, a callback is called to handle the success condition.
    */
            GraphRequest data_request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject json_object,
                                GraphResponse response) {


                            Intent returnIntent = getIntent();
                            returnIntent.putExtra("Fb_Details", json_object.toString());
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        }
                    });
            Bundle permission_param = new Bundle();
            permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
            data_request.setParameters(permission_param);
            data_request.executeAsync();

        }
    }
      /**
         * Attempts to sign in or register the account specified by the login form.
         * If there are form errors (invalid email, missing fields, etc.), the
         * errors are presented and no actual login attempt is made.
         */
        private void attemptLogin() {
            Intent login = new Intent(this, HomeActivity.class);

            startActivity(login);
        }

    @Override
    public void onBackPressed() {
        this.finish();
            super.onBackPressed();
        }
    }