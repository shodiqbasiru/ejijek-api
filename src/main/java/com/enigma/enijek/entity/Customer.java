package com.enigma.enijek.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "m_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "m_customer_seq")
    @SequenceGenerator(name = "m_customer_seq",sequenceName = "m_customer_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "customer_name",nullable = false)
    private String customerName;
    @Column(name = "phone_number",nullable = false,unique = true)
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST})
    private List<Order> orderEntityList;
}
