package API;

import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.List;
import java.util.function.BiFunction;

public class SubscriberEventStore implements MessageDispatchInterceptor {

    @Override
    public BiFunction handle(List list) {
        return null;
    }
}