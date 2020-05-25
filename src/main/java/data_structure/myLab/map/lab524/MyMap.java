package data_structure.myLab.map.lab524;

public interface MyMap<K,V> {

    V put(K k, V v);

    V get(K k);

    int size();

    interface Entry<K, V>{
        /**
         * 根据entry对象获取key值
         * @return
         */
        K getKey();
        /**
         * 根据entry对象获取value值
         * @return
         */
        V getValue();
    }

}
