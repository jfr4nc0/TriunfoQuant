package com.jcanossa.triunfo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Segment {

	@Id
	protected String marketSegmentId;
	protected String marketId;
	
	public Segment() {
		super();
	}
}
