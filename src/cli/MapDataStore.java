package cli;

import java.util.HashMap;

public class MapDataStore extends AbstractDataStore {

    private HashMap<String, String> map;

    public MapDataStore() {
        map = new HashMap<>();
    }

    @Override
    protected boolean create(String key, String value) {
        map.put(key, value);
        return true;
    }

    @Override
    protected String read(String key) {
        if(map.containsKey(key)) {
            return map.get(key);
        }
        return "";
    }

}
