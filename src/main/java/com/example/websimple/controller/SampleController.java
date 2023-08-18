package com.example.websimple.controller;

import com.example.websimple.dto.ErrorResponse;
import com.example.websimple.exception.ErrorCode;
import com.example.websimple.exception.WebSampleException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController
public class SampleController {
//    @RequestMapping(value ="/order/1", method = RequestMethod.GET)
//    public String getOrder() {
//        log.info("Get Some order");
//        return "orderId:1, orderAmount:1000";
//    }

//    @GetMapping("/order/1")
//    public String getOrder() {
//        log.info("Get Some order");
//        return "orderId:1, orderAmount:1000";
//    }

    // Get : 매개 변수 받아오기
    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException, SQLIntegrityConstraintViolationException {
        log.info("Get Some order: " + id);

        if ("500".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_BIG_ID_ERROR,
                    "500 is not too big orderId.");
        }

        if ("3".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_SMALL_ID_ERROR,
                    "3 is not too small orderId.");
        }

        if ("4".equals(id)) {
            throw new SQLIntegrityConstraintViolationException(
                    "Duplicated insertion was tried.");
        }

        return "orderId: " + id + ", orderAmount:1000";
    }

    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String id) {
        log.info("Delete Some order: " + id);
        return "orderId: " + id;
    }

    // Get : 파라미터로 받기 RequestParam에는 required = true가 default이고 defaultValue = "-" 로 지정할수도 있다.
    @GetMapping("/order")
    public String getOrderWithRequestParam(@RequestParam("orderId") String id, @RequestParam("orderAmount") String amount) {
        log.info("Get order: " + id + "amount :" + amount);
        return "orderId: " + id + ", orderAmount: " + amount;
    }
//    @PostMapping("/order")
//    public String createOrderByPOST() {
//        log.info("Create order");
//        return "order created -> orderId:1, orderAmount:1000";
//    }

    @PostMapping("/order")
    public String createOrderWithRequestBodyHeader(@RequestBody CreateOrderRequest createOrderRequest, @RequestHeader String userAccountId) {
        log.info("Create order : " + createOrderRequest + ", userAccountId : " + userAccountId);
        return "orderId:" + createOrderRequest.getOrderId() + ", " + "orderAmount:" + createOrderRequest.getOrderAmount();
    }

    @PutMapping("/order")
    public String createOrderByPUT() {
        log.info("Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }

    @Data
    public static class CreateOrderRequest { // inner 클래스
        private String orderId;
        private Integer orderAmount;
    }
}
