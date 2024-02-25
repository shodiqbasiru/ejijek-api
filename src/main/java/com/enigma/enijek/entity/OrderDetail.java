package com.enigma.enijek.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_order_detail", schema = "public", catalog = "db_enijek")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_detail_seq")
    @SequenceGenerator(name = "order_detail_seq", sequenceName = "t_order_detail_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "entry_point", nullable = false)
    private String entryPoint;
    @Column(name = "end_point", nullable = false)
    private String endPoint;
    @Column(name = "distance", nullable = false)
    private Integer distance;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false)
    private Driver driver;

}
