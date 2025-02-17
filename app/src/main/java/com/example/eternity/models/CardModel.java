package com.example.eternity.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "cards")
public class CardModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String cardNumber;
    private String expiryDate;
    private String cvc;
    private String cardHolder;


    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardModel cardModel = (CardModel) o;
        return id == cardModel.id &&
                Objects.equals(title, cardModel.title) &&
                Objects.equals(cardNumber, cardModel.cardNumber) &&
                Objects.equals(cardHolder, cardModel.cardHolder) &&
                Objects.equals(expiryDate, cardModel.expiryDate) &&
                Objects.equals(cvc, cardModel.cvc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, cardNumber, cardHolder, expiryDate, cvc);
    }
}
