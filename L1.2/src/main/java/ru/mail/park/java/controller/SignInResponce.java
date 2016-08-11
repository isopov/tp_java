package ru.mail.park.java.controller;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInResponce extends SimpleResponce {

	@JsonProperty("Name")
	private final String name;

	public SignInResponce(@NotNull String status, @NotNull String name) {
		super(status);
		this.name = name;
	}

	@NotNull
	public String getName() {
		return name;
	}

}
