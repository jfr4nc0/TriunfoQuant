package com.jcanossa.triunfo.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcanossa.triunfo.entity.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long>{

}
