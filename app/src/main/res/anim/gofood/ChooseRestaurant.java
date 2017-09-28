package incop.ark.lyte.adaboo.gofood;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by adaboo on 8/19/17.
 */

public class ChooseRestaurant extends AppCompatActivity {

    //for the sliding view
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.banner1,R.drawable.banner2,R.drawable.banner3};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();


    private RecyclerView recyclerView;
    private incop.ark.lyte.adaboo.gofood.RestaurantAdapter adapter;

    //my setter go here
    private List<incop.ark.lyte.adaboo.gofood.Restaurant_Model> albumList;

    DrawerLayout drawerLayout;

    final String TAG = this.getClass().getSimpleName();

    RequestQueue mRequestQueue;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = incop.ark.lyte.adaboo.gofood.StaticVariables.loginUrl;

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response

                        //response was perfect
                        Log.d(TAG,response );

                        Post post = gson.fromJson(response, Post.class);

                         Log.d(TAG, post.toString() );

                       // List<Post> posts = Arrays.asList(gson.fromJson(response, Post[].class));
                       // Log.i("PostActivity", posts.size() + " posts loaded.");
                        //for (Post post : posts) {
                         //   Log.i("PostActivity", post.company_name + ": " + post.company_image);
                       // }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        // Handle error

                        if(error instanceof TimeoutError){


                        }else if (error instanceof NoConnectionError){


                        }else if (error instanceof NetworkError){


                        }else if (error instanceof ServerError){


                        }else if (error instanceof AuthFailureError){


                        }else if (error instanceof ParseError){


                        }

                            //Log.d(TAG,error.toString() );
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("username","arnold");
                params.put("password","1234");

                return params;
            }
        };



        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

        }
    }