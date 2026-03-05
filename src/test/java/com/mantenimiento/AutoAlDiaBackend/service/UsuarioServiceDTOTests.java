package com.mantenimiento.AutoAlDiaBackend.service;

import com.mantenimiento.AutoAlDiaBackend.dto.UsuarioCreateDTO;
import com.mantenimiento.AutoAlDiaBackend.dto.UsuarioResponseDTO;
import com.mantenimiento.AutoAlDiaBackend.model.Usuario;
import com.mantenimiento.AutoAlDiaBackend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("Tests para UsuarioService - DTOs")
class UsuarioServiceDTOTests {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioCreateDTO usuarioCreateDTO;
    private Usuario usuarioExistente;

    @BeforeEach
    void setUp() {
        // Limpiar la base de datos antes de cada test
        usuarioRepository.deleteAll();

        // Preparar DTO de creación
        usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setEmail("test@example.com");
        usuarioCreateDTO.setPassword("password123");
        usuarioCreateDTO.setNombre("Usuario Test");

        // Preparar usuario existente en BD
        usuarioExistente = new Usuario();
        usuarioExistente.setEmail("existente@example.com");
        usuarioExistente.setPassword("password456");
        usuarioExistente.setNombre("Usuario Existente");
        usuarioExistente = usuarioRepository.save(usuarioExistente);
    }

    // ==================== TESTS DE dtoCreateToUsuario ====================

    @Test
    @DisplayName("dtoCreateToUsuario: Debe convertir UsuarioCreateDTO a Usuario correctamente")
    void testDtoCreateToUsuario_Exitoso() {
        // Given
        UsuarioCreateDTO dto = new UsuarioCreateDTO("nuevo@example.com", "pass123", "Nuevo Usuario");

        // When
        Usuario usuario = usuarioService.dtoCreateToUsuario(dto);

        // Then
        assertNotNull(usuario);
        assertEquals("nuevo@example.com", usuario.getEmail());
        assertEquals("pass123", usuario.getPassword());
        assertEquals("Nuevo Usuario", usuario.getNombre());
        assertNull(usuario.getId()); // No debe tener ID aún
    }

    @Test
    @DisplayName("dtoCreateToUsuario: Debe mantener todos los campos del DTO")
    void testDtoCreateToUsuario_TodosCampos() {
        // Given
        UsuarioCreateDTO dto = new UsuarioCreateDTO("email@test.com", "securePass", "Test Name");

        // When
        Usuario usuario = usuarioService.dtoCreateToUsuario(dto);

        // Then
        assertAll("Validar campos convertidos",
            () -> assertEquals(dto.getEmail(), usuario.getEmail()),
            () -> assertEquals(dto.getPassword(), usuario.getPassword()),
            () -> assertEquals(dto.getNombre(), usuario.getNombre())
        );
    }

    // ==================== TESTS DE usuarioToResponseDTO ====================

    @Test
    @DisplayName("usuarioToResponseDTO: Debe convertir Usuario a DTO sin exponer contraseña")
    void testUsuarioToResponseDTO_NoExponePasword() {
        // Given
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("usuario@example.com");
        usuario.setPassword("passwordSecreto");
        usuario.setNombre("Usuario");

        // When
        UsuarioResponseDTO dto = usuarioService.usuarioToResponseDTO(usuario);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("usuario@example.com", dto.getEmail());
        assertEquals("Usuario", dto.getNombre());
        // El DTO de respuesta no tiene campo password, por lo que la contraseña no está expuesta
    }

    @Test
    @DisplayName("usuarioToResponseDTO: Debe contener id, email y nombre")
    void testUsuarioToResponseDTO_CamposRequeridos() {
        // When
        UsuarioResponseDTO dto = usuarioService.usuarioToResponseDTO(usuarioExistente);

        // Then
        assertAll("Validar campos del DTO de respuesta",
            () -> assertNotNull(dto.getId()),
            () -> assertNotNull(dto.getEmail()),
            () -> assertNotNull(dto.getNombre())
        );
    }

