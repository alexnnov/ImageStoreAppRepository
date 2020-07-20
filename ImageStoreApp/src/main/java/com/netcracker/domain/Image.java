package com.imagestore.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String author;
	
	private String publicationDate;
	
	private String category;
	
	
	private double listPrice;
	private double ourPrice;
	private boolean active=true;
	

	private int inStockNumber;
	
	@Transient
	private MultipartFile imageImage;
	

	@OneToMany(mappedBy = "image")
	@JsonIgnore
	private List<ImageToCartItem> imageToCartItemList;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public double getListPrice() {
		return listPrice;
	}


	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}


	public double getOurPrice() {
		return ourPrice;
	}


	public void setOurPrice(double ourPrice) {
		this.ourPrice = ourPrice;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	


	public int getInStockNumber() {
		return inStockNumber;
	}


	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}


	public MultipartFile getImageImage() {
		return imageImage;
	}


	public void setImageImage(MultipartFile imageImage) {
		this.imageImage = imageImage;
	}


	public List<ImageToCartItem> getImageToCartItemList() {
		return imageToCartItemList;
	}


	public void setImageToCartItemList(List<ImageToCartItem> imageToCartItemList) {
		this.imageToCartItemList = imageToCartItemList;
	}

	
	
}
