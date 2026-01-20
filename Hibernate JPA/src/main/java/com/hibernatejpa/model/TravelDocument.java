package com.hibernatejpa.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class TravelDocument {
    private String passport;
    private String nidCard;
    private String visa;
}
