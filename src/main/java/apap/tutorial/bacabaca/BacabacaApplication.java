package apap.tutorial.bacabaca;

import apap.tutorial.bacabaca.DTO.BukuMapper;
import apap.tutorial.bacabaca.DTO.PenerbitMapper;
import apap.tutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.request.CreatePenerbitRequestDTO;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class BacabacaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BacabacaApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(BukuService bukuService, PenerbitService penerbitService, BukuMapper bukuMapper, PenerbitMapper penerbitMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			var bukuDTO = new CreateBukuRequestDTO();
			var fakeBook = faker.book();
			var fakeDate = faker.date();

			bukuDTO.setHarga(new BigDecimal(Math.random()*1000000));
			bukuDTO.setJudul(fakeBook.title());
			bukuDTO.setTahunTerbit(String.valueOf(fakeDate.past(36500, TimeUnit.DAYS).getYear()+1900));

			var penerbitDTO = new CreatePenerbitRequestDTO();
			penerbitDTO.setNamaPenerbit(fakeBook.publisher());
			penerbitDTO.setEmail(faker.internet().emailAddress());
			penerbitDTO.setAlamat(faker.address().fullAddress());

			var penerbit = penerbitMapper.createPenerbitRequestDTOToPenerbit(penerbitDTO);
			penerbit = penerbitService.createPenerbit(penerbit);

			var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
			buku.setPenerbit(penerbit);
			bukuService.saveBuku(buku);
		};
	}

}
