/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author seijee
 */

public class MovieProduct implements Serializable {
    private Integer id;
    private String description;
    private String title;
    private String price;
    private String asin;
    private String detailPageUrl;
    private String Binding;
    private String productType, ProductGroup; //should be "Movie","DVD" not be "TV Series Episode Video on Demand"
    private String imagelink;
    private ArrayList<String> Descriptions, DescriptionSources;

    public ArrayList<String> getDescriptions() {
        if (Descriptions == null){
            Descriptions = new ArrayList<String>();
        }
        return Descriptions;
    }

    public ArrayList<String> getDescriptionSources() {
        if (DescriptionSources==null){
            DescriptionSources = new ArrayList<String>();
        }
        return DescriptionSources;
    }
    
    
    public void addNewDescription (String source, String desc){
        getDescriptions().add(desc);
        getDescriptionSources().add(source);
    }
    
    public String getProductType() {
        return productType;
    }

    public String getProductGroup() {
        return ProductGroup;
    }

    public void setProductGroup(String ProductGroup) {
        this.ProductGroup = ProductGroup;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        if (description==null){
            description="";
            for (int i=0; i<getDescriptions().size(); i++){
                description += getDescriptionSources().get(i)+" : <br/>"+getDescriptions().get(i);
            }
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getDetailPageUrl() {
        return detailPageUrl;
    }

    public void setDetailPageUrl(String detailPageUrl) {
        this.detailPageUrl = detailPageUrl;
    }

    public String getBinding() {
        return Binding;
    }

    public void setBinding(String Binding) {
        this.Binding = Binding;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
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
        if (!(object instanceof MovieProduct)) {
            return false;
        }
        MovieProduct other = (MovieProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "<img style='float:left;' height='150px' src='"+imagelink+"' />"
                +"<p>Title=<b>" + title +"</b>"
                +"[ <a href='"+ detailPageUrl +"'> Detail Page </a> ]<br/>"
                +"ASIN = "+asin+"<br/>"
                +"Price ="+price+"<br/>"
                +"Binding = "+Binding+"<br/>"
                +"Product Type"+productType+"<br/>"
                +"</p><div style='clear:both;'></div>"+
                this.getDescription();
    }

    public String consolePrint() {
        return "image: "+imagelink+"\n"
                +"ASIN = "+asin+" ; "
                +"Title=" + title +"\n"
                +"Price ="+price+" ; "
                +"Binding = "+Binding+" ; "
                +"Product Type = "+productType+" ; "
                +"DetailPage = "+ detailPageUrl +"\n"
                +"Discription = "+
                this.getDescription()+"\n*********************\n";
    }
}
