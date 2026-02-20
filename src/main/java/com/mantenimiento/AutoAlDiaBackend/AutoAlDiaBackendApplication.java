package com.mantenimiento.AutoAlDiaBackend;

import com.mantenimiento.AutoAlDiaBackend.model.*;
import com.mantenimiento.AutoAlDiaBackend.model.enums.EstadoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoDocumento;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoRecordatorio;
import com.mantenimiento.AutoAlDiaBackend.model.enums.TipoServicio;
import com.mantenimiento.AutoAlDiaBackend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class AutoAlDiaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoAlDiaBackendApplication.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner initDatabase(
			UsuarioRepository usuarioRepository,
			VehiculoRepository vehiculoRepository,
			MantenimientoRepository mantenimientoRepository,
			RecordatorioMantenimientoRepository recordatorioRepository,
			DocumentoRepository documentoRepository) {

		return args -> {
			System.out.println("\n========================================");
			System.out.println("🚀 INICIANDO PRUEBAS DE BASE DE DATOS");
			System.out.println("========================================\n");

			// ============================================
			// 1. CREAR USUARIO
			// ============================================
			System.out.println("📝 Creando usuario...");
			Usuario usuario = new Usuario();
			usuario.setEmail("juan.perez@example.com");
			usuario.setPassword("password123");
			usuario.setNombre("Juan Pérez");
			usuario = usuarioRepository.save(usuario);
			System.out.println("✅ Usuario creado: " + usuario.getNombre() + " (ID: " + usuario.getId() + ")");

			// ============================================
			// 2. CREAR VEHÍCULO
			// ============================================
			System.out.println("\n🚗 Creando vehículo...");
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setUsuario(usuario);
			vehiculo.setMarca("Toyota");
			vehiculo.setModelo("Corolla");
			vehiculo.setAnio(2020);
			vehiculo.setPatente("ABC123");
			vehiculo.setKm_actual(50000);
			vehiculo = vehiculoRepository.save(vehiculo);
			System.out.println("✅ Vehículo creado: " + vehiculo.getMarca() + " " + vehiculo.getModelo() +
					" (ID: " + vehiculo.getId() + ")");

			// ============================================
			// 3. CREAR REGISTROS DE MANTENIMIENTO
			// ============================================
			System.out.println("\n🔧 Creando registros de mantenimiento...");

			// Mantenimiento 1
			Mantenimiento mantenimiento1 = new Mantenimiento();
			mantenimiento1.setVehiculo(vehiculo);
			mantenimiento1.setTipoServicio(TipoServicio.CAMBIO_ACEITE);
			mantenimiento1.setDescripcion("Cambio de aceite y filtro");
			mantenimiento1.setFecha_mantenimiento(LocalDate.now().minusMonths(2));
			mantenimiento1.setKm_al_servicio(45000);
			mantenimiento1.setCosto(new BigDecimal("8500.00"));
			mantenimiento1.setLugar("Taller Mecánico Central");
			mantenimientoRepository.save(mantenimiento1);

			// Mantenimiento 2
			Mantenimiento mantenimiento2 = new Mantenimiento();
			mantenimiento2.setVehiculo(vehiculo);
			mantenimiento2.setTipoServicio(TipoServicio.NEUMATICOS);
			mantenimiento2.setDescripcion("Rotación de neumáticos");
			mantenimiento2.setFecha_mantenimiento(LocalDate.now().minusMonths(1));
			mantenimiento2.setKm_al_servicio(48000);
			mantenimiento2.setCosto(new BigDecimal("3000.00"));
			mantenimiento2.setLugar("Gomería Norte");
			mantenimientoRepository.save(mantenimiento2);

			// Mantenimiento 3
			Mantenimiento mantenimiento3 = new Mantenimiento();
			mantenimiento3.setVehiculo(vehiculo);
			mantenimiento3.setTipoServicio(TipoServicio.FRENOS);
			mantenimiento3.setDescripcion("Cambio de pastillas de freno");
			mantenimiento3.setFecha_mantenimiento(LocalDate.now().minusWeeks(2));
			mantenimiento3.setKm_al_servicio(49500);
			mantenimiento3.setCosto(new BigDecimal("12000.00"));
			mantenimiento3.setLugar("Taller Mecánico Central");
			mantenimientoRepository.save(mantenimiento3);

			System.out.println("✅ 3 mantenimientos creados");

			// ============================================
			// 4. CREAR RECORDATORIOS
			// ============================================
			System.out.println("\n⏰ Creando recordatorios...");

			// Recordatorio por kilometraje
			RecordatorioMantenimiento recordatorio1 = new RecordatorioMantenimiento();
			recordatorio1.setVehiculo(vehiculo);
			recordatorio1.setTipoRecordatorio(TipoRecordatorio.BASADO_EN_KM);
			recordatorio1.setTipoServicio(TipoServicio.CAMBIO_ACEITE);
			recordatorio1.setRecordatorio_km(55000);
			recordatorio1.setEstado(EstadoRecordatorio.PENDIENTE);
			recordatorioRepository.save(recordatorio1);

			// Recordatorio por fecha
			RecordatorioMantenimiento recordatorio2 = new RecordatorioMantenimiento();
			recordatorio2.setVehiculo(vehiculo);
			recordatorio2.setTipoRecordatorio(TipoRecordatorio.BASADO_EN_FECHA);
			recordatorio2.setTipoServicio(TipoServicio.REVISION_GENERAL);
			recordatorio2.setRecordatorio_fecha(LocalDate.now().plusMonths(1));
			recordatorio2.setEstado(EstadoRecordatorio.PENDIENTE);
			recordatorioRepository.save(recordatorio2);

			System.out.println("✅ 2 recordatorios creados");

			// ============================================
			// 5. CREAR DOCUMENTOS
			// ============================================
			System.out.println("\n📄 Creando documentos...");

			// Seguro
			Documento seguro = new Documento();
			seguro.setVehiculo(vehiculo);
			seguro.setTipoDocumento(TipoDocumento.SEGURO);
			seguro.setFecha_expiro(LocalDate.now().plusMonths(6));
			seguro.setNotas("Seguro todo riesgo");
			documentoRepository.save(seguro);

			// VTV
			Documento vtv = new Documento();
			vtv.setVehiculo(vehiculo);
			vtv.setTipoDocumento(TipoDocumento.VTV);
			vtv.setFecha_expiro(LocalDate.now().minusMonths(1)); // Vencido
			vtv.setNotas("Revisar urgente");
			documentoRepository.save(vtv);

			// Patente
			Documento patente = new Documento();
			patente.setVehiculo(vehiculo);
			patente.setTipoDocumento(TipoDocumento.PATENTE);
			patente.setFecha_expiro(LocalDate.now().plusDays(15)); // Por vencer
			patente.setNotas("Pago trimestral");
			documentoRepository.save(patente);

			System.out.println("✅ 3 documentos creados");

			/*

			// ============================================
			// 6. PRUEBAS DE LECTURA (READ)
			// ============================================
			System.out.println("\n========================================");
			System.out.println("📖 PRUEBAS DE LECTURA");
			System.out.println("========================================\n");

			// Buscar usuario por email
			System.out.println("🔍 Buscar usuario por email:");
			usuarioRepository.findByEmail("juan.perez@example.com")
					.ifPresent(u -> System.out.println("   Encontrado: " + u.getNombre()));

			// Listar vehículos del usuario
			System.out.println("\n🔍 Vehículos del usuario:");
			vehiculoRepository.findByUsuarioId(usuario.getId())
					.forEach(v -> System.out.println("   - " + v.getMarca() + " " + v.getModelo()));

			// Buscar vehículo por patente
			System.out.println("\n🔍 Buscar vehículo por patente:");
			vehiculoRepository.findByPatente("ABC123")
					.ifPresent(v -> System.out.println("   Encontrado: " + v.getMarca() + " " + v.getModelo()));

			// Historial de mantenimientos
			System.out.println("\n🔍 Historial de mantenimientos:");
			mantenimientoRepository.findByVehiculoIdOrderByFechaDesc(vehiculo.getId())
					.forEach(m -> System.out.println("   - " + m.getFecha() + " | " +
							m.getTipoServicio() + " | $" + m.getCosto()));

			// Recordatorios pendientes
			System.out.println("\n🔍 Recordatorios pendientes:");
			recordatorioRepository.findByVehiculoIdAndEstado(vehiculo.getId(), EstadoRecordatorio.PENDIENTE)
					.forEach(r -> System.out.println("   - " + r.getTipoServicio() + " | Tipo: " + r.getTipo()));

			// Documentos vencidos
			System.out.println("\n🔍 Documentos vencidos:");
			documentoRepository.findDocumentosVencidos(vehiculo.getId(), LocalDate.now())
					.forEach(d -> System.out.println("   - " + d.getTipo() + " | Venció: " + d.getFechaVencimiento()));

			// Documentos por vencer (próximos 30 días)
			System.out.println("\n🔍 Documentos por vencer (próximos 30 días):");
			documentoRepository.findDocumentosPorVencer(vehiculo.getId(), LocalDate.now(), LocalDate.now().plusDays(30))
					.forEach(d -> System.out.println("   - " + d.getTipo() + " | Vence: " + d.getFechaVencimiento()));

			// ============================================
			// 7. PRUEBAS DE ACTUALIZACIÓN (UPDATE)
			// ============================================
			System.out.println("\n========================================");
			System.out.println("✏️  PRUEBAS DE ACTUALIZACIÓN");
			System.out.println("========================================\n");

			System.out.println("📝 Actualizando kilometraje del vehículo...");
			vehiculo.setKilometrajeActual(52000);
			vehiculoRepository.save(vehiculo);
			System.out.println("✅ Kilometraje actualizado a: " + vehiculo.getKilometrajeActual() + " km");

			System.out.println("\n📝 Completando un recordatorio...");
			recordatorio1.setEstado(EstadoRecordatorio.COMPLETADO);
			recordatorioRepository.save(recordatorio1);
			System.out.println("✅ Recordatorio marcado como completado");

			// ============================================
			// 8. PRUEBAS DE CONTEO Y ESTADÍSTICAS
			// ============================================
			System.out.println("\n========================================");
			System.out.println("📊 ESTADÍSTICAS");
			System.out.println("========================================\n");

			long cantidadVehiculos = vehiculoRepository.countByUsuarioId(usuario.getId());
			System.out.println("🚗 Cantidad de vehículos del usuario: " + cantidadVehiculos);

			long cantidadMantenimientos = mantenimientoRepository.countByVehiculoId(vehiculo.getId());
			System.out.println("🔧 Cantidad de mantenimientos realizados: " + cantidadMantenimientos);

			BigDecimal costoTotal = mantenimientoRepository.calcularCostoTotalPorVehiculo(vehiculo.getId());
			System.out.println("💰 Costo total de mantenimientos: $" + costoTotal);

			// ============================================
			// 9. PRUEBA DE ELIMINACIÓN (DELETE)
			// ============================================
			System.out.println("\n========================================");
			System.out.println("🗑️  PRUEBA DE ELIMINACIÓN");
			System.out.println("========================================\n");

			System.out.println("❌ Eliminando un recordatorio completado...");
			recordatorioRepository.delete(recordatorio1);
			System.out.println("✅ Recordatorio eliminado");

			long recordatoriosRestantes = recordatorioRepository.count();
			System.out.println("📋 Recordatorios restantes en BD: " + recordatoriosRestantes);

			// ============================================
			// FIN DE PRUEBAS
			// ============================================
			System.out.println("\n========================================");
			System.out.println("✅ TODAS LAS PRUEBAS COMPLETADAS");
			System.out.println("========================================\n");

			*/
		};
	}
}
