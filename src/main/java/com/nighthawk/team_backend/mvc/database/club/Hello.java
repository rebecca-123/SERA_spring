package com.nighthawk.team_backend.mvc.database.club;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "{JSON: Hello World}";
	}

}