package com.example.mfu.controller;

import com.example.mfu.model.PurchaseMufRequest;
import com.example.mfu.model.WithdrawMufRequest;
import com.example.mfu.service.MutualFundPWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/muf")
public class MutualFundController {

    @Autowired
    private MutualFundPWService mutualFundPWService;

    @PostMapping("/withdraw_muf")
    public ResponseEntity<?> withdrawMutualFund(@RequestBody WithdrawMufRequest request) {
        try {
            mutualFundPWService.withdrawMutualFund(request);
            return ResponseEntity.ok().body("Successfully withdrew mutual funds from portfolio");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/purchase_muf")
    public ResponseEntity<?> purchaseMutualFund(@RequestBody PurchaseMufRequest request) {
        try {
            mutualFundPWService.purchaseMutualFund(request);
            return ResponseEntity.ok().body("Successfully purchased mutual funds and added to portfolio");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}