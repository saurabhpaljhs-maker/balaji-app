package com.balaji.config;

import com.balaji.model.*;
import com.balaji.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final FrameSizeRepository frameSizeRepo;
    private final BeadingOptionRepository beadingRepo;
    private final CoverOptionRepository coverRepo;
    private final AdminUserRepository adminRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("STARTING DATA INITIALIZATION");
            seedAdmin();
            seedFrameSizes();
            seedBeadingOptions();
            seedCoverOptions();
            log.info("DATA INITIALIZATION COMPLETED SUCCESSFULLY");
        } catch (Exception e) {
            log.error("ERROR DURING DATA INITIALIZATION: {}", e.getMessage(), e);
        }
    }

    private void seedAdmin() {
        try {
            if (adminRepo.count() == 0) {
                AdminUser admin = new AdminUser();
                admin.setUsername("balaji_admin");
                admin.setPassword(passwordEncoder.encode("Balaji@2024#Secure"));
                admin.setRole("ROLE_ADMIN");
                adminRepo.save(admin);
                log.info("Admin user created successfully");
            } else {
                log.info("Admin user already exists");
            }
        } catch (Exception e) {
            log.error("Error seeding admin: {}", e.getMessage());
        }
    }

    private void seedFrameSizes() {
        try {
            if (frameSizeRepo.count() == 0) {
                List<FrameSize> sizes = new ArrayList<>();
                
                sizes.add(createFrameSize("4x6", 4, 6, "80", "Wallet / ID Photo", "4 x 6"));
                sizes.add(createFrameSize("5x7", 5, 7, "100", "Passport / Portrait", "5 x 7"));
                sizes.add(createFrameSize("6x8", 6, 8, "130", "Table Display", "6 x 8"));
                sizes.add(createFrameSize("8x10", 8, 10, "180", "Photo Portrait", "8 x 10"));
                sizes.add(createFrameSize("10x12", 10, 12, "240", "Family Photo", "10 x 12"));
                sizes.add(createFrameSize("10x14", 10, 14, "280", "Event Photo", "10 x 14"));
                sizes.add(createFrameSize("10x15", 10, 15, "290", "Landscape", "10 x 15"));
                sizes.add(createFrameSize("12x14", 12, 14, "320", "Group Photo", "12 x 14"));
                sizes.add(createFrameSize("12x15", 12, 15, "340", "Large Portrait", "12 x 15"));
                sizes.add(createFrameSize("12x18", 12, 18, "390", "Wedding Photo", "12 x 18"));
                sizes.add(createFrameSize("16x20", 16, 20, "520", "Wall Display", "16 x 20"));
                sizes.add(createFrameSize("16x24", 16, 24, "620", "Gallery Wall", "16 x 24"));
                sizes.add(createFrameSize("18x24", 18, 24, "680", "Poster Size", "18 x 24"));
                sizes.add(createFrameSize("20x30", 20, 30, "850", "Large Wall Art", "20 x 30"));
                sizes.add(createFrameSize("24x36", 24, 36, "1150", "Statement Piece", "24 x 36"));
                sizes.add(createFrameSize("30x40", 30, 40, "1600", "Grand Display", "30 x 40"));
                
                frameSizeRepo.saveAll(sizes);
                log.info("Frame sizes seeded: {} items", sizes.size());
            } else {
                log.info("Frame sizes already exist");
            }
        } catch (Exception e) {
            log.error("Error seeding frame sizes: {}", e.getMessage());
        }
    }

    private void seedBeadingOptions() {
        try {
            if (beadingRepo.count() == 0) {
                List<BeadingOption> beadings = new ArrayList<>();
                
                beadings.add(createBeading("0.7 inch", "0.7 inch", 0.7, "40", "White Gold", "linear-gradient(135deg,#F5F5DC 0%,#FFD700 25%,#FFA500 50%,#FF8C00 75%,#DAA520 100%)", "White Gold - Thin delicate border", 6));
                beadings.add(createBeading("0.7 inch", "0.7 inch", 0.7, "45", "Coffee Ivory", "linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5F5DC 75%,#000000 100%)", "Coffee Ivory - Two tone classic", 6));
                beadings.add(createBeading("0.7 inch", "0.7 inch", 0.7, "50", "Sky Blue Gold", "linear-gradient(135deg,#87CEEB 0%,#4682B4 25%,#4169E1 50%,#FFD700 75%,#B8860B 100%)", "Sky Blue Gold - Modern elegant", 6));

                beadings.add(createBeading("1 inch", "1 inch", 1.0, "60", "Black T-2", "repeating-linear-gradient(45deg,#1a1a1a 0px,#1a1a1a 3px,#404040 3px,#404040 6px,#2a2a2a 6px,#2a2a2a 10px)", "Black T-2 - Textured finish", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "65", "Black T-3", "repeating-linear-gradient(45deg,#0a0a0a 0px,#0a0a0a 2px,#2a2a2a 2px,#2a2a2a 5px,#1a1a1a 5px,#1a1a1a 8px,#3a3a3a 8px,#3a3a3a 10px)", "Black T-3 - Deep texture", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "70", "Black T-4", "repeating-linear-gradient(90deg,#000000 0px,#000000 2px,#1a1a1a 2px,#1a1a1a 4px,#2a2a2a 4px,#2a2a2a 6px,#3a3a3a 6px,#3a3a3a 8px,#4a4a4a 8px,#4a4a4a 10px)", "Black T-4 - Lattice pattern", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "75", "Radiant Orange", "linear-gradient(135deg,#FF8C00 0%,#FF6347 25%,#FF4500 50%,#DC143C 75%,#8B0000 100%)", "Radiant Orange - Vibrant warm", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "75", "Radiant Red", "linear-gradient(135deg,#000000 0%,#DC143C 25%,#FF1493 50%,#C71585 75%,#8B0000 100%)", "Radiant Red - Bold passion", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "80", "Tiger Pattern", "repeating-linear-gradient(45deg,#000000 0px,#000000 3px,#8B4513 3px,#8B4513 7px,#D2B48C 7px,#D2B48C 10px,#654321 10px,#654321 14px)", "Tiger Pattern - Wild stripes", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "70", "N Wood Pin", "linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5DEB3 75%,#C19A6B 100%)", "N Wood Pin - Natural wood", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "65", "White Gold", "linear-gradient(135deg,#F5F5DC 0%,#FFD700 30%,#DAA520 60%,#B8860B 100%)", "White Gold - Elegant combo", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "70", "Sky Blue Gold", "linear-gradient(135deg,#87CEEB 0%,#4682B4 25%,#FFD700 50%,#DAA520 75%,#8B7355 100%)", "Sky Blue Gold - Ocean breeze", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "75", "CZ RST", "linear-gradient(135deg,#8B4513 0%,#6B3410 25%,#D2B48C 50%,#8B4513 75%,#654321 100%)", "CZ RST - Rich chocolate", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "70", "Rose White RST", "linear-gradient(135deg,#F5E6D3 0%,#FFB6C1 25%,#E6D4C7 50%,#D2B48C 75%,#A0826D 100%)", "Rose White RST - Soft pink", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "80", "Gold White RST", "linear-gradient(135deg,#FFD700 0%,#FFA500 25%,#F5F5DC 50%,#DAA520 75%,#8B7355 100%)", "Gold White RST - Luxurious", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "65", "Pine Wood RST", "linear-gradient(135deg,#D2B48C 0%,#C19A6B 25%,#F5DEB3 50%,#D2B48C 75%,#8B7355 100%)", "Pine Wood RST - Natural warm", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "68", "Brown Pine RST", "linear-gradient(135deg,#3E2723 0%,#5D4037 25%,#D2B48C 50%,#C19A6B 75%,#1A0E0E 100%)", "Brown Pine RST - Dark wood", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "62", "Black RST", "linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#2a2a2a 50%,#1a1a1a 75%,#000000 100%)", "Black RST - Classic bold", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "72", "Coffee Ivory RST", "linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5F5DC 75%,#3E2723 100%)", "Coffee Ivory RST - Two tone", 8));
                beadings.add(createBeading("1 inch", "1 inch", 1.0, "77", "Black Brown RST", "linear-gradient(135deg,#000000 0%,#3E2723 25%,#8B4513 50%,#A0522D 75%,#1A0E0E 100%)", "Black Brown RST - Deep contrast", 8));

                beadings.add(createBeading("1.2 inch", "1.2 inch", 1.2, "85", "CZ Model 6", "linear-gradient(135deg,#8B4513 0%,#6B3410 25%,#D2B48C 50%,#8B4513 75%,#654321 100%)", "CZ - Rich baroque", 9));
                beadings.add(createBeading("1.2 inch", "1.2 inch", 1.2, "90", "Black Gold Model 6", "linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#FFD700 50%,#DAA520 75%,#2a2a2a 100%)", "Black Gold - Luxury accent", 9));

                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "95", "Natural Louvers", "linear-gradient(135deg,#8B6F47 0%,#C19A6B 25%,#DAA520 50%,#F5DEB3 75%,#8B7355 100%)", "Natural Louvers - Elegant", 10));
                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "100", "CZ Model 122", "linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#DAA520 50%,#F5DEB3 75%,#6B4423 100%)", "CZ RST - Warm luxury", 10));
                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "98", "Night Wood Gold", "linear-gradient(135deg,#2C1810 0%,#3E2723 25%,#8B6F47 50%,#C19A6B 75%,#8B4513 100%)", "Night Wood Gold - Dark rich", 10));

                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "110", "B Gold Louvers", "linear-gradient(135deg,#000000 0%,#8B4513 25%,#DAA520 50%,#F5DEB3 75%,#000000 100%)", "B Gold Louvers - Premium", 10));
                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "105", "B Pinewood", "linear-gradient(135deg,#000000 0%,#D2B48C 25%,#F5DEB3 50%,#D2B48C 75%,#000000 100%)", "B Pinewood - Light accent", 10));
                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "108", "B Louvers", "linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5DEB3 75%,#8B4513 100%)", "B Louvers - Classic wood", 10));
                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "112", "B Gold Pine", "linear-gradient(135deg,#000000 0%,#FFD700 25%,#F5DEB3 50%,#F0F8FF 75%,#000000 100%)", "B Gold Pine - Contrast", 10));
                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "115", "Gold Black T1", "linear-gradient(135deg,#DAA520 0%,#1a1a1a 25%,#2a2a2a 50%,#1a1a1a 75%,#000000 100%)", "Gold Black T1 - Bold modern", 10));
                beadings.add(createBeading("1.25 inch", "1.25 inch", 1.25, "118", "Black Gold T5", "repeating-linear-gradient(45deg,#000000 0px,#000000 3px,#FFD700 3px,#FFD700 6px,#1a1a1a 6px,#1a1a1a 10px)", "Black Gold T5 - Striped", 10));

                beadingRepo.saveAll(beadings);
                log.info("Beading options seeded: {} items", beadings.size());
            } else {
                log.info("Beading options already exist");
            }
        } catch (Exception e) {
            log.error("Error seeding beading options: {}", e.getMessage(), e);
        }
    }

    private void seedCoverOptions() {
        try {
            if (coverRepo.count() == 0) {
                List<CoverOption> covers = new ArrayList<>();
                
                covers.add(createCover("MATTE", "Matte Lamination", "Matte", "Soft anti-glare finish", "80"));
                covers.add(createCover("GLOSS", "Gloss Lamination", "Gloss", "Vivid shiny finish", "60"));
                covers.add(createCover("GLASS", "Real Glass", "Glass", "Traditional glass cover", "150"));
                covers.add(createCover("ACRYLIC", "Acrylic Sheet", "Acrylic", "Crystal-clear shatterproof", "200"));
                covers.add(createCover("NONE", "No Cover", "None", "Frame only, no cover", "0"));
                
                coverRepo.saveAll(covers);
                log.info("Cover options seeded: {} items", covers.size());
            } else {
                log.info("Cover options already exist");
            }
        } catch (Exception e) {
            log.error("Error seeding cover options: {}", e.getMessage());
        }
    }

    private FrameSize createFrameSize(String size, int width, int height, String price, String popular, String label) {
        FrameSize fs = new FrameSize();
        fs.setSize(size);
        fs.setWidthInch(width);
        fs.setHeightInch(height);
        fs.setBasePrice(new BigDecimal(price));
        fs.setPopularFor(popular);
        fs.setDisplayLabel(label);
        return fs;
    }

    private BeadingOption createBeading(String widthLabel, String displayWidth, Double widthValue, String price, String pattern, String gradient, String description, Integer borderPx) {
        BeadingOption bo = new BeadingOption();
        bo.setWidthLabel(widthLabel);
        bo.setDisplayWidth(displayWidth);
        bo.setWidthValue(widthValue);
        bo.setAdditionalPrice(new BigDecimal(price));
        bo.setPattern(pattern);
        bo.setGradientCss(gradient);
        bo.setDescription(description);
        bo.setBorderPx(borderPx);
        return bo;
    }

    private CoverOption createCover(String type, String displayName, String emoji, String description, String price) {
        CoverOption co = new CoverOption();
        co.setCoverType(type);
        co.setDisplayName(displayName);
        co.setEmoji(emoji);
        co.setDescription(description);
        co.setAdditionalPrice(new BigDecimal(price));
        return co;
    }
}
