package com.enigma.enijek.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandInfoResponse {
    private Integer id;
    private String driverName;
    private String licensePlate;
    private String phoneNumber;
}
