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
@Table(name = "m_distance", schema = "public", catalog = "db_enijek")
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "m_distance_seq")
    @SequenceGenerator(name = "m_distance_seq",sequenceName = "m_distance_id_seq",allocationSize = 1)
    @Column(name = "id")
    private int id;
    @Column(name = "distance",nullable = false)
    private int distance;
    @Column(name = "price",nullable = false)
    private long price;
    @OneToMany(mappedBy = "distance",cascade = {CascadeType.PERSIST})
    private List<OrderDetail> orderDetailEntityList;

}
