package com.example.stockprice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin(origins = "*")
public class StockController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
    private double stockPrice = 100.0;

    @GetMapping("/api/stock-price")
    public double getStockPrice() {
        return stockPrice;
    }

    @PostMapping("/api/update-price")
    public ResponseEntity<String> updatePrice(@RequestParam double newPrice) {
        stockPrice = newPrice;
        List<SseEmitter> deadEmitters = new ArrayList<>();
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().data(stockPrice));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        });
        emitters.removeAll(deadEmitters);
        return ResponseEntity.ok("Prix mis à jour");
    }

    @GetMapping("/api/price-stream")
    public SseEmitter streamStockPrice() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        try {
            emitter.send(SseEmitter.event().data(stockPrice));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }
}