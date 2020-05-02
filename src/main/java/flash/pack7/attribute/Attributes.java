package flash.pack7.attribute;

import flash.pack7.session.Session;
import io.netty.util.AttributeKey;


public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
