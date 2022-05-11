package iotbay.model;
import java.io.Serializable;

/**  
 *
 * @author rubabrahman
 */
public class Catalogue implements Serializable {
    //the fields
    private int id;
    private String title;
    private double price;
    private int stock;
    private String image;
    private String description;
    private String type;
    
    //initialise the variables
    public Catalogue(int id, String title, double price, String description, String image, int stock, String type) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
        this.stock = stock;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        String titleCapital = title.substring(0, 1).toUpperCase() + title.substring(1);
        return titleCapital;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    
}