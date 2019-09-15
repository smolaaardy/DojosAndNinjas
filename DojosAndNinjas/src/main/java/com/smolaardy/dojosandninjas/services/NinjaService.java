package com.smolaardy.dojosandninjas.services;

import java.util.Optional;

import com.smolaardy.dojosandninjas.models.Ninja;
import com.smolaardy.dojosandninjas.repositories.NinjaRepository;

public class NinjaService {
	
	public NinjaRepository ninjaRepo;
	public NinjaService(NinjaRepository ninjaRepo) {
		this.ninjaRepo = ninjaRepo;
	}
	
	public Long addNinja(Ninja ninja) {
		ninjaRepo.save(ninja);
		return ninja.getId();
	}
	
	public Ninja findNinja(Long id) {
        Optional<Ninja> optionalNinja = ninjaRepo.findById(id);
        if(optionalNinja.isPresent()) {
            return optionalNinja.get();
        } else {
            return null;
        }
    }
	
	public void updateNinja(Ninja ninja) {
		ninjaRepo.save(ninja);
	}
	
	public void deleteNinja(Long id) {
		ninjaRepo.deleteById(id);
	}

}
