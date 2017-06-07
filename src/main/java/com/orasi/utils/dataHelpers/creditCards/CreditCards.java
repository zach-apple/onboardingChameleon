package com.orasi.utils.dataHelpers.creditCards;

/**
 * Container class to store credit cards and easily retrieve them
 * 
 * @author justin.phlegar@orasi.com
 *
 * 
 */
public class CreditCards {
    /*
     * Expected storage pattern is as follows
     * return new CreditCard(
     * cardType,
     * nameOnCard,
     * encodedCardNumber,
     * encodedSecurityCode,
     * expirationMonth,
     * expirationYear,
     * billingAddress,
     * billingAddress2,
     * billingCity,
     * billingState,
     * billingCountry,
     * billingZipCode
     * );
     */

    public final static CreditCard MASTERCARD() {
        return new CreditCard(
                "MASTERCARD",
                "Ted Tester",
                "MjIyMzAwMDA0ODQwMDAxMQ==",
                "MDM2",
                "12",
                "20",
                "201 Hamilton Av",
                "",
                "Trenton",
                "NJ",
                "USA",
                "08609");
    }

    public final static CreditCard VISA() {
        return new CreditCard(
                "VISA",
                "Niki OReilly",
                "NDI2NjkwMjAzNjI1MDY0Mw==",
                "OTgw",
                "12",
                "20",
                "123 Test Lane",
                "",
                "Orlando",
                "FL",
                "USA",
                "32830");
    }

    public final static CreditCard VISA_EXPIRED() {
        return new CreditCard(
                "VISA_EXPIRED",
                "Niki OReilly",
                "NDI2NjkwMjAzNjI1MDY0Mw==",
                "OTgw",
                "12",
                "12",
                "123 Test Lane",
                "",
                "Orlando",
                "FL",
                "USA",
                "32830");
    }
}
