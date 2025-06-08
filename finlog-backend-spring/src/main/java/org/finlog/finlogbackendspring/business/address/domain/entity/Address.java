package org.finlog.finlogbackendspring.business.address.domain.entity;

import org.finlog.finlogbackendspring.config.entity.DomainEntity;

import java.util.Optional;

public class Address extends DomainEntity {

    private String zipCode;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String streetNumber;
    private String complement;

    public Address(){
    }

    public Address(Long id, String zipCode, String state, String city, String neighborhood, String street, String streetNumber, String complement) {
        super(id);
        this.setZipCode(zipCode);
        this.setState(state);
        this.setCity(city);
        this.setNeighborhood(neighborhood);
        this.setStreet(street);
        this.setStreetNumber(streetNumber);
        this.setComplement(complement);
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Optional<String> getComplement() {
        return Optional.ofNullable(complement);
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
