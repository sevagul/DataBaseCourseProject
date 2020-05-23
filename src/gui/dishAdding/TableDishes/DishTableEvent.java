package gui.dishAdding.TableDishes;

public class DishTableEvent {
    int reciepeId;

    public DishTableEvent(int reciepeId) {
        this.reciepeId = reciepeId;
    }

    public void setReciepeId(int reciepeId) {
        this.reciepeId = reciepeId;
    }

    public int getReciepeId() {
        return reciepeId;
    }

}
