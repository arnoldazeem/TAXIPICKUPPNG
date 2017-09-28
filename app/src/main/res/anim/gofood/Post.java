package incop.ark.lyte.adaboo.gofood;

/**
 * Created by adaboo on 8/23/17.
 */

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Post {

    @SerializedName("id")
    long ID;

    @SerializedName("date")
    Date dateCreated;

    String results;
    String Status;
    String company_image;
    String body;

}