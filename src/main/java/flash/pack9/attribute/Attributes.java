package flash.pack9.attribute;

import flash.pack9.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
