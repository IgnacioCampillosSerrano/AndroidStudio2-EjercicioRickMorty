package com.example.androidstudio2_ejerciciorickmorty.modelos;

import java.util.List;

public class Character {
	private String image;
	private String gender;
	private String species;
	private String created;
	private Origin origin;
	private String name;
	private Location location;
	private List<String> episode;
	private int id;
	private String type;
	private String url;
	private String status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setSpecies(String species){
		this.species = species;
	}

	public String getSpecies(){
		return species;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setOrigin(Origin origin){
		this.origin = origin;
	}

	public Origin getOrigin(){
		return origin;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setEpisode(List<String> episode){
		this.episode = episode;
	}

	public List<String> getEpisode(){
		return episode;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"image = '" + image + '\'' + 
			",gender = '" + gender + '\'' + 
			",species = '" + species + '\'' + 
			",created = '" + created + '\'' + 
			",origin = '" + origin + '\'' + 
			",name = '" + name + '\'' + 
			",location = '" + location + '\'' + 
			",episode = '" + episode + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",url = '" + url + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}