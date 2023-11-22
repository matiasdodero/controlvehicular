package matias.controlvehicular.Exception;

public class AppointmentTimeUnavailableException extends RuntimeException {

    public AppointmentTimeUnavailableException(String message) {
        super(message);
    }


    public AppointmentTimeUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}

