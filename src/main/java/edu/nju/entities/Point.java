package edu.nju.entities;

public class Point {
	private String title;
	private double px;
	private double py;
	
	public Point(String title, double px, double py) {
		super();
		this.title = title;
		this.px = px;
		this.py = py;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPx() {
		return px;
	}
	public void setPx(double px) {
		this.px = px;
	}
	public double getPy() {
		return py;
	}
	public void setPy(double py) {
		this.py = py;
	}
}
