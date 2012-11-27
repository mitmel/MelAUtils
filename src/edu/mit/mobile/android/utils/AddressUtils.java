package edu.mit.mobile.android.utils;

/*
 * AddressUtils.java
 * Copyright (C) 2010 MIT Mobile Experience Lab
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.location.Address;
import android.text.TextUtils;
import android.util.Log;

public class AddressUtils {
    public final static String TAG = AddressUtils.class.getSimpleName();
    // this regular expression should match what would be considered a bad description of the
    // address.
    private static Pattern BAD_DESCRIPTION_RE = Pattern.compile("[\\d\\s-]+");

    public static String addressToName(Address address) {

        final Address thisLocation = address;
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Location: " + thisLocation);
        }
        String title = thisLocation.getFeatureName();

        final Matcher m = BAD_DESCRIPTION_RE.matcher(title == null ? "" : title);

        if (TextUtils.isEmpty(title) || m.matches()) {
            title = thisLocation.getAddressLine(0);
        }

        String locality = thisLocation.getLocality();
        String country = thisLocation.getCountryName();

        if (TextUtils.isEmpty(locality)) {
            locality = thisLocation.getAdminArea();
        }

        if (TextUtils.isEmpty(country)) {
            country = thisLocation.getCountryCode();
        }

        if (TextUtils.isEmpty(title) || m.reset(title).matches()) {
            title = thisLocation.getSubLocality();
        }

        if (TextUtils.isEmpty(title) || m.reset(title).matches()) {

            title = (locality != null ? locality + ", " : "") + country;
        } else {
            title = (TextUtils.isEmpty(title) ? "" : title + ", ") + locality;
        }

        return title;
    }

    public static String addressToNameRelative(Address here, Address there) {

        return null;
    }

}
