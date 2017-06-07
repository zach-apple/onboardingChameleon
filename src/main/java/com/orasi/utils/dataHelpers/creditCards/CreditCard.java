package com.orasi.utils.dataHelpers.creditCards;

import com.orasi.utils.Base64Coder;

public class CreditCard {
    private String nameOnCard;
    private String cardNumber;
    private String securityCode;
    private String cardType;
    private String expireMonth;
    private String expireYear;
    private String billingStreet;
    private String billingStreet2;
    private String billingCity;
    private String billingState;
    private String billingCountry;
    private String billingZip;

    public CreditCard(String cardType, String nameOnCard, String cardNumber, String securityCode, String expireMonth, String expireYear,
            String billingStreet, String billingStreet2, String billingCity, String billingState, String billingCountry, String billingZip) {

        this.cardType = cardType;
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        this.billingStreet = billingStreet;
        this.billingStreet2 = billingStreet2;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingCountry = billingCountry;
        this.billingZip = billingZip;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getCardNumber() {
        return Base64Coder.decodeString(cardNumber);
    }

    public String getSecurityCode() {
        return Base64Coder.decodeString(securityCode);
    }

    public String getCardType() {
        return cardType;
    }

    public String getExpireMonth() {
        return expireMonth;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public String getBillingStreet2() {
        return billingStreet2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public String getBillingZip() {
        return billingZip;
    }

    @Override
    public String toString() {
        return String.format("Generating Credit card with the following info:%nCard Type:%n%s%n%nName on Card:%n%s%n%nAddress Info:%n%s%n%s, %s %s%n%s",
                cardType, nameOnCard, billingStreet, billingCity, billingState, billingCountry, billingZip);
    }
}
