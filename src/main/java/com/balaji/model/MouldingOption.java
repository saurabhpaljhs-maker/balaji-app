package com.balaji.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

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

    @Column(nullable = false)
    private String modelNo;           // "50", "5", "23" etc.

    @Column(nullable = false)
    private String sizeInch;          // "0.7\"", "1\"", "1.25\"" etc.

    @Column(nullable = false)
    private String displayName;       // "Black", "Golden", "Black Gold" etc.

    @Column(nullable = false)
    private String colorCategory;     // "Black", "Brown", "Golden", "Golden Brown", "Multi-color"

    @Column(nullable = false)
    private Double thicknessInch;     // 0.7, 1.0, 1.25, 1.5, 1.75, 2.0, 2.5, 3.0 etc.

    @Column(nullable = false)
    private Integer thicknessRank;    // 1=thinnest, 10=thickest (for sorting)

    @Column(nullable = false)
    private BigDecimal additionalPrice;

    @Column(nullable = false, length = 300)
    private String gradientCss;       // CSS gradient for preview

    @Column(nullable = false, length = 300)
    private String imageUrl;          // SVG or image URL for thumbnail

    @Column(nullable = false)
    private String description;       // "Classic plain finish", "Ornate design" etc.

    @Column(nullable = false)
    private Boolean isPopular;        // Mark popular ones
}
