package th.go.dss.register.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import th.go.dss.register.models.Registration;
import th.go.dss.register.services.RegistrationService;


@RestController
@RequestMapping("/API/Registration")  
public class RegistrationRestController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	
	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(value= "/{id}", method = {RequestMethod.GET})
	public Registration findById(@PathVariable Integer id) {
		log.debug("geting registration id: " + id);
		return registrationService.findRegistrationById(id);
	}
	
	@RequestMapping(value= "/round/{roundNumber}", method = {RequestMethod.GET})
	public Iterable<Registration> findByRound(@PathVariable Integer roundNumber) {
		log.debug("finding registration round: " + roundNumber);
		return registrationService.findRegistrationforRound(roundNumber);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT}) 
	public Registration save(@RequestBody JsonNode node) throws JsonMappingException {
		
		log.debug(node.toPrettyString());
		
		
		ObjectMapper mapper = new ObjectMapper();
		Registration reg = mapper.convertValue(node, Registration.class);
		
		
		log.debug(reg.getRound());
		
		return registrationService.saveRegistration(reg);
		
		
	}

}