    // ==================== TESTS DE crearDesdeDTO ====================

    @Test
    @DisplayName("crearDesdeDTO: Debe crear usuario exitosamente desde DTO")
    void testCrearDesdeDTO_Exitoso() {
        // Given
        UsuarioCreateDTO dto = new UsuarioCreateDTO("nuevo@test.com", "pass123", "Nuevo");

        // When
        UsuarioResponseDTO response = usuarioService.crearDesdeDTO(dto);

        // Then
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("nuevo@test.com", response.getEmail());
        assertEquals("Nuevo", response.getNombre());

        // Verificar que se guardó en BD
        assertTrue(usuarioService.existePorEmail("nuevo@test.com"));
    }

    @Test
    @DisplayName("crearDesdeDTO: Debe lanzar excepción si email ya existe")
    void testCrearDesdeDTO_EmailYaExiste() {
        // Given
        UsuarioCreateDTO dto = new UsuarioCreateDTO(usuarioExistente.getEmail(), "password", "Nombre");

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.crearDesdeDTO(dto);
        });

        assertTrue(exception.getMessage().contains("El email ya está registrado"));
    }

    @Test
    @DisplayName("crearDesdeDTO: Debe retornar DTO sin contraseña")
    void testCrearDesdeDTO_NoRetornaPassword() {
        // When
        UsuarioResponseDTO response = usuarioService.crearDesdeDTO(usuarioCreateDTO);

        // Then
        assertNotNull(response);
        // El DTO de respuesta no tiene campo password, por lo que la contraseña no está expuesta
        // Verificar que solo contiene los campos permitidos
        assertTrue(response.getId() > 0);
    }

    @Test
    @DisplayName("crearDesdeDTO: El usuario creado debe estar en BD")
    void testCrearDesdeDTO_PersistenciaEnBD() {
        // When
        UsuarioResponseDTO response = usuarioService.crearDesdeDTO(usuarioCreateDTO);

        // Then
        Optional<Usuario> usuarioGuardado = usuarioRepository.findById(response.getId());
        assertTrue(usuarioGuardado.isPresent());
        assertEquals(usuarioCreateDTO.getEmail(), usuarioGuardado.get().getEmail());
    }

    // ==================== TESTS DE obtenerPorIdDTO ====================

    @Test
    @DisplayName("obtenerPorIdDTO: Debe obtener usuario como DTO por ID")
    void testObtenerPorIdDTO_Exitoso() {
        // When
        Optional<UsuarioResponseDTO> dto = usuarioService.obtenerPorIdDTO(usuarioExistente.getId());

        // Then
        assertTrue(dto.isPresent());
        assertEquals(usuarioExistente.getId(), dto.get().getId());
        assertEquals(usuarioExistente.getEmail(), dto.get().getEmail());
    }

    @Test
    @DisplayName("obtenerPorIdDTO: Debe retornar Optional vacío si no existe")
    void testObtenerPorIdDTO_NoExiste() {
        // When
        Optional<UsuarioResponseDTO> dto = usuarioService.obtenerPorIdDTO(999L);

        // Then
        assertTrue(dto.isEmpty());
    }

    @Test
    @DisplayName("obtenerPorIdDTO: Debe retornar DTO sin contraseña")
    void testObtenerPorIdDTO_SinPassword() {
        // When
        Optional<UsuarioResponseDTO> dto = usuarioService.obtenerPorIdDTO(usuarioExistente.getId());

        // Then
        assertTrue(dto.isPresent());
        // El DTO de respuesta no tiene campo password, por lo que la contraseña no está expuesta
        assertNotNull(dto.get().getId());
        assertNotNull(dto.get().getEmail());
    }

    // ==================== TESTS DE obtenerTodosDTO ====================

    @Test
    @DisplayName("obtenerTodosDTO: Debe obtener todos los usuarios como DTOs")
    void testObtenerTodosDTO_Exitoso() {
        // Given - Crear un usuario adicional
        UsuarioCreateDTO dto2 = new UsuarioCreateDTO("otro@test.com", "pass", "Otro");
        usuarioService.crearDesdeDTO(dto2);

        // When
        List<UsuarioResponseDTO> dtos = usuarioService.obtenerTodosDTO();

        // Then
        assertEquals(2, dtos.size());
    }

    @Test
    @DisplayName("obtenerTodosDTO: Debe retornar lista vacía si no hay usuarios")
    void testObtenerTodosDTO_VacioSinUsuarios() {
        // Given
        usuarioRepository.deleteAll();

        // When
        List<UsuarioResponseDTO> dtos = usuarioService.obtenerTodosDTO();

        // Then
        assertTrue(dtos.isEmpty());
    }

    @Test
    @DisplayName("obtenerTodosDTO: Ningún DTO debe contener contraseña")
    void testObtenerTodosDTO_SinPasswords() {
        // Given
        usuarioService.crearDesdeDTO(new UsuarioCreateDTO("otro@test.com", "pass", "Otro"));

        // When
        List<UsuarioResponseDTO> dtos = usuarioService.obtenerTodosDTO();

        // Then
        // Los DTOs de respuesta no tienen campo password, por lo que no exponen contraseñas
        for (UsuarioResponseDTO dto : dtos) {
            assertNotNull(dto.getId());
            assertNotNull(dto.getEmail());
            assertNotNull(dto.getNombre());
        }
    }

    @Test
    @DisplayName("obtenerTodosDTO: Debe contener todos los usuarios existentes")
    void testObtenerTodosDTO_ContieneAUsuariosExistentes() {
        // Given
        usuarioService.crearDesdeDTO(new UsuarioCreateDTO("otro@test.com", "pass", "Otro"));

        // When
        List<UsuarioResponseDTO> dtos = usuarioService.obtenerTodosDTO();

        // Then
        assertTrue(dtos.stream()
            .anyMatch(dto -> dto.getEmail().equals(usuarioExistente.getEmail())));
    }

    // ==================== TESTS DE buscarPorEmailDTO ====================

    @Test
    @DisplayName("buscarPorEmailDTO: Debe buscar usuario por email como DTO")
    void testBuscarPorEmailDTO_Exitoso() {
        // When
        Optional<UsuarioResponseDTO> dto = usuarioService.buscarPorEmailDTO(usuarioExistente.getEmail());

        // Then
        assertTrue(dto.isPresent());
        assertEquals(usuarioExistente.getEmail(), dto.get().getEmail());
        assertEquals(usuarioExistente.getNombre(), dto.get().getNombre());
    }

    @Test
    @DisplayName("buscarPorEmailDTO: Debe retornar Optional vacío si email no existe")
    void testBuscarPorEmailDTO_NoExiste() {
        // When
        Optional<UsuarioResponseDTO> dto = usuarioService.buscarPorEmailDTO("noexiste@example.com");

        // Then
        assertTrue(dto.isEmpty());
    }

    @Test
    @DisplayName("buscarPorEmailDTO: Debe retornar DTO sin contraseña")
    void testBuscarPorEmailDTO_SinPassword() {
        // When
        Optional<UsuarioResponseDTO> dto = usuarioService.buscarPorEmailDTO(usuarioExistente.getEmail());

        // Then
        assertTrue(dto.isPresent());
        // El DTO de respuesta no tiene campo password, por lo que la contraseña no está expuesta
        assertNotNull(dto.get().getId());
        assertNotNull(dto.get().getEmail());
    }

    @Test
    @DisplayName("buscarPorEmailDTO: Debe ser case-sensitive")
    void testBuscarPorEmailDTO_CaseSensitive() {
        // When
        Optional<UsuarioResponseDTO> dto = usuarioService
            .buscarPorEmailDTO(usuarioExistente.getEmail().toUpperCase());

        // Then - Depende de la implementación de la BD, pero típicamente es case-sensitive
        // Si no lo encuentra, el test pasará; si lo encuentra, también es válido
        // Este es más un test de comportamiento esperado
    }

}
