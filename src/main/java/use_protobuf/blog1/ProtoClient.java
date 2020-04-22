package use_protobuf.blog1;

import java.io.InputStream;
import java.net.Socket;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: LinZiYu
 * \* Date: 2020/4/21
 * \* Time: 18:02
 * \* Description:
 * \
 */
public class ProtoClient {

    public static void main(String[] args) {
        ProtoClient pc=new ProtoClient();
        System.out.println("beign:");
        pc.runget();
    }

    public void runget() {
        Socket socket = null;
        try {
            //socket = new Socket("localhost", 12345);
            socket = new Socket("localhost", 12345);
            // head
            Msg.CMsgHead head = Msg.CMsgHead.newBuilder().setMsglen(5).setMsgtype(1)
                    .setMsgseq(3).setTermversion(41).setMsgres(5)
                    .setTermid("Client:head").build();

            // body
            Msg.CMsgReg body = Msg.CMsgReg.newBuilder().setArea(11).setRegion(22)
                    .setShop(33).setRet(44).setTermid("Clent:body").build();

            // Msg
            Msg.CMsg msg = Msg.CMsg.newBuilder()
                    .setMsghead(head.toByteString().toStringUtf8())
                    .setMsgbody(body.toByteString().toStringUtf8()).build();

            // 向服务器发送信息
            System.out.println("sendMsg...");
            msg.writeTo(socket.getOutputStream());

            // 接受服务器的信息
            InputStream input = socket.getInputStream();

            System.out.println("recvMsg:");
            byte[] by = recvMsg(input);
            printMsg(Msg.CMsg.parseFrom(by));

            input.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void printMsg(Msg.CMsg g) {

        try {
            Msg.CMsgHead h = Msg.CMsgHead.parseFrom(g.getMsghead().getBytes());
            StringBuffer sb = new StringBuffer();
            if (h.hasMsglen())
                sb.append("==msglen===" + h.getMsglen() + "\n");
            if (h.hasMsgres())
                sb.append("==msgres===" + h.getMsgres() + "\n");
            if (h.hasMsgseq())
                sb.append("==msgseq===" + h.getMsgseq() + "\n");
            if (h.hasMsgtype())
                sb.append("==msgtype===" + h.getMsgtype() + "\n");
            if (h.hasTermid())
                sb.append("==termid===" + h.getTermid() + "\n");
            if (h.hasTermversion())
                sb.append("==termversion===" + h.getTermversion() + "\n");

            Msg.CMsgReg bo = Msg.CMsgReg.parseFrom(g.getMsgbody().getBytes());
            if (bo.hasArea())
                sb.append("==area==" + bo.getArea() + "\n");
            if (bo.hasRegion())
                sb.append("==region==" + bo.getRegion() + "\n");
            if (bo.hasShop())
                sb.append("==shop==" + bo.getShop() + "\n");
            if (bo.hasRet())
                sb.append("==ret==" + bo.getRet() + "\n");
            if (bo.hasTermid())
                sb.append("==termid==" + bo.getTermid() + "\n");

            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public byte[] recvMsg(InputStream inpustream) {
        byte[] temp = null;
        try {

            byte len[] = new byte[1024];
            int count = inpustream.read(len);

            temp = new byte[count];
            for (int i = 0; i < count; i++) {
                temp[i] = len[i];
            }
            return temp;
        } catch (Exception e) {
            System.out.println(e.toString());
            return temp;
        }
    }




}
