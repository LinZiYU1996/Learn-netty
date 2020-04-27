package SSl.javasecuresocketextension.package2;

import javax.net.ssl.*;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/26
 * \* Time: 10:59
 * \* Description:
 * \
 */
public class CatServer implements Runnable, HandshakeCompletedListener{
    //server端处理流程和代码
//处理流程：
//1）加载server的keystore文件，需要指定keystore的密码(storepass)。
//KeyStore类型有如下三种：
//jceks - The proprietary keystore implementation provided by the SunJCE provider.
//jks - The proprietary keystore implementation provided by the SUN provider.
//pkcs12 - The transfer syntax for personal identity information as defined in PKCS #12.
//
//2）加载server的truststore文件，需要指定truststore的密码(storepass)。
//
//3) 创建KeyManagerFactory对象并用1）中加载的keystore和server密钥对的密码(keypass)来初始化。
//
//4) 创建TrustManagerFactory对象并用2）中加载的truststore来初始化。truststore中存的是client的公钥，不需要keypass也可以访问。
//
//5）创建SSLContext并用3）和4）中创建的KeyManagerFactory和TrustManagerFactory对象来初始化。
//http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#SSLContext
//创建SSLContext是需要给出SSLContext Algorithms。上面这个链接中给出了合法的SSLContext Algorithms，有如下可用值。
//SSL - Supports some version of SSL; may support other versions
//SSLv2 - Supports SSL version 2 or later; may support other versions
//SSLv3 - Supports SSL version 3; may support other versions
//TLS - Supports some version of TLS; may support other versions
//TLSv1 - Supports RFC 2246: TLS version 1.0 ; may support other versions
//TLSv1.1 - Supports RFC 4346: TLS version 1.1 ; may support other versions
//TLSv1.2 - Supports RFC 5246: TLS version 1.2 ; may support other versions
//
//6）创建SSLServerSocketFactory，在指定的端口上创建SSLServerSocket并设定需要客户端证书：setNeedClientAuth(true)
//
//7）在SSLServerSocket对象上调用accept()方法等待客户端的连接。
//客户端连上来之后这个函数会返回一个SSLSocket对象，在这个对象的输入输出流上进行读写。
//在这个SSLSocket对象上可以添加一个HandshakeCompletedListener的监听器，SSL/TLS握手结束后这个监听器的handshakeCompleted方法就会被调用。
//客户端有三种方法会触发握手：
//
//显式调用startHandshake方法/calling startHandshake which explicitly begins handshakes, or
//在socket对象上进行read或write操作/any attempt to read or write application data on this socket causes an implicit handshake, or
//在socket对象上调用getSession方法/a call to getSession tries to set up a session if there is no currently valid session, and an implicit handshake is done.
    public static final int SERVER_PORT = 11123;

    private final Socket _s;
    private String peerCerName;

    public CatServer(Socket s) {
        _s = s;
    }

    public static void main(String[] args) throws Exception {
        String serverKeyStoreFile = "E:\\Learn-netty\\Learn-netty\\src\\main\\java\\SSl\\javasecuresocketextension\\package2\\catserver.keystore";
        String serverKeyStorePwd = "catserverks";
        String catServerKeyPwd = "catserver";
        String serverTrustKeyStoreFile = "E:\\Learn-netty\\Learn-netty\\src\\main\\java\\SSl\\javasecuresocketextension\\package2\\catservertrust.keystore";
        String serverTrustKeyStorePwd = "catservertrustks";

        KeyStore serverKeyStore = KeyStore.getInstance("JKS");
        serverKeyStore.load(new FileInputStream(serverKeyStoreFile), serverKeyStorePwd.toCharArray());

        KeyStore serverTrustKeyStore = KeyStore.getInstance("JKS");
        serverTrustKeyStore.load(new FileInputStream(serverTrustKeyStoreFile), serverTrustKeyStorePwd.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(serverKeyStore, catServerKeyPwd.toCharArray());

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(serverTrustKeyStore);

        SSLContext sslContext = SSLContext.getInstance("TLSv1");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
        SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(SERVER_PORT);
        sslServerSocket.setNeedClientAuth(true);

        while (true) {
            SSLSocket s = (SSLSocket)sslServerSocket.accept();
            CatServer cs = new CatServer(s);
            s.addHandshakeCompletedListener(cs);
            new Thread(cs).start();
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(_s.getInputStream()));
            PrintWriter writer = new PrintWriter(_s.getOutputStream(), true);

            writer.println("Welcome~, enter exit to leave.");
            String s;
            while ((s = reader.readLine()) != null && !s.trim().equalsIgnoreCase("exit")) {
                writer.println("Echo: " + s);
            }
            writer.println("Bye~, " + peerCerName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                _s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handshakeCompleted(HandshakeCompletedEvent event) {
        try {
            X509Certificate cert = (X509Certificate) event.getPeerCertificates()[0];
            peerCerName = cert.getSubjectX500Principal().getName();
        } catch (SSLPeerUnverifiedException ex) {
            ex.printStackTrace();
        }
    }
}
