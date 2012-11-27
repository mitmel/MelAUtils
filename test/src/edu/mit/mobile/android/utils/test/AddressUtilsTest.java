package edu.mit.mobile.android.utils.test;

import java.util.Locale;

import android.location.Address;
import android.test.AndroidTestCase;
import edu.mit.mobile.android.utils.AddressUtils;

public class AddressUtilsTest extends AndroidTestCase {

    public void testStatic() {
        Address a = new Address(Locale.US);
        a.setAddressLine(0, "1234 Main St.");
        a.setLocality("Citysville");

        assertEquals("1234 Main St., Citysville", AddressUtils.addressToName(a));
        // ////////

        a = getFullGeocoded();

        assertEquals("2381-2397 Massachusetts Ave, Cambridge", AddressUtils.addressToName(a));

        a.setFeatureName("");

        assertEquals("2381-2397 Massachusetts Ave, Cambridge", AddressUtils.addressToName(a));

        a.setAddressLine(0, null);

        assertEquals("2381-2397 Massachusetts Ave, Cambridge", AddressUtils.addressToName(a));
    }

    private Address getFullGeocoded() {
        final Address a = new Address(Locale.US);

        a.setAddressLine(0, "2381-2397 Massachusetts Ave");
        a.setAddressLine(1, "Cambridge, MA 02140");
        a.setAddressLine(2, "USA");
        a.setAdminArea("Massachusetts");
        a.setLocality("Cambridge");
        a.setThoroughfare("Massachusetts Ave");
        a.setPostalCode("02140");
        a.setCountryCode("US");
        a.setCountryName("United States");
        a.setLatitude(42.3969102);
        a.setLongitude(-71.1296033);

        return a;
    }
}
