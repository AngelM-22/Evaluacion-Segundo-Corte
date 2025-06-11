package maajpixup.dao.impl;

import org.gerdoc.pixup.model.Estado;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadoDaoImplTest {

    @Test
    void getInstance() {
    }

    @Test
    void findAll() {
        List<Estado>list = null;
        EstadoDao estadoDao = EstadoDaoImpl.getInstance();
        list = estadoDao.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        list.forEach(System.out::println);
    }

    @Test
    void save() {
        EstadoDao estadoDao = EstadoDaoImpl.getInstance();
        Estado estado = null;
        boolean res= false;
        estado =new Estado();
        estado.setNombre("CDMX");
        res =estadoDao.save(estado);
        assertTrue(res);
    }

    @Test
    void update() {
        EstadoDao estadoDao = EstadoDaoImpl.getInstance();
        Estado estado = null;
        boolean res= false;
        estado = estadoDao.findById(1);
        assertNotNull(estado);
        estado.setNombre("Ciudad de Mexico");
        res =estadoDao.update(estado);
        assertTrue(res);
    }

    @Test
    void delete() {
        EstadoDao estadoDao = EstadoDaoImpl.getInstance();
        Estado estado = null;
        boolean res= false;
        estado = estadoDao.findById(2);
        assertNotNull(estado);
        res =estadoDao.delete(estado);
        assertTrue(res);
    }

    @Test
    void findById(){
        EstadoDao estadoDao = EstadoDaoImpl.getInstance();
        Estado estado = null;
        estado= estadoDao.findById(1);
        assertNotNull(estado);
        assertEquals(1, estado.getId());
        assertEquals("CDMX", estado.getNombre());
    }
}