package com.enigma.enijek.model.request;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Date date;
    private Integer customerId;
    private List<OrderDetailRequest> orderDetailRequests;
}
