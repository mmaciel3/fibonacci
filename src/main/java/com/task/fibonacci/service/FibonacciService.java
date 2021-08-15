package com.task.fibonacci.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class FibonacciService {
	Map<BigInteger, BigInteger> fibonacciCache = new HashMap<>();

	public BigInteger calculate(BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) == 0) {
			return BigInteger.ZERO;
		}

		if (n.compareTo(BigInteger.TWO) < 1) {
			return BigInteger.ONE;
		}

		BigInteger cachedValueForN = fibonacciCache.get(n);

		if (cachedValueForN != null) {
			return cachedValueForN;
		}

		BigInteger fibNMinus1 = calculate(n.subtract(BigInteger.ONE));
		BigInteger fibNMinus2 = calculate(n.subtract(BigInteger.TWO));
		BigInteger fibN = fibNMinus1.add(fibNMinus2);

		fibonacciCache.put(n, fibN);

		return fibN;
	}
}