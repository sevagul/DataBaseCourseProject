package new_model.dbClasses;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SelectResult extends ArrayList<KeyVals> {
    public SelectResult(int initialCapacity) {
        super(initialCapacity);
    }

    public SelectResult() {
    }

    public SelectResult(@NotNull Collection<? extends KeyVals> c) {
        super(c);
    }

    @Override
    public String toString() {
        String answ = "";
        for(KeyVals kv: this){
            for (Map.Entry mapElement : kv.entrySet()) {
                String key = (String) mapElement.getKey();
                String value = (String) mapElement.getValue();
                answ += key + " : " + value + ", ";
            }
            answ += "\n";
        }
        return answ;
    }
}
