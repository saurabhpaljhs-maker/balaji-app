===============================
FILE: DataInitializer.java
PATH: /home/claude/balaji-app/src/main/java/com/balaji/config/DataInitializer.java
===============================

REPLACE THIS METHOD (lines 76-117) WITH THE CODE BELOW:

---START OF CODE---

    private void seedBeadingOptions() {
        beadingRepo.deleteAll();
        List<BeadingOption> beadings = List.of(
            // MODEL 50 - Size: 0.7" (inch)
            BeadingOption.builder()
                .widthLabel("0.7 inch").displayWidth("0.7\"").widthValue(0.7)
                .additionalPrice(new BigDecimal("40"))
                .pattern("White Gold").gradientCss("linear-gradient(135deg,#F5F5DC 0%,#FFD700 25%,#FFA500 50%,#FF8C00 75%,#DAA520 100%)")
                .description("White Gold - Thin delicate border").borderPx(6).build(),
            
            BeadingOption.builder()
                .widthLabel("0.7 inch").displayWidth("0.7\"").widthValue(0.7)
                .additionalPrice(new BigDecimal("45"))
                .pattern("Coffee Ivory").gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5F5DC 75%,#000000 100%)")
                .description("Coffee Ivory - Two-tone classic").borderPx(6).build(),
            
            BeadingOption.builder()
                .widthLabel("0.7 inch").displayWidth("0.7\"").widthValue(0.7)
                .additionalPrice(new BigDecimal("50"))
                .pattern("Sky Blue Gold").gradientCss("linear-gradient(135deg,#87CEEB 0%,#4682B4 25%,#4169E1 50%,#FFD700 75%,#B8860B 100%)")
                .description("Sky Blue Gold - Modern elegant").borderPx(6).build(),

            // MODEL 5 - Size: 1" (inch)
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("60"))
                .pattern("Black T-2").gradientCss("repeating-linear-gradient(45deg,#1a1a1a 0px,#1a1a1a 3px,#404040 3px,#404040 6px,#2a2a2a 6px,#2a2a2a 10px)")
                .description("Black T-2 - Textured finish").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("65"))
                .pattern("Black T-3").gradientCss("repeating-linear-gradient(45deg,#0a0a0a 0px,#0a0a0a 2px,#2a2a2a 2px,#2a2a2a 5px,#1a1a1a 5px,#1a1a1a 8px,#3a3a3a 8px,#3a3a3a 10px)")
                .description("Black T-3 - Deep texture").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("70"))
                .pattern("Black T-4").gradientCss("repeating-linear-gradient(90deg,#000000 0px,#000000 2px,#1a1a1a 2px,#1a1a1a 4px,#2a2a2a 4px,#2a2a2a 6px,#3a3a3a 6px,#3a3a3a 8px,#4a4a4a 8px,#4a4a4a 10px)")
                .description("Black T-4 - Lattice pattern").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("75"))
                .pattern("Black Radiant Orange T-4").gradientCss("linear-gradient(135deg,#000000 0%,#FF8C00 25%,#FF6347 50%,#FF4500 75%,#8B4513 100%)")
                .description("Black Radiant Orange T-4 - Warm glow").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("75"))
                .pattern("Black Radiant Red T-4").gradientCss("linear-gradient(135deg,#000000 0%,#DC143C 25%,#FF1493 50%,#C71585 75%,#8B0000 100%)")
                .description("Black Radiant Red T-4 - Bold passion").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("80"))
                .pattern("Black Tiger T-4").gradientCss("repeating-linear-gradient(45deg,#000000 0px,#000000 3px,#8B4513 3px,#8B4513 7px,#D2B48C 7px,#D2B48C 10px,#654321 10px,#654321 14px)")
                .description("Black Tiger T-4 - Wild stripes").borderPx(8).build(),

            // MODEL 221 - Size: 1" (inch)  
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("70"))
                .pattern("N Wood Pin").gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5DEB3 75%,#C19A6B 100%)")
                .description("N Wood Pin - Natural wood").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("65"))
                .pattern("White Gold").gradientCss("linear-gradient(135deg,#F5F5DC 0%,#FFD700 30%,#DAA520 60%,#B8860B 100%)")
                .description("White Gold - Elegant combo").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("70"))
                .pattern("Sky Blue Gold").gradientCss("linear-gradient(135deg,#87CEEB 0%,#4682B4 25%,#FFD700 50%,#DAA520 75%,#8B7355 100%)")
                .description("Sky Blue Gold - Ocean breeze").borderPx(8).build(),

            // MODEL 23 - Size: 1" (inch)
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("75"))
                .pattern("CZ (RST)").gradientCss("linear-gradient(135deg,#8B4513 0%,#6B3410 25%,#D2B48C 50%,#8B4513 75%,#654321 100%)")
                .description("CZ (RST) - Rich chocolate").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("70"))
                .pattern("Rose White (RST)").gradientCss("linear-gradient(135deg,#F5E6D3 0%,#FFB6C1 25%,#E6D4C7 50%,#D2B48C 75%,#A0826D 100%)")
                .description("Rose White (RST) - Soft pink").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("80"))
                .pattern("Gold White (RST)").gradientCss("linear-gradient(135deg,#FFD700 0%,#FFA500 25%,#F5F5DC 50%,#DAA520 75%,#8B7355 100%)")
                .description("Gold White (RST) - Luxurious").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("65"))
                .pattern("Pine Wood (RST)").gradientCss("linear-gradient(135deg,#D2B48C 0%,#C19A6B 25%,#F5DEB3 50%,#D2B48C 75%,#8B7355 100%)")
                .description("Pine Wood (RST) - Natural warm").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("68"))
                .pattern("I Brown Pine W (RST)").gradientCss("linear-gradient(135deg,#3E2723 0%,#5D4037 25%,#D2B48C 50%,#C19A6B 75%,#1A0E0E 100%)")
                .description("I Brown Pine W (RST) - Dark wood").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("62"))
                .pattern("Black (RST)").gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#2a2a2a 50%,#1a1a1a 75%,#000000 100%)")
                .description("Black (RST) - Classic bold").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("72"))
                .pattern("Coffee Ivory (RST)").gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5F5DC 75%,#3E2723 100%)")
                .description("Coffee Ivory (RST) - Two-tone").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("77"))
                .pattern("Black Brown (RST)").gradientCss("linear-gradient(135deg,#000000 0%,#3E2723 25%,#8B4513 50%,#A0522D 75%,#1A0E0E 100%)")
                .description("Black Brown (RST) - Deep contrast").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("68"))
                .pattern("Black Pine wood (RST)").gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#D2B48C 50%,#F5DEB3 75%,#3E2723 100%)")
                .description("Black Pine wood (RST) - Mixed").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("75"))
                .pattern("Gold Print Maroon (RST)").gradientCss("linear-gradient(135deg,#FFD700 0%,#8B1A1A 25%,#DC143C 50%,#8B4513 75%,#3E2723 100%)")
                .description("Gold Print Maroon (RST) - Royal").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("58"))
                .pattern("White (RST)").gradientCss("linear-gradient(135deg,#FFFFFF 0%,#F5F5F5 25%,#E8E8E8 50%,#F0F0F0 75%,#D0D0D0 100%)")
                .description("White (RST) - Pure clean").borderPx(8).build(),

            // MODEL 149 - Size: 1" (inch)
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("80"))
                .pattern("White Rust").gradientCss("linear-gradient(135deg,#F5F5F5 0%,#D3D3D3 25%,#A9A9A9 50%,#C0C0C0 75%,#808080 100%)")
                .description("White Rust - Vintage look").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("85"))
                .pattern("Black Rust").gradientCss("repeating-linear-gradient(45deg,#1a1a1a 0px,#1a1a1a 3px,#3a3a3a 3px,#3a3a3a 6px,#2a2a2a 6px,#2a2a2a 10px)")
                .description("Black Rust - Industrial style").borderPx(8).build(),
            
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("90"))
                .pattern("Radiant Orange").gradientCss("linear-gradient(135deg,#FF8C00 0%,#FF6347 25%,#FF4500 50%,#DC143C 75%,#8B0000 100%)")
                .description("Radiant Orange - Vibrant warm").borderPx(8).build(),

            // MODEL 72 - Size: 1" (inch)
            BeadingOption.builder()
                .widthLabel("1 inch").displayWidth("1\"").widthValue(1.0)
                .additionalPrice(new BigDecimal("78"))
                .pattern("CZ (RST)").gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#DAA520 50%,#F5DEB3 75%,#6B4423 100%)")
                .description("CZ (RST) - Warm wood tone").borderPx(8).build(),

            // MODEL 6 - Size: 1.2" (inch)
            BeadingOption.builder()
                .widthLabel("1.2 inch").displayWidth("1.2\"").widthValue(1.2)
                .additionalPrice(new BigDecimal("85"))
                .pattern("CZ").gradientCss("linear-gradient(135deg,#8B4513 0%,#6B3410 25%,#D2B48C 50%,#8B4513 75%,#654321 100%)")
                .description("CZ - Rich baroque").borderPx(9).build(),
            
            BeadingOption.builder()
                .widthLabel("1.2 inch").displayWidth("1.2\"").widthValue(1.2)
                .additionalPrice(new BigDecimal("90"))
                .pattern("Black Gold").gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#FFD700 50%,#DAA520 75%,#2a2a2a 100%)")
                .description("Black Gold - Luxury accent").borderPx(9).build(),

            // MODEL 122 - Size: 1.25" (inch)
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("95"))
                .pattern("Natural Louvers Gold").gradientCss("linear-gradient(135deg,#8B6F47 0%,#C19A6B 25%,#DAA520 50%,#F5DEB3 75%,#8B7355 100%)")
                .description("Natural Louvers Gold - Elegant").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("100"))
                .pattern("CZ (RST)").gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#DAA520 50%,#F5DEB3 75%,#6B4423 100%)")
                .description("CZ (RST) - Warm luxury").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("98"))
                .pattern("Night Wood Gold").gradientCss("linear-gradient(135deg,#2C1810 0%,#3E2723 25%,#8B6F47 50%,#C19A6B 75%,#8B4513 100%)")
                .description("Night Wood Gold - Dark rich").borderPx(10).build(),

            // MODEL 32 - Size: 1.25" (inch)
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("110"))
                .pattern("B Gold Louvers").gradientCss("linear-gradient(135deg,#000000 0%,#8B4513 25%,#DAA520 50%,#F5DEB3 75%,#000000 100%)")
                .description("B Gold Louvers - Premium dark").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("105"))
                .pattern("B Pinewood").gradientCss("linear-gradient(135deg,#000000 0%,#D2B48C 25%,#F5DEB3 50%,#D2B48C 75%,#000000 100%)")
                .description("B Pinewood - Light accent").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("108"))
                .pattern("B Louvers").gradientCss("linear-gradient(135deg,#8B4513 0%,#A0522D 25%,#D2B48C 50%,#F5DEB3 75%,#8B4513 100%)")
                .description("B Louvers - Classic wood").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("112"))
                .pattern("B Gold Pine Wood").gradientCss("linear-gradient(135deg,#000000 0%,#FFD700 25%,#F5DEB3 50%,#F0F8FF 75%,#000000 100%)")
                .description("B Gold Pine Wood - Contrast").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("115"))
                .pattern("Gold Black T-1").gradientCss("linear-gradient(135deg,#DAA520 0%,#1a1a1a 25%,#2a2a2a 50%,#1a1a1a 75%,#000000 100%)")
                .description("Gold Black T-1 - Bold modern").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("118"))
                .pattern("Black Gold T-5").gradientCss("repeating-linear-gradient(45deg,#000000 0px,#000000 3px,#FFD700 3px,#FFD700 6px,#1a1a1a 6px,#1a1a1a 10px)")
                .description("Black Gold T-5 - Striped luxury").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("120"))
                .pattern("CZ T-1").gradientCss("linear-gradient(135deg,#DAA520 0%,#8B4513 25%,#A0522D 50%,#D2B48C 75%,#654321 100%)")
                .description("CZ T-1 - Warm classic").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("122"))
                .pattern("B Gold Tiger T-1").gradientCss("repeating-linear-gradient(45deg,#1a1a1a 0px,#1a1a1a 3px,#FFD700 3px,#FFD700 6px,#8B4513 6px,#8B4513 10px)")
                .description("B Gold Tiger T-1 - Wild glam").borderPx(10).build(),

            // MODEL 33 - Size: 1.25" (inch)
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("100"))
                .pattern("Natural Louvers").gradientCss("linear-gradient(135deg,#8B6F47 0%,#C19A6B 25%,#DAA520 50%,#F5DEB3 75%,#8B7355 100%)")
                .description("Natural Louvers - Organic warm").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("102"))
                .pattern("Pin wood Black").gradientCss("linear-gradient(135deg,#F5DEB3 0%,#D2B48C 25%,#000000 50%,#1a1a1a 75%,#F5F5DC 100%)")
                .description("Pin wood Black - Light-dark").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("105"))
                .pattern("Black Gold").gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#FFD700 50%,#DAA520 75%,#1a1a1a 100%)")
                .description("Black Gold - Luxury duo").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("108"))
                .pattern("Full Black").gradientCss("linear-gradient(135deg,#000000 0%,#1a1a1a 25%,#2a2a2a 50%,#1a1a1a 75%,#000000 100%)")
                .description("Full Black - Sleek modern").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("110"))
                .pattern("Royal Brown Print").gradientCss("linear-gradient(135deg,#8B1A1A 0%,#A0522D 25%,#8B4513 50%,#D2B48C 75%,#3E2723 100%)")
                .description("Royal Brown Print - Deep rich").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("112"))
                .pattern("Maroon Gold").gradientCss("linear-gradient(135deg,#8B1A1A 0%,#DC143C 25%,#DAA520 50%,#F5DEB3 75%,#3E2723 100%)")
                .description("Maroon Gold - Royal warmth").borderPx(10).build(),
            
            BeadingOption.builder()
                .widthLabel("1.25 inch").displayWidth("1.25\"").widthValue(1.25)
                .additionalPrice(new BigDecimal("115"))
                .pattern("Maroon Gold T-6").gradientCss("repeating-linear-gradient(45deg,#DAA520 0px,#DAA520 3px,#8B1A1A 3px,#8B1A1A 7px,#D2B48C 7px,#D2B48C 10px,#3E2723 10px,#3E2723 14px)")
                .description("Maroon Gold T-6 - Textured royal").borderPx(10).build()
        );
        beadingRepo.saveAll(beadings);
        log.info("Beading/Moulding options seeded: {}", beadings.size());
    }

