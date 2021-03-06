package model;

public class Dish extends Recipe{
    public static int MainDish = 1;
    public static int Meat = 2;
    public static int Drink = 3;
    public static int Salat = 4;
    public static int Apetizzer = 5;

    private int type;
    private String name;
    private int id;
    private static int count = 0;

    public Dish(){
        super();
    }

    public Dish(String name, Recipe recipe, int type){
        this.type = type;
        this.name = name;
        putAll(recipe);
        id = count;
        count++;
    }

    public Dish(String name, Recipe recipe, int type, int id){
        this.type = type;
        this.name = name;
        putAll(recipe);
        this.id = id;
        count = id + 1;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        float answer = 0;
        for(Product i: keySet()){
            answer += (i.getPrice() * get(i) )/(float)1000;
        }
        return answer;
    }

    public int getType() {
        return type;
    }
    public String getInfo(){
        String answ = name + ":\n";
        for( Product product  : keySet()){
            answ += product.getName() + " + ";
        }
        answ = answ.substring(0, answ.length() -2);
        return answ + "\n";
    }
}
