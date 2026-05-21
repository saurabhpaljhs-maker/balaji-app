package com.balaji.config;

import com.balaji.model.FrameSize;
import com.balaji.model.MouldingOption;
import com.balaji.repository.FrameSizeRepository;
import com.balaji.repository.MouldingOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * DataInitializer - Load Accurate Industries moulding data on startup
 * 19 models from PDF catalogue
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final MouldingOptionRepository mouldingRepository;
    private final FrameSizeRepository frameSizeRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (mouldingRepository.count() > 0 && frameSizeRepository.count() > 0) {
            log.info("✓ Database already populated with moulding data");
            return;
        }
        
        log.info("🔄 Loading BALAJI Photo Frames initial data...");
        
        if (frameSizeRepository.count() == 0) {
            loadFrameSizes();
        }
        
        if (mouldingRepository.count() == 0) {
            loadMouldings();
        }
        
        log.info("✓ Data initialization complete!");
        log.info("  - Mouldings: " + mouldingRepository.count());
        log.info("  - Frame Sizes: " + frameSizeRepository.count());
    }
    
    private void loadFrameSizes() {
        List<FrameSize> frameSizes = Arrays.asList(
            new FrameSize(null, "4x6", "Wallet / ID Photo", 80.0),
            new FrameSize(null, "5x7", "Passport / Portrait", 100.0),
            new FrameSize(null, "6x8", "Table Display", 130.0),
            new FrameSize(null, "8x10", "Photo Portrait", 180.0),
            new FrameSize(null, "10x12", "Family Photo", 240.0),
            new FrameSize(null, "10x14", "Event Photo", 280.0),
            new FrameSize(null, "10x15", "Panorama", 320.0),
            new FrameSize(null, "12x18", "Large Portrait", 420.0),
            new FrameSize(null, "16x20", "Gallery Print", 580.0),
            new FrameSize(null, "20x24", "Premium Wall Art", 780.0)
        );
        frameSizeRepository.saveAll(frameSizes);
        log.info("✓ Loaded " + frameSizes.size() + " frame sizes");
    }
    
    private void loadMouldings() {
        List<MouldingOption> mouldings = Arrays.asList(
            MouldingOption.builder()
                .modelNo("50").size("0.7\"").widthMm(15).heightMm(18)
                .availableColors("White Gold,Coffee Ivory,Sky Blue Gold,N Wood Pin,White Black")
                .priceMultiplier(1.1).supplier("Accurate Industries")
                .description("Classic 0.7 inch moulding with 5 colour options")
                .build(),
            
            MouldingOption.builder()
                .modelNo("5").size("1\"").widthMm(24).heightMm(13)
                .availableColors("Black T-2,Black T-3,CZ (RST),Black T-4,Black (RST),Black Gold (RST),Black Radiant Orange T-4,Black Radiant Red T-4,Black Tiger T-4")
                .priceMultiplier(1.2).supplier("Accurate Industries")
                .description("Premium 1 inch moulding with 9 colour variations")
                .build(),
            
            MouldingOption.builder()
                .modelNo("221").size("1\"").widthMm(21).heightMm(20)
                .availableColors("N Wood Pin,White Gold,Sky Blue Gold,Black (RST),White (RST),Black Silver (RST),Black Gold (RST)")
                .priceMultiplier(1.25).supplier("Accurate Industries")
                .description("1 inch classic with 7 sophisticated colours")
                .build(),
            
            MouldingOption.builder()
                .modelNo("23").size("1\"").widthMm(24).heightMm(19)
                .availableColors("CZ (RST),Rose White (RST),Gold White (RST),Pine Wood (RST),I Brown Pine W (RST),Black (RST),Coffee Ivory (RST),Black Brown (RST),Black Pine wood (RST),Gold Print Maroon (RST),White (RST)")
                .priceMultiplier(1.3).supplier("Accurate Industries")
                .description("Elegant 1 inch with 11 luxury colour finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("149").size("1\"").widthMm(26).heightMm(15)
                .availableColors("White Rust,Black Rust,Radiant Orange")
                .priceMultiplier(1.15).supplier("Accurate Industries")
                .description("1 inch textured with rust finish options")
                .build(),
            
            MouldingOption.builder()
                .modelNo("72").size("1\"").widthMm(27).heightMm(16)
                .availableColors("CZ (RST)")
                .priceMultiplier(1.2).supplier("Accurate Industries")
                .description("1 inch premium with classic finish")
                .build(),
            
            MouldingOption.builder()
                .modelNo("6").size("1.2\"").widthMm(30).heightMm(12)
                .availableColors("CZ,Black Gold")
                .priceMultiplier(1.35).supplier("Accurate Industries")
                .description("1.2 inch width with 2 premium finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("122").size("1.25\"").widthMm(26).heightMm(21)
                .availableColors("Natural Louvers Gold,CZ (RST),Night Wood Gold,White Rose (RST),White Gold (RST),White Black (RST),Black Gold (RST),White Marron (RST),White Pine Wood (RST),Pine Wood Brown,Black (RST)")
                .priceMultiplier(1.4).supplier("Accurate Industries")
                .description("1.25 inch deluxe with 11 premium colours")
                .build(),
            
            MouldingOption.builder()
                .modelNo("32").size("1.25\"").widthMm(32).heightMm(27)
                .availableColors("B Gold Louvers,B Pinewood,B Louvers,B Gold Pine Wood,Gold Black T-1,Black Gold Tiger T-5,CZ T-1,B Gold Tiger T-1")
                .priceMultiplier(1.5).supplier("Accurate Industries")
                .description("1.25 inch luxury with 8 louvered finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("33").size("1.25\"").widthMm(33).heightMm(22)
                .availableColors("Natural Louvers,Pin wood Black,Black Gold,Full Black,Royal Brown Print,Maroon Gold,Maroon Gold T-6")
                .priceMultiplier(1.45).supplier("Accurate Industries")
                .description("1.25 inch elegant with 7 louvered options")
                .build(),
            
            MouldingOption.builder()
                .modelNo("51").size("1.35\"").widthMm(35).heightMm(12)
                .availableColors("Radiant Red T-5,Radiant Orange T-5,Copper Tiger T-5,Gold Black T-5,CZ T-5,Black T-5")
                .priceMultiplier(1.5).supplier("Accurate Industries")
                .description("1.35 inch radiant with 6 vibrant finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("123").size("1.5\"").widthMm(40).heightMm(16)
                .availableColors("Rose White (RST),Gold White (RST),Black Pin wood (RST),Black Radiant Orange P,Black Plain,Black Radiant Red P,Black Brown,CZ Plain,Coffee Ivory,Black (RST)")
                .priceMultiplier(1.6).supplier("Accurate Industries")
                .description("1.5 inch premium with 10 exclusive finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("321").size("1.5\"").widthMm(40).heightMm(22)
                .availableColors("Black G,Natural Louvers B,Pine wood B")
                .priceMultiplier(1.65).supplier("Accurate Industries")
                .description("1.5 inch classic with 3 natural finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("15").size("1.75\"").widthMm(45).heightMm(13)
                .availableColors("Black Pine wood T-5,Black Brown T-5,Black T-5,CZ-T5")
                .priceMultiplier(1.75).supplier("Accurate Industries")
                .description("1.75 inch wide with 4 textured finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("168").size("2\"").widthMm(45).heightMm(26)
                .availableColors("Black (RST),Black Gold (RST),Black Louvers -B,Maroon G (RST),CZ GOLD,Maroon Gold - M,Rose White (RST),CZ (RST),Louvers -B")
                .priceMultiplier(1.85).supplier("Accurate Industries")
                .description("2 inch grand with 9 luxurious finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("105").size("2\"").widthMm(52).heightMm(24)
                .availableColors("Maroon G (RST),Black (RST),CZ (RST),Pine Wood Black (RST),Louvers Black (RST),Pine Wood B (RST)")
                .priceMultiplier(1.9).supplier("Accurate Industries")
                .description("2 inch extra wide with 6 premium finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("159").size("2\"").widthMm(50).heightMm(14)
                .availableColors("Maroon T-6,Black T-6,Black T-7,Black T-8,Black T-9,Black T-10,Black T-11,Black T-12")
                .priceMultiplier(1.8).supplier("Accurate Industries")
                .description("2 inch textured with 8 varied finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("312").size("2\"").widthMm(51).heightMm(32)
                .availableColors("CZ (RST),Black Dark Wood (RST),Black Gold (RST)")
                .priceMultiplier(2.0).supplier("Accurate Industries")
                .description("2 inch ultra premium with 3 exclusive finishes")
                .build(),
            
            MouldingOption.builder()
                .modelNo("212").size("3\"").widthMm(75).heightMm(29)
                .availableColors("Night wood-B,CZ-RST,Royal Maroon sign,Black (RST)")
                .priceMultiplier(2.2).supplier("Accurate Industries")
                .description("3 inch exhibition grade with 4 royal finishes")
                .build()
        );
        
        mouldingRepository.saveAll(mouldings);
        log.info("✓ Loaded " + mouldings.size() + " moulding options from Accurate Industries");
    }
}
