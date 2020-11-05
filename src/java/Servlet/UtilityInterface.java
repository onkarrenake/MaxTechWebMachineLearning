/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

/**
 *
 * @author ar
 */
interface UtilityInterface {

    public Integer save_performance(String name, String email, String contact, String company, String password, String conformpassword); // interface method (does not have a body)

    public String getStatus(String email, String password, String tablename); // interface method (does not have a body)
}

// Pig "implements" the Animal interface

