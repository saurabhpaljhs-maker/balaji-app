package com.balaji.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MouldingOption - PS Moulding से Accurate Industries Catalogue
 * 19 models: 0.7" से 3" तक
 */
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
    private String modelNo;        // "50", "5", "221", etc.
    
    @Column(nullable = false)
    private String size;           // "0.7\"", "1\"", "1.25\"", etc.
    
    @Column(nullable = false)
    private Integer widthMm;       // 15, 24, 26, etc. (mm)
    
    @Column(nullable = false)
    private Integer heightMm;      // 18, 13, 20, etc. (mm)
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String availableColors;  // Comma-separated: "White Gold,Coffee Ivory,Sky Blue Gold"
    
    @Column(nullable = false)
    private Double priceMultiplier;  // 1.1 से 2.2 तक (base frame price के साथ multiply)
    
    @Column(nullable = false)
    private String supplier;         // "Accurate Industries"
    
    @Column(nullable = false)
    private String description;      // Optional description
    
    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;
    
    // Helper methods
    public String[] getColorArray() {
        return availableColors != null ? availableColors.split(",") : new String[0];
    }
    
    public Integer getColorCount() {
        return getColorArray().length;
    }
}
