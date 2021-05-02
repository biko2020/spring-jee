package org.arc.springmvc;

import lombok.Data;
import org.arc.springmvc.dao.PatientRepository;
import org.arc.springmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner {
	@Autowired
	private  PatientRepository patientRepository;

	public SpringmvcApplication(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	patientRepository.save(new Patient(null,"Mustapha",new Date(),1,false));
	patientRepository.save(new Patient(null,"Adil",new Date(),2,false));
	patientRepository.save(new Patient(null,"Samira",new Date(),3,false));
	patientRepository.save(new Patient(null,"Amar",new Date(),4,true));
	patientRepository.save(new Patient(null,"Hamid",new Date(),5,true));
	patientRepository.save(new Patient(null,"Fatima",new Date(),6,true));

	patientRepository.findAll().forEach(p-> {
		System.out.println(p.toString());

	 });

		 System.out.println("***** Recherche Par Nom ****");
		List<Patient> patient = patientRepository.findByNameContains("A");
		patient.forEach(p->{
			System.out.println(p.getName());
		});



		System.out.println("***** Recherche par Malade ****");
		List<Patient> patient2 = patientRepository.findByMalade(true);
		patient2.forEach(p->{
			System.out.println(p.isMalade());
		});

		System.out.println("***** Recherche Multiples ****");
		List<Patient> patient3 = patientRepository.findByNameContainsAndMalade("m",false);
		patient3.forEach(p->{
			System.out.println(p.toString());
		});


		System.out.println("***** Supprimer un patient par son ID ****");
		List<Patient> patient4 = patientRepository.findAll();
		patient4.forEach(p->{
			System.out.println(p.toString());
		});

		System.out.println("***** Pagination ****");
		Page<Patient> page = patientRepository.findAll(PageRequest.of(0,2));
		System.out.println("Total page :"+ page.getTotalPages());
		List<Patient> list = page.getContent();
		list.forEach(p->{
			System.out.println(p.toString());
		});


		System.out.println("***** Recherche les deux patients commence par A ****");
		Page<Patient> pg = patientRepository.findByNameContains("A",PageRequest.of(0,2));
		pg.forEach(p->{
			System.out.println(p.getName());
		});
	}
}
