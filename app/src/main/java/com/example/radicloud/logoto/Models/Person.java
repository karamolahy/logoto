package com.example.radicloud.logoto.Models;

import android.media.Image;

import java.io.File;

/**
 * Created by ahmad on 9/22/2016.
 */

public class Person {
    String name;
    String fname;
    String email;
    String mobile;
    String phone;
    String fax;
    String address;
    String jobDesc;
    String geoTag;
    String avatar;
    String resume;
    String token;

    public Person(){
        this.name = "";
        this.email = "";
        this.mobile = "";
        this.phone = "";
        this.fax = "";
        this.address = "";
        this.jobDesc = "";
        this.geoTag = "";
        this.avatar = "";
        this.resume = "";

    }
    public Person(String name, String email, String mobile, String phone, String fax, String address,
                  String jobDesc, String geoTag, String avatar, String resume) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.phone = phone;
        this.fax = fax;
        this.address = address;
        this.jobDesc = jobDesc;
        this.geoTag = geoTag;
        this.avatar = avatar;
        this.resume = resume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getGeoTag() {
        return geoTag;
    }

    public void setGeoTag(String geoTag) {
        this.geoTag = geoTag;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String  avatar) {
        this.avatar = avatar;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
