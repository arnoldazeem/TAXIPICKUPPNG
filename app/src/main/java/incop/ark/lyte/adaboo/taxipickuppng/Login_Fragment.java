package incop.ark.lyte.adaboo.taxipickuppng;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class Login_Fragment extends Fragment implements OnClickListener {
	private static View view;

	private static EditText emailid, password;
	private static Button loginButton;
	private static TextView forgotPassword, signUp;
	private static CheckBox show_hide_password;
	private static LinearLayout loginLayout;
	private static Animation shakeAnimation;
	private static FragmentManager fragmentManager;

    RequestQueue requestQueue;
    String finals;
    String getEmailId,getPassword;
    DelayedProgressDialog progressDialog;

	public Login_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.login, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initiate Views
	private void initViews() {


		fragmentManager = getActivity().getSupportFragmentManager();
        //session = new SessionManager(getActivity().getApplicationContext());

		emailid = (EditText) view.findViewById(R.id.input_email);
		password = (EditText) view.findViewById(R.id.input_password);
		loginButton = (Button) view.findViewById(R.id.loginBtn);
		forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
		signUp = (TextView) view.findViewById(R.id.link_signup);
		show_hide_password = (CheckBox) view
				.findViewById(R.id.show_hide_password);

		loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

		// Load ShakeAnimation
		shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
				R.anim.shake);

        //progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleSmall);

         progressDialog = new DelayedProgressDialog();

	}

	// Set Listeners
	private void setListeners() {
		loginButton.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
		signUp.setOnClickListener(this);

		// Set check listener over checkbox for showing and hiding password
		show_hide_password
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton button,
							boolean isChecked) {

						// If it is checkec then show password else hide
						// password
						if (isChecked) {

							show_hide_password.setText(R.string.hide_pwd);// change
																			// checkbox
																			// text

							password.setInputType(InputType.TYPE_CLASS_TEXT);
							password.setTransformationMethod(HideReturnsTransformationMethod
									.getInstance());// show password
						} else {
							show_hide_password.setText(R.string.show_pwd);// change
																			// checkbox
																			// text

							password.setInputType(InputType.TYPE_CLASS_TEXT
									| InputType.TYPE_TEXT_VARIATION_PASSWORD);
							password.setTransformationMethod(PasswordTransformationMethod
									.getInstance());// hide password

						}

					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginBtn:
			checkValidation();
			break;

		case R.id.forgot_password:

			// Replace forgot password fragment with animation
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer,
							new ForgotPassword_Fragment(),
							Utils.ForgotPassword_Fragment).commit();
			break;

		case R.id.link_signup:
			// Replace signup frgament with animation
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer, new SignUp_Fragment(),
							Utils.SignUp_Fragment).commit();
			break;
		}

	}

	// Check Validation before login
	private void checkValidation() {
		// Get email id and password
         getEmailId = emailid.getText().toString();
		 getPassword = password.getText().toString();

		// Check patter for email id
		Pattern p = Pattern.compile(Utils.regEx);

		Matcher m = p.matcher(getEmailId);

		// Check for both field is empty or not
		if (getEmailId.equals("") || getEmailId.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			new CustomToast().Show_Toast(getActivity(), view,
					"Enter both credentials.");

		}
		// Check if email id is valid or not
		else if (!m.find())
			new CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");
		// Else do login and do your stuff
		else{
			Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
					.show();
            try {
                signupRequest();
            }catch (Exception E){
                E.printStackTrace();
            }


        }

	}



	private void signupRequest() throws Exception{

		requestQueue = Volley.newRequestQueue(getActivity());

		finals = "https://vast-springs-89039.herokuapp.com/login";

        //"Rocky"
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("firstName","Rocky");
		jsonBody.put("password", getPassword);

		final String mRequestBody = jsonBody.toString();

		RequestQueue mQueue = Volley.newRequestQueue(getActivity());

        progressDialog.show(getActivity().getSupportFragmentManager(), "tag");



		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, finals, jsonBody, new Response.Listener<JSONObject>() {


			@Override
			public void onResponse(JSONObject response) {


                try {

                    Log.d("TAG", response.get("status").toString());

					String message = response.get("status").toString();

                   // Intent in = new Intent(getActivity(), MainActivity.class);
                   // startActivity(in);

                    if(message.contains("success")){
					SharedPreferences pref = getActivity().getSharedPreferences("loginData", MODE_PRIVATE);
					SharedPreferences.Editor editor = pref.edit();
					editor.putString("firstName", "Rocky");
					editor.putString("password", getPassword);
					editor.commit();

					Intent in = new Intent(getActivity(), MainActivity.class);
					startActivity(in);
				}
				else{
					Toast.makeText(getActivity(), "Something went wrong. Cannot login.", Toast.LENGTH_LONG).show();
				}

                }catch (Exception E){
                    E.printStackTrace();
                }



                progressDialog.cancel();

			}

            //dismiss or cancel the dialog
            //progressDialog.cancel();


		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);

                //dismiss or cancel the dialog
                progressDialog.cancel();
			}


		}){

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("Content-Type", "application/json");
				String creds = String.format("%s:%s","taxiUserBeta","taxiUserBetaPass123");
				String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
				params.put("Authorization", auth);
				return params;
			}

		};


		mQueue.add(jsonObjectRequest);

	}


}
