package com.ibm.taskManager.model;

public class EmployeeModel {

    private Long id;
    private String idm;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String role;
    private CredentialModel credential;

    public EmployeeModel() {
    }

    public EmployeeModel(
            Long id, String firstName,
            String lastName, String phone,
            String email, String address,
            String role, CredentialModel credential) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.credential = credential;
    }

    public String getIdm() {
        return idm;
    }

    public void setIdm(String idm) {
        this.idm = idm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CredentialModel getCredential() {
        return credential;
    }

    public void setCredential(CredentialModel credential) {
        this.credential = credential;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}