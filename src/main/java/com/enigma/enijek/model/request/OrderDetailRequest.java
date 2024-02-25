package com.enigma.enijek.model.request;

import com.enigma.enijek.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailRequest {
    private Integer orderId;
    private String entryPoint;
    private String endPoint;
    private Integer distance;
    private Integer driverId;
}
