package com.fns.warehouse.service.domain.entity;

import java.util.regex.Pattern;

public class Location {

    private float latitude;
    private float longitude;
    private String address;
    private String city;
    private String province;
    private String postalCode;

    // Constructor
    public Location(float latitude, float longitude, String address, String city, String province, String postalCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        validateLocation();
    }


    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }


    // Method to update the location details
    public void updateLocation(float latitude, float longitude, String address, String city, String province, String postalCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        validateLocation(); // Re-validate whenever updating
    }

    // Location validation logic
    private void validateLocation() {
        // Validate address fields are not null or empty
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        if (province == null || province.isEmpty()) {
            throw new IllegalArgumentException("Province cannot be empty");
        }
        if (postalCode == null || postalCode.isEmpty() || !isValidPostalCode(postalCode)) {
            throw new IllegalArgumentException("Invalid postal code format");
        }

        // Validate latitude and longitude ranges
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Invalid latitude value. Latitude must be between -90 and 90 degrees.");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid longitude value. Longitude must be between -180 and 180 degrees.");
        }
    }

    // Helper method to validate postal code format (example using simple regex)
    private boolean isValidPostalCode(String postalCode) {
        String postalCodeRegex = "^[A-Za-z0-9]{3,10}$"; // Simplified pattern for postal code
        return Pattern.matches(postalCodeRegex, postalCode);
    }
}
