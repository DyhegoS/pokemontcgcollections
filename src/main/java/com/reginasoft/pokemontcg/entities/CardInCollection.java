package com.reginasoft.pokemontcg.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_card_in_collection")
public class CardInCollection implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cardcollection_id")
    private CardCollections cardCollection;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    private Integer quantity;

    public CardInCollection() {
    }

    public CardInCollection(Long id, CardCollections cardCollection, Card card, Integer quantity) {
        this.id = id;
        this.cardCollection = cardCollection;
        this.card = card;
        this.quantity = quantity;
    }

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardCollections getCollection() {
        return cardCollection;
    }

    public void setCollection(CardCollections cardCollection) {
        this.cardCollection = cardCollection;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardInCollection)) return false;
        CardInCollection that = (CardInCollection) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
