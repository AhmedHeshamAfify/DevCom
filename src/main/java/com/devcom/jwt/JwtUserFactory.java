package com.devcom.jwt;

import com.devcom.models.User;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static User create(User user) {
		return new User(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getType(),
				user.getScore());
	}

}
