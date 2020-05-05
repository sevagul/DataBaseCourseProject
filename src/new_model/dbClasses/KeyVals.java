package new_model.dbClasses;

import Utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class KeyVals extends HashMap<String, String> {
    public KeyVals(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }
    public KeyVals(int initialCapacity) {
        super(initialCapacity);
    }
    public KeyVals(Map<? extends String, ? extends String> m) {
        super(m);
    }
    public KeyVals() {}
    public KeyVals(String key, String val) {
        super();
        put(key, val);
    }
    public String getUpdateSetting(){
        String setting = "";

        for(String key: keySet()){
            setting +=  key + " = '" + get(key) + "', ";
        }
        return deleteLastComa(setting);
    }
    public String getUpdateConditionAnd(){
        String cond = "";

        for(String key: keySet()){
            cond +=  key + " = '" + get(key) + "' AND ";
        }
        return cond.substring(0, cond.length() - 4);
    }
    public String deleteLastComa(String str){
        return str.strip().substring(0, str.length() - 2);
    }
    public int getInt(String key){
        return Utils.stringToNatural(get(key));
    }
}
