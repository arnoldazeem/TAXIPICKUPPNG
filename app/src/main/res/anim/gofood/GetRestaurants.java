package incop.ark.lyte.adaboo.gofood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by adaboo on 9/13/17.
 */

public class GetRestaurants  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.getrestaurants);


            incop.ark.lyte.adaboo.gofood.CustomGrid adapter = new incop.ark.lyte.adaboo.gofood.CustomGrid(incop.ark.lyte.adaboo.gofood.GetRestaurants.this, webs, imageId);
            grid=(GridView)findViewById(R.id.grid);
            grid.setAdapter(adapter);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String vari = webs[position][1];
                    Intent ii = new Intent(incop.ark.lyte.adaboo.gofood.GetRestaurants.this, PostActivity.class);
                    ii.putExtra("url", vari);
                    startActivity(ii);

                    //  switch(position)
                  //  {
                  //      case 0:
                  //          Intent new1Activity = new Intent(GetRestaurants.this,PostActivity.class);
                  //          startActivity(new1Activity);
                  //          break;
                 //       case 1:
                 //           Intent myIntent = new Intent(GetRestaurants.this, PostActivity.class);
                            //myIntent.putExtra("key", value); //Optional parameters
                 //           startActivity(myIntent);
                //            break;
                //        default:

                 //   }

                }
            });

        }


    GridView grid;



    String[][] webs = {
            {"Tasty Bites" ,"tastybites"} ,
            {"Golden Bowl" ,"goldenbowl"} ,
            {"Kopitiam" ,"kopitiam"} ,
            {"Beijing Noodle House" ,"beijing"} ,

            {"Buffalo Burger" ,"buffaloburger"} ,
            {"Mr Mikes" ,"mrmikes"} ,
            {"Cellar-Restaurant" ,"cellar"} ,
            {"Enzo's Express Takeaway" ,"enzos"} ,

            {"Fusion Bistro" ,"fusion"} ,
            {"LAMANA" ,"lamana"} ,
            {"Big Boi Burger" ,"bigboi"} ,

            {"Tandoor on the Harbour" ,"tandoor"} ,
            {"Royal Papua Yacht Club Restaurant" ,"rpyc"} ,
            {"Mojo Social Restaurant" ,"mojo"} ,

    } ;




    String[]web = {
            "Tasty Bites" ,
            "Golden Bowl",
            "Kopitiam",
            "Beijing Noodle House",
            "Buffalo Burger",
            "Mr Mikes",
            "Cellar-Restaurant",
            "Enzo's Express Takeaway",
            "Fusion Bistro",
            "LAMANA",
            "Big Boi Burger",
            "Tandoor on the Harbour",
            "Royal Papua Yacht Club Restaurant",
            "Mojo Social Restaurant",
    };

            int[] imageId = {
            R.drawable.tastybiteslogo,
            R.drawable.goldenbowllogosmall,
            R.drawable.kopitiamlogosmall,
                    R.drawable.beijingnoodlehouselogo,
                    R.drawable.buffalologo,
                    R.drawable.mrmikessmall,
                    R.drawable.thecellarrestaurant,
                    R.drawable.enzoslogo,
                    R.drawable.fusionlogo,
                    R.drawable.lamana,
                    R.drawable.bigboi,
                    R.drawable.tandoor,
                    R.drawable.royal,
                    R.drawable.mojosocial,

    };


}
