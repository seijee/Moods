/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import static objects.ImdbData.IMDB_FIND_BY_RATING_RANGEMOOD;
import org.eclipse.jdt.internal.compiler.impl.Constant;

/**
 *
 * @author seijee
 */
@Entity
@Table(name = "imdb_data")
@XmlRootElement
@NamedQueries({
//    @NamedQuery(name = "Imdb.findAll", query = "SELECT i FROM ImdbData i ORDER BY :order")
//    ,@NamedQuery(name = "ImdbData.findById", query = "SELECT i FROM ImdbData i WHERE i.id = :id")
//    ,@NamedQuery(name = "ImdbData.findByImdbId", query = "SELECT i FROM ImdbData i WHERE i.imdbId = :imdbId")
//    ,@NamedQuery(name = "ImdbData.findByTitle", query = "SELECT i FROM ImdbData i WHERE i.title = :title")
//    ,@NamedQuery(name = "ImdbData.findByVotes", query = "SELECT i FROM ImdbData i WHERE i.votes = :votes")
//    ,@NamedQuery(name = "ImdbData.findByRating", query = "SELECT i FROM ImdbData i WHERE i.rating > :rating")
    @NamedQuery(name =  IMDB_FIND_BY_RATING_RANGEMOOD , query = "SELECT i FROM ImdbData i WHERE (i.moodOne=:mood OR i.moodTwo=:mood OR i.moodThree=:mood) AND i.rating >= :minRating AND i.rating < :maxRating")
//    ,@NamedQuery(name = "ImdbData.findByImage", query = "SELECT i FROM ImdbData i WHERE i.image = :image")
//    ,@NamedQuery(name = "ImdbData.findByActors", query = "SELECT i FROM ImdbData i WHERE i.actors = :actors")
//    ,@NamedQuery(name = "ImdbData.findByGenre", query = "SELECT i FROM ImdbData i WHERE i.genre = :genre")
})
public class ImdbData implements Serializable {
    public static final String IMDB_FIND_BY_RATING_RANGEMOOD = "ImdbData.findByRatingRangeAndMood";
    public static final String GET_ALL = "findAll";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "IMDB_ID")
    private String imdbId;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @Column(name = "VOTES")
    private int votes;
    @Basic(optional = false)
    @Column(name = "RATING")
    private float rating;
    @Basic(optional = false)
    @Column(name = "IMAGE")
    private String image;
    @Basic(optional = false)
    @Column(name = "ACTORS")
    private String actors;
    @Basic(optional = false)
    @Column(name = "GENRE")
    private String genre;
    @Basic(optional = true)
    @Column(name = "MOOD_ONE")
    private String moodOne;
    @Basic(optional = true)
    @Column(name = "MOOD_TWO")
    private String moodTwo;
    @Basic(optional = true)
    @Column(name = "MOOD_THREE")
    private String moodThree;
    
    public ImdbData() {
    }

    public ImdbData(Integer id) {
        this.id = id;
    }

    public ImdbData(Integer id, String imdbId, String title, int votes, float rating, String image, String actors, String genre, String moodOne, String moodTwo, String moodThree) {
        this.id = id;
        this.imdbId = imdbId;
        this.title = title;
        this.votes = votes;
        this.rating = rating;
        this.image = image;
        this.actors = actors;
        this.genre = genre;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMoodOne() {
        return moodOne;
    }

    public void setMoodOne(String moodOne) {
        this.moodOne = moodOne;
    }

    public String getMoodTwo() {
        return moodTwo;
    }

    public void setMoodTwo(String moodTwo) {
        this.moodTwo = moodTwo;
    }

    public String getMoodThree() {
        return moodThree;
    }

    public void setMoodThree(String moodThree) {
        this.moodThree = moodThree;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImdbData)) {
            return false;
        }
        ImdbData other = (ImdbData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb = "Id : "+id;
        sb += "<br/>IMDB ID : "+imdbId;
        sb += "<br/>Title : "+title;
        sb += "<br/>Genre : "+genre;
        sb += "<br/>Actors : "+actors;
        sb += "<br/>Image Url : "+image+"\n";
        return sb;
    }
}
