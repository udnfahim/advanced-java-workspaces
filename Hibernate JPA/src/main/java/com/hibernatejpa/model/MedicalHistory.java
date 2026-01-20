package com.hibernatejpa.model;

import jakarta.persistence.*;

@Embeddable
public class MedicalHistory {
    private String diseases;
    private String drugs;
}
