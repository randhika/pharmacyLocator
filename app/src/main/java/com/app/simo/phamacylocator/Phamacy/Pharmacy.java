package com.app.simo.phamacylocator.Phamacy;


import com.app.simo.phamacylocator.Doctor.Doctor;
import com.app.simo.phamacylocator.Drug.Drug;
import com.app.simo.phamacylocator.Offer.Offer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("offers")
    @Expose
    private List<Offer> offers = new ArrayList<Offer>();
    @SerializedName("doctor")
    @Expose
    private List<Doctor> doctor = new ArrayList<Doctor>();
    @SerializedName("drugs")
    @Expose
    private List<Drug> drugs = new ArrayList<Drug>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The lat
     */
    public Double getLat() {
        return Double.valueOf(lat);
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lng
     */
    public Double getLng() {
        return Double.valueOf(lng);
    }

    /**
     *
     * @param lng
     * The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The offers
     */
    public List<Offer> getOffers() {
        return offers;
    }

    /**
     *
     * @param offers
     * The offers
     */
    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    /**
     *
     * @return
     * The doctor
     */
    public List<Doctor> getDoctor() {
        return doctor;
    }

    /**
     *
     * @param doctor
     * The doctor
     */
    public void setDoctor(List<Doctor> doctor) {
        this.doctor = doctor;
    }

    /**
     *
     * @return
     * The drugs
     */
    public List<Drug> getDrugs() {
        return drugs;
    }

    /**
     *
     * @param drugs
     * The drugs
     */
    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }


}