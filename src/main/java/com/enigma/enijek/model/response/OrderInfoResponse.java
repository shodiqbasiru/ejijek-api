package com.enigma.enijek.model.response;

import com.enigma.enijek.entity.Customer;
import com.enigma.enijek.entity.OrderDetail;
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
public class OrderInfoResponse {
    private Integer id;
    private Date date;
    private Integer customerId;
    private List<OrderDetailResponse> orderDetails;
}
