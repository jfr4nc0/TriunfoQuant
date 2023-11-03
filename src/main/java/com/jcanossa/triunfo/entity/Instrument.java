package com.jcanossa.triunfo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Instrument {

	@Id
	public List<String> intrumentId; // [marketId, symbol]
	public String cficode;
	public String symbol;
	public Segment segment;
	public Number lowLimitPrice;
	public Number highLimitPrice;
	public Number minPriceIncrement;
	public Number minTradeVol;
	public Number maxTradeVol;
	public Number tickSize;
	public Number contractMultiplier;
	public Number roundLot;
	public Number priceConvertionFactor;
	public String maturityDate;
	public String currency;
	public List<String> orderTypes;
	public List<String> timesInForce;
	public String securityType;
	public String settlTyoe;
	public Number instrumentPricePrecision;
	public Number instrumentSizePrecision;
	public String securityId;
	public String securityIdSource;
	public String securityDescription;
	public List<String[]> tickPriceRanges;
	
	public Instrument(List<String> intrumentId, String cficode, String symbol, Segment segment, Number lowLimitPrice,
			Number highLimitPrice, Number minPriceIncrement, Number minTradeVol, Number maxTradeVol, Number tickSize,
			Number contractMultiplier, Number roundLot, Number priceConvertionFactor, String maturityDate,
			String currency, List<String> orderTypes, List<String> timesInForce, String securityType, String settlTyoe,
			Number instrumentPricePrecision, Number instrumentSizePrecision, String securityId, String securityIdSource,
			String securityDescription, List<String[]> tickPriceRanges) {
		super();
		this.intrumentId = intrumentId;
		this.cficode = cficode;
		this.symbol = symbol;
		this.segment = segment;
		this.lowLimitPrice = lowLimitPrice;
		this.highLimitPrice = highLimitPrice;
		this.minPriceIncrement = minPriceIncrement;
		this.minTradeVol = minTradeVol;
		this.maxTradeVol = maxTradeVol;
		this.tickSize = tickSize;
		this.contractMultiplier = contractMultiplier;
		this.roundLot = roundLot;
		this.priceConvertionFactor = priceConvertionFactor;
		this.maturityDate = maturityDate;
		this.currency = currency;
		this.orderTypes = orderTypes;
		this.timesInForce = timesInForce;
		this.securityType = securityType;
		this.settlTyoe = settlTyoe;
		this.instrumentPricePrecision = instrumentPricePrecision;
		this.instrumentSizePrecision = instrumentSizePrecision;
		this.securityId = securityId;
		this.securityIdSource = securityIdSource;
		this.securityDescription = securityDescription;
		this.tickPriceRanges = tickPriceRanges;
	}

	@Override
	public String toString() {
		return "Instrument [intrumentId=" + intrumentId + ", cficode=" + cficode + ", symbol=" + symbol + ", segment="
				+ segment + ", lowLimitPrice=" + lowLimitPrice + ", highLimitPrice=" + highLimitPrice
				+ ", minPriceIncrement=" + minPriceIncrement + ", minTradeVol=" + minTradeVol + ", maxTradeVol="
				+ maxTradeVol + ", tickSize=" + tickSize + ", contractMultiplier=" + contractMultiplier + ", roundLot="
				+ roundLot + ", priceConvertionFactor=" + priceConvertionFactor + ", maturityDate=" + maturityDate
				+ ", currency=" + currency + ", orderTypes=" + orderTypes + ", timesInForce=" + timesInForce
				+ ", securityType=" + securityType + ", settlTyoe=" + settlTyoe + ", instrumentPricePrecision="
				+ instrumentPricePrecision + ", instrumentSizePrecision=" + instrumentSizePrecision + ", securityId="
				+ securityId + ", securityIdSource=" + securityIdSource + ", securityDescription=" + securityDescription
				+ ", tickPriceRanges=" + tickPriceRanges + "]";
	}
	
	
}
