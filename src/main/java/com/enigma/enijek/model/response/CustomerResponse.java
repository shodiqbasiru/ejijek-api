    package com.enigma.enijek.model.response;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class CustomerResponse {
        private Integer id;
        private String customerName;
        private String phoneNumber;
        private String address;
    }
