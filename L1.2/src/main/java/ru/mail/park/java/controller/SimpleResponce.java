package ru.mail.park.java.controller;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleResponce {

	@JsonProperty("Status")
	private final String status;

	public SimpleResponce(@NotNull String status) {
		this.status = status;
	}

	@NotNull
	public String getStatus() {
		return status;
	}

}
