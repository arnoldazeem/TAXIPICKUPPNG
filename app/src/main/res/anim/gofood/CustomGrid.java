package incop.ark.lyte.adaboo.gofood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by adaboo on 9/13/17.
 */

public class CustomGrid extends BaseAdapter {

        private Context mContext;
        private final String[][] web;
        private final int[] Imageid;

        public CustomGrid(Context c, String[][] web, int[] Imageid ) {
            mContext = c;
            this.Imageid = Imageid;
            this.web = web;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return web.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View grid;


            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                grid = inflater.inflate(R.layout.grid_single_restaurant, null);

            }
                else {
                    grid = (View) convertView;
                }

                TextView textView = (TextView) grid.findViewById(R.id.grid_text);
                ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);

                String gotten = web[position][0];

                textView.setText(gotten);
               // picasso
               // Picasso.with(mContext)
               //         .load(Imageid[position])
               //         .placeholder( R.drawable.progress_animation )
               //         .into(imageView);
                imageView.setImageResource(Imageid[position]);

            return grid;
        }
    }

