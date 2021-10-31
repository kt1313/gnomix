package pl.clockworkjava.gnomix.domain.guest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

   private GuestRepository repository;

   @Autowired
   public GuestService(GuestRepository repository){
      this.repository=repository;
   }

   public List<Guest> findAllGuests(){
return this.repository.findAllGuests();
   }
}
