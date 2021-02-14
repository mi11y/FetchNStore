package cli;

import java.util.HashMap;

public class MapDataStore extends AbstractDataStore {

    private final HashMap<String, String> map;

    public MapDataStore() {
        map = new HashMap<>();
    }

    @Override
    protected boolean create(String key, String value) {
        if(key == null || value == null) {
            return false;
        }
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
