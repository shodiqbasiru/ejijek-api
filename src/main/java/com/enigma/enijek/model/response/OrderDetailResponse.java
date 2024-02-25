package com.enigma.enijek.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private Integer id;
    private Integer driverId;
    private String entryPoint;
    private String endPoint;
    private Integer distance;
}
