/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rottentomatoes;

import org.json.JSONException;

/**
 *
 * @author Adi
 */
public class Main {
    
    public static void main(String args[]) throws JSONException{
        System.getProperties().put("http.proxyHost", "172.16.0.87");
        System.getProperties().put("http.proxyPort", "8080");
        new RottenTomatoeWrapper().callRottenTomatoes("Inception");
    }
}
