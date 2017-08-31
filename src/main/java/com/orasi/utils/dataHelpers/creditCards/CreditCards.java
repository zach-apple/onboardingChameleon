package com.orasi.utils.dataHelpers.creditCards;

import com.orasi.exception.AutomationException;

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

    public final static CreditCard AMEX() {
        return new CreditCard(
                "AMEX",
                "Bernard Calderon",
                "MzcxNDQ5NjM1Mzk4NDMx",
                "MzI2NQ==",
                "12",
                "20",
                "333 Branson Landing",
                "",
                "Branson",
                "MO",
                "USA",
                "65616");
    }

    public final static CreditCard DISCOVER() {
        return new CreditCard(
                "DISCOVER",
                "Will Lund",
                "NjAxMTA5OTkwMDE5ODU0Ng==",
                "NzE4",
                "12",
                "20",
                "1500 Polaris Parkway",
                "",
                "Columbus",
                "OH",
                "USA",
                "43240");
    }

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

    /**
     * This method allows you to enter the type of card you want
     *
     * @param type
     * @return
     */
    public static CreditCard getCreditCardByType(String type) {
        switch (type.toLowerCase().replaceAll("\\s+|_", "")) {

            case "amex":
            case "americanexpress":
                return AMEX();

            case "disc":
            case "discover":
                return DISCOVER();

            case "mc":
            case "mastercard":
                return MASTERCARD();

            case "visa":
                return VISA();

            case "visaexpired":
                return VISA_EXPIRED();

            /*
             * case "gc":
             * case "gift":
             * case "giftcard":
             * // return GIFTCARD(); for later storage
             * break;
             */

        }

        // Should not be at this point unless card was not found above
        throw new AutomationException("Credit Card type of [ " + type + " ] was not valid or availible");
    }
}
