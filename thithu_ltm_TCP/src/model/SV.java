/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class SV implements Serializable{
    private int id;
    private String ten;
    private Date dob;
    private String khoa;
    private String que;

    public SV() {
    }

    public SV(int id, String ten, Date dob, String khoa, String que) {
        this.id = id;
        this.ten = ten;
        this.dob = dob;
        this.khoa = khoa;
        this.que = que;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getQue() {
        return que;
    }

    public void setQue(String que) {
        this.que = que;
    }
    
}
