package cli;

import java.util.HashMap;

/**
 * This class implements create and read methods from the AbstractDataStore
 * class. They are implemented such that create allows for both creating and
 * updating key-value pairs.
 *
 * The backing data structure here is a HashMap, where values are stored
 * until the program exits.
 */
public class MapDataStore extends AbstractDataStore {

    /**
     * The backing data store for MapDataStore, in this case, a HashMap<String, String>.
     */
    private final HashMap<String, String> map;

    public MapDataStore() {
        map = new HashMap<>();
    }

    /**
     * This method will store the key-value pair in the data store. If the key already exists
     * in the data store, it's previous value will be overwritten with the calling value.
     *
     * @param key The key in the "Key-Value" pair
     * @param value The value in the "Key-Value" pair
     * @return A boolean (true) indicating that the creation or update of the key-value pair was
     * successful, false otherwise.
     */
    @Override
    protected boolean create(String key, String value) {
        if(key == null || value == null) {
            return false;
        }
        map.put(key, value);
        return true;
    }

    /**
     * Returns the value of a given key from the data store if it exists.
     * @param key The key whose value will be returned if stored in the data store.
     * @return A string containing the retrieved value from the data store. If the
     * key does not exist, the empty string is returned.
     */
    @Override
    protected String read(String key) {
        if(map.containsKey(key)) {
            return map.get(key);
        }
        return "";
    }

}
