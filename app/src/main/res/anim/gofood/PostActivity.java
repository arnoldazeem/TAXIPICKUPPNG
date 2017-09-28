package incop.ark.lyte.adaboo.gofood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by adaboo on 8/23/17.
 */

    //private static final String ENDPOINT = "https://kylewbanks.com/rest/posts.json";

    public class PostActivity extends AppCompatActivity {

       private static final String JsonURL = "https://gofoodpng.biz/api/hello/get_categories_";
        private ProgressBar progressBar;
        // String data = "";
        // Defining the Volley request queue that handles the URL request concurrently
        RequestQueue requestQueue;
        ProgressDialog progress;
        JSONArray products  = null;
        GridView grid;
        String finals;
        String urls;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_post);
            // Creates the Volley request queue
             requestQueue = Volley.newRequestQueue(this);

           // progressBar=(ProgressBar)findViewById(R.id.progressBar);

            Intent iin= getIntent();
            Bundle b = iin.getExtras();


            try {
                urls =(String) b.get("url");

            }catch (Exception e){
                Log.d("url issues",e.toString());
            }

             finals = JsonURL + urls + "/";

            Toast.makeText(incop.ark.lyte.adaboo.gofood.PostActivity.this, "You Clicked at " +finals, Toast.LENGTH_SHORT).show();

            progress = new ProgressDialog(this);
            //initialize the progress dialog and show it
            progress.setMessage("loading The data....");
            progress.show();

            JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, finals, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub

                    ArrayList<restaurant_List_Bulk> items = new ArrayList<restaurant_List_Bulk>();
                    try {

                    if (response != null) {


                        products = response.getJSONArray("results");


                        for (int i = 0; i < products.length();  i++) {

                            String all = products.get(i).toString();

                           // List<restaurant_List_Bulk> elephantList = Arrays.asList(all.split(","));
                            String[] separated = all.split(",");

                            if (separated.length == 3){

                            items.add(new restaurant_List_Bulk( separated[0],separated[1],separated[2]));
                            }else{

                                items.add(new restaurant_List_Bulk( separated[0],separated[1],null));
                            }
                        }


                        incop.ark.lyte.adaboo.gofood.Postactivity_adapter adapter = new incop.ark.lyte.adaboo.gofood.Postactivity_adapter(incop.ark.lyte.adaboo.gofood.PostActivity.this, items);
                        grid=(GridView)findViewById(R.id.grid);
                        grid.setAdapter(adapter);
                        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                               Toast.makeText(incop.ark.lyte.adaboo.gofood.PostActivity.this, "You Clicked at " + position, Toast.LENGTH_SHORT).show();

                            }
                        });

                    }

                    }catch (JSONException e) {
                            // TODO: handle exception
                            e.printStackTrace();
                        } catch (Exception ex) {
                        // TODO: handle exception
                        ex.printStackTrace();
                        System.out.println("********************* "
                                + ex.toString());
                        Toast.makeText(incop.ark.lyte.adaboo.gofood.PostActivity.this,
                                ex.toString(), Toast.LENGTH_LONG)
                                .show();
                    }

                            progress.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                    progress.dismiss();
                }




                });
            requestQueue.add(jsObjRequest);



        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__home, menu);
        return true;
    }

}