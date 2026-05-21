package com.balaji.service;

import com.balaji.model.FrameSize;
import com.balaji.model.MouldingOption;
import com.balaji.repository.FrameSizeRepository;
import com.balaji.repository.MouldingOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MouldingService {
    
    private final MouldingOptionRepository mouldingRepository;
    private final FrameSizeRepository frameSizeRepository;
    
    // ── GET ALL MOULDINGS ──────────────────────────────────────
    public List<MouldingOption> getAllMouldings() {
        return mouldingRepository.findAllByActiveTrue();
    }
    
    public List<MouldingOption> getMouldingsBySize(String size) {
        return mouldingRepository.findBySize(size);
    }
    
    public Optional<MouldingOption> getMouldingByModel(String modelNo) {
        return mouldingRepository.findByModelNo(modelNo);
    }
    
    public List<String> getAllUniqueSizes() {
        return mouldingRepository.findAllUniqueSizes();
    }
    
    public List<MouldingOption> searchMouldings(String search) {
        if (search == null || search.trim().isEmpty()) {
            return getAllMouldings();
        }
        return mouldingRepository.searchByColorOrModel(search.trim());
    }
    
    public List<MouldingOption> getMouldingsBySupplier(String supplier) {
        return mouldingRepository.findAllBySupplier(supplier);
    }
    
    // ── QUOTE CALCULATOR ───────────────────────────────────────
    public Map<String, Object> calculateQuote(String frameSize, String mouldingModel, Integer quantity) {
        Map<String, Object> response = new HashMap<>();
        
        if (quantity == null || quantity < 1) quantity = 1;
        
        Optional<FrameSize> frameSizeOpt = frameSizeRepository.findBySize(frameSize);
        if (frameSizeOpt.isEmpty()) {
            response.put("success", false);
            response.put("error", "Invalid frame size: " + frameSize);
            return response;
        }
        
        Optional<MouldingOption> mouldingOpt = mouldingRepository.findByModelNo(mouldingModel);
        if (mouldingOpt.isEmpty()) {
            response.put("success", false);
            response.put("error", "Invalid moulding model: " + mouldingModel);
            return response;
        }
        
        FrameSize frameObj = frameSizeOpt.get();
        MouldingOption mouldingObj = mouldingOpt.get();
        
        Double basePrice = frameObj.getPrice();
        Double mouldingMultiplier = mouldingObj.getPriceMultiplier();
        Double unitPrice = basePrice * mouldingMultiplier;
        Long totalPrice = Math.round(unitPrice * quantity);
        
        response.put("success", true);
        response.put("frameSize", frameSize);
        response.put("frameLabel", frameObj.getLabel());
        response.put("mouldingModel", mouldingModel);
        response.put("mouldingSize", mouldingObj.getSize());
        response.put("quantity", quantity);
        response.put("basePrice", Math.round(basePrice));
        response.put("mouldingPremium", Math.round(basePrice * (mouldingMultiplier - 1)));
        response.put("unitPrice", Math.round(unitPrice));
        response.put("totalPrice", totalPrice);
        
        Map<String, Object> breakdown = new HashMap<>();
        breakdown.put("baseFramePrice", Math.round(basePrice));
        breakdown.put("mouldingPremium", Math.round(basePrice * (mouldingMultiplier - 1)));
        breakdown.put("finalUnitPrice", Math.round(unitPrice));
        breakdown.put("quantity", quantity);
        breakdown.put("totalPrice", totalPrice);
        response.put("breakdown", breakdown);
        
        return response;
    }
    
    public Map<String, Object> getMouldingStats() {
        List<MouldingOption> all = getAllMouldings();
        Set<String> uniqueSizes = new HashSet<>(getAllUniqueSizes());
        
        int totalColors = all.stream()
            .mapToInt(m -> m.getColorCount())
            .sum();
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalModels", all.size());
        stats.put("uniqueSizes", uniqueSizes.size());
        stats.put("totalColors", totalColors);
        stats.put("supplier", "Accurate Industries");
        
        return stats;
    }
}
