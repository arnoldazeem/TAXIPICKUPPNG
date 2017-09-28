package incop.ark.lyte.adaboo.gofood;

/**
 * Created by adaboo on 7/29/17.
 */

public class Restaurant_Model {

        private String name;
      //  private int numOfSongs;
        private int thumbnail;

        public Restaurant_Model() {
        }

        public Restaurant_Model(String name, int thumbnail) {
            this.name = name;
            this.thumbnail = thumbnail;
        }

        public String getName() {
            return name;
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

