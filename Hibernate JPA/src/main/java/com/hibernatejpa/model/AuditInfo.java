package com.hibernatejpa.model;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class AuditInfo {
    private String createdBy;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
