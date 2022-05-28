package com.example.myapplication;

public class ret_rev {
    private String rev_num;
    private String user;

    public ret_rev() {
    }

    public ret_rev(String rev_num, String user) {
        this.rev_num = rev_num;
        this.user = user;
    }

    public String getRev_num() {
        return rev_num;
    }

    public void setRev_num(String rev_num) {
        this.rev_num = rev_num;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
