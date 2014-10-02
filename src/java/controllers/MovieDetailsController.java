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
    public static MovieDetails GetMovieDetails (ImdbData myMovie){
        MovieDetails movieDetails = new MovieDetails(myMovie.getId(), myMovie.getTitle());
        
        movieDetails.setMovieId(myMovie.getId());
        movieDetails.setTitle( myMovie.getTitle());
        List<MovieProduct> mProducts = paapi.PaapiCall.itemSearch(myMovie.getTitle(), myMovie.getActors(), "");
        
        movieDetails.setDescription(SelectDescription(mProducts));
        
        for (MovieProduct mp : mProducts){
            if (mp.getProductGroup().equalsIgnoreCase("DVD")){
                if (mp.getBinding().equalsIgnoreCase("Blu-ray")){ //Blu-ray disk is the preference
                    movieDetails.setDvdAsin( mp.getAsin());
                    movieDetails.setDvdLink(mp.getDetailPageUrl());
                    movieDetails.setDvdPrice( mp.getPrice());
                }else if (movieDetails.getDvdAsin() == null || movieDetails.getDvdAsin()==""){ //take any DVD if blu-ray is NA
                    movieDetails.setDvdAsin( mp.getAsin());
                    movieDetails.setDvdLink(mp.getDetailPageUrl());
                    movieDetails.setDvdPrice( mp.getPrice());
                }
            }else if (mp.getBinding().equalsIgnoreCase("Amazon Instant Video")){
                movieDetails.setInstantVasin( mp.getAsin());
                movieDetails.setInstantVlink(mp.getDetailPageUrl());
                movieDetails.setInstantVprice(mp.getPrice());
            }
        }
        return movieDetails;
    }
    
    private static String SelectDescription (List<MovieProduct> mProducts){
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
