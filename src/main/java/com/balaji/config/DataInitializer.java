package com.balaji.config;

import com.balaji.model.*;
import com.balaji.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final FrameSizeRepository     frameSizeRepo;
    private final BeadingOptionRepository beadingRepo;
    private final CoverOptionRepository   coverRepo;
    private final AdminUserRepository     adminRepo;
    private final BCryptPasswordEncoder   passwordEncoder;

    @Value("${app.admin.username:balaji_admin}")
    private String adminUsername;

    @Value("${app.admin.password:Balaji@2024#Secure}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        seedAdmin();
        seedFrameSizes();
        seedBeadingOptions();
        seedCoverOptions();
        log.info("BALAJI Frames - All master data loaded!");
    }

    private void seedAdmin() {
        adminRepo.deleteAll();
        AdminUser admin = AdminUser.builder()
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .role("ROLE_ADMIN")
                .build();
        adminRepo.save(admin);
        log.info("Admin created: {}", adminUsername);
    }

    private void seedFrameSizes() {
        frameSizeRepo.deleteAll();
        List<FrameSize> sizes = List.of(
            FrameSize.builder().size("4x6").widthInch(4).heightInch(6).basePrice(new BigDecimal("80")).popularFor("Wallet / ID Photo").displayLabel("4 x 6").build(),
            FrameSize.builder().size("5x7").widthInch(5).heightInch(7).basePrice(new BigDecimal("100")).popularFor("Passport / Portrait").displayLabel("5 x 7").build(),
            FrameSize.builder().size("6x8").widthInch(6).heightInch(8).basePrice(new BigDecimal("130")).popularFor("Table Display").displayLabel("6 x 8").build(),
            FrameSize.builder().size("8x10").widthInch(8).heightInch(10).basePrice(new BigDecimal("180")).popularFor("Photo Portrait").displayLabel("8 x 10").build(),
            FrameSize.builder().size("10x12").widthInch(10).heightInch(12).basePrice(new BigDecimal("240")).popularFor("Family Photo").displayLabel("10 x 12").build(),
            FrameSize.builder().size("10x14").widthInch(10).heightInch(14).basePrice(new BigDecimal("280")).popularFor("Event Photo").displayLabel("10 x 14").build(),
            FrameSize.builder().size("10x15").widthInch(10).heightInch(15).basePrice(new BigDecimal("290")).popularFor("Landscape").displayLabel("10 x 15").build(),
            FrameSize.builder().size("12x14").widthInch(12).heightInch(14).basePrice(new BigDecimal("320")).popularFor("Group Photo").displayLabel("12 x 14").build(),
            FrameSize.builder().size("12x15").widthInch(12).heightInch(15).basePrice(new BigDecimal("340")).popularFor("Large Portrait").displayLabel("12 x 15").build(),
            FrameSize.builder().size("12x18").widthInch(12).heightInch(18).basePrice(new BigDecimal("390")).popularFor("Wedding Photo").displayLabel("12 x 18").build(),
            FrameSize.builder().size("16x20").widthInch(16).heightInch(20).basePrice(new BigDecimal("520")).popularFor("Wall Display").displayLabel("16 x 20").build(),
            FrameSize.builder().size("16x24").widthInch(16).heightInch(24).basePrice(new BigDecimal("620")).popularFor("Gallery Wall").displayLabel("16 x 24").build(),
            FrameSize.builder().size("18x24").widthInch(18).heightInch(24).basePrice(new BigDecimal("680")).popularFor("Poster Size").displayLabel("18 x 24").build(),
            FrameSize.builder().size("20x30").widthInch(20).heightInch(30).basePrice(new BigDecimal("850")).popularFor("Large Wall Art").displayLabel("20 x 30").build(),
            FrameSize.builder().size("24x36").widthInch(24).heightInch(36).basePrice(new BigDecimal("1150")).popularFor("Statement Piece").displayLabel("24 x 36").build(),
            FrameSize.builder().size("30x40").widthInch(30).heightInch(40).basePrice(new BigDecimal("1600")).popularFor("Grand Display").displayLabel("30 x 40").build()
        );
        frameSizeRepo.saveAll(sizes);
        log.info("Frame sizes seeded: {}", sizes.size());
    }

    private void seedBeadingOptions() {
        beadingRepo.deleteAll();
        List<BeadingOption> beadings = List.of(
            BeadingOption.builder()
                .widthLabel("0.7 inch").displayWidth("0.7\"").widthValue(0.7)
                .additionalPrice(new BigDecimal("40"))
                .pattern("White Gold")
                .gradientCss("linear-gradient(135deg,#F5F5DC 0%,#FFD700 25%,#FFA500 50%,#FF8C00 75%,#DAA520 100%)")
                .description("White Gold - Thin delicate border").borderPx(6).build(),
            
            BeadingOption.builder()
                .widthLabel("0.7 inch").displayWidth("0.7\"").widthValue(0.7)
                .additionalPrice(new BigDecimal("45"))
                .pattern("Coffee Ivory")
                .gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5F5DC 75%,#000000 100%)")
                .description("Coffee Ivory - Two-tone classic").borderPx(6).build(),
            
            BeadingOption.builder()
                .widthLabel("0.7 inch").displayWidth("0.7\"").widthValue(0.7)
                .additionalPrice(new BigDecimal("50"))
                .pattern("Sky Blue Gold")
                .gradientCss("linear-gradient(135deg,#87CEEB 0%,#4682B4 25%,#4169E1 50%,#FFD700 75%,#B8860B 100%)")
                .description("Sky Blue Gold - Modern elegant").borderPx(6).build(),

            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("60"))
                .pattern("Black T-2")
                .gradientCss("repeating-linear-gradient(45deg,#1a1a1a 0px,#1a1a1a 3px,#404040 3px,#404040 6px,#2a2a2a 6px,#2a2a2a 10px)")
                .description("Black T-2 - Textured finish").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("65"))
                .pattern("Black T-3")
                .gradientCss("repeating-linear-gradient(45deg,#0a0a0a 0px,#0a0a0a 2px,#2a2a2a 2px,#2a2a2a 5px,#1a1a1a 5px,#1a1a1a 8px,#3a3a3a 8px,#3a3a3a 10px)")
                .description("Black T-3 - Deep texture").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("70"))
                .pattern("Black T-4")
                .gradientCss("repeating-linear-gradient(90deg,#000000 0px,#000000 2px,#1a1a1a 2px,#1a1a1a 4px,#2a2a2a 4px,#2a2a2a 6px,#3a3a3a 6px,#3a3a3a 8px,#4a4a4a 8px,#4a4a4a 10px)")
                .description("Black T-4 - Lattice pattern").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("75"))
                .pattern("Black Radiant Orange T-4")
                .gradientCss("linear-gradient(135deg,#000000 0%,#FF8C00 25%,#FF6347 50%,#FF4500 75%,#8B4513 100%)")
                .description("Black Radiant Orange T-4 - Warm glow").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("75"))
                .pattern("Black Radiant Red T-4")
                .gradientCss("linear-gradient(135deg,#000000 0%,#DC143C 25%,#FF1493 50%,#C71585 75%,#8B0000 100%)")
                .description("Black Radiant Red T-4 - Bold passion").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("80"))
                .pattern("Black Tiger T-4")
                .gradientCss("repeating-linear-gradient(45deg,#000000 0px,#000000 3px,#8B4513 3px,#8B4513 7px,#D2B48C 7px,#D2B48C 10px,#654321 10px,#654321 14px)")
                .description("Black Tiger T-4 - Wild stripes").borderPx(8).build(),

            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("70"))
                .pattern("N Wood Pin")
                .gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5DEB3 75%,#C19A6B 100%)")
                .description("N Wood Pin - Natural wood").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("65"))
                .pattern("White Gold")
                .gradientCss("linear-gradient(135deg,#F5F5DC 0%,#FFD700 30%,#DAA520 60%,#B8860B 100%)")
                .description("White Gold - Elegant combo").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("70"))
                .pattern("Sky Blue Gold")
                .gradientCss("linear-gradient(135deg,#87CEEB 0%,#4682B4 25%,#FFD700 50%,#DAA520 75%,#8B7355 100%)")
                .description("Sky Blue Gold - Ocean breeze").borderPx(8).build(),

            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("75"))
                .pattern("CZ (RST)")
                .gradientCss("linear-gradient(135deg,#8B4513 0%,#6B3410 25%,#D2B48C 50%,#8B4513 75%,#654321 100%)")
                .description("CZ (RST) - Rich chocolate").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("70"))
                .pattern("Rose White (RST)")
                .gradientCss("linear-gradient(135deg,#F5E6D3 0%,#FFB6C1 25%,#E6D4C7 50%,#D2B48C 75%,#A0826D 100%)")
                .description("Rose White (RST) - Soft pink").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("80"))
                .pattern("Gold White (RST)")
                .gradientCss("linear-gradient(135deg,#FFD700 0%,#FFA500 25%,#F5F5DC 50%,#DAA520 75%,#8B7355 100%)")
                .description("Gold White (RST) - Luxurious").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("65"))
                .pattern("Pine Wood (RST)")
                .gradientCss("linear-gradient(135deg,#D2B48C 0%,#C19A6B 25%,#F5DEB3 50%,#D2B48C 75%,#8B7355 100%)")
                .description("Pine Wood (RST) - Natural warm").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("68"))
                .pattern("I Brown Pine W (RST)")
                .gradientCss("linear-gradient(135deg,#3E2723 0%,#5D4037 25%,#D2B48C 50%,#C19A6B 75%,#1A0E0E 100%)")
                .description("I Brown Pine W (RST) - Dark wood").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("62"))
                .pattern("Black (RST)")
                .gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#2a2a2a 50%,#1a1a1a 75%,#000000 100%)")
                .description("Black (RST) - Classic bold").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("72"))
                .pattern("Coffee Ivory (RST)")
                .gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5F5DC 75%,#3E2723 100%)")
                .description("Coffee Ivory (RST) - Two-tone").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("77"))
                .pattern("Black Brown (RST)")
                .gradientCss("linear-gradient(135deg,#000000 0%,#3E2723 25%,#8B4513 50%,#A0522D 75%,#1A0E0E 100%)")
                .description("Black Brown (RST) - Deep contrast").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("68"))
                .pattern("Black Pine wood (RST)")
                .gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#D2B48C 50%,#F5DEB3 75%,#3E2723 100%)")
                .description("Black Pine wood (RST) - Mixed").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("75"))
                .pattern("Gold Print Maroon (RST)")
                .gradientCss("linear-gradient(135deg,#FFD700 0%,#8B1A1A 25%,#DC143C 50%,#8B4513 75%,#3E2723 100%)")
                .description("Gold Print Maroon (RST) - Royal").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("58"))
                .pattern("White (RST)")
                .gradientCss("linear-gradient(135deg,#FFFFFF 0%,#F5F5F5 25%,#E8E8E8 50%,#F0F0F0 75%,#D0D0D0 100%)")
                .description("White (RST) - Pure clean").borderPx(8).build(),

            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("80"))
                .pattern("White Rust")
                .gradientCss("linear-gradient(135deg,#F5F5F5 0%,#D3D3D3 25%,#A9A9A9 50%,#C0C0C0 75%,#808080 100%)")
                .description("White Rust - Vintage look").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("85"))
                .pattern("Black Rust")
                .gradientCss("repeating-linear-gradient(45deg,#1a1a1a 0px,#1a1a1a 3px,#3a3a3a 3px,#3a3a3a 6px,#2a2a2a 6px,#2a2a2a 10px)")
                .description("Black Rust - Industrial style").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("90"))
                .pattern("Radiant Orange")
                .gradientCss("linear-gradient(135deg,#FF8C00 0%,#FF6347 25%,#FF4500 50%,#DC143C 75%,#8B0000 100%)")
                .description("Radiant Orange - Vibrant warm").borderPx(8).build(),

            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("78"))
                .pattern("CZ (RST) Model 72")
                .gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#DAA520 50%,#F5DEB3 75%,#6B4423 100%)")
                .description("CZ (RST) - Warm wood tone").borderPx(8).build(),

            BeadingOption.builder()
                .widthLabel("1.2 inch").displayWidth("1.2\"").widthValue(1.2)
                .additionalPrice(new BigDecimal("85"))
                .pattern("CZ Model 6")
                .gradientCss("linear-gradient(135deg,#8B4513 0%,#6B3410 25%,#D2B48C 50%,#8B4513 75%,#654321 100%)")
                .description("CZ - Rich baroque").borderPx(9).build(),
            
            BeadingOption.builder()
                .widthLabel("1.2 inch").displayWidth("1.2\"").widthValue(1.2)
                .additionalPrice(new BigDecimal("90"))
                .pattern("Black Gold Model 6")
                .gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#FFD700 50%,#DAA520 75%,#2a2a2a 100%)")
                .description("Black Gold - Luxury accent").borderPx(9).build(),

            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("95"))
                .pattern("Natural Louvers Gold")
                .gradientCss("linear-gradient(135deg,#8B6F47 0%,#C19A6B 25%,#DAA520 50%,#F5DEB3 75%,#8B7355 100%)")
                .description("Natural Louvers Gold - Elegant").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("100"))
                .pattern("CZ Model 122")
                .gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#DAA520 50%,#F5DEB3 75%,#6B4423 100%)")
                .description("CZ (RST) - Warm luxury").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("98"))
                .pattern("Night Wood Gold")
                .gradientCss("linear-gradient(135deg,#2C1810 0%,#3E2723 25%,#8B6F47 50%,#C19A6B 75%,#8B4513 100%)")
                .description("Night Wood Gold - Dark rich").borderPx(10).build(),

            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("110"))
                .pattern("B Gold Louvers")
                .gradientCss("linear-gradient(135deg,#000000 0%,#8B4513 25%,#DAA520 50%,#F5DEB3 75%,#000000 100%)")
                .description("B Gold Louvers - Premium dark").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("105"))
                .pattern("B Pinewood")
                .gradientCss("linear-gradient(135deg,#000000 0%,#D2B48C 25%,#F5DEB3 50%,#D2B48C 75%,#000000 100%)")
                .description("B Pinewood - Light accent").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("108"))
                .pattern("B Louvers")
                .gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5DEB3 75%,#8B4513 100%)")
                .description("B Louvers - Classic wood").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("112"))
                .pattern("B Gold Pine Wood")
                .gradientCss("linear-gradient(135deg,#000000 0%,#FFD700 25%,#F5DEB3 50%,#F0F8FF 75%,#000000 100%)")
                .description("B Gold Pine Wood - Contrast").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("115"))
                .pattern("B Gold Pine Wood T1")
                .gradientCss("linear-gradient(135deg,#DAA520 0%,#1a1a1a 25%,#2a2a2a 50%,#1a1a1a 75%,#000000 100%)")
                .description("Gold Black T-1 - Bold modern").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("118"))
                .pattern("Black Gold T5")
                .gradientCss("repeating-linear-gradient(45deg,#000000 0px,#000000 3px,#FFD700 3px,#FFD700 6px,#1a1a1a 6px,#1a1a1a 10px)")
                .description("Black Gold T-5 - Striped luxury").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("120"))
                .pattern("CZ T1")
                .gradientCss("linear-gradient(135deg,#DAA520 0%,#8B4513 25%,#A0522D 50%,#D2B48C 75%,#654321 100%)")
                .description("CZ T-1 - Warm classic").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("122"))
                .pattern("B Gold Tiger T1")
                .gradientCss("repeating-linear-gradient(45deg,#1a1a1a 0px,#1a1a1a 3px,#FFD700 3px,#FFD700 6px,#8B4513 6px,#8B4513 10px)")
                .description("B Gold Tiger T-1 - Wild glam").borderPx(10).build(),

            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("100"))
                .pattern("Natural Louvers M33")
                .gradientCss("linear-gradient(135deg,#8B6F47 0%,#C19A6B 25%,#DAA520 50%,#F5DEB3 75%,#8B7355 100%)")
                .description("Natural Louvers - Organic warm").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("102"))
                .pattern("Pin wood Black")
                .gradientCss("linear-gradient(135deg,#F5DEB3 0%,#D2B48C 25%,#000000 50%,#1a1a1a 75%,#F5F5DC 100%)")
                .description("Pin wood Black - Light-dark").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("105"))
                .pattern("Black Gold M33")
                .gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#FFD700 50%,#DAA520 75%,#1a1a1a 100%)")
                .description("Black Gold - Luxury duo").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("108"))
                .pattern("Full Black M33")
                .gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#2a2a2a 50%,#1a1a1a 75%,#000000 100%)")
                .description("Full Black - Sleek modern").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("110"))
                .pattern("Royal Brown Print")
                .gradientCss("linear-gradient(135deg,#8B1A1A 0%,#A0522D 25%,#8B4513 50%,#D2B48C 75%,#3E2723 100%)")
                .description("Royal Brown Print - Deep rich").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("112"))
                .pattern("Maroon Gold")
                .gradientCss("linear-gradient(135deg,#8B1A1A 0%,#DC143C 25%,#DAA520 50%,#F5DEB3 75%,#3E2723 100%)")
                .description("Maroon Gold - Royal warmth").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("115"))
                .pattern("Maroon Gold T6")
                .gradientCss("repeating-linear-gradient(45deg,#DAA520 0px,#DAA520 3px,#8B1A1A 3px,#8B1A1A 7px,#D2B48C 7px,#D2B48C 10px,#3E2723 10px,#3E2723 14px)")
                .description("Maroon Gold T-6 - Textured royal").borderPx(10).build()
        );
        beadingRepo.saveAll(beadings);
        log.info("Beading/Moulding options seeded: {}", beadings.size());
    }

    private void seedCoverOptions() {
        coverRepo.deleteAll();
        List<CoverOption> covers = List.of(
            CoverOption.builder().coverType("MATTE").displayName("Matte Lamination").emoji("Matte").description("Soft anti-glare finish").additionalPrice(new BigDecimal("80")).build(),
            CoverOption.builder().coverType("GLOSS").displayName("Gloss Lamination").emoji("Gloss").description("Vivid shiny finish").additionalPrice(new BigDecimal("60")).build(),
            CoverOption.builder().coverType("GLASS").displayName("Real Glass").emoji("Glass").description("Traditional glass cover").additionalPrice(new BigDecimal("150")).build(),
            CoverOption.builder().coverType("ACRYLIC").displayName("Acrylic Sheet").emoji("Acrylic").description("Crystal-clear shatterproof").additionalPrice(new BigDecimal("200")).build(),
            CoverOption.builder().coverType("NONE").displayName("No Cover").emoji("None").description("Frame only, no cover").additionalPrice(new BigDecimal("0")).build()
        );
        coverRepo.saveAll(covers);
        log.info("Cover options seeded: {}", covers.size());
    }
}
