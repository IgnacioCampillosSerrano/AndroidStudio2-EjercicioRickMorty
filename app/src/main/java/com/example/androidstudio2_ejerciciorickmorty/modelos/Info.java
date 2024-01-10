package com.example.androidstudio2_ejerciciorickmorty.modelos;

public class Info{
	private String next;
	private int pages;
	private String prev;
	private int count;

	public void setNext(String next){
		this.next = next;
	}

	public String getNext(){
		return next;
	}

	public void setPages(int pages){
		this.pages = pages;
	}

	public int getPages(){
		return pages;
	}

	public void setPrev(String prev){
		this.prev = prev;
	}

	public Object getPrev(){
		return prev;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	@Override
 	public String toString(){
		return 
			"Info{" + 
			"next = '" + next + '\'' + 
			",pages = '" + pages + '\'' + 
			",prev = '" + prev + '\'' + 
			",count = '" + count + '\'' + 
			"}";
		}
}
