package com.enigma.enijek.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "m_brands_motorcycles")
public class BrandMotorcycles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brands_seq")
    @SequenceGenerator(name = "brands_seq",sequenceName = "m_brands_motorcycles_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "brand_name")
    private String brandName;
    @Column(name = "model_name")
    private String modelName;
    @OneToMany(mappedBy = "brandMotorcycles")
    private List<Driver> drivers;
}
