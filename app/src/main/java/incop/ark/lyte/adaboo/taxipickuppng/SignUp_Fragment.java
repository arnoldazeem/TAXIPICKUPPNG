package incop.ark.lyte.adaboo.taxipickuppng;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.md5simply.MD5;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class SignUp_Fragment extends Fragment implements OnClickListener {
	private static View view;
	private static EditText fullName, emailId, mobileNumber, location,
			password, confirmPassword,state,zippy;

	private static TextView login;
	private static Button signUpButton;
	private static CheckBox terms_conditions;


    RequestQueue requestQueue;
    String finals;
    String getFullName;
    String getEmailId;
    String getMobileNumber;
    String getLocation;
    String getCity;
    String getZipcode;
    String getPassword;
    String getConfirmPassword;
	String getencryptedpassword;
    ArrayAdapter<String> adapter;
    MaterialBetterSpinner spinnercity;

    private static String S_URL ="https://vast-springs-89039.herokuapp.com/register";

    CheckBox checkBoxTerms;private Snackbar snackbar;
    //private ProgressDialog pd;
    ProgressBar  pd;

    String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

	public SignUp_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.register, container, false);
		initViews();
		setListeners();



		return view;
	}

	// Initialize all views
	private void initViews() {
		fullName = (EditText) view.findViewById(R.id.fullName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
		location = (EditText) view.findViewById(R.id.location);
       // state = (EditText) view.findViewById(R.id.state);
        zippy = (EditText) view.findViewById(R.id.zipcode);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);


        spinnercity = (MaterialBetterSpinner)
                view.findViewById(R.id.spinnerci);



         adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);


        spinnercity.setAdapter(adapter);


}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signUpBtn:

			// Call checkValidation method
			checkValidation();
			break;

		case R.id.already_user:

			// Replace login fragment
			new Authentication().replaceLoginFragment();
			break;
		}

	}

	// Check Validation Method
	private void checkValidation() {

		// Get all edittext texts
		 getFullName = fullName.getText().toString();
		 getEmailId = emailId.getText().toString();
		 getMobileNumber = mobileNumber.getText().toString();
		 getLocation = location.getText().toString();
         getCity = spinnercity.getText().toString();
         getZipcode = zippy.getText().toString();
		 getPassword = password.getText().toString();
		 getConfirmPassword = confirmPassword.getText().toString();

		getencryptedpassword = MD5.encrypt(getPassword);

		// Pattern match for email id
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getLocation.equals("") || getLocation.length() == 0
                || getCity.equals("") || getCity.length() == 0
                || getZipcode.equals("") || getZipcode.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0)

			new CustomToast().Show_Toast(getActivity(), view,
					"All fields are required.");


		// Check if email id valid or not
		else if (!m.find())
			new CustomToast().Show_Toast(getActivity(), view,
					"Your Email Id is Invalid.");

		// Check if both password should be equal
		else if (!getConfirmPassword.equals(getPassword))
			new CustomToast().Show_Toast(getActivity(), view,
					"Both password doesn't match.");

		// Make sure user should check Terms and Conditions checkbox
		else if (!terms_conditions.isChecked())
			new CustomToast().Show_Toast(getActivity(), view,
					"Please select Terms and Conditions.");

		// Else do signup or do your stuff
		else {
			Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT)
					.show();

            try {
              //  UserRegistration();
            }catch (Exception E){
               E.printStackTrace();
            }

		}

	}




    public void UserRegistration(){

        // Showing progress dialog at user registration time.
        //progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
        //progressDialog.show();

        finals = "https://vast-springs-89039.herokuapp.com/register";

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, finals,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                       // progressDialog.dismiss();

                        // Showing Echo Response Message Coming From Server.
                        Toast.makeText(getActivity(), ServerResponse, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                       // progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(getActivity(), volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("name", getFullName);
                params.put("email", getEmailId);
                params.put("MobileNumber", getMobileNumber);
                params.put("location", getLocation);
                params.put("User_Password", getPassword);
                params.put("city", getCity);



                //params.put("User_Full_Name", NameHolder);

                return params;
            }

                @Override
                public Map<String, String> getHeaders(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                String creds = String.format("%s:%s","taxiUserBeta","taxiUserBetaPass123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }


    private void signupRequest() throws Exception{


        requestQueue = Volley.newRequestQueue(getActivity());

        finals = "https://vast-springs-89039.herokuapp.com/register";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("firstName", "Rocky");
        jsonBody.put("lastName", "Balboa");

        final String mRequestBody = jsonBody.toString();

        RequestQueue mQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, finals, jsonBody, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", response + "toString()");
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
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



    public void showSnackbar(String stringSnackbar){
        //snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
          //      .setActionTextColor(getResources().getColor(R.color.colorPrimary))
          //      .show();
    }


}
