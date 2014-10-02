/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import static objects.MovieDetails.GET_BY_ID;
import static objects.MovieDetails.GET_BY_TITLE;

/**
 *
 * @author seijee
 */
@Entity
@Table(name = "moviedetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = GET_BY_ID, query = "SELECT m FROM MovieDetails m WHERE m.movieId = :MovieId"),
    @NamedQuery(name = GET_BY_TITLE, query = "SELECT m FROM MovieDetails m WHERE m.title = :title")
})
public class MovieDetails implements Serializable {
    public static final String GET_BY_ID = "Moviedetails.findById";
    public static final String GET_BY_TITLE = "Moviedetails.findByTitle";
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MOVIE_ID", nullable = false)
    private Integer movieId;
    @Basic(optional = false)
    @Column(name = "TITLE", nullable = false, length = 1024)
    private String title;
    @Column(name = "DVD_LINK", length = 2056)
    private String dvdLink;
    @Column(name = "DVD_ASIN", length = 64)
    private String dvdAsin;
    @Column(name = "DVD_PRICE", length = 16)
    private String dvdPrice;
    @Column(name = "INSTANT_VLINK", length = 2056)
    private String instantVlink;
    @Column(name = "INSTANT_VASIN", length = 64)
    private String instantVasin;
    @Column(name = "INSTANT_VPRICE", length = 16)
    private String instantVprice;
    @Lob
    @Column(name = "DESCRIPTION", length = 65535)
    private String description;

    public MovieDetails() {
    }

    public MovieDetails(Integer movieId) {
        this.movieId = movieId;
    }

    public MovieDetails(Integer movieId, String title) {
        this.movieId = movieId;
        this.title = title;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="Getters Setters">
    
    public Integer getMovieId() {
        return movieId;
    }
    
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDvdLink() {
        return dvdLink;
    }
    
    public void setDvdLink(String dvdLink) {
        this.dvdLink = dvdLink;
    }
    
    public String getDvdAsin() {
        return dvdAsin;
    }
    
    public void setDvdAsin(String dvdAsin) {
        this.dvdAsin = dvdAsin;
    }
    
    public String getDvdPrice() {
        return dvdPrice;
    }
    
    public void setDvdPrice(String dvdPrice) {
        this.dvdPrice = dvdPrice;
    }
    
    public String getInstantVlink() {
        return instantVlink;
    }
    
    public void setInstantVlink(String instantVlink) {
        this.instantVlink = instantVlink;
    }
    
    public String getInstantVasin() {
        return instantVasin;
    }
    
    public void setInstantVasin(String instantVasin) {
        this.instantVasin = instantVasin;
    }
    
    public String getInstantVprice() {
        return instantVprice;
    }
    
    public void setInstantVprice(String instantVprice) {
        this.instantVprice = instantVprice;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
//</editor-fold>
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieId != null ? movieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieDetails)) {
            return false;
        }
        MovieDetails other = (MovieDetails) object;
        if ((this.movieId == null && other.movieId != null) || (this.movieId != null && !this.movieId.equals(other.movieId))) {
            return false;
        }
        return true;
    }

    
//    @Override
//    public String toString() {
//        String response = "";
//        int ind = controllers.ImdbDBController.getCache().indexOf(new ImdbData(movieId));
//        response += controllers.ImdbDBController.getCache().get(ind).toString();
//        return response + "<br/>"+description;
//    }

    @Override
    public String toString() {
        String response = "[" + movieId + "]" + title;
        
        if (dvdLink!=null){
        response += "<br/><a target='_blank' href='"+ dvdLink +"'>DVD [" +
                dvdAsin + "]</a> Price :" + dvdPrice; 
        }
        if (instantVlink!=null){
        response += "<br/><a target='_blank' href='"+ instantVlink +"'>Instant Video [" +
                instantVasin + "]</a> Price :" + instantVprice;
        }
        response += "<br/><hr/>" + description ;
        return response;
    }
    
}
