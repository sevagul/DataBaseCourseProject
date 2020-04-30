package model;

public class FoodStuff {
    String name;
    int price; //per unit(kg, litre, bottle(vine), units(egg))
    int id;

    public FoodStuff(String name, int price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo(){
        String answer = "";
        answer = id + "\t" + name + "\t" + price + "\n";
        return answer;
    }
}
