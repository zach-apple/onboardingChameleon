package com.orasi.utils;

import java.util.Arrays;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBase64Encoder {
    char[] testByteChars = null;
    byte[] testByteBytes = null;
    byte[] testByteArraytoTest = new byte[]{43,23,64,78,24,14,124,12,23,47,43,21,23,14,45,68,84,93,2,23,114,45,35,35,23,3,32,34};
    
    @Test(groups={"regression", "smoke"})
    public void encodeByte(){
	testByteChars = Base64Coder.encode(testByteArraytoTest);
	Assert.assertTrue(testByteChars.length > 0);
	Assert.assertTrue(Arrays.equals(testByteChars,"KxdAThgOfAwXLysVFw4tRFRdAhdyLSMjFwMgIg==".toCharArray()));
    }
    
    @Test(groups="regression", dependsOnMethods="encodeByte")
    public void decodeByte(){
	testByteBytes = Base64Coder.decode(testByteChars);
	Assert.assertTrue(Arrays.equals(testByteBytes,testByteArraytoTest));
    }
       
    @Test(groups="regression")
    public void encodeSizes(){
	Random random = new Random();
	byte[] orig1024 = new byte[1024];
        random.nextBytes(orig1024);
           
        byte[] orig2048 = new byte[2048];
        random.nextBytes(orig2048);
           
        byte[] orig4096 = new byte[4096];
        random.nextBytes(orig4096);
           
        byte[] orig8192 = new byte[8192];
        random.nextBytes(orig8192);
           
        char[] enc1024 = Base64Coder.encode(orig1024);
        char[] enc2048 = Base64Coder.encode(orig2048);
        char[] enc4096 = Base64Coder.encode(orig4096);
        char[] enc8192 = Base64Coder.encode(orig8192);
           
        byte[] dec1024 = Base64Coder.decode(enc1024);
        byte[] dec2048 = Base64Coder.decode(enc2048);
        byte[] dec4096 = Base64Coder.decode(enc4096);
        byte[] dec8192 = Base64Coder.decode(enc8192);
           
        Assert.assertTrue(Arrays.equals(orig1024, dec1024));
        Assert.assertTrue(Arrays.equals(orig2048, dec2048));
        Assert.assertTrue(Arrays.equals(orig4096, dec4096));
        Assert.assertTrue(Arrays.equals(orig8192, dec8192));
    }
  
    @Test(groups="regression")
    public void encodeString(){
	char base64_encoded_test[] = ("U28/PHA+VGhpcyA0LCA1LCA2LCA3LCA4LCA5LCB6LCB7LCB8LCB9IHRlc3RzIEJhc2U2NCBlbmNv" +
		"ZGVyLiBTaG93IG1lOiBALCBBLCBCLCBDLCBELCBFLCBGLCBHLCBILCBJLCBKLCBLLCBMLCBNLCBO"+
		"LCBPLCBQLCBRLCBSLCBTLCBULCBVLCBWLCBXLCBYLCBZLCBaLCBbLCBcLCBdLCBeLCBfLCBgLCBh"+
		"LCBiLCBjLCBkLCBlLCBmLCBnLCBoLCBpLCBqLCBrLCBsLCBtLCBuLCBvLCBwLCBxLCByLCBzLg==").toCharArray();
	
	String test_string = "So?<p>" +
		    "This 4, 5, 6, 7, 8, 9, z, {, |, } tests Base64 encoder. " +
		    "Show me: @, A, B, C, D, E, F, G, H, I, J, K, L, M, " +
		    "N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \\, ], ^, _, `, " +
		    "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s.";
	
	Assert.assertTrue(Arrays.equals( Base64Coder.encodeString(test_string).toCharArray(), base64_encoded_test));
    }
    
    @Test(groups="regression")
    public void decodeString(){
	String base64_decoded_test = "U28/PHA+VGhpcyA0LCA1LCA2LCA3LCA4LCA5LCB6LCB7LCB8LCB9IHRlc3RzIEJhc2U2NCBlbmNv" +
		"ZGVyLiBTaG93IG1lOiBALCBBLCBCLCBDLCBELCBFLCBGLCBHLCBILCBJLCBKLCBLLCBMLCBNLCBO"+
		"LCBPLCBQLCBRLCBSLCBTLCBULCBVLCBWLCBXLCBYLCBZLCBaLCBbLCBcLCBdLCBeLCBfLCBgLCBh"+
		"LCBiLCBjLCBkLCBlLCBmLCBnLCBoLCBpLCBqLCBrLCBsLCBtLCBuLCBvLCBwLCBxLCByLCBzLg==";
	
	char test_string[] =  ("So?<p>" +
		    "This 4, 5, 6, 7, 8, 9, z, {, |, } tests Base64 encoder. " +
		    "Show me: @, A, B, C, D, E, F, G, H, I, J, K, L, M, " +
		    "N, O, P, Q, R, S, T, U, V, W, X, Y, Z, [, \\, ], ^, _, `, " +
		    "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s.").toCharArray();
	
	Assert.assertTrue(Arrays.equals( Base64Coder.decodeString(base64_decoded_test).toCharArray(), test_string));
    }
}
