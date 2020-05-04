package model;

import Utils.Utils;

import java.io.*;
import java.util.ArrayList;

public class FoodBase extends ArrayList<Product> {
    int count;
    public FoodBase(){
        super();
        readFromFile("FoodBase.txt");
        count = getNextId();
        addFromFile("additionalFood.txt");
    }
    public void putProduct(String name, int price){
        boolean accept = true;
        for(Product product: this){
            if(product.getName().equals(name)) {
                accept = false;
                product.setPrice(price);
            }
        }
        if(accept){
            add(new Product(name, price, count));
            count ++;
        }
    }
    public void writeToFile(String file){
        try{
            PrintWriter pw;
            pw = new PrintWriter(file);
            pw.print(getInfo());
            pw.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void readFromFile(String file){
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String datal = br.readLine();
            while(datal != null && !datal.equals(""))
            {
                String[] words = datal.split("\t");
                if(words.length == 3)
                    add(new Product(words[1], Utils.stringToNatural(words[2]), Utils.stringToNatural(words[0])));
                datal = br.readLine();
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException f){
            System.out.println(f.getMessage());
        }
    }

    public void addFromFile(String file){
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String datal = br.readLine();
            while(datal != null && !datal.equals(""))
            {
                String[] words = datal.split("\t");
                if(words.length == 2)
                    putProduct(words[0], Utils.stringToNatural(words[1]));
                datal = br.readLine();
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException f){
            System.out.println(f.getMessage());
        }

    }

    public String getInfo(){
        String answer = "";
        for(Product product:this){
            answer = answer.concat(product.getInfo());
        }
        return answer;
    }


    public String getReadableInfo(){
        String answer = "У базі є такі продукти:\n";
        String[] lines = getInfo().split("\n");
        for(String line: lines){
            String[] words = line.split("\t");
            answer += words[0] + ". " + words[1] + ". " + words[2] + " грн(за кг/літр)\n";
        }
        return answer;
    }
    public int getNextId(){
        int answer = 0;
        for(Product product: this){
            if(product.getId() >= answer);
                answer = product.getId() + 1;
        }
        return answer;
    }
    public Product getById(int id){
        for(Product product: this){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
