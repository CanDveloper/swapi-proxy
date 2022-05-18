package com.tech.proxyapi;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tech.proxyapi.exceptions.EmptySearchException;
import com.tech.proxyapi.services.ProxyApiService;

@SpringBootTest
public class ProxyApiServiceTest {

	@Autowired
	ProxyApiService proxyApiService;

	@Test
	public void testExtractingPersonInfoNullArgument() {
		Exception exception = assertThrows(EmptySearchException.class, () -> {
			proxyApiService.extractingPersonInfo(null);
		});

		String expectedMessage = "No existe el personaje indicado";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testExtractingPersonInfoStrangeCharactersString() {
		Exception exception = assertThrows(EmptySearchException.class, () -> {
			proxyApiService.extractingPersonInfo("大");
		});

		String expectedMessage = "No existe el personaje indicado";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
