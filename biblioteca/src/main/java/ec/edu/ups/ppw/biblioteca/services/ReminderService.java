package ec.edu.ups.ppw.biblioteca.services;

import jakarta.ejb.Singleton;

import java.util.List;

import ec.edu.ups.ppw.biblioteca.controller.PrestamoRepository;
import ec.edu.ups.ppw.biblioteca.model.EmailService;
import ec.edu.ups.ppw.biblioteca.model.Prestamo;
import jakarta.ejb.Schedule;
import jakarta.ejb.Timer;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;

@Singleton
public class ReminderService {

    @Inject
    private EmailService emailService;

    @Inject
    private PrestamoRepository prestamoRepository;

    @Schedule(hour = "*", minute = "*/30", second = "0", info = "Recordatorio de libros")
    public void sendReminders(Timer timer) {
        System.out.println("Iniciando el envío de recordatorios...");
        List<Prestamo> prestamos = prestamoRepository.findLoansNearDueDate();
        for (Prestamo prestamo : prestamos) {
            try {
                String usuarioNombre = prestamo.getUsuario().getUsername();
                String libroTitulo = prestamo.getLibro().getTitulo();
                String fechaDevolucion = prestamo.getFechaDevolucion().toString(); // Asegúrate de formatear esta fecha según lo necesites

                String emailBody = 
                        "<p>Estimado/a " + usuarioNombre + ",</p>" +
                        "<p>Este es un recordatorio de que el libro '<strong>" + libroTitulo + "</strong>' que has prestado debe ser devuelto el <strong>" + fechaDevolucion + "</strong>.</p>" +
                        "<p>Por favor, asegúrate de devolverlo antes de la fecha límite para evitar cargos por retraso.</p>" +
                        "<p>Gracias.</p>";
                emailService.sendEmail(
                        prestamo.getUsuario().getEmail(),
                        "Recordatorio: Devolución de Libro Próxima",
                        emailBody
                );
                System.out.println("Correo enviado a: " + prestamo.getUsuario().getEmail());
            } catch (MessagingException e) {
                e.printStackTrace(); // Manejo de errores
            }
        }
    }
}
