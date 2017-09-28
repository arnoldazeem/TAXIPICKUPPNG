package incop.ark.lyte.adaboo.gofood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by adaboo on 9/22/17.
 */

public class Postactivity_adapter extends BaseAdapter {

    private Context mContext;
    private List<restaurant_List_Bulk> itemlist;

    public Postactivity_adapter(Context c, List<restaurant_List_Bulk> itemlist) {
        mContext = c;
        this.itemlist = itemlist;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemlist.size();
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
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            restaurant_List_Bulk items = itemlist.get(position);

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_postactivity_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            TextView textnum = (TextView) grid.findViewById(R.id.grid_num);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(items.getName());
            textnum.setText("("+items.getMenu()+")");
            // picasso
            Picasso.with(mContext)
                    .load(items.getThumbnail())
                    .placeholder( R.drawable.progress_animation )
                    .into(imageView);
            //imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}

