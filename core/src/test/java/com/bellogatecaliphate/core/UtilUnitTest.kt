package com.bellogatecaliphate.core

import org.junit.Assert.assertEquals
import org.junit.Test
import java.security.SecureRandom

class UtilUnitTest {
	
	@Test
	fun testGenerateRandomNumberWithFixedLength() {
		val length = 5
		val randomNumber = generateRandomNumberWithFixedLength(length)
		assertEquals(
			length,
			randomNumber.length
		) // Check if the length is correct assertTrue(randomNumber.all { it.isDigit() }) // Check if all characters are digits
	}
	
	@Test
	fun testGenerateRandomNumberWithZeroLength() {
		val length = 0
		val randomNumber = generateRandomNumberWithFixedLength(length)
		assertEquals("0", randomNumber) // Check if it returns "0" for length 0
	}
	
	@Test
	fun testGenerateRandomNumberWithNegativeLength() {
		val length = - 5
		val randomNumber = generateRandomNumberWithFixedLength(length)
		assertEquals("0", randomNumber) // Check if it returns "0" for negative length
	}
	
	// Helper function (same as before)
	private fun generateRandomNumberWithFixedLength(length: Int): String {
		if (length <= 0) {
			return "0"
		}
		val random = SecureRandom()
		val sb = StringBuilder(length)
		for (i in 0 until length) {
			sb.append(random.nextInt(10))
		}
		return sb.toString()
	}
}