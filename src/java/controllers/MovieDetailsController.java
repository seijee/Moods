/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.HashMap;
import java.util.List;
import objects.ImdbData;
import objects.MovieDetails;

/**
 *
 * @author seijee
 */
public class MovieDetailsController {
    private static boolean comparePrice(String a, String b){
        try{
            float x = Float.parseFloat(a.substring(1));
            float y = Float.parseFloat(b.substring(1));
            if (x<y) return true;
            else return false;
        }catch (Exception e){
            return false;
        }
    }
    public static MovieDetails GetMovieDetails (ImdbData myMovie){
        MovieDetails movieDetails = new MovieDetails(myMovie.getId(), myMovie.getTitle());
        
        movieDetails.setMovieId(myMovie.getId());
        movieDetails.setTitle( myMovie.getTitle());
        List<MovieProduct> mProducts = paapi.PaapiCall.itemSearch(myMovie.getTitle(), myMovie.getActors(), "");
        
        movieDetails.setDescription(SelectDescription(mProducts, movieDetails));
        if (movieDetails.getDescription()==""){
            mProducts = paapi.PaapiCall.itemSearch(myMovie.getTitle(),"", "");
            movieDetails.setDescription(SelectDescription(mProducts, movieDetails));
        }
        
        for (MovieProduct mp : mProducts){
            if (mp.getProductGroup().equalsIgnoreCase("DVD")){
                if (comparePrice(mp.getPrice(), movieDetails.getDvdPrice())){ 
                    movieDetails.setDvdAsin( mp.getAsin());
                    movieDetails.setDvdLink(mp.getDetailPageUrl());
                    movieDetails.setDvdPrice( mp.getPrice());
                }
//                if (mp.getBinding().equalsIgnoreCase("Blu-ray")){ //Blu-ray disk is the preference
//                    movieDetails.setDvdAsin( mp.getAsin());
//                    movieDetails.setDvdLink(mp.getDetailPageUrl());
//                    movieDetails.setDvdPrice( mp.getPrice());
//                }else if (movieDetails.getDvdAsin() == null || movieDetails.getDvdAsin()==""){ //take any DVD if blu-ray is NA
//                    movieDetails.setDvdAsin( mp.getAsin());
//                    movieDetails.setDvdLink(mp.getDetailPageUrl());
//                    movieDetails.setDvdPrice( mp.getPrice());
//                }
            }else if (mp.getBinding().equalsIgnoreCase("Amazon Instant Video")){
                movieDetails.setInstantVasin( mp.getAsin());
                movieDetails.setInstantVlink(mp.getDetailPageUrl());
                movieDetails.setInstantVprice(mp.getPrice());
            }
        }
        return movieDetails;
    }
    
    private static String SelectDescription (List<MovieProduct> mProducts , MovieDetails md){
        HashMap<String, String> map = new HashMap<String, String>();
        for (MovieProduct mp : mProducts){
            for (int i=0; i<mp.getDescriptionSources().size(); i++){
                if (mp.getProductGroup().equalsIgnoreCase("DVD"))
                map.put(mp.getDescriptionSources().get(i), mp.getDescriptions().get(i));
            }
        }
        //System.out.println("Extracting description");
        String description = "";
        for (String key : map.keySet()){
            if (key.toLowerCase().contains("amazon.com")){
                description = map.get(key);
                break;
            }else{
                if (description.length() < map.get(key).length()){
                    description = map.get(key);
                }
            }
        }
        return description;
    }
}
