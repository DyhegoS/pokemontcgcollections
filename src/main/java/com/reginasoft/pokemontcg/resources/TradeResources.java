package com.reginasoft.pokemontcg.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reginasoft.pokemontcg.dto.TradeRequestDTO;
import com.reginasoft.pokemontcg.dto.TradeResponseDTO;
import com.reginasoft.pokemontcg.entities.Trade;
import com.reginasoft.pokemontcg.service.TradeService;

@RestController
@RequestMapping(value = "/trades")
public class TradeResources {
	
	@Autowired
	private TradeService service;
	
	@GetMapping
	public ResponseEntity<List<Trade>> findAll(){
		List<Trade> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Trade> findById(@PathVariable Long id){
		Trade obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("/propose")
    public ResponseEntity<TradeResponseDTO> proposeTrade(
            @RequestParam Long userProposeId,
            @RequestBody TradeRequestDTO dto) {
        TradeResponseDTO response = service.createTrade(userProposeId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{tradeId}/accept")
    public ResponseEntity<TradeResponseDTO> acceptTrade(
            @PathVariable Long tradeId,
            @RequestParam Long userReceiverId) {
        TradeResponseDTO response = service.acceptTrade(tradeId, userReceiverId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<TradeResponseDTO> getTrade(@PathVariable Long tradeId) {
        TradeResponseDTO response = service.getTrade(tradeId);
        return ResponseEntity.ok(response);
    }
}
