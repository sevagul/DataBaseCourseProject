package new_model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SelectResult extends ArrayList<HashMap<String, String>> {
    public SelectResult(int initialCapacity) { super(initialCapacity); }

    public SelectResult() { }

    public SelectResult(@NotNull Collection<? extends HashMap<String, String>> c) { super(c); }

    @Override
    public String toString() {
        String answ = "";
        for(HashMap<String, String> hm: this){
            for (Map.Entry mapElement : hm.entrySet()) {
                String key = (String) mapElement.getKey();
                String value = (String) mapElement.getValue();
                answ += key + " : " + value + ", ";
            }
            answ += "\n";
        }
        return answ;
    }
}
