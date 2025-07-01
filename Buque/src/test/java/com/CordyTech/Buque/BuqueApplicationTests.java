package com.CordyTech.Buque;


import com.CordyTech.Buque.model.Buque;
import com.CordyTech.Buque.repository.BuqueRepository;
import com.CordyTech.Buque.service.BuqueService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuqueApplicationTests {

	private BuqueRepository buqueRepository;
	private BuqueService buqueService;

	@BeforeEach
	void setUp() {
		buqueRepository = mock(BuqueRepository.class);
		buqueService = new BuqueService();
		buqueService.setBuqueRepository(buqueRepository);
	}

	@Test
	void testSaveBuque() {
		Buque buque = new Buque("B123", "Titanic", 300, new Date(), new Date());
		when(buqueRepository.save(buque)).thenReturn(buque);
		Buque resultado = buqueService.saveBuque(buque);
		assertEquals("Titanic", resultado.getNombreBuque());
		verify(buqueRepository, times(1)).save(buque);
	}

	@Test
	void testGetAllBuques() {
		List<Buque> lista = List.of(new Buque("B1", "A", 100, new Date(), new Date()));
		when(buqueRepository.findAll()).thenReturn(lista);
		List<Buque> resultado = buqueService.getAllBuques();
		assertEquals(1, resultado.size());
		assertEquals("A", resultado.get(0).getNombreBuque());
	}

	@Test
	void testGetBuqueById() {
		Buque buque = new Buque("B1", "A", 100, new Date(), new Date());
		when(buqueRepository.findById("B1")).thenReturn(Optional.of(buque));
		Optional<Buque> resultado = buqueService.getBuqueById("B1");
		assertTrue(resultado.isPresent());
		assertEquals("A", resultado.get().getNombreBuque());
	}

	@Test
	void testDeleteBuque() {
		doNothing().when(buqueRepository).deleteById("B1");
		buqueService.deleteBuque("B1");
		verify(buqueRepository, times(1)).deleteById("B1");
	}

}
