import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginSystem {
    public static void main(String[] args) throws Exception {
        try{
            ServerSocket ss=new ServerSocket(6263);
            Socket s=ss.accept();
            DataInputStream in=new DataInputStream(s.getInputStream());
            DataOutputStream out=new DataOutputStream(s.getOutputStream());
            FileReader fr=new FileReader("password.txt");
            BufferedReader br=new BufferedReader(fr);

            String password="",pass="";
            pass=br.readLine();
            while(true){
                password=in.readUTF();
                if(pass.compareTo(password)==0){
                    out.writeUTF("OK");
                    out.flush();
                    s.close();
                    ss.close();
                }
                else{
                    out.writeUTF("NO");
                    out.flush();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
