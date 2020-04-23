package use_protobuf.the_speed;

public interface TestCallback {

    String getName();

    byte[] writeObject(Object source);

    Object readObject(byte[] bytes);


}
