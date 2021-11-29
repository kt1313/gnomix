package pl.clockworkjava.gnomix.domain.reservation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {



    private ReservationRepository reservationRepository;

@Autowired
    public ReservationService(ReservationRepository reservationRepository){
    this.reservationRepository=reservationRepository;
}


}
