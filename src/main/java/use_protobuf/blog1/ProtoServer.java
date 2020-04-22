package use_protobuf.blog1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 17:56
 * \* Description:
 * \
 */
public class ProtoServer implements Runnable{


    @Override
    public void run() {
        try {
            System.out.println("beign:");
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                System.out.println("等待接收用户连接：");
                // 接受客户端请求
                Socket client = serverSocket.accept();

                DataOutputStream dataOutputStream;
                DataInputStream dataInputStream;

                try {
                    InputStream inputstream = client.getInputStream();
                    dataOutputStream = new DataOutputStream(
                            client.getOutputStream());

                    byte len[] = new byte[1024];
                    int count = inputstream.read(len);
                    byte[] temp = new byte[count];
                    for (int i = 0; i < count; i++) {
                        temp[i] = len[i];
                    }

                    Msg.CMsg msg = Msg.CMsg.parseFrom(temp);

                    Msg.CMsgHead head = Msg.CMsgHead.parseFrom(msg.getMsghead()
                            .getBytes());
                    System.out.println("==len===" + head.getMsglen());
                    System.out.println("==res===" + head.getMsgres());
                    System.out.println("==seq===" + head.getMsgseq());
                    System.out.println("==type===" + head.getMsgtype());
                    System.out.println("==Termid===" + head.getTermid());
                    System.out.println("==Termversion==="
                            + head.getTermversion());

                    Msg.CMsgReg body = Msg.CMsgReg.parseFrom(msg.getMsgbody()
                            .getBytes());
                    System.out.println("==area==" + body.getArea());
                    System.out.println("==Region==" + body.getRegion());
                    System.out.println("==shop==" + body.getShop());

                    sendProtoBufBack(dataOutputStream);
                    inputstream.close();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    client.close();
                    System.out.println("close");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private byte[] getProtoBufBack() {

        // head
        Msg.CMsgHead head = Msg.CMsgHead.newBuilder().setMsglen(10).setMsgtype(21)
                .setMsgseq(32).setTermversion(43).setMsgres(54)
                .setTermid("Server:head").build();

        // body
        Msg.CMsgReg body = Msg.CMsgReg.newBuilder().setArea(11).setRegion(22)
                .setShop(33).setRet(44).setTermid("Server:body").build();

        // Msg
        Msg.CMsg msg = Msg.CMsg.newBuilder()
                .setMsghead(head.toByteString().toStringUtf8())
                .setMsgbody(body.toByteString().toStringUtf8()).build();

        return msg.toByteArray();
    }

    private void sendProtoBufBack(DataOutputStream dataOutputStream) {

        byte[] backBytes = getProtoBufBack();

        // Integer len2 = backBytes.length;

        // byte[] cmdHead2 = BytesUtil.IntToBytes4(len2);

        try {
            // dataOutputStream.write(cmdHead2, 0, cmdHead2.length);
            dataOutputStream.write(backBytes, 0, backBytes.length);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        Thread desktopServerThread = new Thread(new ProtoServer());
        desktopServerThread.start();

    }

}
