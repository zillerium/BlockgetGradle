import java.io.*;
import java.util.Arrays;
import java.net.*;
import org.apache.commons.io.IOUtils;

public class BlockgetSocket {

    public void addData(String serverName, int port) {

        String input_path = "./";
        String output_path = "./";

        InputStream inputStream = null;
        try
        {
            int blocksize = 8192;

            URL myurl = new URL("http://blockget.online/rsa.pdf");
            URLConnection yc = myurl.openConnection();
         //   BufferedReader in = new BufferedReader(new InputStreamReader(
         //           yc.getInputStream()));

           // InputStreamReader inputStreamReader = new InputStreamReader(yc.getInputStream());
            inputStream = myurl.openStream();
          //  inputStream = new FileInputStream(input_path + "voda.pdf");
        //    int blocksize=100000; // 100k blocks
            byte[] buffer = new byte[blocksize];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ByteArrayOutputStream ba = new ByteArrayOutputStream();

            int bytesRead;
            int i=0;
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                baos.write(buffer, 0, bytesRead);
                i++;
            }

          //  int numberBlocks=10; // blocksize of writing files
          //  int blocksize = i/numberBlocks;

            byte[][] allbyte=new byte[i][blocksize];

            byte b [] = baos.toByteArray();
            // b.length array length
            System.out.println("size page " + b.length);
          //  for(int x = 0; x < 1000; x++) {
          //      System.out.print((char)b[x]);
           // /}
            for(int x = 0; x < i; x++) {
                int byteblock = blocksize*x;
                if (!(byteblock > b.length)) {
                    int endblock = byteblock + blocksize;
                    if (endblock > b.length) endblock = b.length; // last block
                    System.out.println("endblock = " + endblock);
                    System.out.println("byteblock = " + byteblock);
                    allbyte[x] = Arrays.copyOfRange(b, byteblock, endblock);
                }
            }


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );

   //         for(int x=0;x<i;x++) {
   //             OutputStream ost = new FileOutputStream("./voda" + x + ".pdf");

   //             outputStream.write( allbyte[x] );
   //             ost.write(allbyte[x]);
   //             ost.close();
   //         }

            byte c[] = outputStream.toByteArray( );


            for (int y=0;y<allbyte.length;y++) {
                Socket socket = new Socket(serverName, port);
                socket.setKeepAlive(true);

//		dOut.writeInt(allbyte.length); // write length of the message
                DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());

                dOut.write(allbyte[y]);
                InputStream inFromServer1 = socket.getInputStream();
                DataInputStream in1 = new DataInputStream(inFromServer1);
                //byte[] inbytes = IOUtils.toByteArray(in1);
                String strIn = IOUtils.toString(in1);
                System.out.println("Server says " + strIn);

                System.out.println("closing");
                dOut.close();
                socket.close();
            }

// 		dOut.write("testing\n");
            //dOut.close();

         //   OutputStream os = new FileOutputStream("./vodanew.pdf");

            // Starts writing the bytes in it
         //   os.write(b);

            // Close the file
         //   os.close();
         //   OutputStream os1 = new FileOutputStream("./vodanew1.pdf");

            // Starts writing the bytes in it
         //   os1.write(c);
            System.out.println("Successfully done");

            // Close the file
         //   os1.close();
            System.out.println("waiting for answer");

//	 	Socket client = new Socket(serverName, port);

     /*       InputStream inFromServer1 = socket.getInputStream();
            DataInputStream in1 = new DataInputStream(inFromServer1);
            //byte[] inbytes = IOUtils.toByteArray(in1);
            String strIn = IOUtils.toString(in1);
            System.out.println("Server says " + strIn);
            socket.close();
            System.out.println("closing");
            dOut.close();
            */


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if (inputStream != null ) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    // ignore ... any significant errors should already have been
                    // reported via an IOException from the final flush.
                }
            }
        }
    }
}

