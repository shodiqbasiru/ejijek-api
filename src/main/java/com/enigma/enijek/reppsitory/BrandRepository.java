package com.enigma.enijek.reppsitory;

import com.enigma.enijek.entity.BrandMotorcycles;
import com.enigma.enijek.model.response.BrandResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandMotorcycles,String> {
}
