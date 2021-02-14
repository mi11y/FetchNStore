package cli;

/**
 * This class defines the common methods any data store should have.
 * Those being:
 * - Create
 * - Read
 * - Update
 * - Delete
 *
 * The purpose leaves implementation ultimately up to subclasses. This class'
 * purpose is to define a contract of the expected methods that a DataStore can
 * implement. This allows classes such as Prompt to assume that a DataStore
 * supports CRUD (or at least, in Prompt's case, create and read.
 */
public abstract class AbstractDataStore {

    /**
     * Create shall accepts key-value pairs and stores them in the backing
     * data store. If storing is successful, true shall be returned. Otherwise,
     * false is returned.
     *
     * In some use cases, create() can behave more as an upsert (such is the case with
     * the PutCommand).
     * @param key The key in the "Key-Value" pair
     * @param value The value in the "Key-Value" pair
     * @return A boolean (true) indicating if storing the key-value pair was
     * successful, false otherwise.
     */
    protected boolean create(String key, String value) {
        return false;
    }

    /**
     * Read shall accept a key and retrieve the value from the backing data store.
     * If the key is stored, the corresponding value is returned. Otherwise, the
     * empty string is returned if the key is not in the data store.
     *
     * @param key The key whose value will be returned if stored in the data store.
     * @return A string representing the value stored for a given key if the key is
     * stored in the data store. The empty string is returned otherwise.
     */
    protected String read(String key) {
        return "";
    }

    /**
     * Update shall update a key in the backing data store to the given
     * new value. If successful, true shall be returned, otherwise false. It is up
     * to the subclass to determine if the key must exist in the datastore before
     * it can be updated.
     *
     * @param key An optionally existing key in the data-store
     * @param newValue The new value to be set for the given key in the data store
     * @return A boolean (true) indicating if the update was successful, false otherwise.
     */
    protected boolean update(String key, String newValue) {
        return false;
    }

    /**
     * Delete shall remove the provided key and it's value from the backing data store.
     * If successful, true shall be returned, false otherwise.
     *
     * @param key The key in the data store which will be removed along with it's value
     * @return A boolean (true) indicating if the delete was successful, false otherwise.
     */
    protected boolean delete(String key) {
        return false;
    }
}