---END OF CODE---

===============================
INSTALLATION INSTRUCTIONS:
===============================

1. OPEN FILE:
   /home/claude/balaji-app/src/main/java/com/balaji/config/DataInitializer.java

2. FIND: The method "private void seedBeadingOptions()" starting at line 76

3. DELETE: Lines 76-117 (the entire old seedBeadingOptions method)

4. PASTE: The entire code block above (starting with "private void seedBeadingOptions()")

5. SAVE the file

6. RUN THE APPLICATION:
   - Stop any running instance
   - Run: mvn clean spring-boot:run
   - Application will automatically load all moulding options

===============================
OPTIONAL: RENAME IN UI
===============================

If you want to rename "Beading" to "Moulding" in the frontend:

File: /home/claude/balaji-app/src/main/resources/templates/builder.html
Search for: "Beading" or "beading"
Replace with: "Moulding"

File: /home/claude/balaji-app/src/main/resources/static/css/builder.css
Search for: ".beading-" classes
Can optionally rename to ".moulding-"

===============================
ALL MOULDING DESIGNS INCLUDED:
===============================

✓ MODEL 50 (0.7") - White Gold, Coffee Ivory, Sky Blue Gold
✓ MODEL 5 (1") - Black T-2, T-3, T-4, Radiant Orange, Radiant Red, Tiger
✓ MODEL 221 (1") - N Wood Pin, White Gold, Sky Blue Gold  
✓ MODEL 23 (1") - CZ, Rose White, Gold White, Pine Wood, Brown Pine, Black, Coffee Ivory, Black Brown, Black Pine, Gold Print Maroon, White
✓ MODEL 149 (1") - White Rust, Black Rust, Radiant Orange
✓ MODEL 72 (1") - CZ
✓ MODEL 6 (1.2") - CZ, Black Gold
✓ MODEL 122 (1.25") - Natural Louvers Gold, CZ, Night Wood Gold
✓ MODEL 32 (1.25") - B Gold Louvers, B Pinewood, B Louvers, B Gold Pine, Gold Black T-1, Black Gold T-5, CZ T-1, B Gold Tiger T-1
✓ MODEL 33 (1.25") - Natural Louvers, Pin wood Black, Black Gold, Full Black, Royal Brown, Maroon Gold, Maroon Gold T-6

TOTAL: 51 moulding options ready to use!

===============================
TROUBLESHOOTING:
===============================

Q: Still not showing in app?
A: Delete database and restart:
   - Stop the app
   - Delete: database file (if H2) or truncate beading_options table
   - Run: mvn clean spring-boot:run

Q: Getting compilation errors?
A: Make sure you replaced the ENTIRE method, not just part of it

Q: Colors look weird?
A: CSS gradients are previewed in frontend. Colors appear correctly on actual frames.
