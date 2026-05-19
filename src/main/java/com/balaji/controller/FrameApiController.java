package com.balaji.controller;

import com.balaji.model.BeadingOption;
import com.balaji.model.CoverOption;
import com.balaji.model.FrameSize;
import com.balaji.repository.BeadingOptionRepository;
import com.balaji.repository.CoverOptionRepository;
import com.balaji.repository.FrameSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FrameApiController {

    private final FrameSizeRepository frameSizeRepo;
    private final BeadingOptionRepository beadingRepo;
    private final CoverOptionRepository coverRepo;

    // ─────────────────────────────────────────────
    // Frame Sizes
    // ─────────────────────────────────────────────
    @GetMapping("/sizes")
    public List<FrameSize> getSizes() {
        return frameSizeRepo.findAll();
    }

    // ─────────────────────────────────────────────
    // Beading Options
    // ─────────────────────────────────────────────
    @GetMapping("/beadings")
    public List<BeadingOption> getBeadings() {
        return beadingRepo.findAll();
    }

    // ─────────────────────────────────────────────
    // Moulding Options
    // Frontend may call /mouldings
    // ─────────────────────────────────────────────
    @GetMapping("/mouldings")
    public List<BeadingOption> getMouldings() {
        return beadingRepo.findAll();
    }

    // ─────────────────────────────────────────────
    // Cover Options
    // ─────────────────────────────────────────────
    @GetMapping("/covers")
    public List<CoverOption> getCovers() {
        return coverRepo.findAll();
    }
}
