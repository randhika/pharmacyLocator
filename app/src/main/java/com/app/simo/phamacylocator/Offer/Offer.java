package com.app.simo.phamacylocator.Offer;


import com.app.simo.phamacylocator.Service.Service;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("pharmacy_id")
    @Expose
    private String pharmacyId;
    @SerializedName("service_id")
    @Expose
    private String serviceId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("service")
    @Expose
    private Service service;

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
     * The pharmacyId
     */
    public String getPharmacyId() {
        return pharmacyId;
    }

    /**
     *
     * @param pharmacyId
     * The pharmacy_id
     */
    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    /**
     *
     * @return
     * The serviceId
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     *
     * @param serviceId
     * The service_id
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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
     * The service
     */
    public Service getService() {
        return service;
    }

    /**
     *
     * @param service
     * The service
     */
    public void setService(Service service) {
        this.service = service;
    }

}