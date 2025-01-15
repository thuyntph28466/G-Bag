package fpoly.datn.ecommerce_website.restController.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fpoly.datn.ecommerce_website.dto.TransportationFeeDTO;
import fpoly.datn.ecommerce_website.service.serviceImpl.GiaoHangTietKiemServiceImpl;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/public")
public class GiaoHangTietKiemController {
    private static final String API_BASE_URL = "https://online-gateway.ghn.vn/shiip/public-api/master-data/";

    @Autowired
    private GiaoHangTietKiemServiceImpl giaoHangTietKiemService;

    @GetMapping("/shipfee")
    public ResponseEntity tinhFee(@RequestParam() String tinh, @RequestParam() String huyen, @RequestParam() String xa,@RequestParam() Integer quantity,@RequestParam() Long price) {
        try {
            System.out.println(quantity);
            System.out.println(price);
            return giaoHangTietKiemService.tinhShip(tinh,huyen,xa,quantity,price);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/wards")
    @ResponseBody
    public String getWardsByDistrict(@RequestParam("district_id") Integer districtId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("token", "bc002d03-9cf5-11ee-96dc-de6f804954c9");
            headers.set("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(API_BASE_URL + "ward?district_id=" + districtId, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return null;
            }
        } catch (HttpClientErrorException e) {
            return "";
        }
    }


    @PostMapping("/transportationFee")
    @ResponseBody
    public String getFee(@RequestBody TransportationFeeDTO transportationFeeDTO) {
        return giaoHangTietKiemService.getFee(transportationFeeDTO);
    }
}