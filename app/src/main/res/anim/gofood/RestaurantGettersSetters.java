package incop.ark.lyte.adaboo.gofood;

/**
 * Created by adaboo on 8/19/17.
 */

public class RestaurantGettersSetters {

    private String name;
    //  private int numOfSongs;
    private int thumbnail;

    private int Id;

    public RestaurantGettersSetters() {
    }

    public RestaurantGettersSetters(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }

    public int setId() {
        return Id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}