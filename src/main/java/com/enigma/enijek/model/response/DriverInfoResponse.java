package com.enigma.enijek.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverInfoResponse {
    private Integer id;
    private String brandName;
    private String modelName;
}
