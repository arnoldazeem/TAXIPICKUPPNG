package incop.ark.lyte.adaboo.gofood;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Fragment extends Fragment implements OnClickListener {
	private static View view;

	private static EditText emailid, password;
	private static Button loginButton;
	private static TextView forgotPassword, signUp;
	private static CheckBox show_hide_password;
	private static LinearLayout loginLayout;
	private static Animation shakeAnimation;
	private static FragmentManager fragmentManager;
    ProgressDialog pDialog;
    AQuery aq;
    incop.ark.lyte.adaboo.gofood.SessionManager session;
    String getEmailId,getPassword;

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
        session = new incop.ark.lyte.adaboo.gofood.SessionManager(getActivity().getApplicationContext());

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
							incop.ark.lyte.adaboo.gofood.Utils.ForgotPassword_Fragment).commit();
			break;

		case R.id.link_signup:

			// Replace signup frgament with animation
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer, new incop.ark.lyte.adaboo.gofood.SignUp_Fragment(),
							incop.ark.lyte.adaboo.gofood.Utils.SignUp_Fragment).commit();
			break;
		}

	}

	// Check Validation before login
	private void checkValidation() {
		// Get email id and password
         getEmailId = emailid.getText().toString();
		 getPassword = password.getText().toString();

		// Check patter for email id
		Pattern p = Pattern.compile(incop.ark.lyte.adaboo.gofood.Utils.regEx);

		Matcher m = p.matcher(getEmailId);

		// Check for both field is empty or not
		if (getEmailId.equals("") || getEmailId.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			new incop.ark.lyte.adaboo.gofood.CustomToast().Show_Toast(getActivity(), view,
					"Enter both credentials.");

		}
		// Check if email id is valid or not
		else if (!m.find())
			new incop.ark.lyte.adaboo.gofood.CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");
		// Else do login and do your stuff
		else
			//Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
			//		.show();
            login();

	}




	//login
    private void login() {
        // TODO Auto-generated method stub
        pDialog.setMessage("Loging in..");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        Map<String, Object> params = new HashMap<String, Object>();
        aq.progress(pDialog).ajax(
                incop.ark.lyte.adaboo.gofood.StaticVariables.loginUrl + "login&username=" + getEmailId
                        + "&password=" + getPassword, params,
                JSONObject.class,

                new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject json,
                                         AjaxStatus status) {

                        try {

                            System.out.println(json.toString());
                            int success = json.getInt(incop.ark.lyte.adaboo.gofood.StaticVariables.SUCCESS);

                            if (success == 1) {

                                int id = json.getInt(incop.ark.lyte.adaboo.gofood.StaticVariables.USER_ID);

                                session.createLoginSession(id);

                                //Intent intent = new Intent(getActivity(),
                                //        Yenfa_Bus.class);

                               /// intent.putExtra("user_id", id);
                               // startActivity(intent);
                               // finish();

                            } else {

                                Toast.makeText(
                                        getActivity(),
                                        json.getString(incop.ark.lyte.adaboo.gofood.StaticVariables.MESSAGE),
                                        Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception ex) {
                            // TODO: handle exception
                            ex.printStackTrace();
                            System.out.println("********************* "
                                    + ex.toString());
                            Toast.makeText(getActivity(),
                                    "Server cannot be found", Toast.LENGTH_LONG)
                                    .show();
                        }

                    }
                });

    }




}
