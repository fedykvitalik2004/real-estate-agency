package org.vitalii.fedyk.realestateagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vitalii.fedyk.realestateagency.entity.Realtor;

public interface RealtorRepository extends JpaRepository<Realtor, Long> {
}
