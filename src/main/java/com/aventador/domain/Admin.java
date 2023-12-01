package com.aventador.domain;

public class Admin {

    private String id = "08213065";//工号
    private String account = "805539325";//账号
    private String password = "Liu20030218";//管理员密码

    public Admin() {
    }

    public Admin(String id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

    /**
     * 获取
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Admin{id = " + id + ", account = " + account + ", password = " + password + "}";
    }
}
