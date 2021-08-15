package com.task.fibonacci.controller;

import com.task.fibonacci.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping("/fib")
public class FibonacciController {

	@Autowired
	private FibonacciService fibonacciService;

	@GetMapping(value = "")
	public Map<String, BigInteger> fibonacci(@RequestParam(value = "n") String n) {
		BigInteger number = new BigInteger(n);

		if (number.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("n must be a positive number");
		}
		BigInteger result = fibonacciService.calculate(number);

		return Map.of("result", result);
	}
}