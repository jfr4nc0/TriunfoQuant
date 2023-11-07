package com.jcanossa.triunfo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Segment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marketSegmentId;
	private String marketId;
	
	public Segment() {}
	
	public Segment(Long id, String marketSegmentId, String marketId) {
		super();
		this.id = id;
		this.marketSegmentId = marketSegmentId;
		this.marketId = marketId;
	}

	public Segment(String marketSegmentId, String marketId) {
		this.marketSegmentId = marketSegmentId;
		this.marketId = marketId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarketSegmentId() {
		return marketSegmentId;
	}

	public void setMarketSegmentId(String marketSegmentId) {
		this.marketSegmentId = marketSegmentId;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	
	@Override
	public String toString() {
		return "Segment [id=" + id + ", marketSegmentId=" + marketSegmentId + ", marketId=" + marketId + "]";
	}
}
