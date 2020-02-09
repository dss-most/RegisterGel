package th.go.dss.register.services;

import th.go.dss.register.models.Registration;

public interface RegistrationService {
	Iterable<Registration> findRegistrationforRound(Integer roundNumber);	
	Registration saveRegistration(Registration registration);
	Registration findRegistrationById(Integer id);
 
}
