package com.ssafy.happyhouse.model.dto;

public class DijkstraData {
	private int from;
	private int to;
	private double distance;
	private double time;
	
	public DijkstraData() {}
	public DijkstraData(int from) {
		this.from = from;
	}
	public DijkstraData(int from, int to, double distance, double time) {
		super();
		this.from = from;
		this.to = to;
		this.distance = distance;
		this.time = time;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "DijkstraData [from=" + from + ", to=" + to + ", distance=" + distance + ", time=" + time + "]";
	}
	
	
	
}
