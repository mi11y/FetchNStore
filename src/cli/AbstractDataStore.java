package cli;

/**
 * This class defines the common methods any data store should have.
 * Those being:
 * - Create
 * - Read
 * - Update
 * - Delete
 *
 * A subclass can chose which methods to implement.
 */
public abstract class AbstractDataStore {
    protected boolean create(String key, String value) {
        return false;
    }

    protected String read(String key) {
        return "";
    }

    protected boolean update(String key, String newValue) {
        return false;
    }

    protected boolean delete(String key) {
        return false;
    }
}
