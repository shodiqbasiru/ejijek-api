package com.enigma.enijek.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_order", schema = "public", catalog = "db_enijek")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq")
    @SequenceGenerator(name = "order_seq",sequenceName = "t_order_id_seq",allocationSize = 1)
    private int id;
    @Column(name = "date",nullable = false)
    private Date date;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "customer_id", referencedColumnName = "id",nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order",cascade = {CascadeType.PERSIST})
    private List<OrderDetail> orderDetailEntityList;

}
