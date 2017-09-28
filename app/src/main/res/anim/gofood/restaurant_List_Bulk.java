package incop.ark.lyte.adaboo.gofood;

/**
 * Created by adaboo on 9/21/17.
 */

public class restaurant_List_Bulk {

    private String name;
    private String numOfmenu;
    private String thumbnail;

    public restaurant_List_Bulk() {
    }

    public restaurant_List_Bulk(String name, String numOfmenu, String thumbnail ) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.numOfmenu = numOfmenu;
    }

    public String getName() {
        return name;
    }

    public String getMenu() {
        return numOfmenu;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setMenu(String numOfmenu) {
        this.numOfmenu = numOfmenu;
    }
}


