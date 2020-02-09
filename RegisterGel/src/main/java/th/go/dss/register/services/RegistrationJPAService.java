package th.go.dss.register.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.dss.register.models.QRegistration;
import th.go.dss.register.models.Registration;
import th.go.dss.register.repositories.RegistrationRepository;

@Service
public class RegistrationJPAService implements RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepo;
	
	@Override
	public Iterable<Registration> findRegistrationforRound(Integer roundNumber) {
		
		return registrationRepo.findAll(QRegistration.registration.round.eq(roundNumber));
	}

	
	
	@Override
	public Registration findRegistrationById(Integer id) {
		// 
		Optional<Registration> dbReg =registrationRepo.findById(id); 
		
		return dbReg.get();
	}



	@Override
	public Registration saveRegistration(Registration registration) {
		Registration reg = new Registration();
		if(registration.getId() != null) {
			Optional<Registration> dbReg = registrationRepo.findOne(QRegistration.registration.id.eq(registration.getId()));
			
			if(dbReg.isPresent()) {
				reg = dbReg.get();
			}
		}
		
		// now set reg
		reg.setEmail(registration.getEmail());
		reg.setFirstName(registration.getFirstName());
		reg.setLastName(registration.getLastName());
		reg.setOccupation(registration.getOccupation());
		reg.setWorkplace(registration.getWorkplace());
		reg.setPhone(registration.getPhone());
		reg.setStatus("REGISTER");
		reg.setRound(registration.getRound());
		reg.setCreatedTime(new Date());
		
		registrationRepo.save(reg);
		
		
		return reg;
	}

}
