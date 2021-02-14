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
    public void shouldReturnNullWhenStoringNullKeyValuePair() {
        MapDataStore testMapDataStore = new MapDataStore();

        boolean createSuccess = testMapDataStore.create(null, null);

        Assert.assertFalse(
                "Attempting to store null key-value pair should return false",
                createSuccess
        );
    }
}
