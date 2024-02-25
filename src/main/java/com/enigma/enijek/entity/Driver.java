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
@Table(name = "m_driver", schema = "public", catalog = "db_enijek")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "m_driver_seq")
    @SequenceGenerator(name = "m_driver_seq", sequenceName = "m_driver_id_seq",allocationSize = 1)
    private int id;

    @Column(name = "driver_name",nullable = false)
    private String driverName;

    @Column(name = "license_plate",nullable = false,unique = true)
    private String licensePlate;

    @Column(name = "phone_number",nullable = false,unique = true)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id",nullable = false)
    private BrandMotorcycles brandMotorcycles;

    @OneToMany(mappedBy = "driver")
    private List<OrderDetail> orderDetailEntityList;

}
