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
        if (adminRepo.count() > 0) return;
        AdminUser admin = AdminUser.builder()
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .role("ROLE_ADMIN")
                .build();
        adminRepo.save(admin);
        log.info("Admin created: {}", adminUsername);
    }

    private void seedFrameSizes() {
        if (frameSizeRepo.count() > 0) return;
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
        if (beadingRepo.count() > 0) return;
        List<BeadingOption> beadings = List.of(

            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("60"))
                .pattern("ornate-gold")
                .gradientCss("url('/images/beading-1-ornate-gold.jpg')")
                .description("Ornate Gold Floral")
                .borderPx(14).build(),

            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("90"))
                .pattern("multi-style")
                .gradientCss("url('/images/beading-2-multi-style.jpg')")
                .description("Multi Style Collection")
                .borderPx(18).build(),

            BeadingOption.builder()
                .widthLabel("1.5 inch").displayWidth("1.5\"").widthValue(1.5)
                .additionalPrice(new BigDecimal("120"))
                .pattern("brown-floral")
                .gradientCss("url('/images/beading-3-brown-floral.jpg')")
                .description("Brown Floral Carved")
                .borderPx(22).build(),

            BeadingOption.builder()
                .widthLabel("1.75 inch").displayWidth("1.75\"").widthValue(1.75)
                .additionalPrice(new BigDecimal("150"))
                .pattern("gold-ornate")
                .gradientCss("url('/images/beading-4-gold-ornate.jpg')")
                .description("Gold Ornate Premium")
                .borderPx(26).build(),

            BeadingOption.builder()
                .widthLabel("2 inch").displayWidth("2\"").widthValue(2.0)
                .additionalPrice(new BigDecimal("180"))
                .pattern("black-oval")
                .gradientCss("url('/images/beading-5-black-oval.jpg')")
                .description("Black Oval Classic")
                .borderPx(30).build()
        );
        beadingRepo.saveAll(beadings);
        log.info("Beading options seeded: {}", beadings.size());
    }

    private void seedCoverOptions() {
        if (coverRepo.count() > 0) return;
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
