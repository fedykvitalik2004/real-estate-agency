package org.vitalii.fedyk.realestateagency.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vitalii.fedyk.realestateagency.dto.LikePropertyDto;
import org.vitalii.fedyk.realestateagency.service.ConsumerService;

@RestController
@RequestMapping("/api/consumers")
@AllArgsConstructor
public class ConsumerController {
    private ConsumerService consumerService;

    @PostMapping("/toggleLikeProperty")
    public boolean toggleLikeProperty(@RequestBody @Valid LikePropertyDto likePropertyDto) {
        return consumerService.toggleLikeProperty(likePropertyDto.getId(), likePropertyDto.getPropertyId());
    }
}
