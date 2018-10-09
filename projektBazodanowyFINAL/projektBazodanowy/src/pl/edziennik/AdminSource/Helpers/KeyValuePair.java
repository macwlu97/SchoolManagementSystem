package pl.edziennik.AdminSource.Helpers;

public class KeyValuePair {
    private final Integer key;
    private final String value;
    public KeyValuePair(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey()   {    return key;    }

    public String toString() {    return value;  }
}
