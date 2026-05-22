package com.balaji.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moulding_options")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouldingOption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String modelNo;
    
    @Column(nullable = false)
    private String size;
    
    @Column(nullable = false)
    private Integer widthMm;
    
    @Column(nullable = false)
    private Integer heightMm;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String availableColors;
    
    @Column(nullable = false)
    private Double priceMultiplier;
    
    @Column(nullable = false)
    private String supplier;
    
    @Column(nullable = false)
    private String description;
    
    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;
    
    public String[] getColorArray() {
        return availableColors != null ? availableColors.split(",") : new String[0];
    }
    
    public Integer getColorCount() {
        return getColorArray().length;
    }
}
