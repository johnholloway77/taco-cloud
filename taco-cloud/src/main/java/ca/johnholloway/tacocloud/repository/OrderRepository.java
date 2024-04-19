package ca.johnholloway.tacocloud.repository;

import ca.johnholloway.tacocloud.model.Taco;
import ca.johnholloway.tacocloud.model.TacoOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
   // TacoOrder save(TacoOrder tacoOrder);

    List<Taco> findByDeliveryZip(String deliveryZip);

    /*
    readOrdersByDeliveryZipAndPlacedAtBetween() will be parsed by Spring Framework
    and create a method that will read the orders with the zip matching that passed into the argument deliveryZip
    as well as those orders made between the arguments for startDate and endDate.

    This method will be created by the framework automatically by parsing the name of the method and the parameters.
     */
    List<TacoOrder> readOrdersByDeliveryZipAndPlaceAtBetween(
            String deliveryZip, Date startDate, Date endDate);

    List<TacoOrder> findDeliveryToAndDeliveryCityAllIgnoreCase(
            String deliveryTo, String deliveryCity);

    /*
    Read more on @Query annotation as book is vague
     */
//    @Query("Order o where o.deliveryCity='Calgary'")
//    List<TacoOrder> readOrdersDeliveredInCalgary();

}
