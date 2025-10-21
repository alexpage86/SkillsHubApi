package com.spotevent.rest.dto;
import java.time.LocalDate;
import java.util.List;

import com.spotevent.rest.entity.KeyWord;

public class EventFilterRequest {
	
	private LocalDate from;
    private LocalDate to;
    private List<KeyWord> interests;

    private Double latMin;
    private Double latMax;
    private Double lngMin;
    private Double lngMax;

    // Getters et setters pour les dates
    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    // Getters et setters pour les coordonnées
    public Double getLatMin() {
        return latMin;
    }

    public void setLatMin(Double latMin) {
        this.latMin = latMin;
    }

    public Double getLatMax() {
        return latMax;
    }

    public void setLatMax(Double latMax) {
        this.latMax = latMax;
    }

    public Double getLngMin() {
        return lngMin;
    }

    public void setLngMin(Double lngMin) {
        this.lngMin = lngMin;
    }

    public Double getLngMax() {
        return lngMax;
    }

    public void setLngMax(Double lngMax) {
        this.lngMax = lngMax;
    }

    // Getters et setters pour les intérêts
    public List<KeyWord> getInterests() {
        return interests;
    }

    public void setInterests(List<KeyWord> interests) {
        this.interests = interests;
    }
    
}
