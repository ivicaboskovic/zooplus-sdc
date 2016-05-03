package com.zooplus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NonExistingAuctionException extends Exception {

	private final int auctionId;

	public NonExistingAuctionException(String message, int auctionId) {
		super(message);
		this.auctionId = auctionId;
	}

	public int getAuctionId() {
		return auctionId;
	}
}
