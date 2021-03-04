package cli;

import org.junit.Assert;
import org.junit.Test;

public class MapDataStoreTest {

    @Test
    public void shouldStoreAndReadKeyValuePair() {
        MapDataStore testMapDataStore = new MapDataStore();

        String testKey = "key";
        String testValue = "value";

        boolean createSuccess = testMapDataStore.create(testKey, testValue);

        Assert.assertTrue("Storing valid key-value pair should return true", createSuccess);
        Assert.assertEquals(
                "The map data store should save and produce the value when given the test key",
                testValue,
                testMapDataStore.read(testKey)
        );
    }

    @Test
    public void shouldReturnEmptyStringForUndefinedKey() {
        MapDataStore testMapDataStore = new MapDataStore();

        testMapDataStore.create("key", "value");

        Assert.assertEquals(
                "The map data store should return the empty string when accessing an undefined key",
                "",
                testMapDataStore.read("potato")
        );
    }

    @Test
    public void shouldReturnFalseWhenStoringNullKeyValuePair() {
        MapDataStore testMapDataStore = new MapDataStore();

        boolean createSuccess = testMapDataStore.create(null, null);

        Assert.assertFalse(
                "Attempting to store null key-value pair should return false",
                createSuccess
        );
    }

    @Test
    public void shouldRejectCreatingExistingKeyValuePair() {
        MapDataStore testMapDataStore = new MapDataStore();

        testMapDataStore.create("key", "oldValue");
        boolean doubleCreateSuccess = testMapDataStore.create("key", "newValue");

        Assert.assertFalse(
                "Attempting to store an existing key value pair (using the same key) should return false",
                doubleCreateSuccess
        );
    }

    @Test
    public void shouldUpdateExistingKeyValuePair() {
        MapDataStore testMapDataStore = new MapDataStore();

        testMapDataStore.create("key", "oldValue");

        boolean updateSuccess = testMapDataStore.update("key", "newValue");

        Assert.assertTrue(
                "Updating a valid key value pair should return true",
                updateSuccess
        );
    }

    @Test
    public void shouldUpdateValidKeyValuePair() {
        MapDataStore testMapDataStore = new MapDataStore();

        testMapDataStore.create("key", "oldValue");

        testMapDataStore.update("key", "newValue");

        Assert.assertEquals(
                "Updating a valid, existing key value pair should update the pair in the data store",
                "newValue",
                testMapDataStore.read("key")
        );
    }

    @Test
    public void shouldReturnFalseUpdatingWithKeyNull() {
        MapDataStore testMapDataStore = new MapDataStore();
        testMapDataStore.create("key", "oldValue");

        boolean updateSuccess = testMapDataStore.create(null, "newValue");
        Assert.assertFalse(
                "Updating a key value pair and passing a null key should return false",
                updateSuccess
        );
    }

    @Test
    public void shouldReturnFalseUpdatingWithValueNull() {
       MapDataStore testMapDataStore = new MapDataStore();
       testMapDataStore.create("key", "oldValue");

       boolean updateSuccess = testMapDataStore.update("key", null);
       Assert.assertFalse(
               "Updating a key value pair and passing a null value for an existing key should return false",
               updateSuccess
       );
    }

    @Test
    public void shouldReturnFalseUpdatingUndefinedKey() {
        MapDataStore testMapDataStore = new MapDataStore();

        boolean updateSuccess = testMapDataStore.update("key", "value");
        Assert.assertFalse(
                "Attempting to update a key that does not exist should return false",
                updateSuccess
        );
    }
}
