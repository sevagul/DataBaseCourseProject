package gui.foodStuffAdding;

public class FormEvent {
        private String foodName;
        private int foodPrice;

        public FormEvent(int foodPrice, String foodName){
            this.foodPrice = foodPrice;
            this.foodName = foodName;
        }

        public int getfoodPrice() {
            return foodPrice;
        }

        public String getfoodName() {
            return foodName;
        }
}
