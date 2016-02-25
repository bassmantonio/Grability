package com.grability.DTO;

import java.util.Properties;

/**
 * This class represents an application price.
 * @author: Mauricio Antonio Moreno Marulanda.
 * @version: 24/02/2016
 */
public class PriceDTO {

    //Properties
    private String Label;
    private String Amount;
    private String Currency;

    /**
     * Constructor
     * @param label Price label
     * @param amount Price amount
     * @param currency Price currency
     */
    public PriceDTO(String label, String amount, String currency) {
        Label = label;
        Amount = amount;
        Currency = currency;
    }

    // Gets and sets Label Attribute
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    // Gets and sets Amount Attribute
    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    // Gets and sets Currency Attribute
    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }
}
