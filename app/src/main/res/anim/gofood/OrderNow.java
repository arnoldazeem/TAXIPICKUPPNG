package incop.ark.lyte.adaboo.gofood;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by adaboo on 9/14/17.
 */

public class OrderNow extends AppCompatActivity implements View.OnClickListener {

    Button order;

    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.order_now);

        order = (Button) findViewById(R.id.order_nw);

        order.setOnClickListener(this);



        text = "<html><body><p align=\"justify\">";

        text+= " <p>Step 1</p>" +
                "<p> Once you have added your desired items to your cart, click on 'View Cart' to confirm your order and quantity.\n" +
                " If you made changes to the quantity, you must click on 'Update Cart'.</p>" +
                "<p>Step 2</p>" +
                "<p>Click on 'Proceed to Checkout'. This will take you to Billing &amp; Delivery</p>" +
                "<p>Step 3</p>" +
                "" +
                "<p>In the Billing &amp; Delivery section, you will notice fields to input details.</p>" +
                "" +
                "<p>Where there are Red *, you must enter info and select from the drop down menus before you can place your order.</p>" +
                "<p>Step 4</p>" +
                "" +
                "<p>Once phone number and names are entered, you must select State/County then Town/City then Suburb that you live in.\n" +
                " You must then type your section/lot or house number and street name under the 'Address' field, before the delivery fees can be calculated.\n" +
                " If your suburb is already selected and you cannot proceed or your delivery method is not available, you must re type in your 'Address' details even though your address details are showing.</p>" +
                "<p>NOTE</p>" +
                " Once you do this, it will re calculate your delivery fee and you will be able to place your order\n" +
                "</body></Html>" ;
        text+= "</p></body></html>";

        }


    @Override
    public void onClick(View v) {
        alertme();

    }


    void alertme(){

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_justified);
        dialog.setTitle("How to order.");

        WebView view = new WebView(this);
        view.setVerticalScrollBarEnabled(false);

        ((RelativeLayout) dialog.findViewById(R.id.linear)).addView(view);

        view.loadData(getString(R.string.hello), "text/html; charset=utf-8", "utf-8");



        dialog.show();
    }
}
